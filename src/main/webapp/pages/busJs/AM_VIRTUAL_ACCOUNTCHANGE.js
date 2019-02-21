buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'新增',fun:'tog(this)',buttonToken:'add'},
             {name:'删除',fun:'delRows(this)',buttonToken:'delete'},
             {name:'修改',fun:'updateRow(this)',buttonToken:'update'},
             {name:'提交',fun:'querySubmit(this)',buttonToken:'submit'},
             {name:'审批',fun:'queryAudit(this)',buttonToken:'audit'},
             {name:'联查审批流',fun:'queryWorkFlow_onClick(this)',buttonToken:'queryWorkFlow'},
             {name:'导出',fun:'printAll_onClick(this)',buttonToken:'update'},
			];

function queryTable(t){
	alert("queryTable");
}

function querySubmit(t){
	alert("querySubmit");
}

function queryAudit(t){
	alert("queryAudit");
}

function queryWorkFlow_onClick(t){
	alert("queryWorkFlow_onClick");
}

function printAll_onClick(t){
	alert("printAll_onClick");
}