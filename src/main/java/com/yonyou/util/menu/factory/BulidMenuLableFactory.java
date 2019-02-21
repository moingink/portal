package com.yonyou.util.menu.factory;

import java.util.HashMap;
import java.util.Map;

import com.yonyou.util.menu.IBulidMenuLable;
import com.yonyou.util.menu.imp.BulidMenuLable;
import com.yonyou.util.menu.imp.BulidMenuLableForVeliches;
import com.yonyou.util.menu.imp.BulidMenuLableForVelichesForNew;

public class BulidMenuLableFactory {
	
	private static Map<String,IBulidMenuLable> CACHE_FACTORY =new HashMap<String,IBulidMenuLable>();
	
	private static String DEFAULT_TYPE="0";
	
	static {
		CACHE_FACTORY.put("0", new BulidMenuLable());
		CACHE_FACTORY.put("1", new BulidMenuLableForVelichesForNew());
		CACHE_FACTORY.put("2", new BulidMenuLableForVeliches());
	}
	
	public static IBulidMenuLable getBulidMenuLable(String type){
		
		if(CACHE_FACTORY.containsKey(type)){
			return CACHE_FACTORY.get(type);
		}else{
			return CACHE_FACTORY.get(DEFAULT_TYPE);
		}
	}
}
