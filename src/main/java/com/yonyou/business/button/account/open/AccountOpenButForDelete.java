package com.yonyou.business.button.account.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONArray;
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
public class AccountOpenButForDelete extends AccountButtonUtil {

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
 		dcmsDAO.update(tabName, entityList, new SqlWhereEntity());
		
		String jsonMessage = "{\"message\":\"删除成功\"}";
		
		this.ajaxWrite(jsonMessage, request, response);
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {

	}

}
