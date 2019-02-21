function transToServer(url,jsonData){
    	var message;
    	$.ajax({
    		async: false,
    		type: "post",
			url: url,
			dataType: "json",
			data:{"jsonData":jsonData},
			success: function(data){
				message = data['message'];
				},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//登录超时
		    	if(XMLHttpRequest.getResponseHeader("TIMEOUTURL")!=null){
		    		window.top.location.href = XMLHttpRequest.getResponseHeader("TIMEOUTURL");
		    	}
				message ="请求失败";
			}
			});
    	return message;
    };