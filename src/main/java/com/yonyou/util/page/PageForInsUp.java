package com.yonyou.util.page;

import java.util.Map;

import com.yonyou.util.page.util.SingleQueryFrameUtil;

/**
 * 新增/修改页面 html
 * @author moing_ink
 * <p>创建时间 ： 2016年12月27日
 * @version 1.0
 */
public class PageForInsUp  extends PageForHtml{

	@Override
	public int getRows(int _rows) {
		// TODO 自动生成的方法存根
		if(_rows>0){
			return _rows;
		}else{
			return 2;
		}
	}

	@Override
	protected String findId(String filed_code,String input_type) {
		// TODO 自动生成的方法存根
		return filed_code;
	}

	@Override
	public String appendHtml(Map<String,Object> dataMap,int row,int col) {
		// TODO 自动生成的方法存根
		String idVal="";
		if(dataMap!=null&&dataMap.containsKey("ID")){
			idVal = dataMap.get("ID").toString();
		}
		return "<input type='hidden' id='ID' value='"+idVal+"'/>";
	}

	@Override
	public int findMarginTopPx() {
		// TODO 自动生成的方法存根
		return 10;
	}

	@Override
	protected String findInputType(String inputType) {
		// TODO 自动生成的方法存根
		if(inputType!=null&&inputType.length()>0&&inputType.endsWith(SingleQueryFrameUtil.TYPE_DATE_SECTION)){
			return SingleQueryFrameUtil.TYPE_DATE;
		}else{
			return inputType;
		}
	}

	@Override
	protected String findPageType() {
		// TODO 自动生成的方法存根
		return PageUtil.PAGE_TYPE_FOR_INSERT_UPDATE;
	}

	@Override
	protected void appendParamMap(Map<String, String> paramMap) {
		// TODO 自动生成的方法存根
		
	}


}
