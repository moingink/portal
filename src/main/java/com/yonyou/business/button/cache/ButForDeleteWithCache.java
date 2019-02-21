package com.yonyou.business.button.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.business.DataSourceUtil;
import com.yonyou.business.MetaDataUtil;
import com.yonyou.business.RmDictReferenceUtil;
import com.yonyou.business.button.util.ButForDelete;
import com.yonyou.util.jdbc.IBaseDao;

public class ButForDeleteWithCache extends ButForDelete {

	@Override
	protected void afterOnClick(IBaseDao dcmsDAO, HttpServletRequest request,
			HttpServletResponse response) {

		String dataSourceCode = request.getParameter("dataSourceCode");
		if ("CD_DATASOURCE" == dataSourceCode)
			DataSourceUtil.clear();
		else if ("CD_METADATA" == dataSourceCode || "CD_METADATA_DETAIL" == dataSourceCode)
			MetaDataUtil.clear();
		else if ("RM_CODE_DATA" == dataSourceCode || "RM_CODE_TYPE" == dataSourceCode)
			RmDictReferenceUtil.clear();

	}

}
