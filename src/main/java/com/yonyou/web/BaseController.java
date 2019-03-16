package com.yonyou.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.MetaDataUtil;
import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.business.WorkflowNodeUtil;
import com.yonyou.business.entity.MenuEntity;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.iuap.persistence.utils.IDGenerator;
import com.yonyou.util.BussnissException;
import com.yonyou.util.DateUtil;
import com.yonyou.util.ExcelTools;
import com.yonyou.util.JsonUtils;
import com.yonyou.util.jdbc.BaseDao;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.menu.factory.BulidMenuLableFactory;
import com.yonyou.util.page.PageUtil;
import com.yonyou.util.soa.SoaProcessing;
import com.yonyou.util.soa.TreeUtil;
import com.yonyou.util.sql.DataSourceCodeConstants;
import com.yonyou.util.sql.SQLUtil.WhereEnum;
import com.yonyou.util.sql.SqlTableUtil;
import com.yonyou.util.sql.SqlWhereEntity;
import com.yonyou.util.sql.StringToObjUtil;
import com.yonyou.util.wsystem.service.ORG;

/**
 * 
* @ClassName: BaseController 
* @Description: 基础controller类,可处理基本CRUD操作，如存在其他业务操作可继承后重写方法
* @author 博超
* @date 2016年12月27日 
*
 */
@RestController
@RequestMapping(value = "/base")
public class BaseController  implements DataSourceCodeConstants{
	
	@Autowired
	protected CacheManager cacheManager;
	
	@Autowired
	protected IBaseDao dcmsDAO;
	
	/**
	* 
	* @Title: init 
	* @Description: 查询数据库记录 
	* @param request
	* @param response
	* @throws Exception
	*/
	@RequestMapping(params = "cmd=init")
	public void init(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String dataSourceCode = request.getParameter("dataSourceCode");
		SqlTableUtil sqlEntity = DataSourceUtil.dataSourceToSQL(dataSourceCode);
		sqlEntity.appendSqlWhere(getQueryCondition(request));
		String limitStr = request.getParameter("limit");
		String offsetStr = request.getParameter("offset");
		int limit = limitStr != null ? Integer.parseInt(limitStr) : 10;
		int offset = offsetStr != null ? Integer.parseInt(offsetStr) : 0;
		List<Map<String, Object>> mapList = dcmsDAO.query(sqlEntity, offset, limit);
		mapList = RmDictReferenceUtil.transByDict(mapList, dataSourceCode);
		System.out.println("#######mapList\t" + mapList);
		int total = 10;
		total = dcmsDAO.queryLength(sqlEntity);
		sqlEntity.clearTableMap();
		System.out.println("#######length\t" + 10);
		// 需要返回的数据有总记录数和行数据
		String json = "{\"total\":" + total + ",\"rows\":" + JsonUtils.object2json(mapList) + "}";
		System.out.println("############" + json);
		
		out.print(json);
		out.flush();
		out.close();
		
	}

	
	@RequestMapping(params = "cmd=uploadCor") 
	public void uploadCor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}
	
	@RequestMapping(params = "cmd=jsload") 
	public void jsload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nodeCode = request.getParameter("nodeCode");
		if(null==nodeCode || "".equals(nodeCode)){
			
	}
		String roleCode = request.getParameter("roleCode");
		if(null==roleCode || "".equals(roleCode)){
			
			
		}

		 PrintWriter out;
			try {
				String jsstr =" function loadjsTest(){ "
						+ "alert('11111');\r\n"
						+ "alert('22222');\r\n"
						+ "alert('33333');\r\n"
						+ "}";
				out = response.getWriter();
				response.setContentType("application/json;charset=UTF-8");   
				out.write(jsstr.toString());
			    out.flush();	                  
			    out.close(); 
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  
			
	}
	@RequestMapping(params = "cmd=loadmenu") 
	public void loadmenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String rolecode = request.getParameter("rolecode");
