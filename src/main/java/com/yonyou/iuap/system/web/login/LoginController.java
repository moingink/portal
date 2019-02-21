package com.yonyou.iuap.system.web.login;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenEntity.Role;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.iuap.auth.session.SessionManager;
import com.yonyou.iuap.auth.shiro.AuthConstants;
import com.yonyou.iuap.auth.token.ITokenProcessor;
import com.yonyou.iuap.auth.token.TokenParameter;
import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.iuap.system.service.AccountService;
import com.yonyou.util.CreateImageCodeUtil;
import com.yonyou.util.PropertyFileUtil;
import com.yonyou.util.RmPartyConstants;
import com.yonyou.util.jdbc.BaseDao;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.log.BusLogIdByThread;
import com.yonyou.util.log.BusLogger;
import com.yonyou.util.page.select.SingleQueryFrameForSelectByCompany;
import com.yonyou.util.security.XssAndSqlHttpServletRequestWrapper;
import com.yonyou.util.soa.SoaProcessing;
import com.yonyou.util.sql.DataSourceCodeConstants;
import com.yonyou.util.theme.ThemePath;
import com.yonyou.util.wsystem.service.ORG;

/**
 * 默认登录逻辑示例，项目使用时候根据自己的需求，添加rsa加密、配置token生成参数等
 */
@Controller
@RequestMapping(value = {"/login","/managerLogin"})
public class LoginController implements DataSourceCodeConstants{
	
    private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final int HASH_INTERATIONS = 1024;
	
	public static final String Login ="loginVehicle";
	
	public static final String main_portal="/pages/mainPortal.jsp";
	
    @Autowired
    private SessionManager sessionManager;

	@Autowired
	protected AccountService accountService;
	//为网页版本的登录Controller指定webTokenProcessor 相应的移动的指定为maTokenProcessor
	@Autowired
	protected ITokenProcessor webTokenProcessor;
	
	@Autowired
	protected CacheManager cacheManager;
	
