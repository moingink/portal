buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'手工下载',fun:'manualDownload(this)',buttonToken:'download'},
             {name:'打印',fun:'print_Onclick(this)',buttonToken:'print'},
             {name:'导出当页',fun:'export_Onclick(this)',buttonToken:'export'},
             {name:'导出全部',fun:'exportAll_onClick(this)',buttonToken:'exportall'},
			];

function queryTable(t){
	alert("queryTable");
}

function manualDownload(t){
	alert("manualDownload");
}

function print_Onclick(t){
	alert("print_Onclick");
}

function export_Onclick(t){
	alert("export_Onclick");
}

function exportAll_onClick(t){
	alert("exportAll_onClick");
}