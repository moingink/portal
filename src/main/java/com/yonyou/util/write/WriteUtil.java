package com.yonyou.util.write;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yonyou.util.write.read.ReadAbs;
import com.yonyou.util.write.read.imp.ReadForInit;
import com.yonyou.util.write.read.imp.ReadForMessage;
import com.yonyou.util.write.vo.WriteEntity;
import com.yonyou.util.write.write.WriteMessage;


public class WriteUtil {
	
	
	
	private  String URL ="D://write//";
	private  String READ_INIT_MARK="init";
	private  String READ_MESSAGE_MARK="message";
	
	public void run() throws IOException{
		ReadAbs<Map<String,Map<String,String>>> readInit =new ReadForInit();
		ReadForMessage readMessage =new ReadForMessage();
		WriteMessage out =new WriteMessage();
		Map<String,Map<String,String>> readInitMap=readInit.read(URL+READ_INIT_MARK);
		Map<String,WriteEntity> map =readMessage.read(URL+READ_MESSAGE_MARK, readInitMap);
		//out.setDataMap(readInitMap);
		out.wirte(URL, map, readInit.findErrorMessage()+readMessage.findErrorMessage());
		System.out.println("OK");
	}
	
	
	public static void main(String [] args) throws IOException{
		WriteUtil write =new WriteUtil();
		write.run();
		
		//String aa ="公司名称	参选框";
		//System.out.println(aa.indexOf(" "));
	}
}
