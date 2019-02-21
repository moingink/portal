package com.yonyou.util.write.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class ReadAbs<T> {
	
	private boolean isErrorMessage =false;
	private StringBuffer ErrorMessage =new StringBuffer();
	private String fileName ="";
	
	public T read(String url) throws IOException{
		System.out.println(url);
		T t =init();
		File file =new File(url);
		if(file.isDirectory()){
			for(File fs:file.listFiles()){
				System.out.println("READ FILE "+fs.getCanonicalPath());
				fileName=fs.getCanonicalPath();
				t =this.readMessage(t, fs);
			}
		}else{
			t =this.readMessage(t, file);
			fileName=url;
		}
		return t;
		
	}
	
	protected T readMessage(T t,File f) throws IOException{
		BufferedReader in =new  BufferedReader(new FileReader(f)); 
		t=this.bulidMessage(in, t);
		in.close();
		return t;
	}
	
	protected abstract T init();
	protected abstract T bulidMessage(BufferedReader in,T t) throws IOException;
	
	protected void setErrorMessage(String errorMessage){
		isErrorMessage =true;
		ErrorMessage.append(errorMessage);
	}
	
	public boolean findIsErrorMessage(){
		return isErrorMessage;
	}
	
	public String findErrorMessage(){
		return ErrorMessage.toString();
	}
	
	protected String writeErrorMessage(int index,String message){
		return "文件：\t"+fileName+"\t第"+index+"行：\t"+message+"\n";
	}
	
	protected String replayNullEmpay(String str){
		if(str.getBytes()[0]==-17){
			return str.substring(1);
		}else{
			return str;
		}
	}
}
