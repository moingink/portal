package com.yonyou.iuap.system.web.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yonyou.business.api.RmPartyConstants;
import com.yonyou.util.SpringContextUtil;
import com.yonyou.util.jdbc.IBaseDao;

public class RoleForTheme{
	
	public IBaseDao getDcmsDAO() {
		return (IBaseDao) SpringContextUtil.getBean("dcmsDAO");
	}

	public List<String> getWorkbenchPic(String userId,String companyId){
		if(userId==""||companyId==""){
			return null;
		}else{
			String sql = "select R.ROLE_CODE as ROLE_CODE from RM_ROLE R join RM_PARTY_ROLE PR on R.ID = PR.ROLE_ID where PR.OWNER_PARTY_ID = '"+userId+"' and ((R.IS_SYSTEM_LEVEL = '1' and ((R.OWNER_ORG_ID IS NULL or r.owner_org_id ='')or((R.OWNER_ORG_ID IS NOT NULL or r.owner_org_id ='') AND EXISTS (SELECT id FROM rm_party_relation rr WHERE rr.party_view_id = '1000200700000000001' AND rr.parent_party_id = R.OWNER_ORG_ID "+RmPartyConstants.recurPartySql(companyId)+")) )) or (r.is_system_level = '0' and R.OWNER_ORG_ID ='"+companyId+"')) and r.Enable_Status='1'";
			String urlSql = "select distinct rm.picture_url as PICTURE_URL from rm_picture_info rm join role_theme_info rt on rm.theme_code = rt.theme_code where rt.dr='0' and rt.role_code in ("+sql+")";
			List<Map<String,Object>> list = getDcmsDAO().query(urlSql, "");
			List<String> newList = new ArrayList<String>();
			if(list.size()<1){
				newList.add("/images/lunbo_new_0.png");
				newList.add("/images/lunbo_new_1.png");
				newList.add("/images/lunbo_new_2.png");
				newList.add("/images/lunbo_new_3.png");
			}else{
				for(Map<String,Object> map : list){
					String url = map.get("PICTURE_URL").toString();
					newList.add(url);
				}
				System.out.print(newList);
			}
			return newList;
		}
	}
}