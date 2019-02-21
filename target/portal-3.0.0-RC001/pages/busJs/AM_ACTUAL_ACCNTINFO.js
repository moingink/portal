buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'开立回填单',fun:'queryOpen_onClick(this)',buttonToken:'open'},
             {name:'变更申请单',fun:'queryChange_onClick(this)',buttonToken:'change'},
             {name:'冻结申请单',fun:'queryFreeze_onClick(this)',buttonToken:'freeze'},
             {name:'解冻申请单',fun:'queryUnfreeze_onClick(this)',buttonToken:'unfreeze'},
             {name:'销户申请单',fun:'queryDestory_onClick(this)',buttonToken:'destory'},
             {name:'打印',fun:'printCurrentPage_onClick(this)',buttonToken:'add'},
             {name:'导出',fun:'printAll_onClick(this)',buttonToken:'update'},
             {name:'--新增',fun:'tog(this)',buttonToken:'add'},
             {name:'--修改',fun:'updateRow(this)',buttonToken:'update'},
             {name:'--删除',fun:'delRows(this)',buttonToken:'delete'},
			];

function queryOpen_onClick(t){
	alert("queryOpen_onClick");
}

function queryChange_onClick(t){
	alert("queryChange_onClick");
}

function queryFreeze_onClick(t){
	alert("queryFreeze_onClick");
}

function queryUnfreeze_onClick(t){
	alert("queryUnfreeze_onClick");
}

function queryDestory_onClick(t){
	alert("queryDestory_onClick");
}

function printCurrentPage_onClick(t){
	alert("printCurrentPage_onClick");
}

function printAll_onClick(t){
	alert("printAll_onClick");
}