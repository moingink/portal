package com.yonyou.business.entity;

import java.io.Serializable;
import java.util.Map;

public class MenuEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7311937004828418658L;
	private String id ;
	private String text;
	private String totalCode;
	private String url;
	private String parent;
	
	public MenuEntity(Map<String,String> searchParams){
		this.init(searchParams);
	}
	
	private void init(Map<String,String> searchParams){
		id= String.valueOf(searchParams.get("ID"));
		text = searchParams.get("NAME");
		totalCode= searchParams.get("TOTAL_CODE");
		Object data_url=searchParams.get("DATA_VALUE");
		Object parent_total_code=searchParams.get("PARENT_TOTAL_CODE");
		if(data_url==null||data_url.equals("null")){
			url="";
		}else{
			url=(String)data_url;
		}
		if (parent_total_code==null||parent_total_code.equals("null")) {
			parent="#";
		}else{
			parent=(String)parent_total_code;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTotalCode() {
		return totalCode;
	}
	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
    @Override
    public String toString() {
    	// TODO 自动生成的方法存根
    	return String.format("### [text]%s\t[totalcode]%s\t[url]%s\t[parent]%s", this.getText(),this.getTotalCode(),this.getUrl(),this.getParent());
    }
	
}
