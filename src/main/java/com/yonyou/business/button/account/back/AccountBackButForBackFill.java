package com.yonyou.business.button.account.back;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.yonyou.business.button.account.AccountButtonUtil;
import com.yonyou.util.BussnissException;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.sql.SqlWhereEntity;
import com.yonyou.util.wsystem.service.ORG;
/**银行账户开户回填---回填
 * @author XIANGJIAN
 * @date 创建时间：2017年2月16日
 * @version 1.0
 */
public class AccountBackButForBackFill extends AccountButtonUtil {

	@Override
	protected boolean befortOnClick(IBaseDao dcmsDAO,
			HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) throws IOException, BussnissException {
		String dataSourceCode =request.getParameter("dataSourceCode");
		String tabName = findTableNameByDataSourceCode(dataSourceCode);
		JSONObject json = JSONObject.fromObject(request.getParameter("jsonData"));
		java.util.Date utilDate =new Date(System.currentTimeMillis());			//获取当前时间，包括年月日时分秒
		java.sql.Timestamp  sqlDate=new java.sql.Timestamp (utilDate.getTime());	//util.Date转换成sql.Timestamp存入数据库
		json.put("BACKFILL_PERSON", (String)ORG.USER.sendHTTP("001").get("NAME"));		//回填人默认当前登录人
		json.put("BACKFILL_DATE", getSdf().format(sqlDate));				//回填日期默认当前系统时间
		json.put("BACKFILL_STATUS", "6");							//回填状态改为已回填
		dcmsDAO.updateByTransfrom(tabName, json, new SqlWhereEntity());
		
		String jsonMessage = "{\"message\":\"回填成功\"}";
		this.ajaxWrite(jsonMessage, request, response);
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {
	}

}
