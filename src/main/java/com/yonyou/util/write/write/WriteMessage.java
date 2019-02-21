package com.yonyou.util.write.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yonyou.util.SpringContextUtil;
import com.yonyou.util.jdbc.BaseDao;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.write.vo.WriteEntity;

public class WriteMessage {
	
	protected String errorFile ="error.txt";
	protected String sqlFile ="sql.txt";
	
	private Map<String,Map<String,String>> dataMap =null;
	
	public Map<String, Map<String, String>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Map<String, String>> dataMap) {
		this.dataMap = dataMap;
	}

	public void wirte(String url,Map<String,WriteEntity> writeMap,String errorMessage) throws IOException{
		StringBuffer sql =new StringBuffer();
		int index =0;
		for(String tableName:writeMap.keySet()){
			index++;
			WriteEntity entity =writeMap.get(tableName);
			sql.append("---table:name \t =="+index+"\t"+tableName).append("\n");
			sql.append("-----------------------------------------").append("\n");
			sql.append(this.bulidSql(entity.getTable_name(), entity.getDataSource(), entity.getMaintain_field(), entity.getDisplay_field()
					, entity.getQuery_field(), entity.getReturn_field()));
			sql.append("\n");
			sql.append(this.bulidMetaCol(entity.getTable_name(), entity.getInputTypeColMap()));
			sql.append("\n");
		}
		sql.append(this.findMeteUpdateName());
		this.wirte(url+errorFile, errorMessage);
		this.wirte(url+sqlFile, sql.toString());
	}
	
	private String findMeteUpdateName(){
		StringBuffer sql =new StringBuffer();
		if(dataMap!=null){
			sql.append("-----------METE_UPDATE_FILED_NAME_SQL------------------------").append("\n");
			for(String table_name:dataMap.keySet()){
				sql.append("-----------METE_TABLE_NAME:/t"+table_name+"------------------------").append("\n");
				for(String name:dataMap.get(table_name).keySet()){
					sql.append(this.bulidMetaSqlForName(table_name,dataMap.get(table_name).get(name) , name));
					sql.append("\n");
				}
			}
		}
		return sql.toString();
	}
	
	private void wirte(String fileUrl,String message) throws IOException{
		System.out.println("WRITE FILE "+fileUrl);
		File f =new File(fileUrl);
		BufferedWriter out =new BufferedWriter(new FileWriter(f));
		out.write(message);
		out.flush();
		out.close();
	}
	
	
	private String bulidSql(String tableName,String dataSource,String maintain_field,String display_field,String query_field,String return_field){
		StringBuffer sql =new StringBuffer("UPDATE CD_DATASOURCE SET ");
		if(maintain_field.length()>1){
			sql.append("  MAINTAIN_FIELD = '").append(maintain_field).append("' ,");
		}
		if(display_field.length()>1){
			sql.append("  DISPLAY_FIELD = '").append(display_field).append("' ,");
		}
		if(query_field.length()>1){
			sql.append("  QUERY_FIELD = '").append(query_field).append("' ,");
		}
		if(return_field.length()>1){
			sql.append("  RETURN_FIELD = '").append(return_field).append("' ,");
		}
		if(sql.length()>25){
			sql.setLength(sql.length()-1);
			sql.append(" WHERE METADATA_CODE = '").append(tableName).append("'");
			if(dataSource.length()>1){
				sql.append(" AND DATASOURCE_CODE = '").append(dataSource).append("'");
			}
			sql.append(" ; ");
			sql.append("\n");
		}else{
			sql.setLength(0);
		}
		
		return sql.toString();
	}
	
	private String bulidMetaCol(String tableName,Map<String,String> colMessage){
		StringBuffer sql =new StringBuffer();
		for(String col:colMessage.keySet()){
			sql.append(this.bulidMetaSql(tableName, col, colMessage.get(col)));
			sql.append("\n");
		}
		return sql.toString();
	}
	
	private String bulidMetaSql(String tableName,String col,String inputType){
		
		StringBuffer sql =new StringBuffer("UPDATE CD_METADATA_DETAIL");
					 sql.append(" SET INPUT_TYPE = '").append(inputType).append("' ");
					 sql.append(" WHERE FIELD_CODE ='").append(col).append("'");
					 sql.append(" AND METADATA_ID =")
					    .append("( SELECT CD_METADATA.ID  FROM CD_METADATA WHERE CD_METADATA.DATA_CODE ='").append(tableName)
					    .append("') ;");
		
		return sql.toString();
	}
	
	private String bulidMetaSqlForName(String tableName,String col,String name){
		
		StringBuffer sql =new StringBuffer("UPDATE CD_METADATA_DETAIL");
					 sql.append(" SET FIELD_NAME = '").append(name).append("' ");
					 sql.append(" WHERE FIELD_CODE ='").append(col).append("'");
					 sql.append(" AND METADATA_ID =")
					    .append("( SELECT CD_METADATA.ID  FROM CD_METADATA WHERE CD_METADATA.DATA_CODE ='").append(tableName)
					    .append("') ;");
		
		return sql.toString();
	}
}
