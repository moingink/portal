package com.yonyou.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.business.button.ButtonAbs;
import com.yonyou.business.button.cache.ButForDeleteWithCache;
import com.yonyou.business.button.cache.ButForInsertWithCache;
import com.yonyou.business.button.cache.ButForUpdateWithCache;
import com.yonyou.business.button.demobutton.button.DemoButForCheck;
import com.yonyou.business.button.demobutton.button.DemoButForCheckBack;
import com.yonyou.business.button.demobutton.button.DemoButForImport;
import com.yonyou.business.button.demobutton.button.DemoButForInsert;
import com.yonyou.business.button.demobutton.button.DemoButForUpdate;
import com.yonyou.business.button.notify.need.ButForNotityNeed;
import com.yonyou.business.button.util.ButForDelete;
import com.yonyou.business.button.util.ButForSelect;
import com.yonyou.business.button.util.ButForUpdate;


@RestController
@RequestMapping(value = "/buttonBase")
public class ButtonBase extends ButtonController {

	
	private Map<String,ButtonAbs> buttonMap =new HashMap<String,ButtonAbs>();
	{
		/*************************公共的*****************/
		buttonMap.put("add", new DemoButForInsert());//新增
		buttonMap.put("update", new DemoButForUpdate());//修改
		buttonMap.put("update", new ButForUpdate());//修改
		buttonMap.put("delete", new ButForDelete());//删除
		
		/*************************demo*****************/
		buttonMap.put("query", new ButForSelect());//查询
		buttonMap.put("button_check", new DemoButForCheck());//复核
		buttonMap.put("button_checkback", new DemoButForCheckBack());//撤销复核
		buttonMap.put("button_import", new DemoButForImport());//Excel导入
		buttonMap.put("updateByDemo", new DemoButForUpdate());//修改
		
		/*************************带缓存的数据的增删改*****************/
		buttonMap.put("insertWithCache", new ButForInsertWithCache());//新增
		buttonMap.put("updateWithCache", new ButForUpdateWithCache());//修改
		buttonMap.put("deleteWithCache", new ButForDeleteWithCache());//删除
		/*************************业务待办查看*****************/
		buttonMap.put("notityNeed", new ButForNotityNeed());
	}
	
	@Override
	protected Map<String, ButtonAbs> findButtonMap() {
		// TODO 自动生成的方法存根
		return buttonMap;
	}
	
	
	@Override
	public void init(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成的方法存根
		super.init(request, response);
	}

	
}
