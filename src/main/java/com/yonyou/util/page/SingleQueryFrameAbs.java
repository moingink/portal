package com.yonyou.util.page;

import java.util.Map;

/**
 * 页面查询框架抽象类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public abstract class SingleQueryFrameAbs {
	
	/**
	 * 获取页面html信息
	 * @param id ID
	 * @param name 名称
	 * @return 页面HTML信息
	 */
	public String findPageHtml(String id,String name){
		
		return findPageHtml(id,name,"",null);
	}
	
	/**
	 * 获取页面html信息
	 * @param id ID
	 * @param name 名称
	 * @param value 值
	 * @return 页面HTML信息
	 */
	public String findPageHtml(String id,String name,String value){
		
		return findPageHtml(id,name,value,null);
	}
	
	/**
	 * 获取页面html信息
	 * @param id ID
	 * @param name 名称
	 * @param value 值
	 * @param message 数据信息
	 * @return 页面HTML信息
	 */
	public String findPageHtml(String id,String name,String value,Map<String,String> message){
		if(value==null){
			value="";
		}
		StringBuffer html =new StringBuffer();
		if(name!=null&&name.length()>0){
			html.append("<div class='col-md-4' style='white-space:nowrap;'>");
			html.append("<label class='control-label' style='text-align: left' for='"+id+"'>")
			  	.append(name).append("：</label>");
			html.append("</div>");
			html.append("<div class='col-md-8'>")
			  	.append(findSingleFrame(id,name,value,message))
			  	.append("</div>");
		}else{
			html.append(findSingleFrame(id,name,value,message));
		}
		
		return html.toString();
		
	}
	
	/**
	 * 获取页面框架信息
	 * @param id ID
	 * @param name 名称
	 * @param value 值
	 * @param message 信息
	 * @return 页面框架信息
	 */
	protected abstract String findSingleFrame(String id,String name,String value,Map<String,String> message);
	
	/**
	 * 获取页面框架HTML信息
	 * @param id ID
	 * @param name 名称
	 * @param value 值
	 * @param message 信息
	 * @return 页面框架HTML信息
	 */
	public String findSingleHtml(String id,String name,String value,Map<String,String> message){
		return findSingleFrame( id, name, value, message);
	}
	
}
