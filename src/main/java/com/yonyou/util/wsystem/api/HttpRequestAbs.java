package com.yonyou.util.wsystem.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.util.PropertyFileUtil;
import com.yonyou.util.SpringContextUtil;
import com.yonyou.util.wsystem.service.ORG;
import com.yonyou.util.wsystem.util.SoaFlowEncapsulation;

import net.sf.json.JSONObject;


public abstract class HttpRequestAbs {
	
	private static CacheManager cacheManager = (CacheManager) SpringContextUtil.getBean("cacheManager");
	
	public abstract String httpRespString(String url, String jsonString) ;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public JSONObject httpRespJSON(String url, String jsonString){
		
		// 对于api.properties文件中配置的需缓存的接口，请求成功时将返回的json放入redis中缓存，再次请求时则直接由redis获取
		JSONObject reqJson = JSONObject.fromObject(jsonString);
		String serviceKey = "I_" + (String) reqJson.get("serviceCode");
		String jsonReturnString = "";
		if (PropertyFileUtil.containsKey(serviceKey)) {
			StringBuffer redisKey = new StringBuffer(serviceKey);
			String[] redisKeyEle = PropertyFileUtil.getValue(serviceKey).split(",");
			for (String ele : redisKeyEle) {
				redisKey.append(reqJson.get(ele));
			}
			
			if(cacheManager.exists(redisKey.toString())){
				jsonReturnString = cacheManager.get(redisKey.toString());
			}else{
				jsonReturnString =httpRespString( url,  jsonString);
				System.out.println(jsonReturnString);
				jsonReturnString=SoaFlowEncapsulation.decode(jsonReturnString);
				System.out.println("解码后："+jsonReturnString);
				if(jsonReturnString.length()==0){
					jsonReturnString="{'message':''}";
				}
				JSONObject resJson = JSONObject.fromObject(jsonReturnString);
				if("000000".equals(resJson.get("retCode"))){
					cacheManager.setex(redisKey.toString(), jsonReturnString, 10*60);//有效时间10分钟
				}else{
					logger.error("请求接口失败！请求报文："+jsonString+"返回报文："+jsonReturnString);
				}
			}
		}
		
	
		return JSONObject.fromObject(jsonReturnString);
	}
	/**
	 * HTTP请求，返回map数据
	 * @param url
	 * @param jsonString
	 * @return
	 */
	public  Map httpRespMap(String url, String jsonString) {
		return (Map) httpRespJSON(url, jsonString);
	}
}
