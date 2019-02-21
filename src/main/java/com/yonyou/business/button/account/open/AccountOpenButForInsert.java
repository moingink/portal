package com.yonyou.business.button.account.open;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.button.account.AccountButtonUtil;
import com.yonyou.util.BussnissException;
import com.yonyou.util.jdbc.IBaseDao;

/**银行账户开户搜索器
 * @author XIANGJIAN
 * @date 创建时间：2017年2月14日
 * @version 1.0
 */
public class AccountOpenButForInsert extends AccountButtonUtil {

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
		if(dataSourceCode!=null&&dataSourceCode.length()>0){
			Map<String,String> dataMap=DataSourceUtil.getDataSource(dataSourceCode);
			if(dataMap!=null){
				tabName=dataMap.get(DataSourceUtil.DATASOURCE_METADATA_CODE);
			}
		}//def16来存放省份
		JSONObject json = JSONObject.fromObject(request.getParameter("jsonData"));
		java.util.Date utilDate =new Date(System.currentTimeMillis());			//获取当前时间，包括年月日时分秒
		java.sql.Timestamp  sqlDate=new java.sql.Timestamp (utilDate.getTime());	//util.Date转换成sql.Timestamp存入数据库
		json.put("BILL_STATUS", "0");		//默认设置新增单据为保存状态
		json.put("VERSION", "1");
		json.put("BILL_DATE", getSdf().format(sqlDate));
		try {
			dcmsDAO.insertByTransfrom(tabName, json);
		} catch (BussnissException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String jsonMessage = "{\"message\":\"保存成功\"}";
		this.ajaxWrite(jsonMessage, request, response);
		
		return null;
	}

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {
	}

	
	/*AmActualAccntopenVo vo = new AmActualAccntopenVo();
    RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
    RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
    
	RmUserVo userVo = RmProjectHelper.getRmUserVo(request);//获得当前登录用户信息
    vo.setBill_id(ReceiptcodeManager.getLastReceiptCode(IBillType.OPEN_ACTUAL_ACCOUNT, userVo.getCompany().getOu()));//单据号
    vo.setBill_status(IBillState.SAVE);//申请单保存时，单据状态为已保存
    vo.setBill_date(RmDateHelper.getSysTimestamp());//单据日期
    String site = getService().findProv(userVo.getCompany().getSite());
    if(S.isNull(site) && "121001".equals(userVo.getCompany().getOu_id())){ //如果是集团总部
    	site = "集团总部";
    }
    vo.setDef16(site);//def16来存放省份
    //添加账号归集类型 IAccntAccumulateType
    if(!S.isNull(vo.getParent_accnt_num())&&RM_YES.equals(vo.getIs_accumulated())){//如果是自动归集而且有上级账号
    	vo.setDef10(S.getBean(IFtsAccRelationshipService.class).getActAccumulateType(vo.getParent_accnt_num())); //存入账号归集类型
    }
    String id = getService().insert(vo);  //插入单条记录
    request.setAttribute(IGlobalConstants.INSERT_FORM_ID, id);  //新增记录的id放入request属性
    Map<String, Object> mNeedValue = new RmSequenceMap<String, Object>();
    mNeedValue.put(REQUEST_ID, id);
    mNeedValue.put("request_from_page", "add");
    return RmJspHelper.rebuildForward(mapping.findForward("toDetail"),mNeedValue);*/
	
}
