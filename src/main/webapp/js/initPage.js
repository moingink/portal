var oTable = new TableInit();

var $table = $('#table');
var $queryParam = $("#queryParam");
var $inspage = $("#insPage");

var buttonJson =null;
//var selectPageParam='';
//var listPageParam='';
//var maintainPageParam='';

function bulidAlonePage() {
	bulidPage(true,true,true,true);
}

//function initPageParam(_selectPageParam,_listPageParam,_maintainPageParam){
//	selectPageParam=pageParamFormat(_selectPageParam);
//	listPageParam=pageParamFormat(_listPageParam);
//	maintainPageParam=pageParamFormat(_maintainPageParam);
//}


function bulidPage(isBulidSelect,isBulidButton,isBulidListPage,isBulidMaintainPage){
	//showLoading("请稍等！");
	if(dataSourceCode!=null&&dataSourceCode!='null'){
		if(pageName!=null&&pageName!='null'){
			$("#pageName").html(pageName);
		}
		if(isBulidSelect){
			bulidSelect($queryParam,dataSourceCode,'');
		}
		if(isBulidButton){
			bulidButton($("#button_div"),dataSourceCode);
		}
		if(isBulidListPage){
			bulidListPage($table,dataSourceCode,initTableParam);
		}
		if(isBulidMaintainPage){
			bulidMaintainPage($inspage,dataSourceCode,'');
		}
		
		$('.form_date').datetimepicker({
    		minView: 'month',         //设置时间选择为年月日 去掉时分秒选择
   			format:'yyyy-mm-dd',
    	    weekStart: 1,
    	    todayBtn:  1,
    	    autoclose: 1,
    	    todayHighlight: 1,
    	    startView: 2,
    	    forceParse: 0,
    	    showMeridian: 1,
    	    language: 'zh-CN', //设置时间控件为中文
    	    pickerPosition:'bottom-right'           
    	});
	}else{
		alert("dataSource is null");
	}
}
//构建按钮
function bulidButton($b,_dataSourceCode){
	buHtml =buttonToServer(findUrlParam('buttonBuild','build',findPageParamByDataSourceCode(_dataSourceCode)),null);
	bulidButtonHtml($b,buHtml,_dataSourceCode);
}

function bulidSelect($q,_dataSourceCode,_selectPageParam){
	oTable.initQueryParam($q, paramurl + findPageParamByDataSourceCode(_dataSourceCode)+_selectPageParam);
}

function bulidListPage($t,_dataSourceCode,_listPageParam){
	//oTable.initCols($t, colurl + findPageParamByDataSourceCode(_dataSourceCode), qusurl + findPageParamByDataSourceCode(_dataSourceCode)+_listPageParam);
	bulidListPageForQusUrl($t,_dataSourceCode,_listPageParam,qusurl);
}

function bulidListPageForQusUrl($t,_dataSourceCode,_listPageParam,_qusurl){
	//oTable.initCols($t, colurl + findPageParamByDataSourceCode(_dataSourceCode), qusurl + findPageParamByDataSourceCode(_dataSourceCode)+_listPageParam);
	if(_qusurl!=null){
		_qusurl= _qusurl + findPageParamByDataSourceCode(_dataSourceCode)+_listPageParam;
	}
	oTable.initCols($t, colurl + findPageParamByDataSourceCode(_dataSourceCode),_qusurl);
}


function bulidListPageForQusUrlAndColUrl($t,_dataSourceCode,_listPageParam,_qusurl,_colurl){
	//oTable.initCols($t, colurl + findPageParamByDataSourceCode(_dataSourceCode), _qusurl + findPageParamByDataSourceCode(_dataSourceCode)+_listPageParam);
	oTable.initCols($t, _colurl + findPageParamByDataSourceCode(_dataSourceCode),  _qusurl + findPageParamByDataSourceCode(_dataSourceCode)+_listPageParam);
}

function bulidMaintainPage($i,_dataSourceCode,_maintainPageParam){
	if(_maintainPageParam!=null&&_maintainPageParam.length>0){
		oTable.initMaintainCols($i, findDatanurl + findPageParamByDataSourceCode(_dataSourceCode)+_maintainPageParam);
	}else{
		oTable.initMaintainCols($i, maintainurl + findPageParamByDataSourceCode(_dataSourceCode));
	}
}

function setReadonlyByDiv($div){
	$div.each(function(){
		$(this).attr("readonly","readonly");
	});
}

