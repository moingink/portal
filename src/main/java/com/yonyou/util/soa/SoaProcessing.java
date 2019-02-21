package com.yonyou.util.soa;

import java.util.HashMap;
import java.util.Map;

import com.yonyou.business.entity.TokenEntity;
import com.yonyou.util.wsystem.service.ORG;

public class SoaProcessing  {
	
	public static int CACHE_OUT_TIME  =10*60*60;
	
	public static Map<String, String> soaPortalMenu(TokenEntity tokenEntity){
		
		Map<String, String> json=null;
		if(tokenEntity!=null){
			Map<String,String> menuMap =new HashMap<String,String>();
			menuMap.put("serviceCode", SoaRequestCode.PORTAL_MENU);
			menuMap.put("USERID", tokenEntity.USER.getId());
			menuMap.put("COMPANYID", tokenEntity.COMPANY.getCompany_id());
			menuMap.put("roleid",tokenEntity.ROLE.getRoleId());
			json=ORG.MENU.sendHTTPForMap(menuMap);
		}
		return json;
	}
	
	
	public static Map<String, String> soaUserByLoginId(String loginId){
		Map<String, String> json=null;
		if(loginId!=null&&loginId.length()>0){
			Map<String,String> loginMap =new HashMap<String,String>();
			loginMap.put("serviceCode", SoaRequestCode.LOGIN_USER);
			loginMap.put("LOGINID", loginId);
			json=ORG.USER.sendHTTPForMap(loginMap);
		}
		return json;
	}
	
	public static Map<String, String> soaCropByUserId(String userId){
		Map<String, String> json=null;
		if(userId!=null&&userId.length()>0){
			Map<String,String> userMap =new HashMap<String,String>();
			userMap.put("serviceCode", SoaRequestCode.COMPANY);
			userMap.put("USERID", userId);
			json=ORG.USER.sendHTTPForMap(userMap);
		}
		return json;
	}
	
	
	/**
	 * 获取 缓存key 
	 * @param token
	 * @return
	 */
	public static String findCacheKeyByToken(String token){
		return "SESSION_"+token;
	}
	
	public static String findCacheKeyByMenuToken(String token){
		return "MENU_"+token;
	}
	
	public static String md5(String value,String seed){
		if(value==null||seed==null){
			return value;
		}
		return MD5Util.getStringMD5(value);
		//return	Md5Token.getInstance().getLongToken(Md5Token.getInstance().getLongToken(value) + seed);
	}
}
