
	var type_marks =new Object();
	var bus_type_obj=new Object();
	var type_div_obj =new Object();
	var bus_type_size_obj=new Object();
	function bulidNeedHandleCountType(type,bus_type,$div,isUpdate){
		if(type_div_obj[type]==null){
  			type_div_obj[type]=$div;
  		}
		$div=type_div_obj[type];
		if(isUpdate){
			var types =bulidNeedCountData(type);
	  		var appendHtml='';
	  		var mark="";
	  		if(type_marks[type]!=null){
	  			mark=type_marks[type];
	  		}else{
	  			mark =$div.html();
	  			type_marks[type]=mark;
	  		}
	  		bus_type_size_obj[type]=$div.children().length;
	  		var params =["NAME","CODE","BUS_SIZE"];
	  		var appendHtml='';
	  		var html='';
			for(var i=0;i<types.length;i++){
				var values =types[i];
				appendHtml=mark;
				for(var j=0;j<params.length;j++){
					appendHtml=appendHtml.replace("#"+params[j]+"#",values[params[j]]);
				}
				html+=appendHtml;
			}
			$div.html(html);
			$div.show();
		}
		if(bus_type==''){
  			bus_type=bus_type_obj[type];
  		}else{
  			bus_type_obj[type]=bus_type;
  		}
		$active =null;
		if($div.children().length!=bus_type_size_obj[type]){
			$active=$div.find("span:first");
			bus_type_size_obj[type]=$div.children().length;
		}else{
			$active=$div.children().find("[bus_type="+bus_type+"]");
		}
		loadNeedHandle($active,type);
		
		
 }
    
    function changNeedType(){
    	var types=[{"NAME":"全部","CODE":"ALL"}];
    	var url =visit_context+'/buttonBase?cmd=button&buttonToken=findMenuCodes&token='+token;
    	var json =transToServerByNeedCount(url,'');
    	for(var i=0;i<json.length;i++){
    		var obj ={"NAME":json[i]["NAME"],"CODE":json[i]["TOTAL_CODE"]};
    		types.push(obj);
    	}
    	return types;
    }
    
    function bulidNeedCountData(type){
    	var url =visit_context+'/buttonBase?cmd=button&buttonToken='+type+'&token='+token;
    	var json =transToServerByNeedCount(url,'');
    	var dataTypes =[];
    	var types =changNeedType();
    	for(var i=0;i<types.length;i++){
    		var typeObj =types[i];
    		if(json[typeObj["CODE"]]!=null){
    			typeObj["BUS_SIZE"]=json[typeObj["CODE"]];
    			dataTypes.push(typeObj);
    		}
    	}
    	return dataTypes;
    }
    
    
    
    function transToServerByNeedCount(url, jsonData) {
    	var message =[];
    	$.ajax({
    		async : false,
    		type : "post",
    		url : url,
    		dataType : "json",
    		data : {
    			"jsonData" : jsonData
    		},
    		success : function(data) {
    			message =data;
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){
    				//登录超时
    		    	if(XMLHttpRequest.getResponseHeader("TIMEOUTURL")!=null){
    		    		window.top.location.href = XMLHttpRequest.getResponseHeader("TIMEOUTURL");
    		    	}
    				message ="请求失败";
    				message=[];
    			}
    	});
    	return message;
    };
    
    
