package com.yonyou.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.business.button.ButtonAbs;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.BussnissException;
import com.yonyou.util.JsonUtils;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.log.BusLogIdByThread;
import com.yonyou.util.log.BusLogger;
import com.yonyou.util.page.PageUtil;
import com.yonyou.util.sql.SQLUtil.WhereEnum;
import com.yonyou.util.sql.SqlTableUtil;
import com.yonyou.util.sql.SqlWhereEntity;

@RestController
@RequestMapping(value = "/button")
public class ButtonController {
	
	
	@Autowired
	protected IBaseDao dcmsDAO;
	
	protected Map<String,ButtonAbs> findButtonMap(){
		return new HashMap<String,ButtonAbs>();
	}
	
	@RequestMapping(params = "cmd=button")
	public void button(HttpServletRequest request, HttpServletResponse response)  {
		String buttonToken =request.getParameter("buttonToken");
		TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
		if("delete".equalsIgnoreCase(buttonToken) || "update".equalsIgnoreCase(buttonToken)){
			HashMap<String, String> map = new HashMap<>();		
			map.put(BusLogger.LOG_ACTION_TYPE, "no_public_button_log");
			map.put(BusLogger.LOG_USER_ID, tokenEntity.USER.getId());
			map.put(BusLogger.LOG_USER_ID_NAME, tokenEntity.USER.getName());
			map.put(BusLogger.LOG_OWNER_ORG_ID, tokenEntity.COMPANY.getCompany_id());
			map.put(BusLogger.LOG_CONTENT, "URL:"+request.getRequestURL());
			map.put(BusLogger.LOG_OPERATION_INFO, buttonToken);		
			map.put(BusLogger.LOG_ACTION_MODULE, request.getParameter("dataSourceCode"));	
					
	        BusLogger.record_log(map);
		}
		buttonToken = buttonToken.split("_operation")[0];
		if(findButtonMap().containsKey(buttonToken)){
			try {
				findButtonMap().get(buttonToken).onClick(dcmsDAO, request, response);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (BussnissException e) {
				// TODO 自动生成的 catch 块
				this.ajaxWrite(e.getMessage(), request, response);
				e.printStackTrace();
				
			}
		}
		//记录按钮操作信息
		String action_module="1";
		//按钮URL
		String url=request.getLocalAddr();
		//拿按钮名字 buttonToken
		BusLogger.record_log(action_module,url, buttonToken,request, response);
		System.err.println("----------->url="+url);
	}
	
	//记录菜单
		@RequestMapping(params = "cmd=menu")
		public void Ajax_url(HttpServletRequest request, HttpServletResponse response) throws Exception {
			BusLogIdByThread.writeBusLogId(request.getRemoteHost());
			
			BusLogIdByThread.writeBusLogId(request.getRemoteHost());
			response.setCharacterEncoding("utf-8");
			
			String action_module="0";//菜单状态
			String url=request.getParameter("url");//菜单url
			String menuName =request.getParameter("menuName");
			//添加菜单信息
			BusLogger.record_log(action_module,url,menuName, request, response);
			
		}
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
		String paramHtml = PageUtil.findPageHtml(dataSourceCode, PageUtil.PAGE_TYPE_FOR_SELECT);
		
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
		String maintainHtml =PageUtil.findPageHtml(dataSourceCode, PageUtil.PAGE_TYPE_FOR_INSERT_UPDATE);
		
		out.print(maintainHtml);
		out.flush();
		out.close();

	}
	
	protected void ajaxWrite(String ajaxMessage,HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		PrintWriter out =null;
		try {
			out = response.getWriter();
			out.print(ajaxMessage);
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally{
			
			out.flush();
			out.close();
		}
		
	}
	
	
}
