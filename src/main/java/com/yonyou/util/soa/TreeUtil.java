package com.yonyou.util.soa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yonyou.business.entity.MenuEntity;
import com.yonyou.util.JsonUtils;

public class TreeUtil {
	
	
	public static String HEAD_PARENT_CODE ="HeadParentCode";
	/**
	 * 生成机构树
	* @param mylist
	* @return
	 */
	public static String toTreeJOSN(List<Map<String, Object>> mylist) {
		List<Map<String, Object>> jsonList = new ArrayList();
		if (!mylist.isEmpty()) {
			for (int i = 0; i < mylist.size(); i++) {
				Map<String, Object> jsoMap = new LinkedHashMap<String, Object>();
				Map searchParams = mylist.get(i);
				jsoMap.put("id", searchParams.get("TOTAL_CODE"));
				jsoMap.put("text", searchParams.get("NAME"));
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("totalCode", searchParams.get("TOTAL_CODE"));
				data.put("name", searchParams.get("NAME"));
				data.put("id", searchParams.get("ID"));
				data.put("url", searchParams.get("DATA_VALUE"));
				jsoMap.put("data", data);
				if(searchParams.get("TOTAL_CODE").equals("100")){
					System.out.println(searchParams);
				}
				if (searchParams.get("PARENT_TOTAL_CODE").equals("null") ) {
					jsoMap.put("parent", "#");
				} else {
					jsoMap.put("parent", searchParams.get("PARENT_TOTAL_CODE"));
				}
				
				StringBuffer jsoMessage =new StringBuffer();
				for(String key:jsoMap.keySet()){
					jsoMessage.append(key+":"+jsoMap.get(key)).append("\t");
				}
				jsonList.add(jsoMap);
				System.out.println(jsoMessage);
			}

			return JsonUtils.object2json(jsonList);
		}else {
			return null;
		}
	}
	
	
	
	/**
	 * 生成机构树
	* @param mylist
	* @return
	 */
	public static ConcurrentHashMap<String,List<MenuEntity>> bulidMenu(List<Map<String, String>> mylist) {
		ConcurrentHashMap<String,List<MenuEntity>> bulidMenuMap =new ConcurrentHashMap<String,List<MenuEntity>>();
		List<MenuEntity> childList =null;
		String headParentCode="";
		if (!mylist.isEmpty()) {
			for (int i = 0; i < mylist.size(); i++) {
				Map<String,String> searchParams = mylist.get(i);
				MenuEntity menuEntity =new MenuEntity(searchParams);
			    if(menuEntity.getTotalCode().startsWith("100136")){	
			    	//过滤掉手机菜单
			    	continue;
			    }
				System.out.println(menuEntity);
				String parent =menuEntity.getParent();
				if(bulidMenuMap.containsKey(parent)){
					childList=bulidMenuMap.get(parent);
				}else{
					childList = new ArrayList<MenuEntity>();
					bulidMenuMap.put(parent, childList);
				}
				childList.add(menuEntity);
				if(headParentCode.length()==0||(headParentCode.length()>parent.length()&&!parent.equals("#"))){
					headParentCode=parent;
				}
			}
			if(headParentCode.length()>0){
				if(bulidMenuMap.containsKey(headParentCode)){
					bulidMenuMap.put(HEAD_PARENT_CODE, bulidMenuMap.get(headParentCode));
				}
			}
			return bulidMenuMap;
		}else {
			return null;
		}
	}
}
