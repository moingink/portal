package com.yonyou.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.iuap.auth.shiro.AuthConstants;
import com.yonyou.iuap.auth.token.ITokenProcessor;
import com.yonyou.iuap.auth.token.TokenParameter;
import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.util.JsonUtils;
import com.yonyou.util.PropertyFileUtil;
import com.yonyou.util.SpringContextUtil;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.mail.MailHelper;
import com.yonyou.util.soa.SoaProcessing;
import com.yonyou.util.soa.SoaRequestCode;
import com.yonyou.util.sql.SQLUtil.WhereEnum;
import com.yonyou.util.sql.SqlWhereEntity;
import com.yonyou.util.wsystem.service.ORG;

@RestController
@RequestMapping(value = "/passWord")
public class PassWordController {

	@Autowired
	protected IBaseDao dcmsDAO;
	@Autowired
	protected CacheManager cacheManager;
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@RequestMapping(params = "cmd=editPWD")
	@ResponseBody
	public JSONObject editPWD(HttpServletRequest request, HttpServletResponse response,@RequestBody String jsonStr){
		JSONObject jsonObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(jsonStr);
		String sql="select * from rm_user rm_user where rm_user.ID='"+json.getString("userId")+"'";
		List<Map<String, Object>> query = dcmsDAO.query(sql, "");
		if(query.size()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户查询失败");
			return jsonObject;
		}
		Map<String, Object> entity = query.get(0);
		String encodePWD = SoaProcessing.md5(json.getString("oldPWD"),entity.get("LOGIN_ID").toString());
		if(!entity.get("PASSWORD").equals(encodePWD)){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "旧密码错误");
			return jsonObject;
		}
		String newEncodePWD = SoaProcessing.md5(json.getString("newPWD"),entity.get("LOGIN_ID").toString());
		SqlWhereEntity sqlWhereEntity = new SqlWhereEntity();
		sqlWhereEntity.putWhere("ID", json.getString("userId"), WhereEnum.EQUAL_STRING);
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("PASSWORD", newEncodePWD);
		dcmsDAO.update("RM_USER", jsonobject, sqlWhereEntity);
		//修改是否修改密码的状态
		SqlWhereEntity sqlWhereEntity1 = new SqlWhereEntity();
		sqlWhereEntity1.putWhere("ID", json.getString("userId"), WhereEnum.EQUAL_STRING);
		JSONObject jsonobject1 = new JSONObject();
		jsonobject1.put("IS_UPD_PASSWORD", "1");
		dcmsDAO.update("RM_USER", jsonobject1, sqlWhereEntity1);
		
		removeUserCatch(entity.get("LOGIN_ID").toString());