//		if(null==rolecode || "".equals(rolecode)){
//			return ;
//	    }
		  HashMap map = new HashMap();
	        map.put(ORG.USER.HEAD_SERVICE_CODE, "001005001#001");
	        map.put(ORG.USER.HEAD_CUSTOMER, "account");
	        
	        //DefaultIdProvider ss = new DefaultIdProvider();
			
	        //map.put(ORG.USER.HEAD_SERIALNO, ss.generatorID(ORG.USER.HEAD_SERVICE_CODE));
	        map.put(ORG.USER.HEAD_SERIALNO, Thread.currentThread().getName());
	        map.put(ORG.USER.HEAD_REQTIME, DateUtil.getNowTimestamp());
	        map.put(ORG.USER.HEAD_USERID, "1000201100000403588");
	        map.put("COMPANYID", "11");
	        
	        //Map<String, String> user = ORG.USER.sendHTTPForMap(map);
	        Map<String, String> user = ORG.MENU.sendHTTPForMap(map);
	       JSONObject s = JSONObject.fromObject(user.get(ORG.USER.HEAD_DATA).substring(1, user.get(ORG.USER.HEAD_DATA).length()-1));

		 PrintWriter out;
			try {
				String jsstr ="  ["
						+ "	{	text : '<a href=\"#\" onclick=\"javascript:onclickmenu(\'/portal/pages/mainConfig.html\')\">配置</a>',href : '#config',				tags : [ '0' ]			},			{				text : '账户管理',				href : '',				tags : [ '4' ],				nodes : [						{							text : '银行账户管理',							href : '#',							tags : [ '4' ],							nodes : [									{						text : '<a href=\"#\" onclick=\"javascript:onclickmenu(\'/protal/pages/singleTableModify.jsp?pageCode=AM_ACTUAL_ACCNTINFO&pageName=银行账户查询\')\">银行账户查询</a>',	href : '#amActualAccntinfo',									tags : [ '0' ]								}					}		              ]		}";
				out = response.getWriter();
				response.setContentType("application/json;charset=UTF-8");   
				out.write("["+s.toString()+"]");
			    out.flush();	                  
			    out.close(); 
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  
			
	}
	
	
	@RequestMapping(params = "cmd=buttonload") 
	public void buttonload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 PrintWriter out;
			try {
				String buttonstr =" <button id=\"button\" type=\"button\" class=\"btn btn-default\"	onclick=\"loadjsTest()\">动态测试</button>";
				out = response.getWriter();
				response.setContentType("application/json;charset=UTF-8");   
				out.write(buttonstr.toString());
			    out.flush();	                  
			    out.close(); 
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  
			
	}
	/**
	 * 
	* @Title: deletefile 
	* @Description: 删除文件 
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=deletefile") 
	public void deletefile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("deletefile:"+request.getParameter("id"));
		
		 Map<String, Object> fileObject = new HashMap<String, Object>();     
		 
		 HashMap affix = new HashMap();
         affix.put("ID", request.getParameter("id"));
         if(null==request.getParameter("id")||"".equals(request.getParameter("id"))){
        	 fileObject.put("message", "没有获取附件ID！");
             returnUpload(fileObject,response);
             return ;
         }
         
          
         SqlWhereEntity sqlEntity =new SqlWhereEntity();
  		 sqlEntity.putWhere("ID", request.getParameter("id"), WhereEnum.EQUAL_INT);
		 dcmsDAO.delete("RM_AFFIX",sqlEntity);
		
         fileObject.put("message", "删除成功！");
         returnUpload(fileObject,response);
         
	}
	
	/**
	 * 
	* @Title: viewfile 
	* @Description: 查看文件 
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=viewfile") 
	public void viewfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("viewfile:"+request.getParameter("id"));
	}
	
	/**
	 * 
	* @Title: upload 
	* @Description: 上传文件
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=upload") 
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();  
	     ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);  
	     String BS_KEYWORD = request.getParameter("BS_KEYWORD");   
	     String RECORD_ID = request.getParameter("RECORD_ID");   
	     if(null==BS_KEYWORD || "".equals(BS_KEYWORD)){
	    	 Map<String, Object> fileObject = new HashMap<String, Object>();               
             fileObject.put("error", "上传失败！没有获取到业务类型信息（BS_KEYWORD）！");                 
             returnUpload(fileObject,response);
             return ;
	     }
	     if(null==RECORD_ID || "".equals(RECORD_ID)){
	    	 Map<String, Object> fileObject = new HashMap<String, Object>();               
             fileObject.put("error", "上传失败！没有获取到业务唯一标识（RECORD_ID）！");                 
             returnUpload(fileObject,response);
             return ;
	     }
	     
	     String UPLOAD_PATH="d://111";
	     try {  
	            //根据request对象获取所有的文件集合，这里包括input标签输入的值也属于FileInput  
	            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);  
	            Iterator iterator = fileItemList.iterator();  
	            String showFileName = "";  
	            while(iterator.hasNext()){  
	                FileItem fileItem = (FileItem)iterator.next();  
	                if(fileItem.isFormField()){ //是否是表单提交域，可以分区是否上传的附件  
	                    String name = fileItem.getFieldName();  //input标签的name  
	                    String value = fileItem.getString();    //input表单的value  
	                    if("uploadName".equals(name)){  
	                        showFileName = value;  
	                    }  
	                }else{  
	                    String fieldName = fileItem.getFieldName();  //表单提交过来的file input标签中name的属性值  
	                    String fileName = fileItem.getName();  //file input上传的文件名  
	                    String contentType = fileItem.getContentType();  //获得上传文件的类型  
	                    long size = fileItem.getSize();      //上传文件的大小  
	             
	                    String filePath = UPLOAD_PATH+"/"+DateUtil.getNowDate()+"/"+BS_KEYWORD+"/"+RECORD_ID;  
	                    
	                    File direct = new File(filePath); 
	                    if(!direct.exists()){
	                    	direct.mkdirs();
	                    }
	                    
	                    File saveFile = new File(filePath+"/"+fileName);  
	                      
	                    fileItem.write(saveFile); //将文件写入磁盘中  
	                    //成功之后 简单输出一下  
	                    if("CommonBussinessDataImport".equals(BS_KEYWORD)){
	                    	if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
	                    		Map<String, Object> fileObject = new HashMap<String, Object>();               
	                            fileObject.put("error", "上传失败！请上传xls或xlsx格式的文件！");                 
	                            returnUpload(fileObject,response);
	                            return ;
	                    	}
	                    	
	                    	ConcurrentHashMap<String, List<Map<String, String>>> data = ExcelTools.parse(new FileInputStream(filePath+"/"+fileName));
	                    	for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
	                    		//StringToObjUtil.stringToObjForListMap(tempresult.get(strkey), MetaDataUtil.getFiledNameMap(strkey))
	                    		dcmsDAO.insert(entry.getKey(), StringToObjUtil.stringToObjForListMap2(data.get(entry.getKey()), MetaDataUtil.getMetaDataFields(entry.getKey())));
	                    	}
	                    	
	                    }
	                    
	                    fileItem=null;
	                    //写入数据库
	                    HashMap affix = new HashMap();
	                    affix.put("BS_KEYWORD", BS_KEYWORD); 
	                    affix.put("RECORD_ID", RECORD_ID);
	                    affix.put("TITLE", "");
	                    affix.put("OLD_NAME", fileName);
	                    affix.put("SAVE_NAME", filePath);
	                    affix.put("SAVE_SIZE", size);
	                    affix.put("MIME_TYPE", contentType);
	                    affix.put("ENCODING", "");
	                    affix.put("USABLE_STATUS", "1");  
	                    affix.put("ORDER_NUMBER", "1");
	                    
	                	String busId = dcmsDAO.insert("RM_AFFIX",affix);
	                	
	                    Map<String, Object> fileObject = new HashMap<String, Object>();
	                    fileObject.put("size", size);//原始文件大小
	                    fileObject.put("original", "original");//原始文件唯一标识
	                    fileObject.put("originalPath", "originalPath");//原始文件临时存储目录
	                    fileObject.put("thumb", "thumb");//图片的预览文件唯一标识
	                    //fileObject.put("thumbPath", "thumbPath");//图片预览文件临时存储目录
	                    fileObject.put("name", fileName);//原始图片名称
	                    //fileObject.put("url", "url");//原始图片的web查看地址,这个可以设置img.src属性
	                    fileObject.put("thumbnailUrl", "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/metaData?cmd=viewfile&id="+busId);//预览图片的web查看地址
	                    fileObject.put("contentType", "contentType");//上传文件type
	                    fileObject.put("deleteType", "POST");//这是我自己封装的post删除
	                    //这个是我自己封装的删除路径
	                    fileObject.put("deleteUrl", request.getContextPath()+"/metaData?cmd=deletefile&id="+busId);
	                    fileObject.put("message", "上传成功！");
	                    returnUpload(fileObject,response);
	                }  
	            }  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        	 Map<String, Object> fileObject = new HashMap<String, Object>();               
                 fileObject.put("error", "上传失败！"+e.getMessage());                 
                 returnUpload(fileObject,response);
	        }  
	
		
	}
	
	/**
	 * 
	* @Title: returnUpload 
	* @Description: 上传文件后回传状态信息给前台
	* @param fileObject
	* @param response
	 */
	public void returnUpload(Map<String, Object> fileObject,HttpServletResponse response){
		
		Map[] fileArray = new HashMap[1];
        fileArray[0] = fileObject;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("files", JSONArray.fromObject(fileArray));
        //HttpServletResponse response = getResponse();
        PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");   
			out.write(jsonObject.toString());
		    out.flush();	                  
		    out.close(); 
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
                
       
	}

	/**
	 * 
	* @Title: queryColumns 
	* @Description: 初始化列布局
	* @param request
	* @param response
	* @throws IOException
	* @throws BussnissException
	 */
	@RequestMapping(params = "cmd=queryColumns")
	public void queryColumns(HttpServletRequest request,HttpServletResponse response) throws IOException, BussnissException {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String dataSourceCode = request.getParameter("dataSourceCode");
		SqlTableUtil sqlEntity = DataSourceUtil.dataSourceToSQL(dataSourceCode);
		String jsonMessage = IBootUtil.findColJosnStr(IBootUtil.findShowFiledNameMap(sqlEntity.getShowFiledMap(),sqlEntity.getFiledNameMap()));
		System.out.println("####   " + jsonMessage);
		
		out.print(jsonMessage);
		out.flush();
		out.close();

	}
	
	/**
	 * 
	* @Title: queryParam 
	* @Description: 初始化页面查询条件
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=queryParam")
	public void queryParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String dataSourceCode = request.getParameter("dataSourceCode");
		String paramHtml = PageUtil.findPageHtml(dataSourceCode, PageUtil.PAGE_TYPE_FOR_SELECT);;
		
		out.print(paramHtml);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	* @Title: queryMaintainCols 
	* @Description: 初始化维护页面
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=queryMaintainCols")
	public void queryMaintainCols(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String dataSourceCode = request.getParameter("dataSourceCode");
		String maintainHtml = PageUtil.findPageHtml(dataSourceCode, PageUtil.PAGE_TYPE_FOR_INSERT_UPDATE);
		
		out.print(maintainHtml);
		out.flush();
		out.close();

	}
	
	/**
	 * 
	* @Title: insRow 
	* @Description: 新增行 
	* @param request
	* @param response
	* @throws IOException
	 */
	@RequestMapping(params = "cmd=insRow")
	public void insRow(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String tabName = request.getParameter("tabName");
		
		JSONObject json = JSONObject.fromObject(request.getParameter("jsonData"));
//		Map<String,Object> entity = json;
		try {
			tabName=this.findTableName(tabName, request);
			dcmsDAO.insertByTransfrom(tabName, json);
		} catch (BussnissException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		String jsonMessage = "{\"message\":\"保存成功\"}";
		out.print(jsonMessage);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	* @Title: delRows 
	* @Description: 删除行-支持多行 
	* @param request
	* @param response
	* @throws IOException
	* @throws BussnissException
	 */
	@RequestMapping(params = "cmd=delRows")
	public void delRows(HttpServletRequest request,HttpServletResponse response) throws IOException, BussnissException {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String tabName = request.getParameter("tabName");
		JSONArray jsonArray = JSONArray.fromObject(request.getParameter("jsonData"));
		List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jb = (JSONObject) jsonArray.get(i);
			String id = jb.getString("ID");
			Map<String, Object> entity = new HashMap<String, Object>();
			entity.put("ID", id);
			entity.put("DR","1");
			entityList.add(entity);
		}
		tabName=this.findTableName(tabName, request);
 		dcmsDAO.update(tabName, entityList, new SqlWhereEntity());
		
		String jsonMessage = "{\"message\":\"删除成功\"}";
		out.print(jsonMessage);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	* @Title: editRow 
	* @Description: 修改行 
	* @param request
	* @param response
	* @throws IOException
	 */
	@RequestMapping(params = "cmd=editRow")
	public void editRow(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		System.out.println("get uuid:"+IDGenerator.generateObjectID("MYMODULE"));
		
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
	
		String tabName = request.getParameter("tabName");
		JSONObject json = JSONObject.fromObject(request.getParameter("jsonData"));
		try {
			tabName=this.findTableName(tabName, request);
			dcmsDAO.updateByTransfrom(tabName, json, new SqlWhereEntity());
		} catch (BussnissException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		String jsonMessage = "{\"message\":\"修改成功\"}";
		out.print(jsonMessage);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	* @Title: getQueryCondition 
	* @Description: 依据规范，由request获取所有查询条件，并拼入SqlTableUtil实例。页面查询条件id命名规范：SEARCH-(比较符)-(字段名)【等号使用%3D转义】
	* @param request
	* @return
	* @throws BussnissException
	 */
	public SqlWhereEntity getQueryCondition(HttpServletRequest request) throws BussnissException {
		
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "SEARCH-");

		SqlWhereEntity sqlWhere = new SqlWhereEntity();
		for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
			String[] keySplit = entry.getKey().split("-");
			if (keySplit.length == 2) {
				
				String columnName = keySplit[1];
				String compartor = keySplit[0];
				Object value = entry.getValue();
				
				//FIXME 参选回写带来的问题-回传了一个数组
				if(value instanceof String[]){
					value =((String[])value)[0];
				}

				if (value != null && value.toString().length() > 0) {
					//FIXME 暂只考虑了=与like两种情况，且是value为String的情况
					if ("LIKE".equalsIgnoreCase(compartor)) {
						sqlWhere.putWhere(columnName, value.toString(),WhereEnum.ALL_LIKE);
					} else if("DATE".equalsIgnoreCase(compartor)){
						sqlWhere.putWhere(columnName, value.toString(),WhereEnum.TO_DATE_EQUAL);
					}else {
						sqlWhere.putWhere(columnName, value.toString(),WhereEnum.EQUAL_STRING);
					}
				}

			} else {
				throw new BussnissException("前台查询字段ID设置不符合规范");
			}
		}
		return sqlWhere;
	}
	
	/**
	 * 
	* @Title: cacheClear 
	* @Description: 服务器缓存清理 
	* @param request
	* @param response
	* @throws IOException
	 */
	@RequestMapping(params = "cmd=cacheClear")
	public void cacheClear(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
	
		int cacheEnum = Integer.parseInt(request.getParameter("cacheEnum"));
		String cacheName = "";
		String messageTemp = "清理成功!";
		switch (cacheEnum) {
		case 0:
			cacheName = "所有缓存";
			DataSourceUtil.clear();
			MetaDataUtil.clear();
			RmDictReferenceUtil.clear();
			WorkflowNodeUtil.clear();
			break;
		case 1:
			cacheName = "数据源缓存";
			DataSourceUtil.clear();
			break;
		case 2:
			cacheName = "元数据缓存";
			MetaDataUtil.clear();
			break;
		case 3:
			cacheName = "数据字典缓存";
			RmDictReferenceUtil.clear();
			break;
		case 4:
			cacheName = "流程节点缓存";
			WorkflowNodeUtil.clear();
			break;
		case 5:
			cacheName = "稽核规则缓存";
			break;
		default:
			messageTemp = "ERROR：未知缓存";
			break;
		}
		
		String jsonMessage = "{\"message\":\""+cacheName+ messageTemp +"\"}";
		out.print(jsonMessage);
		out.flush();
		out.close();
	}
	
	private String findTableName(String tabName,HttpServletRequest request) throws BussnissException{
		String tableName=tabName;
		String dataSourceCode =request.getParameter("dataSourceCode");
		if(dataSourceCode!=null&&dataSourceCode.length()>0){
			Map<String,String> dataMap=DataSourceUtil.getDataSource(dataSourceCode);
			if(dataMap!=null){
				tableName=dataMap.get(DataSourceUtil.DATASOURCE_METADATA_CODE);
			}
		}
		return tableName;
	}
	
	/**
	* 
	* @Title: init 
	* @Description: 查询数据库记录 
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping(params = "cmd=findMenu")
	public void findMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String token =request.getParameter("token");
		String type  =request.getParameter("type");
		TokenEntity tokenEntity =cacheManager.get(SoaProcessing.findCacheKeyByToken(token));
		String companyid =request.getParameter("companyid");
		String companyname =request.getParameter("companyname");
		String roleid =request.getParameter("roleid");
		String companycode="";
		String tabString = request.getParameter("tab");
		String key = "I_001005001#001"+tokenEntity.USER.getId()+tokenEntity.COMPANY.getCompany_id();
		if(tabString.equals("1")){
			cacheManager.removeCache(key);
		}
		if(companyid.indexOf(COMPANY_MARK)!=-1){
			String[] companys =companyid.split(COMPANY_MARK);
			if(companys.length>1){
				companyid=companys[0];
				companycode=companys[1];
			}
		}
		tokenEntity.COMPANY.setCompany_id(companyid);
		tokenEntity.COMPANY.setCompany_name(companyname);
		tokenEntity.COMPANY.setCompany_code(companycode);
		tokenEntity.ROLE.setRoleId(roleid);
		String json="";
		String lableHtml ="";
		Map<String,String> map =SoaProcessing.soaPortalMenu(tokenEntity);
		ConcurrentHashMap<String,List<MenuEntity>> menuData=null;
		if("000000".equals(map.get(ORG.USER.HEAD_RETCODE))){
        	 JSONArray jsonArray  =JSONArray.fromObject(map.get(ORG.USER.HEAD_DATA));
        	 List<Map<String,String>> list =(List<Map<String,String>>)jsonArray;
        	 menuData=TreeUtil.bulidMenu(list);
        	 lableHtml=BulidMenuLableFactory.getBulidMenuLable(type).bulidMenuLableForA(menuData,TreeUtil.HEAD_PARENT_CODE);
        }
		// 需要返回的数据有总记录数和行数据
		System.out.println("############" + lableHtml);
		String returnJson="{\"message\":\""+lableHtml+"\"}";
		cacheManager.set(SoaProcessing.findCacheKeyByToken(token), tokenEntity);
		cacheManager.set(SoaProcessing.findCacheKeyByMenuToken(token), menuData);
		out.print(returnJson);
		
		out.flush();
		out.close();
		
	}
	
	@RequestMapping(params = "cmd=findNode")
	public void findNode(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String token =request.getParameter("token");
		String totalCode =request.getParameter("totalCode");
		String type  =request.getParameter("type");
		ConcurrentHashMap<String,List<MenuEntity>> menuData =cacheManager.get(SoaProcessing.findCacheKeyByMenuToken(token));
		String lableHtml =BulidMenuLableFactory.getBulidMenuLable(type).bulidMenuByMenuCode(menuData, totalCode, "");
		// 需要返回的数据有总记录数和行数据
		System.out.println("############" + lableHtml);
		String returnJson=lableHtml;
		out.print(returnJson);
		
		out.flush();
		out.close();
	}
	
	@RequestMapping(params = "cmd=findTheme")
	public void findTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String token =request.getParameter("token");
		TokenEntity tokenEntity =cacheManager.get(SoaProcessing.findCacheKeyByToken(token));
		String themeCode =request.getParameter("themeCode");
		tokenEntity.setTheme(themeCode);
		cacheManager.set(SoaProcessing.findCacheKeyByToken(token), tokenEntity);
		out.print("1");
		out.flush();
		out.close();
	}
	
	//查询是否修改过密码
	@RequestMapping(params = "cmd=selectUpdPwd")
	@ResponseBody
	public JSONObject selectUpdPwd(HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		String sql="select IS_UPD_PASSWORD from rm_user where ID='"+request.getParameter("user_id")+"'";
		List<Map<String, Object>> list = dcmsDAO.query(sql, "");
		
		jsonObject.put("IS_UPD_PASSWORD", list.get(0).get("IS_UPD_PASSWORD"));
		return jsonObject;
	}
	
	/**
	 * 根据用户判断进入哪个登陆入口
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getLoginPath")
	public String getLoginPath(String userId){
		String loginPath = "";
		String userSql = "SELECT * FROM rm_user WHERE id = "+userId;		
		List<Map<String, Object>> userList = BaseDao.getBaseDao().query(userSql, "");
		if(userList.size() > 0){
			String adminType = userList.get(0).get("ADMIN_TYPE")!=null?userList.get(0).get("ADMIN_TYPE").toString():"";
			if("2".equals(adminType)){//管理员
				loginPath = "managerLogin"; 
			}else if("1".equals(adminType)){//普通用户
				loginPath = "login";
			}
		}
		return loginPath;
	}
	
}
