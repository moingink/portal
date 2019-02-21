$(function() {

	var defaultData = [
			{
				text : '<a href="#" onclick="javascript:onclickmenu(\'default.html\')">介绍</a>',
				href : 'message',
				tags : [ '4' ]
			},
			{
				text : '<a href="#" onclick="javascript:onclickmenu(\'/portal/pages/mainConfig.html\')">配置</a>',
				href : '#config',
				tags : [ '0' ]
			},
			{
				text : '账户管理',
				href : '',
				tags : [ '4' ],
				nodes : [
						{
							text : '银行账户管理',
							href : '#',
							tags : [ '4' ],
							nodes : [
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'http://127.0.0.1:8089/account/pages/singleTableModify.jsp?pageCode=AM_ACTUAL_ACCNTINFO&pageName=银行账户查询\')">银行账户查询</a>',
										href : '#amActualAccntinfo',
										tags : [ '0' ]
									},
									{
										text : '银行账户开户',
										href : '#',
										tags : [ '4' ],
										nodes : [
												{
													text : '<a href="#" onclick="javascript:onclickmenu(\'/account/pages/singleTableModify.jsp?pageCode=AM_ACTUAL_ACCNTOPEN_OPEN&pageName=银行账户开户申请\')">申请</a>',
													href : '#amActualAccntopen',
													tags : [ '0' ]
												},
												{
													text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_ACTUAL_ACCNTOPEN_BACK&pageName=银行账户开户回填\')">回填</a>',
													href : '#amActualAccntopenBackFill',
													tags : [ '0' ]
												} ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_ACTUAL_ACCOUNTCHANGE&pageName=银行账户变更\')">银行账户变更</a>',
										href : '#amActualAccountchange',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_ACTUAL_ACCOUNTFROZEN&pageName=银行账户冻结\')">银行账户冻结</a>',
										href : '#amActualAccountfrozen',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_ACTUAL_ACCOUNTUNFROZEN&pageName=银行账户解冻\')">银行账户解冻</a>',
										href : '#amActualAccountunfrozen',
										tags : [ '0' ]
									},

									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_ACTUAL_ACCOUNTDESTROY&pageName=银行账户销户\')">银行账户销户</a>',
										href : '#amActualAccountdestroy',
										tags : [ '0' ]
									} ]
						},
						{
							text : '虚拟账户管理',
							href : '#',
							tags : [ '4' ],
							nodes : [
									{
										text : '虚拟账户开户',
										href : '#',
										tags : [ '4' ],
										nodes : [
												{
													text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCNTOPEN_OPEN&pageName=虚拟账户开户申请\')">申请</a>',
													href : '#amVirtualAccntopen',
													tags : [ '0' ]
												},
												{
													text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCNTOPEN_BACK&pageName=虚拟账户开户回填\')">回填</a>',
													href : '#amVirtualAccntopenBackFill',
													tags : [ '0' ]
												} ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCOUNTCHANGE&pageName=虚拟账户变更\')">虚拟账户变更</a>',
										href : '#amVirtualAccountchange',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCOUNTDESTROY&pageName=虚拟账户销户\')">虚拟账户销户</a>',
										href : '#amVirtualAccountdestroy',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCNTINFO&pageName=虚拟账户信息查询\')">虚拟账户信息查询</a>',
										href : '#amVirtualAccntinfo',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=EBANK_DZD_MULTIEVEL&pageName=虚拟账户交易明细查询\')">虚拟账户交易明细查询</a>',
										href : '#amVirtualAccntinfo',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=EBANK_MANUAL_BALANCE&pageName=虚拟账户余额查询\')">虚拟账户余额查询</a>',
										href : '#ebankBalance',
										tags : [ '0' ]
									},
									{
										text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_BILL&pageName=虚拟账户状态联查\')">虚拟账户状态联查</a>',
										href : '#amVirtualBillAction',
										tags : [ '0' ]
									},
									{
										text : '账户查询',
										href : '#',
										tags : [ '4' ],
										nodes : [
												{
													text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_ACCNTOPEN_OPEN&pageName=账户统计\')">账户统计</a>',
													href : '#amVirtualAccntopen',
													tags : [ '0' ]
												} ]
										} ]
									},
						{
							text : '账户监控',
							href : '#',
							tags : [ '4' ],
							nodes : [
										{
											text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=MONITORING_TACTICS&pageName=账户监控策略申请\')">账户监控策略申请</a>',
											href : '#amVirtualAccntopen',
											tags : [ '0' ]
										},
										{
											text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_BILL&pageName=账户超过或低于限定余额预警\')">账户超过或低于限定余额预警</a>',
											href : '#amVirtualBillAction',
											tags : [ '0' ]
										},
										{
											text : '<a href="#" onclick="javascript:onclickmenu(\'singleTableModify.jsp?pageCode=AM_VIRTUAL_BILL&pageName=账户支出超过限定额的预警\')">账户支出超过限定额的预警</a>',
											href : '#amVirtualBillAction',
											tags : [ '0' ]
										} ]
							} ]
			} ];

	var alternateData = [ {
		text : 'Parent 1',
		tags : [ '2' ],
		nodes : [ {
			text : 'Child 1',
			tags : [ '3' ],
			nodes : [ {
				text : 'Grandchild 1',
				tags : [ '6' ]
			}, {
				text : 'Grandchild 2',
				tags : [ '3' ]
			} ]
		}, {
			text : 'Child 2',
			tags : [ '3' ]
		} ]
	}, {
		text : 'Parent 2',
		tags : [ '7' ]
	}, {
		text : 'Parent 3',
		icon : 'glyphicon glyphicon-earphone',
		href : '#demo',
		tags : [ '11' ]
	}, {
		text : 'Parent 4',
		icon : 'glyphicon glyphicon-cloud-download',
		href : '/demo.html',
		tags : [ '19' ],
		selected : true
	}, {
		text : 'Parent 5',
		icon : 'glyphicon glyphicon-certificate',
		color : 'pink',
		backColor : 'red',
		href : 'http://www.tesco.com',
		tags : [ 'available', '0' ]
	} ];

	var json = '[' + '{' + '"text": "Parent 1",' + '"nodes": [' + '{'
			+ '"text": "Child 1",' + '"nodes": [' + '{'
			+ '"text": "Grandchild 1"' + '},' + '{' + '"text": "Grandchild 2"'
			+ '}' + ']' + '},' + '{' + '"text": "Child 2"' + '}' + ']' + '},'
			+ '{' + '"text": "Parent 2"' + '},' + '{' + '"text": "Parent 3"'
			+ '},' + '{' + '"text": "Parent 4"' + '},' + '{'
			+ '"text": "Parent 5"' + '}' + ']';

	function getTree(){
		
		$.ajax({  
            url : "/portal/base?cmd=loadmenu",  
            dataType : "json",  
            type : "GET",  
            success : function(data) { 
            	defaultData=new Array();  
            	defaultData=data;
            	alert(data);
            	
            	$('#treeview1').treeview({
            		enableLinks : true,
            		color : "blue",
            		backColor : "white",
            		onhoverColor : "#00F5FF",
            		borderColor : "white",
            		selectedColor : "blue",
            		selectedBackColor : "#7AC5CD",
            		data : defaultData
            	});
            }  
        });
		
		return defaultData;
	}
	
	loopLevel=0;  
	function obj2treeview(resp,structure){  
	    var nodeArray = new Array();  
	    var i = 0;  
	        for(i= 0;i<resp.length;i++){  
	            var treeViewNodeObj;  
	            var textStr = structure[loopLevel].text;  
	            var nodeStr = structure[loopLevel].nodes;  
	          
	            var subNode;  
	            if(resp[i][nodeStr] != undefined){  
	                loopLevel++;  
	                subNode = obj2treeview(resp[i][nodeStr],structure);  
	                loopLevel--;  
	            }  
	              
	            if(subNode != undefined){  
	                treeViewNodeObj = {  
	                    text: resp[i][textStr],  
	                    nodes: subNode  
	                };  
	            }else{  
	                treeViewNodeObj = {  
	                    text: resp[i][textStr]  
	                };  
	            }  
	            nodeArray.push(treeViewNodeObj);  
	        }  
	        return nodeArray  
	}  
	$('#treeview1').treeview({
		enableLinks : true,
		color : "blue",
		backColor : "white",
		onhoverColor : "#00F5FF",
		borderColor : "white",
		selectedColor : "blue",
		selectedBackColor : "#7AC5CD",
		data : getTree()
	});

	$('#treeview2').treeview({
		levels : 1,
		data : defaultData
	});

	$('#treeview3').treeview({
		levels : 99,
		data : defaultData
	});

	$('#treeview4').treeview({

		color : "#428bca",
		data : defaultData
	});

	$('#treeview5').treeview({
		color : "#428bca",
		expandIcon : 'glyphicon glyphicon-chevron-right',
		collapseIcon : 'glyphicon glyphicon-chevron-down',
		nodeIcon : 'glyphicon glyphicon-bookmark',
		data : defaultData
	});

	$('#treeview6').treeview({
		color : "#428bca",
		expandIcon : "glyphicon glyphicon-stop",
		collapseIcon : "glyphicon glyphicon-unchecked",
		nodeIcon : "glyphicon glyphicon-user",
		showTags : true,
		data : defaultData
	});

	$('#treeview7').treeview({
		color : "#428bca",
		showBorder : false,
		data : defaultData
	});

	$('#treeview8').treeview({
		expandIcon : "glyphicon glyphicon-stop",
		collapseIcon : "glyphicon glyphicon-unchecked",
		nodeIcon : "glyphicon glyphicon-user",
		color : "yellow",
		backColor : "purple",
		onhoverColor : "orange",
		borderColor : "red",
		showBorder : false,
		showTags : true,
		highlightSelected : true,
		selectedColor : "yellow",
		selectedBackColor : "darkorange",
		data : defaultData
	});

	$('#treeview9').treeview({
		expandIcon : "glyphicon glyphicon-stop",
		collapseIcon : "glyphicon glyphicon-unchecked",
		nodeIcon : "glyphicon glyphicon-user",
		color : "yellow",
		backColor : "purple",
		onhoverColor : "orange",
		borderColor : "red",
		showBorder : false,
		showTags : true,
		highlightSelected : true,
		selectedColor : "yellow",
		selectedBackColor : "darkorange",
		data : alternateData
	});

	$('#treeview10').treeview({
		color : "#428bca",
		enableLinks : true,
		data : defaultData
	});

	var $searchableTree = $('#treeview-searchable').treeview({
		data : defaultData,
	});

	var search = function(e) {
		var pattern = $('#input-search').val();
		var options = {
			ignoreCase : $('#chk-ignore-case').is(':checked'),
			exactMatch : $('#chk-exact-match').is(':checked'),
			revealResults : $('#chk-reveal-results').is(':checked')
		};
		var results = $searchableTree.treeview('search', [ pattern, options ]);

		var output = '<p>' + results.length + ' matches found</p>';
		$.each(results, function(index, result) {
			output += '<p>- ' + result.text + '</p>';
		});
		$('#search-output').html(output);
	}

	$('#btn-search').on('click', search);
	$('#input-search').on('keyup', search);

	$('#btn-clear-search').on('click', function(e) {
		$searchableTree.treeview('clearSearch');
		$('#input-search').val('');
		$('#search-output').html('');
	});

	var initSelectableTree = function() {
		return $('#treeview-selectable').treeview(
				{
					data : defaultData,
					multiSelect : $('#chk-select-multi').is(':checked'),
					onNodeSelected : function(event, node) {
						$('#selectable-output').prepend(
								'<p>' + node.text + ' was selected</p>');
					},
					onNodeUnselected : function(event, node) {
						$('#selectable-output').prepend(
								'<p>' + node.text + ' was unselected</p>');
					}
				});
	};
	var $selectableTree = initSelectableTree();

	var findSelectableNodes = function() {
		return $selectableTree.treeview('search', [
				$('#input-select-node').val(), {
					ignoreCase : false,
					exactMatch : false
				} ]);
	};
	var selectableNodes = findSelectableNodes();

	$('#chk-select-multi:checkbox').on('change', function() {
		console.log('multi-select change');
		$selectableTree = initSelectableTree();
		selectableNodes = findSelectableNodes();
	});

	// Select/unselect/toggle nodes
	$('#input-select-node').on('keyup', function(e) {
		selectableNodes = findSelectableNodes();
		$('.select-node').prop('disabled', !(selectableNodes.length >= 1));
	});

	$('#btn-select-node.select-node').on('click', function(e) {
		$selectableTree.treeview('selectNode', [ selectableNodes, {
			silent : $('#chk-select-silent').is(':checked')
		} ]);
	});

	$('#btn-unselect-node.select-node').on('click', function(e) {
		$selectableTree.treeview('unselectNode', [ selectableNodes, {
			silent : $('#chk-select-silent').is(':checked')
		} ]);
	});

	$('#btn-toggle-selected.select-node').on('click', function(e) {
		$selectableTree.treeview('toggleNodeSelected', [ selectableNodes, {
			silent : $('#chk-select-silent').is(':checked')
		} ]);
	});

	var $expandibleTree = $('#treeview-expandible').treeview(
			{
				data : defaultData,
				onNodeCollapsed : function(event, node) {
					$('#expandible-output').prepend(
							'<p>' + node.text + ' was collapsed</p>');
				},
				onNodeExpanded : function(event, node) {
					$('#expandible-output').prepend(
							'<p>' + node.text + ' was expanded</p>');
				}
			});

	var findExpandibleNodess = function() {
		return $expandibleTree.treeview('search', [
				$('#input-expand-node').val(), {
					ignoreCase : false,
					exactMatch : false
				} ]);
	};
	var expandibleNodes = findExpandibleNodess();

	// Expand/collapse/toggle nodes
	$('#input-expand-node').on('keyup', function(e) {
		expandibleNodes = findExpandibleNodess();
		$('.expand-node').prop('disabled', !(expandibleNodes.length >= 1));
	});

	$('#btn-expand-node.expand-node').on('click', function(e) {
		var levels = $('#select-expand-node-levels').val();
		$expandibleTree.treeview('expandNode', [ expandibleNodes, {
			levels : levels,
			silent : $('#chk-expand-silent').is(':checked')
		} ]);
	});

	$('#btn-collapse-node.expand-node').on('click', function(e) {
		$expandibleTree.treeview('collapseNode', [ expandibleNodes, {
			silent : $('#chk-expand-silent').is(':checked')
		} ]);
	});

	$('#btn-toggle-expanded.expand-node').on('click', function(e) {
		$expandibleTree.treeview('toggleNodeExpanded', [ expandibleNodes, {
			silent : $('#chk-expand-silent').is(':checked')
		} ]);
	});

	// Expand/collapse all
	$('#btn-expand-all').on('click', function(e) {
		var levels = $('#select-expand-all-levels').val();
		$expandibleTree.treeview('expandAll', {
			levels : levels,
			silent : $('#chk-expand-silent').is(':checked')
		});
	});

	$('#btn-collapse-all').on('click', function(e) {
		$expandibleTree.treeview('collapseAll', {
			silent : $('#chk-expand-silent').is(':checked')
		});
	});

	var $checkableTree = $('#treeview-checkable').treeview(
			{
				data : defaultData,
				showIcon : false,
				showCheckbox : true,
				onNodeChecked : function(event, node) {
					$('#checkable-output').prepend(
							'<p>' + node.text + ' was checked</p>');
				},
				onNodeUnchecked : function(event, node) {
					$('#checkable-output').prepend(
							'<p>' + node.text + ' was unchecked</p>');
				}
			});

	var findCheckableNodess = function() {
		return $checkableTree.treeview('search', [
				$('#input-check-node').val(), {
					ignoreCase : false,
					exactMatch : false
				} ]);
	};
	var checkableNodes = findCheckableNodess();

	// Check/uncheck/toggle nodes
	$('#input-check-node').on('keyup', function(e) {
		checkableNodes = findCheckableNodess();
		$('.check-node').prop('disabled', !(checkableNodes.length >= 1));
	});

	$('#btn-check-node.check-node').on('click', function(e) {
		$checkableTree.treeview('checkNode', [ checkableNodes, {
			silent : $('#chk-check-silent').is(':checked')
		} ]);
	});

	$('#btn-uncheck-node.check-node').on('click', function(e) {
		$checkableTree.treeview('uncheckNode', [ checkableNodes, {
			silent : $('#chk-check-silent').is(':checked')
		} ]);
	});

	$('#btn-toggle-checked.check-node').on('click', function(e) {
		$checkableTree.treeview('toggleNodeChecked', [ checkableNodes, {
			silent : $('#chk-check-silent').is(':checked')
		} ]);
	});

	// Check/uncheck all
	$('#btn-check-all').on('click', function(e) {
		$checkableTree.treeview('checkAll', {
			silent : $('#chk-check-silent').is(':checked')
		});
	});

	$('#btn-uncheck-all').on('click', function(e) {
		$checkableTree.treeview('uncheckAll', {
			silent : $('#chk-check-silent').is(':checked')
		});
	});

	var $disabledTree = $('#treeview-disabled').treeview(
			{
				data : defaultData,
				onNodeDisabled : function(event, node) {
					$('#disabled-output').prepend(
							'<p>' + node.text + ' was disabled</p>');
				},
				onNodeEnabled : function(event, node) {
					$('#disabled-output').prepend(
							'<p>' + node.text + ' was enabled</p>');
				},
				onNodeCollapsed : function(event, node) {
					$('#disabled-output').prepend(
							'<p>' + node.text + ' was collapsed</p>');
				},
				onNodeUnchecked : function(event, node) {
					$('#disabled-output').prepend(
							'<p>' + node.text + ' was unchecked</p>');
				},
				onNodeUnselected : function(event, node) {
					$('#disabled-output').prepend(
							'<p>' + node.text + ' was unselected</p>');
				}
			});

	var findDisabledNodes = function() {
		return $disabledTree.treeview('search', [
				$('#input-disable-node').val(), {
					ignoreCase : false,
					exactMatch : false
				} ]);
	};
	var disabledNodes = findDisabledNodes();

	// Expand/collapse/toggle nodes
	$('#input-disable-node').on('keyup', function(e) {
		disabledNodes = findDisabledNodes();
		$('.disable-node').prop('disabled', !(disabledNodes.length >= 1));
	});

	$('#btn-disable-node.disable-node').on('click', function(e) {
		$disabledTree.treeview('disableNode', [ disabledNodes, {
			silent : $('#chk-disable-silent').is(':checked')
		} ]);
	});

	$('#btn-enable-node.disable-node').on('click', function(e) {
		$disabledTree.treeview('enableNode', [ disabledNodes, {
			silent : $('#chk-disable-silent').is(':checked')
		} ]);
	});

	$('#btn-toggle-disabled.disable-node').on('click', function(e) {
		$disabledTree.treeview('toggleNodeDisabled', [ disabledNodes, {
			silent : $('#chk-disable-silent').is(':checked')
		} ]);
	});

	// Expand/collapse all
	$('#btn-disable-all').on('click', function(e) {
		$disabledTree.treeview('disableAll', {
			silent : $('#chk-disable-silent').is(':checked')
		});
	});

	$('#btn-enable-all').on('click', function(e) {
		$disabledTree.treeview('enableAll', {
			silent : $('#chk-disable-silent').is(':checked')
		});
	});

	var $tree = $('#treeview12').treeview({
		data : json
	});
});