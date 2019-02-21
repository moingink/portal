buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'搜索器',fun:'querySearch(this)',buttonToken:'querysearch'},
             {name:'在线查询',fun:'query_online(this)',buttonToken:'online'},
             {name:'打印',fun:'print(this)',buttonToken:'print'},
             {name:'导出',fun:'exportAll_onClick(this)',buttonToken:'exportall'},
			];

function queryTable(t){
	alert("queryTable");
}

function querySearch(t){
	alert("querySearch");
}

function query_online(t){
	alert("query_online");
}

function print(t){
	alert("print");
}

function exportAll_onClick(t){
	alert("exportAll_onClick");
}