package com.yonyou.util.page.textarea;

import java.util.Map;

import com.yonyou.util.page.SingleQueryFrameAbs;

/**
 * 文本域生成类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public class SingleQueryFrameForTextarea extends SingleQueryFrameAbs{
	
	
	@Override
	protected String findSingleFrame(String id, String name, String value,
			Map<String, String> message) {
		// TODO 自动生成的方法存根
		StringBuffer html= new StringBuffer();
		html.append("<textarea class='").append(this.findClass()).append("'")
			.append(" id='").append(id).append("' name ='").append(id).append("'")
			.append(" rows ='").append(this.findRows()).append("'>");
		
		html.append(value);
		
		html.append("</textarea>");
		
		return html.toString();
	}
	
	/**
	 * 获取样式
	 * @return
	 */
	public String findClass(){
		return " form-control";
	}
	/**
	 * 获取列长度
	 * @return
	 */
	public String findRows(){
		return "3";
	}
	
}
