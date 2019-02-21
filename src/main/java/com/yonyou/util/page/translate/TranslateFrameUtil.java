package com.yonyou.util.page.translate;

public class TranslateFrameUtil {
	
	/**
	 * 
	 * @param value
	 * @param input_formart
	 * @param input_type
	 * @param enumTypes
	 * @return
	 */
	public static String translate(Object value,String input_formart,String input_type,TranslateEnum ...enumTypes){
		
		String returnValue ="";
		for(TranslateEnum enumType:enumTypes){
			String handle =enumType.getHandle().findTranslateValue(returnValue, input_formart, input_type);
			if(handle!=null&&handle.length()>0){
				returnValue=handle;
			}
		}
		return returnValue;
	}
	
	
	
	
	
	
	
}