	@Autowired
	protected IBaseDao dcmsDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		return Login;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="portal")
	public String portal(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) throws Exception {
		return "mainPortal";
	}
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.POST,value="formLogin")
	public String formLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		long startTime =System.currentTimeMillis();
		BusLogIdByThread.writeBusLogId(request.getRemoteHost());
		//1：获取请求登陆信息
		String loginId = request.getParameter("username");
        String passWord = request.getParameter("pd");
        
        String imageCode = request.getParameter("imageCode");
        
        
        loginId = XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(loginId);
        passWord = XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(passWord);
        if(null==loginId || "".equals(loginId.trim())){
        	logger.info("请输入用户名!");
            model.addAttribute("accounterror", "请输入用户名!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
        }
        
        if(imageCode==null){
			return Login;
		}
        
		Object vaildateCode2 = session.getAttribute("vaildateCode");
		
		if(vaildateCode2 == null){
			return Login;
		}
		
		if(!(imageCode.toUpperCase()).equals(vaildateCode2.toString().toUpperCase())){
			logger.info("验证码错误!");
            model.addAttribute("accounterror", "验证码错误!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",URLDecoder.decode(new String(decodeBase64(passWord)),"utf-8"));
			return Login;
		}
        
        //判断登陆入口
        String userSql = "SELECT * FROM rm_user WHERE login_id = '"+loginId+"'";		
		List<Map<String, Object>> userList = BaseDao.getBaseDao().query(userSql, "");
		if(userList.size() > 0){
			String adminType = userList.get(0).get("ADMIN_TYPE")!=null?userList.get(0).get("ADMIN_TYPE").toString():"";
			String servletPath = request.getServletPath();
			if(servletPath.indexOf("managerLogin") != -1){//管理员入口
				if(!"2".equals(adminType)){
					logger.info("用户名不存在!");
		            model.addAttribute("accounterror", "用户名不存在!");
		            model.addAttribute("username",loginId);
		            model.addAttribute("pd",passWord);
		    		return Login;
				}
			}else{//普通入口
				if(!"1".equals(adminType)){
					logger.info("用户名不存在!");
		            model.addAttribute("accounterror", "用户名不存在!");
		            model.addAttribute("username",loginId);
		            model.addAttribute("pd",passWord);
		    		return Login;
				}
			}
		}else{
			logger.info("用户名不存在!");
            model.addAttribute("accounterror", "用户名不存在!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
		}
        
        if(null==passWord || "".equals(passWord.trim())){
        	logger.info("请输入密码!");
            model.addAttribute("accounterror", "请输入密码!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
        }
        
        if(loginId.length()>20){
        	logger.info("用户名请输入小于20位!");
            model.addAttribute("accounterror", "用户名输入超过20位!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
        	
        }
        
        passWord = URLDecoder.decode(new String(decodeBase64(passWord)),"utf-8");
        //校验验证码
        String vaildateCode = request.getParameter("vaildateCode");
       /* if(vaildateCode==null){
        	logger.error("验证码不能为空");
        	model.addAttribute("accounterror", "验证码不能为空!");
        	model.addAttribute("username",loginId);
            model.addAttribute("password",passWord);
            return "loginNew";
        }
        //输入的验证码转小写
        String transVaildateCode = vaildateCode.toLowerCase();
        long vaildataStartTime =System.currentTimeMillis();
        if(!transVaildateCode.equals(session.getAttribute("vaildateCode"))){
        	logger.error("验证码错误!");
            model.addAttribute("accounterror", "验证码错误!");
            model.addAttribute("username",loginId);
            model.addAttribute("password",passWord);
            return "loginNew";
        }
        */
        //加密密码
        String enPassWord = SoaProcessing.md5(passWord, loginId);
        logger.info("---用户登录,id:{}---", loginId);
		//2:获取token
        //3-1:初始化token实体信息
        TokenEntity tokenEntity =new TokenEntity(findTokenByCookie(request));
        if(!this.validateByCacheToken(tokenEntity, loginId, enPassWord, model)){
        	//3-2:用户缓存信息验证失败，则访问接口进行用户验证
        	if(!this.validateByApi(tokenEntity, loginId, enPassWord,response,model)){
        		//3-2-1:登陆失败 ，返回登陆页面
        		logger.info("用户名密码错误!");
                model.addAttribute("accounterror", "用户名密码错误!");
                model.addAttribute("username",loginId);
                model.addAttribute("pd",passWord);
        		return Login;
        	}
        }
        
        //4:构建公司信息
        //4-1: 获取当前公司信息
        String company_id ="";
        StringBuffer selectHtml =new StringBuffer();
        buffer.append("，获取bulidCompanybegin:"+System.currentTimeMillis());
        this.bulidCompany(tokenEntity, selectHtml, company_id);
        buffer.append("，获取bulidCompanyend:"+System.currentTimeMillis());
        //获取登录用户角色id数组 
        String sql = "SELECT a.* , auth.golbal,auth.personal,auth.company ,auth.personal_data ,auth.company_data  from ( select R.ID,R.ROLE_CODE,R.NAME from RM_ROLE R join RM_PARTY_ROLE PR on R.ID = PR.ROLE_ID where PR.OWNER_PARTY_ID = '"+tokenEntity.USER.getId()+"' and ((R.IS_SYSTEM_LEVEL = '1' and ((R.OWNER_ORG_ID IS NULL or r.owner_org_id ='')or((R.OWNER_ORG_ID IS NOT NULL or r.owner_org_id ='') AND EXISTS (SELECT id FROM rm_party_relation rr WHERE rr.party_view_id = '"+RmPartyConstants.viewId+"' AND rr.parent_party_id = R.OWNER_ORG_ID "
				+ RmPartyConstants.recurPartySql(tokenEntity.COMPANY.getCompany_id()) +" )) )) or (R.OWNER_ORG_ID ='"+tokenEntity.COMPANY.getCompany_id()+"')) and r.Enable_Status='1' and  r.ROLE_CODE <> 'app') a  left JOIN  rm_role_auth  auth on auth.rm_role_id =  a.id  ";		
		List<Map<String, Object>> roleCommonList = BaseDao.getBaseDao().query(sql, "");
		String[] roleIds= null;
		
		String roleNames ="";
		List<Role>roleList = new ArrayList<Role>();
		com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
		if (roleCommonList != null && roleCommonList.size() > 0) {
			roleIds = new String[roleCommonList.size()];
			for(int i=0;i<roleCommonList.size();i++){
				Map<String, Object> map = roleCommonList.get(i);
				//根据当前roleid 和用户id 获取 每个role的所有数据权限
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map));
				String ridString = jsonObject.getString("ID");
				roleIds[i] = ridString;
				String name = jsonObject.getString("NAME");
				//获取数据权限  
				Object golbal = jsonObject.get("GOLBAL");
				//Object user_golbal = jsonObject.get("USER_GOLBAL");
				com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
				String personalIdString = "";
				String companyIdString = "";
				String sqlUserData = "SELECT * from rm_role_auth_user where rm_role_id = '"+ridString+"' and rm_user_id ='"+tokenEntity.USER.getId()+"'";
				List<Map<String, Object>> userAuthData = BaseDao.getBaseDao().query(sqlUserData, "");
				//获取 角色数据权限  和 用户数据权限并集处理   personal_data  COMPANY_DATA  PERSONAL_DATA USER_PERSONAL_DATA  USER_COMPANY_DATA
				Map<String, Object> userAuthMap= new HashMap<String, Object>();
				if(userAuthData.size()>0){
					userAuthMap = userAuthData.get(0);
				}
				com.alibaba.fastjson.JSONObject userData = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(userAuthMap));
				if((null!=golbal && golbal.toString().equals("0"))||(null!=userData.getString("USER_GOLBAL")&&userData.getString("USER_GOLBAL").equals("0"))){
					//全局不需要数据权限 设置json格式
					json.put("gobal", "yes");
					json.put("personal", "");
					json.put("company", "");
				}else{
					json.put("gobal", "no");
					com.alibaba.fastjson.JSONArray role_personal =  com.alibaba.fastjson.JSONArray.parseArray(jsonObject.getString("PERSONAL_DATA"));
					com.alibaba.fastjson.JSONArray role_company =  com.alibaba.fastjson.JSONArray.parseArray(jsonObject.getString("COMPANY_DATA"));
					com.alibaba.fastjson.JSONArray user_personal =  com.alibaba.fastjson.JSONArray.parseArray(userData.getString("PERSONAL_DATA"));
					com.alibaba.fastjson.JSONArray user_company =  com.alibaba.fastjson.JSONArray.parseArray(userData.getString("COMPANY_DATA"));
					//判断组织或者个人数据权限是否勾选
					//String golbal = map.get("GOLBAL").toString();
					if(null!=jsonObject.get("PERSONAL")&&jsonObject.getString("PERSONAL").equals("0")){
						if(role_personal != null){
							for(int k=0;k<role_personal.size();k++){
								personalIdString+= role_personal.getJSONObject(k).getString("ID")+",";
							}
						}
						personalIdString+=tokenEntity.USER.getId();
					}
					if(user_personal != null){
						for(int p=0;p<user_personal.size();p++){
							personalIdString+= user_personal.getJSONObject(p).getString("ID")+",";
						}
					}
					json.put("personal", personalIdString);
					if(null!=jsonObject.get("COMPANY")&&jsonObject.getString("COMPANY").equals("0")){
						if(role_company != null){
							for(int k=0;k<role_company.size();k++){
								companyIdString+= role_company.getJSONObject(k).getString("ID")+",";
							}
						}
						companyIdString += tokenEntity.COMPANY.getCompany_id();
					}
					if(user_company != null){
						for(int p=0;p<user_company.size();p++){
							companyIdString+= user_company.getJSONObject(p).getString("id")+",";
						}
					}
					json.put("company", companyIdString);
				}
				
				if(!name.equals("移动端菜单")){
					TokenEntity tokenEntityRole =new TokenEntity();
					tokenEntityRole.ROLE.setRoleId(roleIds[i]);
					tokenEntityRole.ROLE.setRoleName(name);
					//json.put("roleId", roleIds[i]);
					//tokenEntityRole.ROLE.setDataAuth(com.alibaba.fastjson.JSON.toJSONString(json));
					roleList.add(tokenEntityRole.ROLE);
					roleNames=roleNames+","+name;
				}
				com.alibaba.fastjson.JSONObject jsonObjectA  =  new com.alibaba.fastjson.JSONObject();
				jsonObjectA.put("roleId", roleIds[i]);
				jsonObjectA.put("dataAuth", json);
				jsonArray.add(jsonObjectA);
		    }
		}
		tokenEntity.roleIds = roleIds;
		tokenEntity.roleJsonString=com.alibaba.fastjson.JSONObject.toJSONString(roleList);
		tokenEntity.dataAuthString=jsonArray.toJSONString();
		
		tokenEntity.COMPANY.setCompany_id("");
		tokenEntity.COMPANY.setCompany_name("");
		//查询用户多部门数据返回json格式到前台供选择切换  回传cpmpany_id 来更新token的 company信息
		//先查询部门id 查询部门
		String sqlDeptIds = "SELECT  ORGANIZATION_ID from rm_user where LOGIN_ID = '"+loginId+"' ";
		List<Map<String, Object>> userData = BaseDao.getBaseDao().query(sqlDeptIds, "");
		Map<String, Object> userMap= new HashMap<String, Object>();
		userMap = userData.get(0);
		com.alibaba.fastjson.JSONObject userJSONData = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(userMap));
		String  idsStrings = userJSONData.getString("ORGANIZATION_ID");
		String sqlDeptList = "SELECT id,name from tm_company  where id in ("+idsStrings+")";
		List<Map<String, Object>> companyListData = BaseDao.getBaseDao().query(sqlDeptList, "");
		
		com.alibaba.fastjson.JSONArray companyJsonArray = new com.alibaba.fastjson.JSONArray();
		for(int i=0;i<companyListData.size();i++){
			Map<String, Object> map = companyListData.get(i);
			//根据当前roleid 和用户id 获取 每个role的所有数据权限
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map));
			String id = jsonObject.getString("ID");
			jsonObject.remove("ID");
			jsonObject.put("ID", id);
			companyJsonArray.add(jsonObject);
		}
		tokenEntity.deptJsonString = companyJsonArray.toJSONString();
		
        //5:记录信息
        //5-1:通过接口记录用户登陆信息
        //5-2:记录用户信息
        //添加主题编码
        ThemePath.appendTokenParamByThemeCode(tokenEntity);
        this.appendModel(model, tokenEntity,selectHtml);
        //mainPortal
        long endTime =System.currentTimeMillis();
        logger.error(String.format("###LOGIN_IN  登陆名：%s , 开始时间：%s , 结束时间： %s , 耗时: %s ,线程： %s .", 
        		loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()),buffer.toString());
     
        
        HashMap<String, String> map = new HashMap<>();		
		map.put(BusLogger.LOG_ACTION_TYPE, "login_log");
		map.put(BusLogger.LOG_USER_ID, tokenEntity.USER.getId());
		map.put(BusLogger.LOG_USER_ID_NAME, tokenEntity.USER.getName());
		map.put(BusLogger.LOG_OWNER_ORG_ID, tokenEntity.COMPANY.getCompany_id());
		map.put(BusLogger.LOG_CONTENT, "登录人:"+loginId+";角色:"+roleNames+";公司:"+tokenEntity.COMPANY.getCompany_name()+";token:"+tokenEntity.getToken());
		map.put(BusLogger.LOG_OPERATION_INFO, "login");		
		map.put(BusLogger.LOG_ACTION_MODULE, "login");	
				
        BusLogger.record_log(map);
        
        String token = tokenEntity.getToken();
        session.setAttribute("token", token);
        
