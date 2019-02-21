package com.yonyou.util.menu;

import java.util.List;
import java.util.Map;

import com.yonyou.business.entity.MenuEntity;

public interface IBulidMenuLable {
	
	//获取一级菜单
	public  String bulidMenuLableForA(Map<String,List<MenuEntity>> menuData,String menuCode);
	//根据菜单编码并迭代查询
	public  String bulidMenuByMenuCode(Map<String,List<MenuEntity>> menuData,String menuCode,String mark);
	//获取耳机菜单
	public  String bulidMenuLableForB(Map<String,List<MenuEntity>> menuData,String menuCode);
}
