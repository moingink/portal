package com.yonyou.util.page.input;

import java.util.Map;

import com.yonyou.util.page.SingleQueryFrameAbs;

/**
 * input生成类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public abstract class SingleQueryFrameForInput extends SingleQueryFrameAbs{
	
	
	@Override
	public String findSingleFrame(String id,String name, String value,Map<String,String> message) {
		// TODO 自动生成的方法存根
		StringBuffer html =new StringBuffer();
		
		html.append("<input type='")
			.append(findType()).append("'")
			.append(" class='")
			.append(this.findClass())
			.append("' id='").append(id).append("' name='").append(id).append("'")
			.append(" value ='").append(value).append("' ");
				  
				  
	    html.append(" />");
		
		return html.toString();
	}
	
	/**
	 * 获取input类型
	 * @return
	 */
	public abstract String findType();
	/**
	 * 获取样式
	 * @return
	 */
	public abstract String findClass();
	
	
}
