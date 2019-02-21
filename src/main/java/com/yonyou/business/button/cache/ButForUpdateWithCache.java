package com.yonyou.business.button.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.MetaDataUtil;
import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.business.button.util.ButForUpdate;
import com.yonyou.util.jdbc.IBaseDao;

public class ButForUpdateWithCache extends ButForUpdate {

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {

		String dataSourceCode = request.getParameter("dataSourceCode");
		if ("CD_DATASOURCE".equals(dataSourceCode))
			DataSourceUtil.clear();
		else if ("CD_METADATA".equals(dataSourceCode) || "CD_METADATA_DETAIL".equals(dataSourceCode))
			MetaDataUtil.clear();
		else if ("RM_CODE_DATA".equals(dataSourceCode) || "RM_CODE_TYPE".equals(dataSourceCode))
			RmDictReferenceUtil.clear();

	}

}
