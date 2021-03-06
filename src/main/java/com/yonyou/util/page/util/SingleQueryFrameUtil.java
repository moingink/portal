package com.yonyou.util.page.util;

import java.util.HashMap;
import java.util.Map;

import com.yonyou.util.page.SingleQueryFrameAbs;
import com.yonyou.util.page.date.SingleQueryFrameForDate;
import com.yonyou.util.page.date.SingleQueryFrameForDateSection;
import com.yonyou.util.page.input.SingleQueryFrameForNumber;
import com.yonyou.util.page.input.SingleQueryFrameForText;
import com.yonyou.util.page.reference.SingleQueryFrameForReference;
import com.yonyou.util.page.select.SingleQueryFrameForSelect;
import com.yonyou.util.page.text.SingleQueryFrameForTextVal;
import com.yonyou.util.page.textarea.SingleQueryFrameForTextarea;

/**
 * 页面查询框架工具类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public class SingleQueryFrameUtil {
	
	/** 文本框标示*/
	public static String TYPE_INPUT_TEXT="0";
	/** 数值框标示*/
	public static String TYPE_INPUT_NUMBER="6";
	/** 下拉框标示*/
	public static String TYPE_SELECT="1";
	/** 文本域标示*/
	public static String TYPE_TEXTAREA="2";
	/** 日期标示*/
	public static String TYPE_DATE="4";
	/** 参选标示*/
	public static String TYPE_REFERENCE="3";
	/** 参选标示*/
	public static String TYPE_TEXT_VAL="9";
	/** 日期区间*/
	public static String TYPE_DATE_SECTION="5";
	/** 框架数据*/
	private static Map<String,SingleQueryFrameAbs> SingleQueryMap =new HashMap<String,SingleQueryFrameAbs>();
	
//	private static Map<String,String> SearchMarkMap =new HashMap<String,String>();
	
	/**
	 * 初始化
	 */
	static {
		
		SingleQueryMap.put(TYPE_INPUT_TEXT, new SingleQueryFrameForText());
		SingleQueryMap.put(TYPE_INPUT_NUMBER, new SingleQueryFrameForNumber());
		SingleQueryMap.put(TYPE_SELECT, new SingleQueryFrameForSelect());
		SingleQueryMap.put(TYPE_DATE, new SingleQueryFrameForDate());
		SingleQueryMap.put(TYPE_DATE_SECTION, new SingleQueryFrameForDateSection());
		SingleQueryMap.put(TYPE_TEXTAREA, new SingleQueryFrameForTextarea());
		SingleQueryMap.put(TYPE_REFERENCE, new SingleQueryFrameForReference());
		SingleQueryMap.put(TYPE_TEXT_VAL, new SingleQueryFrameForTextVal());
		
//		SearchMarkMap.put(TYPE_INPUT_TEXT, "SEARCH-LIKE-");
//		SearchMarkMap.put(TYPE_INPUT_NUMBER, "SEARCH-%3D-");
//		SearchMarkMap.put(TYPE_SELECT, "SEARCH-%3D-");
//		SearchMarkMap.put(TYPE_DATE, "SEARCH-DATE-");
//		SearchMarkMap.put(TYPE_DATE_SECTION, "SEARCH-DATE-");
//		SearchMarkMap.put(TYPE_TEXTAREA, "SEARCH-LIKE-");
//		SearchMarkMap.put(TYPE_REFERENCE, "SEARCH-%3D-");
	}
	
	/**
	 * 获取框架生成类
	 * @param input_type input 类型
	 * @return 框架生成类
	 */
	public static SingleQueryFrameAbs  findSingleQuery(String input_type){
		if(SingleQueryMap.containsKey(input_type)){
			return SingleQueryMap.get(input_type);
		}else{
			return SingleQueryMap.get(TYPE_INPUT_TEXT);
		}
	}
	/**
	 * 获取框架分割标示
	 * @param input_type
	 * @return
	 */
	public static String  findSearchMark(String input_type){
//		if(SearchMarkMap.containsKey(input_type)){
//			return SearchMarkMap.get(input_type);
//		}else{
//			return SearchMarkMap.get(TYPE_INPUT_TEXT);
//		}
		//又一版方案——查询条件增加统一前缀，LIKE等通配符改由元数据获取
		return "SEARCH-";
	}
}
