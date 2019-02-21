
var query_buttontoken='';

function queryTable(t) {
	var queryButtonToken=$("#query_buttontoken").val();
	var buttonToken='';
	if(queryButtonToken!=null&&queryButtonToken.length>0){
		buttonToken=$("#query_buttontoken").val();
	}else{
		if(query_buttontoken!=null&&query_buttontoken.length>0){
			buttonToken = query_buttontoken;
		}else{
			buttonToken = $(t).attr("buttonToken");
		}
		$("#query_buttontoken").val(buttonToken);
	}
	oTable.queryTable($table, findBusUrlByButtonTonken(buttonToken,''));
}

function delRows(t){
	var selected = JSON.parse(getSelections());
	if(selected.length < 1){
		oTable.showModal('modal', "请至少选择一条数据进行删除");
		return;
		}
	var message = transToServer(findBusUrlByPublicParam(t,''),getSelections());
	oTable.showModal('modal', message);
	queryTable(t);
}

function back(t){
	tog(t);
	$inspage.find('[id]').val("");
	$("#ins_or_up_buttontoken").val("");
}

function save(t){
	var message ="";
	var buttonToken =$("#ins_or_up_buttontoken").val();
	message = transToServer(findBusUrlByButtonTonken(buttonToken,''),getJson());
	oTable.showModal('modal', message);
	back(t);
	queryTable(t);
}

function updateRow(t){
	var buttonToken=$(t).attr("buttonToken");
	var selected = JSON.parse(getSelections());
	if(selected.length != 1){
		oTable.showModal('modal', "请选择一条数据进行修改");
		return;
	}
	$inspage.find("[id]").each(function() {			
	  	$(this).val(selected[0][$(this).attr("id")]);
	});
	$("#ins_or_up_buttontoken").val(buttonToken);
	tog(t);
}

function tog(t){
	var buttonToken=$(t).attr("buttonToken");
	$("#ins_or_up_buttontoken").val(buttonToken);
	var $bulidtable = $("#bulidTable");
	var $bulidpage = $("#bulidPage");
	if($bulidtable.is(":hidden")){
		$bulidtable.slideDown();
		$bulidpage.slideUp();
	}else{
		$bulidpage.slideDown();
		$bulidtable.slideUp();
	}
}

