package com.yonyou.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yonyou.util.wsystem.api.HttpRequestAbs;


public class HttpRequest extends HttpRequestAbs {
	// 系统管理
	static String SYSTEM_URL = "http://127.0.0.1:8080/tm/service/System";

	private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	/**
	 * HTTP请求，返回json数据
	 * @param url
	 * @param jsonString
	 * @return
	 */
	@Override
	public  String httpRespString(String url, String jsonString) {
		long startTime =System.currentTimeMillis();
		PostMethod post = new PostMethod(url);
		HttpClient client = new HttpClient();
		String repsString = null;
		post.setParameter("jsonData", jsonString);
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int code = post.getStatusCode();
		if (code == 200) {
			
			try {
				InputStream inputStream = post.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = br.readLine()) != null) {
					stringBuffer.append(str);
				}
				repsString = stringBuffer.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			logger.error("请求失败！请求地址：" + url + "请求数据：" + jsonString + "状态码：" + code);
			}
		long entTime =System.currentTimeMillis();
		logger.error("######API_TIME 接口请求耗时：\t"+ (entTime-startTime) +"\t"+ url + "\t请求数据：" + jsonString  );
		post.releaseConnection();
		return repsString;
	}

	

	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HttpRequest http =new HttpRequest();
		//系统管理数据字典
		String reqJsonString = "{'serviceCode':'001001001#001','customer':'','reqTime':'','serialNo':'','directcode':'MAIL'}";
		JSONObject jsonObject = http.httpRespJSON(SYSTEM_URL, reqJsonString);
		System.out.println("httpRespJSON" +((JSONObject)jsonObject.get("data")).get("TASK_STATUS"));
		Map map = http.httpRespMap(SYSTEM_URL, reqJsonString);
		System.out.println("httpRespMap" + map);
	}

}