//        System.err.println(String.format("System###LOGIN_IN  登陆名：%s , 开始时间：%s , 结束时间： %s , 耗时: %s ,线程： %s .", loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()));
        String nginx_portal_url=PropertyFileUtil.getValue("NGINX_PORTAL_URL");
        //记录登陆信息
        //insertUserOnline(tokenEntity,request);
        
		return "redirect:"+nginx_portal_url+main_portal;
	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET,value="signOut")
	public String signOut(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		long startTime =System.currentTimeMillis();
		//1：获取请求登陆信息
		String token =request.getParameter("token");
		String loginId ="";
		TokenEntity tokenEntity = null;
		if(token!=null&&token.length()>0){
			String cacheKey =SoaProcessing.findCacheKeyByToken(token);
			tokenEntity=cacheManager.get(cacheKey);
			if(tokenEntity!=null){
				loginId=tokenEntity.USER.getLoginId();
				this.removeCoken(loginId, response);
				String key = "I_001005001#001"+tokenEntity.USER.getId()+tokenEntity.COMPANY.getCompany_id();
				//I_001005001#00110001050000000006681000106800000000027
				//I_001005001#0010001050000000006681000106800000000027
				logger.info("菜单缓存="+cacheManager.exists(key));
				cacheManager.removeCache(cacheKey);
				if(cacheManager.exists(key)){
					cacheManager.removeCache(key);
				}
				cacheManager.removeCache(SoaProcessing.findCacheKeyByMenuToken(token));
				cacheManager.hdel(TimeOutFilter.REDIS_KEY_ACCESSTIME, token);
				HashMap<String, String> map = new HashMap();		
				map.put(BusLogger.LOG_ACTION_TYPE, "logout_log");
				map.put(BusLogger.LOG_USER_ID, tokenEntity.USER.getId());
				map.put(BusLogger.LOG_USER_ID_NAME, tokenEntity.USER.getName());
				map.put(BusLogger.LOG_OWNER_ORG_ID, tokenEntity.COMPANY.getCompany_id());
				map.put(BusLogger.LOG_CONTENT, "登录人:"+loginId);
				map.put(BusLogger.LOG_OPERATION_INFO, "logout");		
				map.put(BusLogger.LOG_ACTION_MODULE, "logout");	
		        BusLogger.record_log(map);
			}
			cacheManager.removeCache(SoaProcessing.findCacheKeyByMenuToken(token));
			cacheManager.hdel(TimeOutFilter.REDIS_KEY_ACCESSTIME, token);
		}
		long endTime =System.currentTimeMillis();
		logger.error(String.format("###LOGIN_OUT  登陆名：%s , 开始时间：%s , 结束时间： %s , 耗时: %s ,线程： %s .", 
	        		loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()));
		
		return Login;
	}
	
	
	
	/**
	 * 根据cookie 获取 token信息
	 * @param request
	 * @return
	 */
	private String findTokenByCookie(HttpServletRequest request){
		String token ="";
		Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
        	if("token".equals(cookie.getName())){
        		token=cookie.getValue();
        	}
        }
		return token;
	}
	
	/**
	 * 根据缓存进行登陆验证
	 * @param token
	 * @param loginName
	 * @param password
	 * @param model
	 * @return
	 */
	private boolean validateByCacheToken(TokenEntity tokenEntity,String loginName,String password,Model model){
    	String token=tokenEntity.getToken(); 
    	if(token != null && token.length() > 0){
    		TokenEntity cacheTokenEntity = (TokenEntity)cacheManager.get(SoaProcessing.findCacheKeyByToken(token.toString()));
    		tokenEntity.Fill(cacheTokenEntity);
    		return this.validateLogin(tokenEntity, loginName, password,model);
    	}
    	return false;
	}
	
	/**
	 * 通过接口进行登陆验证
	 * @param token
	 * @param loginName
	 * @param password
	 * @param response
	 * @param model
	 * @return
	 */
	private boolean validateByApi(TokenEntity tokenEntity,String loginId,String password,HttpServletResponse response,Model model){
			
		bulidUserByApi(tokenEntity,loginId);
        if(this.validateLogin(tokenEntity, loginId, password,model)){
           tokenEntity.setToken(this.initToken(loginId, response));
    	   return true;
        }
    	return false;
	}
	
	/**
	 * 添加model公共信息
	 * @param model
	 * @param token
	 * @param roleCode
	 * @param corpCode
	 */
	private void appendModel(Model model,TokenEntity tokenEntity,StringBuffer selectHtml){
		tokenEntity.COMPANY.setSelectCompanyHtml(selectHtml.toString());
		String token = tokenEntity.getToken();
		cacheManager.set(SoaProcessing.findCacheKeyByToken(token), tokenEntity);
		//记录访问时间-超时用
		cacheManager.hset(TimeOutFilter.REDIS_KEY_ACCESSTIME, token, System.currentTimeMillis());
		model.addAttribute("token", tokenEntity.getToken());
	}
	
	
	private void bulidThemeSelect(TokenEntity tokenEntity){
		
	}
	/**
	 * 验证登陆
	 * @param user
	 * @param loginName
	 * @param password
	 * @param model
	 * @return
	 */
	private boolean validateLogin(TokenEntity tokenEntity,String loginId,String password,Model model){
		if(tokenEntity!=null){
			if(loginId.equals(tokenEntity.USER.getLoginId())
					&&password.equals(tokenEntity.USER.getPassword())){
				//BusLogger.log("用户登录", tokenEntity.USER.getLoginId()+"已经登录redis校验！");
    			return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 通过接口获取用户信息
	 * @param loginName
	 * @return
	 */
	private void bulidUserByApi(TokenEntity tokenEntity,String loginId){
		
        Map<String, String> userData = SoaProcessing.soaUserByLoginId(loginId);
        
        if("000000".equals(userData.get(ORG.USER.HEAD_RETCODE))){
        	Map<String, Object> userinfo = JSONObject.fromObject(userData.get(ORG.USER.HEAD_DATA));
        	tokenEntity.USER.setLoginId(userinfo.get(ORG.USER.LOGIN_ID.getCode()).toString());
        	tokenEntity.USER.setName(userinfo.get(ORG.USER.NAME.getCode()).toString());
        	tokenEntity.USER.setPassword(userinfo.get(ORG.USER.PASSWORD.getCode()).toString());
        	tokenEntity.USER.setId(userinfo.get(ORG.USER.ID.getCode()).toString());
        	tokenEntity.USER.setLoginTs(System.currentTimeMillis());
        	tokenEntity.USER.setEmail(userinfo.get(ORG.USER.EMAIL.getCode()).toString());
        	tokenEntity.USER.setCustom3(userinfo.get(ORG.USER.CUSTOM3.getCode()).toString());
        	tokenEntity.USER.setAdminType(userinfo.get(ORG.USER.ADMIN_TYPE.getCode()).toString());
        }
      
	}
	
	
	/**
	 * 通过接口获取用户信息
	 * @param loginName
	 * @return
	 */
	private void bulidCompany(TokenEntity tokenEntity,StringBuffer selectHtml,String company_id){
		
		int initIndex =0;
        Map<String, String> companyData = SoaProcessing.soaCropByUserId(tokenEntity.USER.getId());
        
        ConcurrentHashMap<String,String>  selectMap =new ConcurrentHashMap();
        if("000000".equals(companyData.get(ORG.USER.HEAD_RETCODE))){
        	JSONArray jsonArray = JSONArray.fromObject(companyData.get(ORG.USER.HEAD_DATA));
        	JSONObject json =jsonArray.getJSONObject(initIndex);
        	tokenEntity.COMPANY.setCompany_id((json.getString(ORG.USER.ID.getCode())));
        	tokenEntity.COMPANY.setCompany_name((json.getString(ORG.USER.NAME.getCode())));
        	tokenEntity.COMPANY.setCompany_code((json.getString("TEMP_CODE")));
        	company_id=json.getString(ORG.USER.ID.getCode());
        	for(int i=initIndex;i<jsonArray.size();i++){
        		JSONObject jsonObject =jsonArray.getJSONObject(i);
        		selectMap.put(jsonObject.getString(ORG.USER.ID.getCode())+COMPANY_MARK+jsonObject.getString("TEMP_CODE")
        				, jsonObject.getString(ORG.USER.NAME.getCode()));
        	}
        	company_id=company_id+COMPANY_MARK+tokenEntity.COMPANY.getCompany_code();
        	selectHtml.append(new SingleQueryFrameForSelectByCompany().findPageHtml("company_id", "", company_id, selectMap));
        }
      
	}
	
	/**
	 * 初始化token值
	 * @param loginName
	 * @param response
	 * @return
	 */
	private String initToken(String loginId,HttpServletResponse response){
		 
         TokenParameter tp  =this.initTokenParamter(loginId);
      
         Cookie[] cookiestemp = webTokenProcessor.getCookieFromTokenParameter(tp);
         for(Cookie cookie : cookiestemp){
     	   response.addCookie(cookie);
         }
         return  webTokenProcessor.generateToken(tp);
          
	}
	
	/**
	 * 移除cookie信息
	 * @param loginName
	 * @param response
	 */
	private void removeCoken(String loginName,HttpServletResponse response){
		 TokenParameter tp =this.initTokenParamter(loginName);
		 Cookie[] cookiestemp = webTokenProcessor.getCookieFromTokenParameter(tp);
         for(Cookie cookie : cookiestemp){
        	cookie.setMaxAge(0);
     	    response.addCookie(cookie);
         }
	}
	
	/**
	 * 舒适化 tokenparamter 对象信息
	 * @param loginName
	 * @return
	 */
	private TokenParameter initTokenParamter(String loginName){
		 TokenParameter tp = new TokenParameter();
         tp.setUserid(loginName);
         // 设置登录时间
         tp.setLogints(String.valueOf(System.currentTimeMillis()));
         // 租户信息,saas应用登录的时候获取用户信息，设置租户id
         tp.getExt().put(AuthConstants.PARAM_TENANTID , "test_tenant_id");
         return tp;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return Login;
	}
	
	//验证码
	@RequestMapping("/imageCode")
	public void imageCode(HttpServletRequest req, HttpServletResponse response,HttpSession session){
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);           
		CreateImageCodeUtil vCode = new CreateImageCodeUtil(150,40,4,10);
		session.setAttribute("vaildateCode", vCode.getCode());
			try {
					vCode.write(response.getOutputStream());
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				        
	 }

	/**  
	    * 解码  
	    * @param str  
	    * @return string  
	    */    
	 public byte[] decodeBase64(String input) throws Exception{  
		 	Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	        Method mainMethod= clazz.getMethod("decode", String.class);  
	        mainMethod.setAccessible(true);  
	        Object retObj=mainMethod.invoke(null, input);  
	        return (byte[])retObj;  
	}
	 
	 
	 private void insertUserOnline(TokenEntity tokenEntity,HttpServletRequest request){
		 Map<String,Object> dataMap =new HashMap<String,Object>();
		 dataMap.put("USER_ID", tokenEntity.USER.getLoginId());
		 dataMap.put("LOGIN_TIME", TokenUtil.findSystemSqlTimestamp());
		 dataMap.put("LOGIN_IP", TokenUtil.getIpAddress(request));
		 dataMap.put("version", 1);
		 dataMap.put("dr", "0");
		 dcmsDAO.insert("RM_USER_ONLINE_RECORD", dataMap);
	 }
	 
}