		jsonObject.put("state", "success");
		return jsonObject;
	}
	private void removeUserCatch(String loginId){

		Map<String,String> loginMap =new HashMap<String,String>();
		loginMap.put("serviceCode", SoaRequestCode.LOGIN_USER);
		loginMap.put("LOGINID", loginId);
		String jsonstr =JsonUtils.map2json(loginMap);

		JSONObject reqJson = JSONObject.fromObject(jsonstr);
		String serviceKey = "I_" + (String) reqJson.get("serviceCode");

		if (PropertyFileUtil.containsKey(serviceKey)) {
			StringBuffer redisKey = new StringBuffer(serviceKey);
			String[] redisKeyEle = PropertyFileUtil.getValue(serviceKey).split(",");
			for (String ele : redisKeyEle) {
				redisKey.append(reqJson.get(ele));
			}

			cacheManager.removeCache(redisKey.toString());
		}
		
	}
	
	@RequestMapping(params = "cmd=sendEmailCode")
	@ResponseBody
	public JSONObject sendEmailCode(HttpServletRequest request, HttpServletResponse response,HttpSession session,@RequestBody String jsonStr){
		JSONObject jsonObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(jsonStr);
		if(json.get("email")==null
				||json.get("imageCode")==null
				||json.get("loginId")==null
				||json.getString("email").length()==0
				||json.getString("loginId").length()==0
				||json.getString("imageCode").length()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "缺少参数");
			return jsonObject;
		}

		Object vaildateCode = session.getAttribute("vaildateCode");
		if(vaildateCode==null){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "获取session图片验证码失败");
			return jsonObject;
		}
		
		if(!json.getString("imageCode").toUpperCase().equals(vaildateCode.toString().toUpperCase())){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "图片验证码错误");
			return jsonObject;
		}
		

		String sql="select * from rm_user rm_user where rm_user.EMAIL='"+json.getString("email")+"' and LOGIN_ID='"+json.getString("loginId")+"' and rm_user.DR='0'";
		List<Map<String, Object>> query = dcmsDAO.query(sql, "");
		if(query.size()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户不存在");
			return jsonObject;
		}
		if(query.size()>1){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户邮箱重复");
			return jsonObject;
		}

		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < 4; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		
		MailHelper mailHelper=new MailHelper();
		mailHelper.init();
		boolean sendMailSuper = mailHelper.sendMailSuper("VerifyCode", "业务管理系统验证码", "业务管理系统验证码：【"+str2+"】，有效期：3分钟", json.getString("email"));
		if(!sendMailSuper){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "验证码发送失败");
			return jsonObject;
		}

		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MINUTE,3);
		Date time = instance.getTime();
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("code", str2);
		hashMap.put("time", time);
		session.setAttribute("vaildateEmailCode", hashMap);
		
		jsonObject.put("state", "success");
		return jsonObject;
	}

	@RequestMapping(params = "cmd=retrievePWD")
	@ResponseBody
	public JSONObject retrievePWD(HttpServletRequest request, HttpServletResponse response,HttpSession session,@RequestBody String jsonStr){
		JSONObject jsonObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(jsonStr);
		if(json.get("email")==null
				||json.get("code")==null
				||json.get("newPWD")==null
				||json.get("repeatNewPWD")==null
				||json.getString("email").length()==0
				||json.getString("code").length()==0
				||json.getString("newPWD").length()==0
				||json.getString("repeatNewPWD").length()==0 ){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "缺少参数");
			return jsonObject;
		}

		Object vaildateCode = session.getAttribute("vaildateEmailCode");
		if(vaildateCode==null){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "获取session邮箱验证码失败");
			return jsonObject;
		}
		Map<String,Object> map=(Map<String,Object>)vaildateCode;
		Date date=(Date)map.get("time");
		if(date.before(new Date())){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "验证码已过期");
			return jsonObject;
		}
		
		if(!json.getString("code").toUpperCase().equals(map.get("code").toString().toUpperCase())){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "邮箱验证码错误");
			return jsonObject;
		}
		

		String sql="select * from rm_user rm_user where rm_user.EMAIL='"+json.getString("email")+"' and rm_user.DR='0'";
		List<Map<String, Object>> query = dcmsDAO.query(sql, "");
		if(query.size()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户不存在");
			return jsonObject;
		}
		if(query.size()>1){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户邮箱重复");
			return jsonObject;
		}
		Map<String, Object> entity = query.get(0);
		String newEncodePWD = SoaProcessing.md5(json.getString("newPWD"),entity.get("LOGIN_ID").toString());
		SqlWhereEntity sqlWhereEntity = new SqlWhereEntity();
		sqlWhereEntity.putWhere("ID", entity.get("ID").toString(), WhereEnum.EQUAL_STRING);
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("PASSWORD", newEncodePWD);
		try {
			dcmsDAO.update("RM_USER", jsonobject, sqlWhereEntity);


			removeUserCatch(entity.get("LOGIN_ID").toString());
			jsonObject.put("state", "success");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			jsonObject.put("state", "success");
			jsonObject.put("msg", "修改密码出错");
		}
		return jsonObject;
	}

	@RequestMapping(params ="goRetrievePWD")
	public ModelAndView goRetrievePWD(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("retrievePassWord");
		return modelAndView;
	}
	@RequestMapping(params = "goRetrievePWDSendCode")
	public ModelAndView goRetrievePWDSendCode(){
		ModelAndView modelAndView = new ModelAndView("retrievePassWordSendCode");
		return modelAndView;
	}

	@RequestMapping(params = "cmd=queryPersonInfo")
	@ResponseBody
	public JSONObject queryPersonInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody String jsonStr){
		JSONObject jsonObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(jsonStr);
		if(json.get("userId")==null){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "参数缺失");
			return jsonObject;
		}
		String sql="select * from rm_user rm_user where rm_user.ID='"+json.getString("userId")+"'";
		List<Map<String, Object>> query = dcmsDAO.query(sql, "");
		if(query.size()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户查询失败");
			return jsonObject;
		}
		Map<String, Object> entity = query.get(0);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(entity);
		jsonObject.put("state", "success");
		jsonObject.put("data", jsonObj);
		return jsonObject;
	}

	@RequestMapping(params = "cmd=editPersonInfo")
	@ResponseBody
	public JSONObject editPersonInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody String jsonStr){
		JSONObject jsonObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(jsonStr);
		String sql="select * from rm_user rm_user where rm_user.ID='"+json.getString("userId")+"'";
		List<Map<String, Object>> query = dcmsDAO.query(sql, "");
		if(query.size()==0){
			jsonObject.put("state", "fail");
			jsonObject.put("msg", "用户查询失败");
			return jsonObject;
		}
		SqlWhereEntity sqlWhereEntity = new SqlWhereEntity();
		sqlWhereEntity.putWhere("ID", json.getString("userId"), WhereEnum.EQUAL_STRING);
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("EMAIL", json.getString("email"));
		jsonobject.put("CUSTOM3", json.getString("phone"));
		dcmsDAO.update("RM_USER", jsonobject, sqlWhereEntity);


		jsonObject.put("state", "success");
		return jsonObject;
	}
	
	
	
	

}
