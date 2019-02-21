package com.yonyou.util.theme;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.theme.path.ThemePathAbs;
import com.yonyou.util.theme.path.impl.ThemePathForRedis;
import com.yonyou.util.theme.vo.ThemePathEntity;

public class ThemePath  implements IThemePathMark{
	
	private static ThemePathAbs PATH =new ThemePathForRedis();
	
	
	public static void appendTokenParamByThemeCode(TokenEntity token){
		//token.setTheme("vehicles");
		token.setTheme("vehicles");
	}
	
	/**
	 * 获取文件路径
	 * @param request
	 * @param position_code
	 * @return
	 */
	public static String findPath(HttpServletRequest request,String position_code){
		TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
		String contextPath=request.getContextPath();
		String system_code =request.getContextPath().replace("/", "");
		System.out.println(position_code+"----------"+system_code);
		ThemePathEntity entity=PATH.findData(tokenEntity.getTheme(), position_code,system_code);
		String reunPath =bulidPath(contextPath,tokenEntity.getTheme(),entity);
		System.out.println(reunPath);
		return reunPath;
	}
	
	/**
	 * 获取相对路径
	 * @param request
	 * @return
	 */
	public static String findRoute(HttpServletRequest request){
		TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
		String contextPath=request.getContextPath();
		return contextPath+"/pages/t/"+tokenEntity.getTheme()+"/";
	}
	
	/**
	 * 获取主路径
	 * @param request
	 * @return
	 */
	public static String findMainPath(HttpServletRequest request){
		TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
		String contextPath=request.getContextPath();
		return contextPath+"/pages/mainPortal.jsp?token="+tokenEntity.getToken()+"&t="+(new Date().getTime());
	}
	
	
	private static String bulidPath(String contextPath,String theme,ThemePathEntity entity){
		
		if(entity!=null){
			String pathType =entity.getOption_type();
			return contextPath+entity.getVisit_path()+theme+"/"+findPathTypeUrl(pathType)+entity.getVisit_file_name();
		}else{
			System.out.println("没有获取到相关信息");
			return "";
		}
		
	}
	
	private static String findPathTypeUrl(String pathType){
		String pathTypeUrl ="";
		switch(pathType){
			case "1":  break;
			case "2":  pathTypeUrl="css/"; break;
			case "3":  pathTypeUrl="img/"; break;
		}
		return pathTypeUrl;
	}
}
