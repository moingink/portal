package com.yonyou.util.write.read.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yonyou.util.page.util.SingleQueryFrameUtil;
import com.yonyou.util.write.read.ReadAbs;
import com.yonyou.util.write.vo.WriteEntity;

public class ReadForMessage extends ReadAbs<Map<String,WriteEntity>> {
	
	private Map<String,Map<String,String>> readInitMap =null;
	
	public Map<String, WriteEntity> read(String url,Map<String,Map<String,String>> _readInitMap) throws IOException {
		// TODO 自动生成的方法存根
		readInitMap=_readInitMap;
		return super.read(url);
	}
	
	private static Map<String,String> mappingMap =new HashMap<String,String>();
	private static Map<String,Integer> LineTypeMap =new HashMap<String,Integer>();
	static{
		mappingMap.put("参选框", SingleQueryFrameUtil.TYPE_REFERENCE);
		mappingMap.put("下拉框", SingleQueryFrameUtil.TYPE_SELECT);
		mappingMap.put("日期", SingleQueryFrameUtil.TYPE_DATE);
		mappingMap.put("文本域", SingleQueryFrameUtil.TYPE_INPUT_TEXT);
		LineTypeMap.put("查询字段：", 1);
		LineTypeMap.put("展示字段：", 2);
		LineTypeMap.put("新增字段：", 3);
		
	}
	
	@Override
	protected Map<String, WriteEntity> init() {
		// TODO 自动生成的方法存根
		return new HashMap<String,WriteEntity>();
	}

	@Override
	protected Map<String, WriteEntity> bulidMessage(BufferedReader in,
			Map<String, WriteEntity> t) throws IOException {
		// TODO 自动生成的方法存根
		String lineMessage="";
		int index =0;
		String table_name="";
		String dataSource="";
		String lineMark="";
		WriteEntity entity =null;
		while((lineMessage=in.readLine())!=null){
			index++;
			lineMessage=lineMessage.trim();
			lineMessage=lineMessage.replaceAll("\\s+"," ");
			if(!lineMessage.equals("")&&index==1){
				if(lineMessage.indexOf(" ")!=-1){
					String[] messages =lineMessage.split(" ");
					table_name=replayNullEmpay(messages[0].toUpperCase());
					dataSource=messages[1];
				}else{
					table_name=replayNullEmpay(lineMessage.toUpperCase());
				}
				entity =new WriteEntity(table_name,dataSource);
				if(!t.containsKey(entity.findKey())){
					t.put(entity.findKey(), entity);
				}
			}else{
				lineMessage=lineMessage.trim();
				if(lineMessage.length()>0){
					if(LineTypeMap.containsKey(lineMessage)){
						lineMark=lineMessage;
					}else{
						this.bulidWriteEntity(index,lineMark,entity, lineMessage);
					}
				}
				
			}
			
		}
		return t;
	}
	
	private void bulidWriteEntity(int index,String lineMark, WriteEntity entity,String lineMessage){
		String colName ="";
		String inputType="";
		if(lineMessage.indexOf(" ")!=-1){
			String [] lines =lineMessage.split(" ");
			if(lines.length==2){
				colName=lines[0].toUpperCase();
				inputType=lines[1];
			}
		}else{
			colName=lineMessage.toUpperCase();
		}
		if(LineTypeMap.containsKey(lineMark)){
			String colCode =this.findColCode(index,colName, entity);
			this.bulidWriteCol(LineTypeMap.get(lineMark), entity, colCode);
			this.bulidWriteInputType(entity, colCode, inputType);
		}
		
		
	}
	
	private void bulidWriteInputType(WriteEntity entity,String colCode,String inputType){
		if(colCode.length()>0){
			if(mappingMap.containsKey(inputType)){
				entity.putInputTypeColMap(colCode, mappingMap.get(inputType));
			}
		}
		
	}
	
	private String findColCode(int index,String colName,WriteEntity entity){
		String colCode ="";
		
		if(readInitMap!=null){
			if(readInitMap.containsKey(entity.getTable_name())){
				if(readInitMap.get(entity.getTable_name()).containsKey(colName)){
					colCode=readInitMap.get(entity.getTable_name()).get(colName);
				}else{
					this.setErrorMessage(this.writeErrorMessage(index, "["+colName+"]字段，在表["+entity.getTable_name()+"]中没有相应的翻译！"));
				}
			}
			
		}
		
		return colCode;
	}
	
	private void bulidWriteCol(int lineType,WriteEntity entity,String colCode){
		
		if(colCode.length()>0){
			switch(lineType) {
			case 1:
				entity.setQuery_field(colCode);
				break;
			case 2:
				entity.setDisplay_field(colCode);;
				break;
			case 3:
				entity.setMaintain_field(colCode);;
				break;
				
			}
		}
	}
	
	
}
