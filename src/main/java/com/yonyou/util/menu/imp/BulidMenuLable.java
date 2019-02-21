package com.yonyou.util.menu.imp;

import java.util.List;
import java.util.Map;

import com.yonyou.business.entity.MenuEntity;
import com.yonyou.util.menu.IBulidMenuLable;

public class BulidMenuLable implements IBulidMenuLable{
	
	
	
	/**
	 * 获取一级菜单
	 * @param list
	 * @return
	 */
	public  String bulidMenuLableForA(Map<String,List<MenuEntity>> menuData,String menuCode){
		int showLength=6;
		StringBuffer lableHtml =new StringBuffer("");
		String style ="<li class='li-hover'><a href='javascript:void(0)' totalcode='%s' %s>%s</a>";
		String onClick ="onclick='changMenu(this)'";
		String onClickMark ="";
		int index =1;
		boolean isMoreInit=true;
		if(menuCode!=null){
			for(MenuEntity menuEntity:menuData.get(menuCode)){
				
				if(index<=showLength){
					String childHtml ="";
					//关闭2级悬浮菜单
					//String childHtml =bulidMenuLableForB(menuData, menuEntity.getTotalCode());
					if(!childHtml.equals("")){
						onClickMark="";
					}else{
						onClickMark =onClick;
					}
					lableHtml.append(String.format(style, menuEntity.getTotalCode(),onClickMark,menuEntity.getText()));
					lableHtml.append(childHtml);
					lableHtml.append("</li>");
				}else{
					if(isMoreInit){
						lableHtml.append("<li class='dropdown'>")
						 	.append("<a href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>更多 <span class='caret'></span></a>")
						 	.append("<ul class='dropdown-menu'>");
						isMoreInit=false;
					}
					
					String styleMore ="<li><a href='javascript:void(0)' totalcode='%s' %s>%s</a></li>"	; 
					lableHtml.append(String.format(styleMore, menuEntity.getTotalCode(),onClick,menuEntity.getText()));
					lableHtml.append("</li>");
				}
				index++;
			}
		}
		
		return lableHtml.toString();
	}
	
	/**
	 * 根据编码获取菜单信息，迭代并查找下级
	 * @param menuData
	 * @param menuCode
	 * @param mark
	 * @return
	 */
	public  String bulidMenuByMenuCode(Map<String,List<MenuEntity>> menuData,String menuCode,String mark){
		StringBuffer lableHtml =new StringBuffer("<dl>");
		String style ="<dt  totalcode='%s' url='%s' parentcode='%s' onclick='changNode(this)' title=%s><span class='icon icon%s' ></span>%s</dt>";
		lableHtml.append(bulidMenuLableForNode(menuData, menuCode, style,0));
		lableHtml.append("</dl>");
		return lableHtml.toString();
	}
	
	/**
	 * 根据编码查找二级菜单
	 * @param menuData
	 * @param menuCode
	 * @return
	 */
	public  String bulidMenuLableForB(Map<String,List<MenuEntity>> menuData,String menuCode){
		//String style ="<a class='list-group-item' href='#' onclick='bulidNode(this)' totalcode='%s' url='%s'>%s</a>";
		int spacing =170;
		String style ="<li><span class='icon icon%s'></span><a href='#' onclick='changMenu(this)'  totalcode='%s' url='%s'>%s</a></li>";
		StringBuffer lableHtml =new StringBuffer("");
		boolean isFull =true;
		int icon =0;
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				lableHtml.append("<ul style='width:"+menuList.size()*spacing+"px;'>");
				for(MenuEntity menuEntity:menuList){
					icon++;
					if(icon>10){
						icon=0;
					}
					//lableHtml.append(String.format(style, icon,menuEntity.getTotalCode(),menuEntity.getUrl(),menuEntity.getText()));
					if(menuData.containsKey(menuEntity.getTotalCode())){
						System.out.println(menuEntity.getText());
						lableHtml.append(String.format(style, icon,menuEntity.getTotalCode(),menuEntity.getUrl(),menuEntity.getText()));
					}else{
						isFull=false;
					}
					
				}
			}
		}
		lableHtml.append("</ul>");
		if(isFull){
			return lableHtml.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 遍历查找节点信息
	 * @param menuData
	 * @param menuCode
	 * @param mark
	 * @param isIteration
	 * @return
	 */
	public  String bulidMenuLableForNode(Map<String,List<MenuEntity>> menuData,String menuCode,String style,int iterationLength){
		//String style ="<a class='list-group-item' href='#' onclick='changNode(this)' totalcode='%s' url='%s'>"+mark+"%s</a>";
		String mark=findMarkString(iterationLength);
		String childStyle ="<dd totalcode='%s' url='%s' parentcode='%s' style='display: none' onclick='changNode(this)' title='%s' index ='%s'  ><span>"+mark+"</span>&nbsp;%s</dd>";
		StringBuffer lableHtml =new StringBuffer("");
		int index=0;
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				for(MenuEntity menuEntity:menuList){
					index++;
					if(index>10){
						index=1;
					}
					lableHtml.append(String.format(style, menuEntity.getTotalCode(),menuEntity.getUrl(),menuEntity.getParent(),menuEntity.getText(),index,decorateName(menuEntity.getText())));
					if(menuData.containsKey(menuEntity.getTotalCode())){
						iterationLength++;
						lableHtml.append(bulidMenuLableForNode(menuData,menuEntity.getTotalCode(),childStyle,iterationLength));
					}
				}
			}
		}
		lableHtml.append("</ul>");
		return lableHtml.toString();
	}
	
	private  String findMarkString(int iterationLength){
		String mark="";
		for(int i=1;i<=iterationLength;i++){
			mark=mark+"&nbsp;&nbsp;";
		}
		mark=mark+"> ";
		return mark;
	}
	
	private  String decorateName(String name){
		String returnName =name;
		if(name!=null&&name.length()>8){
			returnName =name.substring(0,7)+"...";
		}
		return returnName;
	}
}
