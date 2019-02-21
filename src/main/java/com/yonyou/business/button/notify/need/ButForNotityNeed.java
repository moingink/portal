package com.yonyou.business.button.notify.need;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.business.button.ButtonAbs;
import com.yonyou.util.BussnissException;
import com.yonyou.util.JsonUtils;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.sql.SqlTableUtil;

public class ButForNotityNeed  extends ButtonAbs{

	@Override
	protected boolean befortOnClick(IBaseDao dcmsDAO,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	protected Object execute(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) throws IOException, BussnissException {
		// TODO 自动生成的方法存根
		String dataSourceCode = "WF_NEED_HANDLE";
		SqlTableUtil sqlEntity = DataSourceUtil.dataSourceToSQL(dataSourceCode);
		String pageParam=this.findPageParam(request);
		if(!TOKEN_ENTITY.isEmpty()){
			sqlEntity.appendWhereAnd("NEED_USER_ID ='"+TOKEN_ENTITY.USER.getId()+"'");
		}
		if(pageParam!=null&&pageParam.length()>0){
			sqlEntity.appendWhereAnd(pageParam);
		}
		String limitStr = request.getParameter("limit");
		String offsetStr = request.getParameter("offset");
		int limit = limitStr != null ? Integer.parseInt(limitStr) : 5;
		int offset = offsetStr != null ? Integer.parseInt(offsetStr) : 0;
		List<Map<String, Object>> mapList = dcmsDAO.query(sqlEntity, offset, limit);
		mapList = RmDictReferenceUtil.transByDict(mapList, dataSourceCode);
		sqlEntity.clearTableMap();
		// 需要返回的数据有总记录数和行数据
		/*String json = "{\"message\":" + JsonUtils.object2json(mapList) + "}";*/
		JSONObject json =new JSONObject();
		json.put("message", JsonUtils.object2json(mapList));
		System.out.println(json.toString());
		this.ajaxWrite(json.toString(), request, response);
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO 自动生成的方法存根
		
	}

}
