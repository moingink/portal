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
 * ???????????????????????????????????????????????????????????????????????????rsa???????????????token???????????????
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
	//????????????????????????Controller??????webTokenProcessor ???????????????????????????maTokenProcessor
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
	 * ??????
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
		//1???????????????????????????
		String loginId = request.getParameter("username");
        String passWord = request.getParameter("pd");
        
        String imageCode = request.getParameter("imageCode");
        
        
        loginId = XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(loginId);
        passWord = XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(passWord);
        if(null==loginId || "".equals(loginId.trim())){
        	logger.info("??????????????????!");
            model.addAttribute("accounterror", "??????????????????!");
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
			logger.info("???????????????!");
            model.addAttribute("accounterror", "???????????????!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",URLDecoder.decode(new String(decodeBase64(passWord)),"utf-8"));
			return Login;
		}
        
        //??????????????????
        String userSql = "SELECT * FROM rm_user WHERE login_id = '"+loginId+"'";		
		List<Map<String, Object>> userList = BaseDao.getBaseDao().query(userSql, "");
		if(userList.size() > 0){
			String adminType = userList.get(0).get("ADMIN_TYPE")!=null?userList.get(0).get("ADMIN_TYPE").toString():"";
			String servletPath = request.getServletPath();
			if(servletPath.indexOf("managerLogin") != -1){//???????????????
				if(!"2".equals(adminType)){
					logger.info("??????????????????!");
		            model.addAttribute("accounterror", "??????????????????!");
		            model.addAttribute("username",loginId);
		            model.addAttribute("pd",passWord);
		    		return Login;
				}
			}else{//????????????
				if(!"1".equals(adminType)){
					logger.info("??????????????????!");
		            model.addAttribute("accounterror", "??????????????????!");
		            model.addAttribute("username",loginId);
		            model.addAttribute("pd",passWord);
		    		return Login;
				}
			}
		}else{
			logger.info("??????????????????!");
            model.addAttribute("accounterror", "??????????????????!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
		}
        
        if(null==passWord || "".equals(passWord.trim())){
        	logger.info("???????????????!");
            model.addAttribute("accounterror", "???????????????!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
        }
        
        if(loginId.length()>20){
        	logger.info("????????????????????????20???!");
            model.addAttribute("accounterror", "?????????????????????20???!");
            model.addAttribute("username",loginId);
            model.addAttribute("pd",passWord);
    		return Login;
        	
        }
        
        passWord = URLDecoder.decode(new String(decodeBase64(passWord)),"utf-8");
        //???????????????
        String vaildateCode = request.getParameter("vaildateCode");
       /* if(vaildateCode==null){
        	logger.error("?????????????????????");
        	model.addAttribute("accounterror", "?????????????????????!");
        	model.addAttribute("username",loginId);
            model.addAttribute("password",passWord);
            return "loginNew";
        }
        //???????????????????????????
        String transVaildateCode = vaildateCode.toLowerCase();
        long vaildataStartTime =System.currentTimeMillis();
        if(!transVaildateCode.equals(session.getAttribute("vaildateCode"))){
        	logger.error("???????????????!");
            model.addAttribute("accounterror", "???????????????!");
            model.addAttribute("username",loginId);
            model.addAttribute("password",passWord);
            return "loginNew";
        }
        */
        //????????????
        String enPassWord = SoaProcessing.md5(passWord, loginId);
        logger.info("---????????????,id:{}---", loginId);
		//2:??????token
        //3-1:?????????token????????????
        TokenEntity tokenEntity =new TokenEntity(findTokenByCookie(request));
        if(!this.validateByCacheToken(tokenEntity, loginId, enPassWord, model)){
        	//3-2:??????????????????????????????????????????????????????????????????
        	if(!this.validateByApi(tokenEntity, loginId, enPassWord,response,model)){
        		//3-2-1:???????????? ?????????????????????
        		logger.info("?????????????????????!");
                model.addAttribute("accounterror", "?????????????????????!");
                model.addAttribute("username",loginId);
                model.addAttribute("pd",passWord);
        		return Login;
        	}
        }
        
        //4:??????????????????
        //4-1: ????????????????????????
        String company_id ="";
        StringBuffer selectHtml =new StringBuffer();
        buffer.append("?????????bulidCompanybegin:"+System.currentTimeMillis());
        this.bulidCompany(tokenEntity, selectHtml, company_id);
        buffer.append("?????????bulidCompanyend:"+System.currentTimeMillis());
        //????????????????????????id?????? 
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
				//????????????roleid ?????????id ?????? ??????role?????????????????????
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map));
				String ridString = jsonObject.getString("ID");
				roleIds[i] = ridString;
				String name = jsonObject.getString("NAME");
				//??????????????????  
				Object golbal = jsonObject.get("GOLBAL");
				//Object user_golbal = jsonObject.get("USER_GOLBAL");
				com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
				String personalIdString = "";
				String companyIdString = "";
				String sqlUserData = "SELECT * from rm_role_auth_user where rm_role_id = '"+ridString+"' and rm_user_id ='"+tokenEntity.USER.getId()+"'";
				List<Map<String, Object>> userAuthData = BaseDao.getBaseDao().query(sqlUserData, "");
				//?????? ??????????????????  ??? ??????????????????????????????   personal_data  COMPANY_DATA  PERSONAL_DATA USER_PERSONAL_DATA  USER_COMPANY_DATA
				Map<String, Object> userAuthMap= new HashMap<String, Object>();
				if(userAuthData.size()>0){
					userAuthMap = userAuthData.get(0);
				}
				com.alibaba.fastjson.JSONObject userData = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(userAuthMap));
				if((null!=golbal && golbal.toString().equals("0"))||(null!=userData.getString("USER_GOLBAL")&&userData.getString("USER_GOLBAL").equals("0"))){
					//??????????????????????????? ??????json??????
					json.put("gobal", "yes");
					json.put("personal", "");
					json.put("company", "");
				}else{
					json.put("gobal", "no");
					com.alibaba.fastjson.JSONArray role_personal =  com.alibaba.fastjson.JSONArray.parseArray(jsonObject.getString("PERSONAL_DATA"));
					com.alibaba.fastjson.JSONArray role_company =  com.alibaba.fastjson.JSONArray.parseArray(jsonObject.getString("COMPANY_DATA"));
					com.alibaba.fastjson.JSONArray user_personal =  com.alibaba.fastjson.JSONArray.parseArray(userData.getString("PERSONAL_DATA"));
					com.alibaba.fastjson.JSONArray user_company =  com.alibaba.fastjson.JSONArray.parseArray(userData.getString("COMPANY_DATA"));
					//????????????????????????????????????????????????
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
				
				if(!name.equals("???????????????")){
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
		//?????????????????????????????????json??????????????????????????????  ??????cpmpany_id ?????????token??? company??????
		//???????????????id ????????????
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
			//????????????roleid ?????????id ?????? ??????role?????????????????????
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map));
			String id = jsonObject.getString("ID");
			jsonObject.remove("ID");
			jsonObject.put("ID", id);
			companyJsonArray.add(jsonObject);
		}
		tokenEntity.deptJsonString = companyJsonArray.toJSONString();
		
        //5:????????????
        //5-1:????????????????????????????????????
        //5-2:??????????????????
        //??????????????????
        ThemePath.appendTokenParamByThemeCode(tokenEntity);
        this.appendModel(model, tokenEntity,selectHtml);
        //mainPortal
        long endTime =System.currentTimeMillis();
        logger.error(String.format("###LOGIN_IN  ????????????%s , ???????????????%s , ??????????????? %s , ??????: %s ,????????? %s .", 
        		loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()),buffer.toString());
     
        
        HashMap<String, String> map = new HashMap<>();		
		map.put(BusLogger.LOG_ACTION_TYPE, "login_log");
		map.put(BusLogger.LOG_USER_ID, tokenEntity.USER.getId());
		map.put(BusLogger.LOG_USER_ID_NAME, tokenEntity.USER.getName());
		map.put(BusLogger.LOG_OWNER_ORG_ID, tokenEntity.COMPANY.getCompany_id());
		map.put(BusLogger.LOG_CONTENT, "?????????:"+loginId+";??????:"+roleNames+";??????:"+tokenEntity.COMPANY.getCompany_name()+";token:"+tokenEntity.getToken());
		map.put(BusLogger.LOG_OPERATION_INFO, "login");		
		map.put(BusLogger.LOG_ACTION_MODULE, "login");	
				
        BusLogger.record_log(map);
        
        String token = tokenEntity.getToken();
        session.setAttribute("token", token);
        
