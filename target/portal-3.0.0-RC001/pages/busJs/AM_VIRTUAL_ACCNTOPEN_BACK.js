buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'回填',fun:'backFill_onClick(this)',buttonToken:'batchimport'},
             {name:'修改',fun:'updateRow(this)',buttonToken:'update'},
             {name:'确认生效',fun:'validate_onClick(this)',buttonToken:'submit'},
             {name:'导出',fun:'printAll_onClick(this)',buttonToken:'update'},
             {name:'批量回填',fun:'queryBatchBackfill(this)',buttonToken:'batchbackfill'},
             {name:'批量导入',fun:'queryBatchImport(this)',buttonToken:'batchimport'},
			];

function queryTable(t){
	alert("queryTable");
}

function backFill_onClick(t){
	alert("backFill_onClick");
}

function validate_onClick(t){
	alert("validate_onClick");
}

function queryBatchBackfill(t){
	alert("queryBatchBackfill");
}

function queryBatchImport(t){
	alert("queryBatchImport");
}

function printAll_onClick(t){
	alert("printAll_onClick");
}