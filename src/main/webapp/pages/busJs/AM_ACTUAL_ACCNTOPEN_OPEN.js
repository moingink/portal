query_buttontoken='query';
buttonJson =[
             {name:'搜索器',fun:'setting_Onclick(this)',buttonToken:'open_setting'},
             {name:'查询',fun:'queryTable(this)',buttonToken:query_buttontoken},
             {name:'新增',fun:'insert_onclick(this)',buttonToken:'open_insert'},
             {name:'修改',fun:'update_onclick(this)',buttonToken:'open_update'},
             {name:'删除',fun:'delete_onclick(this)',buttonToken:'open_delete'},
             {name:'提交',fun:'submitAudit_onClick(this)',buttonToken:'open_submit'},
             {name:'审批',fun:'audit_onClick(this)',buttonToken:'open_audit'},
             {name:'复制',fun:'copy_onClick(this)',buttonToken:'open_insert'},
             {name:'回收',fun:'back_onClick(this)',buttonToken:'open_back'},
             {name:'联查审批流',fun:'queryWorkFlow_onClick(this)',buttonToken:'open_queryWorkFlow'},
             {name:'联查回填单',fun:'queryBackfill_onClick(this)',buttonToken:'open_queryBackfill'},
             {name:'打印',fun:'printCurrentPage_onClick(this)',buttonToken:'open_printCurrentPage'}
			];
//目前仅支持单据状态为已保存和审批退回的才可进行删除。单据删除后单据上的附件同时也被删除。
//只有未回填的开户申请单才可以进行回填操作。

//账户性质---------发生改变的时候触发
$("#ACCNT_PROPERTY").change(function(){
	var accnt_property = $("#ACCNT_PROPERTY").val();
	
	//收付属性
	if(accnt_property == '0'){
		$("#RP_ATTRIBUTE").val('2');	//账户性质为“集团顶点户”时，收付属性为“收付”
	} else if(accnt_property=='3' || accnt_property=='4' || accnt_property=='6'){
		$("#RP_ATTRIBUTE").val('1');	//账户性质为“成本专户”“工程专户”“备用金账户”收付属性为“付”
	} else if(accnt_property=='1' || accnt_property=='2' || accnt_property=='8' || accnt_property=='9' || accnt_property=='a'){
		$("#RP_ATTRIBUTE").val('0');	//账户性质为“收入归集户”“一般收入户”“托收账户”“代收账户”“POS卡”时，收付属性为“收”
	}else{
		$("#RP_ATTRIBUTE").val('');	
	}
	
	//是否透支
	if(accnt_property == '3' || accnt_property=='4'){
		$("#IS_OVERDRAWN").val('');		//账户性质为“成本专户”和“工程专户”的此项可为可编辑，其他默认为否
		$("#IS_OVERDRAWN").attr("disabled" , false);	
	}else{
		$("#IS_OVERDRAWN").val('0');
		$("#IS_OVERDRAWN").attr("disabled" , true);
	}
	
	//是否自动归集
	if(accnt_property =='2'||accnt_property =='5'||accnt_property =='6'||accnt_property =='7'||accnt_property =='8'||accnt_property =='9'||accnt_property =='a'||accnt_property =='b'){
		$("#IS_ACCUMULATED").val('0');//“一般收入户”、“托收账户”、“代收账户”、“POS卡”、“备用金账户”、“报备类账户”、 “其他”、“专用账户”时，“是否自动归集”自动设置为“否”，不允许修改
		$("#IS_ACCUMULATED").attr("disabled" , true);
	}else if(accnt_property =='0'||accnt_property =='1'){
		$("#IS_ACCUMULATED").val('1')//账户性质为“收入归集户”和“集团顶点户”的此项为“是”，不可修改。
		$("#IS_ACCUMULATED").attr("disabled" , true);
	}else if(accnt_property == '3' || accnt_property=='4'){
		$("#IS_ACCUMULATED").val('');//账户性质为“成本专户”和“工程专户”的此项可编辑
		$("#IS_ACCUMULATED").attr("disabled" , false);
	}
	
	//账户性质与账户类型校验关系
	if(accnt_property == '3'){//账户性质为“成本专户”，则账户类型可以选择“基本存款账户”和“一般存款账户”
		$("#ACCNT_TYPE").val('');
		$("#ACCNT_TYPE").html('<option selected="selected" value="">==请选择==</option><option value="1">一般存款账户</option><option value="0">基本存款账户</option>');
	}else if(accnt_property == 'b'){//账户性质为“其他”，则账户类型只能选择“个人存折账户”
		$("#ACCNT_TYPE").html('<option selected="selected" value="">==请选择==</option><option value="4">个人存款账户</option>')
		$("#ACCNT_TYPE").val('4');
	}else if(accnt_property =='0'||accnt_property =='1'||accnt_property =='2'||accnt_property =='4'||accnt_property =='5'||accnt_property =='7'||accnt_property =='8'||accnt_property =='9'||accnt_property =='a'){
		$("#ACCNT_TYPE").val('');//	账户性质为“集团顶点户、一般收入户、收入归集户、托收账户、代收账户、pos卡、工程专户、专用账户、报备类账户”，账户类型可以选择“一般存款账户”和 “专用存款账户”
		$("#ACCNT_TYPE").html('<option selected="selected" value="">==请选择==</option><option value="1">一般存款账户</option><option value="2">专用存款账户</option>');
	}else{
		$("#ACCNT_TYPE").val('');
		$("#ACCNT_TYPE").html('<option selected="selected" value="">==请选择==</option><option value="1">一般存款账户</option><option value="0">基本存款账户</option><option value="3">临时存款账户</option><option value="4">个人存款账户</option><option value="2">专用存款账户</option>');
	}
});