//        System.err.println(String.format("System###LOGIN_IN  ????????????%s , ???????????????%s , ??????????????? %s , ??????: %s ,????????? %s .", loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()));
        String nginx_portal_url=PropertyFileUtil.getValue("NGINX_PORTAL_URL");
        //??????????????????
        //insertUserOnline(tokenEntity,request);
        
		return "redirect:"+nginx_portal_url+main_portal;
	}
	
	/**
	 * ??????
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET,value="signOut")
	public String signOut(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		long startTime =System.currentTimeMillis();
		//1???????????????????????????
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
				logger.info("????????????="+cacheManager.exists(key));
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
				map.put(BusLogger.LOG_CONTENT, "?????????:"+loginId);
				map.put(BusLogger.LOG_OPERATION_INFO, "logout");		
				map.put(BusLogger.LOG_ACTION_MODULE, "logout");	
		        BusLogger.record_log(map);
			}
			cacheManager.removeCache(SoaProcessing.findCacheKeyByMenuToken(token));
			cacheManager.hdel(TimeOutFilter.REDIS_KEY_ACCESSTIME, token);
		}
		long endTime =System.currentTimeMillis();
		logger.error(String.format("###LOGIN_OUT  ????????????%s , ???????????????%s , ??????????????? %s , ??????: %s ,????????? %s .", 
	        		loginId,startTime,endTime,(endTime-startTime),Thread.currentThread().getName()));
		
		return Login;
	}
	
	
	
	/**
	 * ??????cookie ?????? token??????
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
	 * ??????????????????????????????
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
	 * ??????????????????????????????
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
	 * ??????model????????????
	 * @param model
	 * @param token
	 * @param roleCode
	 * @param corpCode
	 */
	private void appendModel(Model model,TokenEntity tokenEntity,StringBuffer selectHtml){
		tokenEntity.COMPANY.setSelectCompanyHtml(selectHtml.toString());
		String token = tokenEntity.getToken();
		cacheManager.set(SoaProcessing.findCacheKeyByToken(token), tokenEntity);
		//??????????????????-?????????
		cacheManager.hset(TimeOutFilter.REDIS_KEY_ACCESSTIME, token, System.currentTimeMillis());
		model.addAttribute("token", tokenEntity.getToken());
	}
	
	
	private void bulidThemeSelect(TokenEntity tokenEntity){
		
	}
	/**
	 * ????????????
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
				//BusLogger.log("????????????", tokenEntity.USER.getLoginId()+"????????????redis?????????");
    			return true;
			}
		}
		return false;
	}
	
	
	/**
	 * ??????????????????????????????
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
	 * ??????????????????????????????
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
	 * ?????????token???
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
	 * ??????cookie??????
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
	 * ????????? tokenparamter ????????????
	 * @param loginName
	 * @return
	 */
	private TokenParameter initTokenParamter(String loginName){
		 TokenParameter tp = new TokenParameter();
         tp.setUserid(loginName);
         // ??????????????????
         tp.setLogints(String.valueOf(System.currentTimeMillis()));
         // ????????????,saas??????????????????????????????????????????????????????id
         tp.getExt().put(AuthConstants.PARAM_TENANTID , "test_tenant_id");
         return tp;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return Login;
	}
	
	//?????????
	@RequestMapping("/imageCode")
	public void imageCode(HttpServletRequest req, HttpServletResponse response,HttpSession session){
		// ??????????????????????????????????????????
		response.setContentType("image/jpeg");
		//?????????????????????
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
	    * ??????  
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
