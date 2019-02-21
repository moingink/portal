package com.yonyou.util.menu.imp;

import java.util.List;
import java.util.Map;

import com.yonyou.business.entity.MenuEntity;
import com.yonyou.util.menu.IBulidMenuLable;

public class BulidMenuLableForVeliches implements IBulidMenuLable{
	
	
	/**
	 * 获取一级菜单
	 * @param list
	 * @return
	 */
	public  String bulidMenuLableForA(Map<String,List<MenuEntity>> menuData,String menuCode){
		int showLength=6;
		StringBuffer lableHtml =new StringBuffer("");
		String div="<div class='box-top' >";
		String head="<ul class='top-tab'>";
		String style ="<li %s totalcode='%s' totalname='%s'><p><img src='../vendor/vehicles/img/crm.png'/></p><p>%s</p> </li>";
		String onClickMark ="onclick='openMenu(this)' ";
		int index =1;
		lableHtml.append(div).append(head);
		if(menuCode!=null){
			for(MenuEntity menuEntity:menuData.get(menuCode)){
				String childHtml ="";
				if(index>showLength){
					lableHtml.append("</ul>").append("</div>");
					lableHtml.append("<div class='box-top1' >").append(head);
					index=1;
				}
				lableHtml.append(String.format(style,onClickMark, menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getText()));
				lableHtml.append(childHtml);
				index++;
			}
			lableHtml.append("</ul>").append("</div>");
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
		StringBuffer lableHtml =new StringBuffer("");
		lableHtml.append(bulidMenuLableForB(menuData, menuCode));
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
		String style ="<div class='box-crm'> <p class='crm-top'><span class='top1'></span><span class='top2' totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s' >%s</span></p> <ul class='crm-btn'>";
		StringBuffer lableHtml =new StringBuffer("");
		int index=0;
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				for(MenuEntity menuEntity:menuList){
					index++;
					//lableHtml.append(String.format(style, icon,menuEntity.getTotalCode(),menuEntity.getUrl(),menuEntity.getText()));
					if(menuData.containsKey(menuEntity.getTotalCode())){
						lableHtml.append(String.format(style, menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getUrl(),menuEntity.getParent(),index,menuEntity.getText()));
						lableHtml.append(bulidMenuLableForNode(menuData, menuEntity.getTotalCode()));
						lableHtml.append("</ul></div>");
						
					}
					lableHtml.append("<br/>");
				}
				
			}
		}
		return lableHtml.toString();
	}
	
	/**
	 * 遍历查找节点信息
	 * @param menuData
	 * @param menuCode
	 * @param mark
	 * @param isIteration
	 * @return
	 */
	public  String bulidMenuLableForNode(Map<String,List<MenuEntity>> menuData,String menuCode){
		//String style ="<a class='list-group-item' href='#' onclick='changNode(this)' totalcode='%s' url='%s'>"+mark+"%s</a>";
		//String childStyle ="<dd totalcode='%s' url='%s' parentcode='%s' style='display: none' onclick='changNode(this)' title='%s' index ='%s'  ><span>"+mark+"</span>&nbsp;%s</dd>";
		// 
		String htmlMark=" isPopover='1' data-container='body' data-toggle='popover'    data-content='%s'";
		String style ="<li  %s totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s' onclick='changNode(this)' ><p><img %s src='../../../vendor/vehicles/img/crm-data.png'/></p><p>%s</p></li>";
		String chlidStyle ="<a class='list-group-item '   totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s'  onclick='changNodeFour(this)' >%s&nbsp;&nbsp;<span class='glyphicon glyphicon-new-window'/></a>";
		StringBuffer lableHtml =new StringBuffer("");
		int index=0;
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				for(MenuEntity menuEntity:menuList){
					String html;
					String popverMark;
					index++;
					if(index>6){
						index=0;
						lableHtml.append("<br/>");
					}
					if(menuData.containsKey(menuEntity.getTotalCode())){
						StringBuffer childHtml =new StringBuffer("<div class='list-group'>");
						for(MenuEntity childMenu:menuData.get(menuEntity.getTotalCode())){
							childHtml.append(String.format(chlidStyle, childMenu.getTotalCode(),childMenu.getText(),childMenu.getUrl(),childMenu.getParent(),index,childMenu.getText()));
						}
						childHtml.append("</div>");
						html=String.format(htmlMark,childHtml.toString().replaceAll("'", "&quot;"));
						popverMark="isPopover='1'";
					}else{
						html="";
						popverMark="";
					}
					lableHtml.append(String.format(style, popverMark,menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getUrl(),menuEntity.getParent(),index,html,menuEntity.getText()));
				}
				lableHtml.append("<br/>");
			}
		}
		lableHtml.append("</div>");
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
