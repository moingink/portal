package com.yonyou.util.page.select;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.util.BussnissException;
import com.yonyou.util.page.SingleQueryFrameAbs;
import com.yonyou.util.soa.SoaProcessing;
/**
 * 下拉框生成类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public class SingleQueryFrameForSelectByCompany extends SingleQueryFrameForSelect{

	
	
	public ConcurrentHashMap<String, String> findSelectMap(Map<String,String> message) throws BussnissException{
		//input_format 为 userId
		return (ConcurrentHashMap<String, String>) message;
	}
	
	@Override
	public String findDefaultSelect() {
		// TODO 自动生成的方法存根
		return "";
	}
	
	@Override
	public String appendOnClick() {
		// TODO 自动生成的方法存根
		return "onchange='changeMenu()'";
	}

}