function beforeInsert(t){
	$("#APPLICANT_NAME").val(loginUser);$("#APPLICANT_NAME").attr("disabled" , true);//申请人，不可维护，默认为当期登录人
	$("#CMPNY_NAME").val('用友广信');//公司名称，当前登录人所属公司 
	//单据编号自动生成BILL_ID
	var flag = false;//返回值默认为 字符串 true
	var accnt_type = $("#ACCNT_TYPE").val(); //账户类别
	var accnt_property = $("#ACCNT_PROPERTY").val();//账户性质
	var rp_attribute = $("#RP_ATTRIBUTE").val();//收付属性
	var is_overdrawn = $("#IS_OVERDRAWN").val();//是否透支
	var is_accumulated = $("#IS_ACCUMULATED").val();//是否自动归集  
	var bank_typecode = $("#BANK_TYPECODE").val();//银行类别
	var def20 = $("#DEF20").val();//上级账号调度类型
	var parent_accnt_num = $("#PARENT_ACCNT_NUM").val();//上级账号
	var business_body_name = $("#BUSINESS_BODY_NAME").val();//业务主体
	

}

function insert_onclick(t){
	tog(t);
	beforeInsert(t);
}

//修改信息验证
function update_onclick(t){
	var buttonToken=$(t).attr("buttonToken");
	var selected = JSON.parse(getSelections());
	if(selected.length != 1){
		oTable.showModal('modal', "请选择一条数据进行修改");
		return;
	}
	var accountOpenUpdateUrl = '/account/AOVerification?cmd=AOUpdateVerification' ;
	var message = transToServer(accountOpenUpdateUrl,getSelections());
	if(message.indexOf("原因")>0){
		oTable.showModal('modal', message);
		return ; 
	}else{
		$inspage.find("[id]").each(function() {			
			$(this).val(selected[0][$(this).attr("id")]);
		});
		$("#ins_or_up_buttontoken").val(buttonToken);
		tog(t);
	}
}

function delete_onclick(t){
	var selected = JSON.parse(getSelections());
	if(selected.length < 1){
		oTable.showModal('modal', "请至少选择一条数据进行删除");
		return;
		}
	var accountOpenUpdateUrl = '/account/AOVerification?cmd=AODeleteVerification' ;
	var message = transToServer(accountOpenUpdateUrl,getSelections());
	if(message.indexOf("原因")>0){
		oTable.showModal('modal', message);
		return ; 
	}else{
		var message_del = transToServer(findBusUrlByPublicParam(t,''),getSelections());
		oTable.showModal('modal', message_del);
		queryTable(t);
	}
}

function setting_Onclick(t){
	
}

function submitAudit_onClick(t){
	var buttonToken=$(t).attr("buttonToken");
	var selected = JSON.parse(getSelections());
	if(selected.length != 1){
		oTable.showModal('modal', "请选择一条记录进行提交");
		return;
	}
	var message = transToServer(findBusUrlByPublicParam(t,''),getSelections());
	oTable.showModal('modal', message);
	queryTable(t);
}

function audit_onClick(t){
	
}

//FIXME 对复制的条件，文档未说明，只是做了简单的复制
function copy_onClick(t){
	var buttonToken=$(t).attr("buttonToken");
	var selected = JSON.parse(getSelections());
	if(selected.length != 1){
		oTable.showModal('modal', "请选择一条记录进行复制");
		return;
	}
	//将选择的数据中ID和BILL_DATE清空
	for(var i = 0 ; i < selected.length ; i++){
		 selected[i].ID = "";
		 selected[i].BILL_DATE = "";
	}
	$inspage.find("[id]").each(function() {			
		$(this).val(selected[0][$(this).attr("id")]);
	});
	alert(getSelections());
	$("#ins_or_up_buttontoken").val(buttonToken);
	tog(t);
}

function back_onClick(t){
	var buttonToken=$(t).attr("buttonToken");
	var selected = JSON.parse(getSelections());
	if(selected.length != 1){
		oTable.showModal('modal', "请选择一条数据回收");
		return;
	}
}

function queryBackfill_onClick(t){
	
}

function printCurrentPage_onClick(t){
	
}


/*<li>1、必须上传附件，说明开户原因，并上传有效盖章资料扫描件（盖财务章和公司章皆可）。</li><br/>
<li>2、当“账户类型”选择“其他”的时候，需要在开户原因填写具体原因，并在进行账户开户申请审批时进行重点审核考量。</li><br>
<li>3、账户用途为“其他账户-个人存折”是，账户类型必须为“个人存折账户”。</li><br>
<li>4、开户银行必须填写银行全称，例：中国工商银行北京长安支行营业室 、中国建设银行股份有限公司北京西单支行。</li><br>
<li>5、账户名称须填写全称，例：中国联合网络通信集团有限公司河北省分公司。</li><br>
<li>6、公司名称须填写全称，例：中国联合网络通信集团有限公司河北省分公司。</li><br>
<li>7、银行类别为工农中建招交行和邮储银行，需单独提供上传加盖公章的详细开户原因描述，作为附件上传至资金系统并依据审批流程设置进行流转。</li><br>
<li>8、银行类别为工农中建招交邮储以外的账户，须单独上传加盖公章的证明附件。</li>*/