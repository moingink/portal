package com.yonyou.util.write.vo;

import java.util.HashMap;
import java.util.Map;

public class WriteEntity {
	
	private String table_name;
	
	private String dataSource;
	//返回字段
	private StringBuffer return_field =new StringBuffer();
	//展示字段
	private StringBuffer display_field =new StringBuffer();
	//查询字段
	private StringBuffer query_field =new StringBuffer();
	//维护字段
	private StringBuffer maintain_field =new StringBuffer();
	
	public WriteEntity(String _table_name){
		table_name =_table_name;
	}
	
	public WriteEntity(String _table_name,String _dataSource){
		table_name =_table_name;
		dataSource =_dataSource;
	}
	
	public String findKey(){
		if(dataSource.length()>1){
			return dataSource;
		}else{
			return table_name;
		}
	}
	
	private Map<String,String> inputTypeColMap =new HashMap<String,String>();
	
	public Map<String, String> getInputTypeColMap() {
		return inputTypeColMap;
	}

	public void putInputTypeColMap(String col,String type) {
		inputTypeColMap.put(col, type);
	}

	

	public String getTable_name() {
		return table_name;
	}


	public String getReturn_field() {
		return return_field.toString();
	}

	public void setReturn_field(String return_field) {
		if(this.return_field.length()>1){
			this.return_field.append(",");
		}
		this.return_field.append(return_field);
		
		
	}

	public String getDisplay_field() {
		return display_field.toString();
	}

	public void setDisplay_field(String display_field) {
		if(this.display_field.length()>1){
			this.display_field.append("'||chr(38)||'");
		}
		this.display_field.append(display_field);
		
	}

	public String getQuery_field() {
		return query_field.toString();
	}

	public void setQuery_field(String query_field) {
		if(this.query_field.length()>1){
			this.query_field.append(",");
		}
		this.query_field.append(query_field);
	}

	public String getMaintain_field() {
		return maintain_field.toString();
	}

	public void setMaintain_field(String maintain_field) {
		if(this.maintain_field.length()>1){
			this.maintain_field.append(",");
		}
		this.maintain_field.append(maintain_field);
		
	}

	public String getDataSource() {
		return dataSource;
	}

	
	
	
}
