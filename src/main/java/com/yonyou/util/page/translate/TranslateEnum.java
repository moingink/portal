package com.yonyou.util.page.translate;

import java.sql.Date;

public enum TranslateEnum {
	
	TYPE_SELECT("SELECT","下拉框", new TranslateHandle(){
		@Override
		public String findTranslateValue(Object value, String input_formart,
				String input_type) {
			// TODO 自动生成的方法存根
			return "----";
		}
	}),
	TYPE_TEXT("TEXT","文本",new TranslateHandle(){
		@Override
		public String findTranslateValue(Object value, String input_formart,
				String input_type) {
			// TODO 自动生成的方法存根
			return value.toString();
		}
	}),
	TYPE_DATE("DATE","日期",new TranslateHandle(){
		@Override
		public String findTranslateValue(Object value, String input_formart,
				String input_type) {
			// TODO 自动生成的方法存根
			if(value.getClass().equals(Date.class)){
				String valueStr =value.toString();
				if(valueStr.length()>10){
					return valueStr.substring(0,10);
				}else{
					return valueStr;
				}
			}else{
				return "";
			}
		}
	});
	
	private String type;
	private String name;
	private TranslateHandle handle;
	TranslateEnum(String _type,String _name,TranslateHandle _handle){
		type =_type;
		name =_name;
		handle=_handle;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public TranslateHandle getHandle() {
		return handle;
	}

	public void setHandle(TranslateHandle handle) {
		this.handle = handle;
	}
	
	

}
