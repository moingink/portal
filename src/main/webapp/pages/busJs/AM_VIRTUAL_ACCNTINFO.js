buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'搜索器',fun:'setting_Onclick(this)',buttonToken:'setting'},
             {name:'导出',fun:'printAll_onClick(this)',buttonToken:'update'},
			];

function queryTable(t){
	alert("queryTable");
}

function setting_Onclick(t){
	alert("setting_Onclick");
}

function printAll_onClick(t){
	alert("printAll_onClick");
}