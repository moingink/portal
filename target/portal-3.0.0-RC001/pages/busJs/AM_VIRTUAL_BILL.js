buttonJson =[
             {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
             {name:'搜索器',fun:'setting_Onclick(this)',buttonToken:'setting'},
             {name:'联查单据',fun:'joinQuery(this)',buttonToken:'joinquery'},
			];

function queryTable(t){
	alert("queryTable");
}

function setting_Onclick(t){
	alert("setting_Onclick");
}

function joinQuery(t){
	alert("joinQuery");
}
