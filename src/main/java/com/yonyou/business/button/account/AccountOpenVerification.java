package com.yonyou.business.button.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.util.IdRmrnUtil;
import com.yonyou.util.jdbc.IBaseDao;
import com.yonyou.util.sql.SqlTableUtil;
import com.yonyou.util.sql.SqlWhereEntity;
import com.yonyou.util.sql.SQLUtil.WhereEnum;

/**银行账户验证信息
 * @author XIANGJIAN
 * @date 创建时间：2017年2月14日
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/AOVerification")
public class AccountOpenVerification {
	
	@Autowired
	protected IBaseDao dcmsDAO;
	
	@RequestMapping(params = "cmd=AOUpdateVerification")
	public void AOUpdateVerification(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//提交后的单据不允许修改和删除操作！
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				out = response.getWriter();
				String jsonMessage = "{\"message\":\"0\"}";
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
					result = IdRmrnUtil.getNoPassRmrn("BILL_STATUS", new String[]{"1","2","3","4","7"}, lists,jsonArray) ;
					if(!"".equals(result)){
						reason = "原因：已提交的单据不允许修改！" ;
						jsonMessage = "{\"message\":\""+IdRmrnUtil.writeErrorInfo(result,reason)+"\"}";
					}
				}finally{
					out.print(jsonMessage);
					out.flush();
					out.close();
				}
	}
	
	
	@RequestMapping(params = "cmd=AODeleteVerification")
	public void AODeleteVerification(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//目前仅支持单据状态为已保存和审批退回的才可进行删除。单据删除后单据上的附件同时也被删除。
				response.setCharacterEncoding("utf-8");
				PrintWriter out = null;
				out = response.getWriter();
				String jsonMessage = "{\"message\":\"0\"}";
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
					result = IdRmrnUtil.getNoPassRmrn("BILL_STATUS", new String[]{"1","2","3","4"}, lists,jsonArray) ;
					if(!"".equals(result)){
						reason = "原因：单据状态为已保存和审批退回的才可进行删除！" ;
						jsonMessage = "{\"message\":\""+IdRmrnUtil.writeErrorInfo(result,reason)+"\"}";
					}
				}finally{
					out.print(jsonMessage);
					out.flush();
					out.close();
				}
	}
	
	
}
