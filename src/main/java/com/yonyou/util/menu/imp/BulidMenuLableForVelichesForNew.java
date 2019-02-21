package com.yonyou.util.menu.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yonyou.business.entity.MenuEntity;

public class BulidMenuLableForVelichesForNew extends BulidMenuLableForVeliches{
	
	
protected  Set<String> MenuAPngName =new HashSet<String>();
	
	
	public BulidMenuLableForVelichesForNew(){
		initSetMenuAPng ();
	}
	
	protected void initSetMenuAPng (){
		MenuAPngName.add("财务管理");
		MenuAPngName.add("采购管理");
		MenuAPngName.add("产品管理");
		MenuAPngName.add("合作伙伴管理");
		MenuAPngName.add("后台管理");
		MenuAPngName.add("经营分析管理");
		MenuAPngName.add("客户管理");
		MenuAPngName.add("商机管理");
		MenuAPngName.add("文档管理");
		MenuAPngName.add("系统管理");
		MenuAPngName.add("项目管理");
		MenuAPngName.add("一户一案管理");
		MenuAPngName.add("综合审批管理");
		MenuAPngName.add("审批工作台");
	}
	
	protected String findMenuPng(String name){
		if(MenuAPngName.contains(name)){
			return name;
		}else{
			return "crm";
		}
	}
	
	
	public  String bulidMenuLableForA(Map<String,List<MenuEntity>> menuData,String menuCode){
		
		
		StringBuffer lableHtml =new StringBuffer("");
		String div="<div class='box-top' >";
		String head="<ul class='top-tab'>";
		String style ="<li %s  onclick='openMenu(this)' totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s'><p><img %s src='../vendor/vehicles/menu/%s.png'/></p><p>%s</p> </li>";
		String htmlMark=" isPopover='1' data-container='body' data-toggle='popover'    data-content='%s'";
		String chlidStyle ="<a class='list-group-item ' onclick='openMenuPage(this)'  totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s'  >%s&nbsp;&nbsp;</a>";
		int index =1;
		int showLength=10;
		lableHtml.append(div).append(head);
		if(menuCode!=null){
			if(menuData.get(menuCode)!=null){
			for(MenuEntity menuEntity:menuData.get(menuCode)){
					if(index>showLength){
						lableHtml.append("</ul>").append("</div>");
						lableHtml.append("<div class='box-top1' >").append(head);
						index=1;
					}
					String popverMark="";
					String html="";
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
					lableHtml.append(String.format(style, popverMark,menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getUrl(),menuEntity.getParent(),index,html,this.findMenuPng(menuEntity.getText()),menuEntity.getText()));
					index++;
				}
			}
			lableHtml.append("</ul>").append("</div>");
		}
		
		lableHtml.append("</div>");
		return lableHtml.toString();
	}
	/**
	 * 根据编码查找二级菜单
	 * @param menuData
	 * @param menuCode
	 * @return
	 */
	//<li> <a href='#' totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s' >%s</a>
	public  String bulidMenuLableForB(Map<String,List<MenuEntity>> menuData,String menuCode){
		String style ="<li > <a href='#' class='dropdown-toggle' totalcode='%s' totalname='%s' url='%s' parentcode='%s' index ='%s' onclick='changNodeForB(this)'><b>%s</b><span></span></a> ";
		StringBuffer lableHtml =new StringBuffer("");
		int index=0;
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				for(MenuEntity menuEntity:menuList){
					index++;
					lableHtml.append(String.format(style, menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getUrl(),menuEntity.getParent(),index,menuEntity.getText()));
					if(menuData.containsKey(menuEntity.getTotalCode())){
						
						lableHtml.append(bulidMenuLableForNode(menuData, menuEntity.getTotalCode()));
						
					}
					lableHtml.append("</li>");
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
		String style ="<li  totalcode='%s' totalname='%s' url='%s' parentcode='%s' onclick='changNode(this)' ><a href='#'>&nbsp;&nbsp;%s</a></li>";
		StringBuffer lableHtml =new StringBuffer("<ul class='dropdown-menu' role='menu' id='"+menuCode+"' style='display: none;'> <li class='divider'></li>");
		if(menuData.containsKey(menuCode)){
			List<MenuEntity> menuList =menuData.get(menuCode);
			if(menuList!=null){
				for(MenuEntity menuEntity:menuList){
					lableHtml.append(String.format(style, menuEntity.getTotalCode(),menuEntity.getText(),menuEntity.getUrl(),menuEntity.getParent(),menuEntity.getText()));
					//-------------------------
					System.out.println(menuEntity.getText());
					if(menuData.containsKey(menuEntity.getTotalCode())){
						for(MenuEntity child:menuData.get(menuEntity.getTotalCode())){
							System.out.println("---------------"+child.getText());
							lableHtml.append(String.format(style, child.getTotalCode(),child.getText(),child.getUrl(),child.getParent(),"--"+child.getText()));
							/*if(child.getUrl().length()>0){
								
							}*/
						}
					}
					//-------------------------
				}
				lableHtml.append("<li class='divider'></li></ul>");
			}
		}
		return lableHtml.toString();
	}
	
}
