package com.yonyou.business.button.account.open;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONObject;

import com.yonyou.business.button.account.AccountButtonUtil;
import com.yonyou.util.BussnissException;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.sql.SqlWhereEntity;

/**银行账户开户申请修改
 * @author XIANGJIAN
 * @date 创建时间：2017年2月14日
 * @version 1.0
 */
public class AccountOpenButForUpdate extends AccountButtonUtil {

	@Override
	protected boolean befortOnClick(IBaseDao dcmsDAO,
			HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

	@Override
	protected Object execute(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) throws IOException, BussnissException {
		String dataSourceCode =request.getParameter("dataSourceCode");
		String tabName = findTableNameByDataSourceCode(dataSourceCode);
		JSONObject json = JSONObject.fromObject(request.getParameter("jsonData"));
		dcmsDAO.update(tabName, json, new SqlWhereEntity());
		
		String jsonMessage = "{\"message\":\"修改成功\"}";
		this.ajaxWrite(jsonMessage, request, response);
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {

	}

}
