package com.yonyou.util.wsystem.service;

import com.yonyou.util.wsystem.api.HTTPApi;

public class NoticeService extends HTTPApi{
	
	private String http_url;
	
	public NoticeService(String _http_url){
		http_url=_http_url;
	}
	@Override
	public String findHttpUrl() {
		// TODO 自动生成的方法存根
		return http_url;
	}
	public String getHttp_url() {
		return http_url;
	}
	public void setHttp_url(String http_url) {
		this.http_url = http_url;
	}
	
	
}
