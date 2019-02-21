package com.yonyou.business.button.account.open;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.yonyou.util.IdRmrnUtil;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.sql.SqlTableUtil;
import com.yonyou.util.sql.SqlWhereEntity;
import com.yonyou.util.sql.SQLUtil.WhereEnum;

/**银行账户开户申请提交
 * @author XIANGJIAN
 * @date 创建时间：2017年2月14日
 * @version 1.0
 */
public class AccountOpenButForSubmit extends AccountButtonUtil {

	@Override
	protected boolean befortOnClick(IBaseDao dcmsDAO,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String jsonMessage = "{\"message\":\"提交成功\"}";
		SqlTableUtil sqlTable =new SqlTableUtil("AM_ACTUAL_ACCNTOPEN","");
		String result = null ;
		String reason = null ;
		SqlWhereEntity whereEntity  = new SqlWhereEntity();
		JSONArray jsonArray = JSONArray.fromObject(request.getParameter("jsonData"));	//获取json数组
		Map<String, Object> rmrn = IdRmrnUtil.getIdRmrnMap(jsonArray);
		whereEntity.putWhere("ID", (String)rmrn.get("IDS"), WhereEnum.IN)
		.putWhere("DR", "0", WhereEnum.EQUAL_STRING);
		sqlTable.appendSelFiled("*").appendOrderBy("ID", "DESC").appendSqlWhere(whereEntity);
		List<Map<String, Object>> lists = dcmsDAO.query(sqlTable);
		try{
			result = IdRmrnUtil.getNoPassRmrn("BILL_STATUS", new String[]{"2","3","4","7"}, lists,jsonArray) ;
			if(!"".equals(result)){
				reason = "原因：单据状态为已保存的才可进行提交" ;
				jsonMessage = "{\"message\":\""+IdRmrnUtil.writeErrorInfo(result,reason)+"\"}";
				return true;
			}
			
			result = IdRmrnUtil.getNoPassRmrn("BILL_STATUS", "1", lists,jsonArray) ;
			if(!"".equals(result)){
				reason = "原因：已提交数据不能再次提交" ;
				jsonMessage = "{\"message\":\""+IdRmrnUtil.writeErrorInfo(result,reason)+"\"}";
				return true;
			}
		}finally{
			out.print(jsonMessage);
			out.flush();
			out.close();
		}
		return false;
	}

	@Override
	protected Object execute(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) throws IOException, BussnissException {
		String jsonMessage = "{\"message\":\"提交成功\"}";
		
		String tabName = "AM_ACTUAL_ACCNTOPEN";		//表名
		JSONArray jsonArray = JSONArray.fromObject(request.getParameter("jsonData"));	//获取json数组
		List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>();	
		int len =  jsonArray.size();			
		for (int i = 0; i < len; i++) {
			JSONObject jb = (JSONObject) jsonArray.get(i);
			String id = jb.getString("ID");
			Map<String, Object> entity = new HashMap<String, Object>();
			entity.put("ID", id);
			entity.put("BILL_ID", "1" );
			entity.put("BILL_STATUS",  "1");
			entityList.add(entity);
		}
		dcmsDAO.update(tabName, entityList, new SqlWhereEntity());
		this.ajaxWrite(jsonMessage, request, response);
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {

	}
	//老框架的提交代码实现↓
	/*String pk = request.getParameter(REQUEST_ID);//开户申请单主键
	String billId = request.getParameter("bill_id");//开户申请单单据号
	String billStatus = request.getParameter("bill_status");//申请单状态
	AmActualAccntopenVo bean = getService().find(request.getParameter(REQUEST_ID));  //通过id获取vo
	BpmVariableVo bpmVo = new BpmVariableVo();
	bpmVo.setRecord_id(pk);//单据id
	bpmVo.setBill_num(billId);//单据号
	bpmVo.setBill_type(IBillType.OPEN_ACTUAL_ACCOUNT);//单据类型
	bpmVo.setBs_status(billStatus);//单据状态
	if(IAccntType.INDIVIDUAL.equals(bean.getAccnt_type())){//如果是个人存折账户
		bpmVo.setBill_desc(bean.getDef16()+" "+bean.getBank_typename()+" 个人存折账户");
	}else{
		bpmVo.setBill_desc(bean.getDef16()+" "+bean.getBank_typename());//单据描述 省份+银行类别
	}
	
	String returnFlag = RM_NO;*/
}
