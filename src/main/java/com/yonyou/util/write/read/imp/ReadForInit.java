package com.yonyou.util.write.read.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yonyou.util.write.read.ReadAbs;

public class ReadForInit extends ReadAbs<Map<String,Map<String,String>>>{

	@Override
	protected Map<String, Map<String, String>> init() {
		// TODO 自动生成的方法存根
		return new HashMap<String,Map<String,String>>();
	}

	@Override
	protected Map<String, Map<String, String>> bulidMessage(BufferedReader in,
			Map<String, Map<String, String>> t) throws IOException {
		// TODO 自动生成的方法存根
		String lineMessage="";
		int index =0;
		String table_name="";
		while((lineMessage=in.readLine())!=null){
			index++;
			if(!lineMessage.equals("")&&index==1){
				table_name =lineMessage.toUpperCase();
				if(!t.containsKey(table_name)){
					t.put(table_name, new HashMap<String,String>());
				}
			}else{
				lineMessage=lineMessage.trim();
				if(lineMessage.length()>0){
					String message =lineMessage.substring(4, lineMessage.length()-2);
					message=message.replaceAll("\"", "");
					if(message.indexOf(",")!=-1){
						String[] messages =message.split(",");
						if(messages.length==2){
							t.get(table_name).put(messages[1].trim(), messages[0].trim().toUpperCase());
						}else{
							this.setErrorMessage(this.writeErrorMessage(index, message+" 信息有误！"));
						}
					}else{
						this.setErrorMessage(this.writeErrorMessage(index, message+" 信息有误！"));
					}
				}
				
			}
			
		}
		return t;
	}
	
	

}
