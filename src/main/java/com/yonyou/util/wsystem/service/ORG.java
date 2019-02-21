
package com.yonyou.util.wsystem.service;
import com.yonyou.util.PropertyFileUtil;
import com.yonyou.util.sql.SQLUtil;
import com.yonyou.util.sql.StringToObjUtil;
import com.yonyou.util.wsystem.api.HTTPApi;
import com.yonyou.util.wsystem.api.IMetaDataEntity;
import com.yonyou.util.wsystem.entity.MetaDetal;

/**
 * 组织处理类
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public class ORG   {
	
	private static ORG org =new ORG();
	
	/**
	 * 创建人员调用信息
	 */
	public static USER USER =org.new USER();
	
	
	
	public class USER extends IMetaDataEntity<USER>{
		
		@Override
		protected USER initObj() {
			// TODO 自动生成的方法存根
			return this;
		}
		@Override
		protected void initTable() {
			// TODO 自动生成的方法存根
			TABLE ="RM_USER";
			ALIS_TABLE="US";
		}
		public  MetaDetal ID =new MetaDetal("ID","主键",StringToObjUtil.LONG,"","","");;
		public  MetaDetal NAME=new MetaDetal("NAME","名称",StringToObjUtil.STRING,"","","");
		public  MetaDetal LOCK_STATUS=new MetaDetal("LOCK_STATUS","锁定状态",StringToObjUtil.STRING,"","","");
		public  MetaDetal LOGIN_ID=new MetaDetal("LOGIN_ID","登陆人ID",StringToObjUtil.STRING,"","","");
		public  MetaDetal PASSWORD=new MetaDetal("PASSWORD","密码",StringToObjUtil.STRING,"","","");
		public  MetaDetal AUTHEN_TYPE=new MetaDetal("AUTHEN_TYPE","类型",StringToObjUtil.STRING,"","","");
		public  MetaDetal EMAIL=new MetaDetal("EMAIL","邮件",StringToObjUtil.STRING,"","","");
		public  MetaDetal CUSTOM3=new MetaDetal("CUSTOM3","手机号",StringToObjUtil.STRING,"","","");
		public  MetaDetal ADMIN_TYPE=new MetaDetal("ADMIN_TYPE","用户权限类型",StringToObjUtil.STRING,"","","");
		public  MetaDetal LOGIN_STATUS=new MetaDetal("LOGIN_STATUS","登陆状态",StringToObjUtil.STRING,"","","");
		public  MetaDetal LAST_LOGIN_DATE=new MetaDetal("LAST_LOGIN_DATE","最后登陆时间",StringToObjUtil.DATE,"","","");
		public  MetaDetal LAST_LOGIN_IP=new MetaDetal("LAST_LOGIN_IP","最后登陆ID",StringToObjUtil.STRING,"","","");
		public  MetaDetal MODIFY_DATE=new MetaDetal("MODIFY_DATE","修改时间",StringToObjUtil.DATE,"","","");
		public  MetaDetal MODIFY_IP=new MetaDetal("MODIFY_IP","修改ID",StringToObjUtil.STRING,"","","");
		public  MetaDetal MODIFY_USER_ID=new MetaDetal("MODIFY_USER_ID","修改用户ID",StringToObjUtil.STRING,"","","");
		
		@Override
		public String findHttpUrl() {
			// TODO 自动生成的方法存根
			return PropertyFileUtil.getValue("SYSTEM_URL");
		}
	    
		}
		
	/**
	 * 创建菜单调用信息
	 */
	public static MENU MENU =org.new MENU();
	
	
	
	public class MENU extends IMetaDataEntity<MENU>{
		
		@Override
		protected MENU initObj() {
			// TODO 自动生成的方法存根
			return this;
		}
		@Override
		protected void initTable() {
			// TODO 自动生成的方法存根
			TABLE ="RM_FUNCTION_NODE";
			ALIS_TABLE="RM_FUNCTION_NODE";
		}
		public  MetaDetal ID =new MetaDetal("ID","主键",StringToObjUtil.STRING,"","","");
		public  MetaDetal ICON=new MetaDetal("ICON","图片",StringToObjUtil.STRING,"","","");
		public  MetaDetal NODE_TYPE=new MetaDetal("NODE_TYPE","类型",StringToObjUtil.STRING,"","","");
		public  MetaDetal FUNCTION_PROPERTY=new MetaDetal("FUNCTION_PROPERTY","功能性质",StringToObjUtil.STRING,"","","");
		public  MetaDetal NAME=new MetaDetal("NAME","名称",StringToObjUtil.STRING,"","","");
		public  MetaDetal ORDER_CODE=new MetaDetal("ORDER_CODE","排序编码",StringToObjUtil.STRING,"","","");
		public  MetaDetal DATA_VALUE  =new MetaDetal("DATA_VALUE","URL值",StringToObjUtil.STRING,"","","");
		public  MetaDetal PATTERN_VALUE=new MetaDetal("PATTERN_VALUE","正则匹配",StringToObjUtil.STRING,"","","");
		public  MetaDetal IS_LEAF=new MetaDetal("IS_LEAF","是否末点",StringToObjUtil.STRING,"","","");
		public  MetaDetal HELP_NAME=new MetaDetal("HELP_NAME","帮助文件名",StringToObjUtil.DATE,"","","");
		public  MetaDetal TOTAL_CODE=new MetaDetal("TOTAL_CODE","完全编码",StringToObjUtil.STRING,"","","");

		@Override
		public String findHttpUrl() {
			// TODO 自动生成的方法存根
			return PropertyFileUtil.getValue("SYSTEM_URL");
		}
	    
		}
	
	
	public static void main(String args[]){
	   //System.out.println(ORG.TT.getFileds());
	   //----------------------------------------
	   System.out.println(ORG.USER.ALIS_TABLE);
	   System.out.println(ORG.USER.ID.getCode());
	   System.out.println(ORG.USER.NAME.getName());
	   System.out.println(SQLUtil.findSelectSql(ORG.USER.findSqlTableUtil().appendSelFiled("aabbcc")));
	   System.out.println(SQLUtil.findSelectSql(ORG.USER.findSqlTableUtil()));
	   System.out.println(SQLUtil.findSelectSql(ORG.USER.findSqlTableUtil()));
	   System.out.println(SQLUtil.findSelectSql(ORG.USER.findSqlTableUtil()));
	}


	
}
