var setting = {
	view : {
		expandSpeed: "",
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	async : {
		enable: true, 
		url: "/admin/department/listJson.do",
		autoParam: ["id", "name=n", "level=lv"],
		otherParam: {"otherParam":"test"},
		dataFilter: filter 
	},
	edit : {
		enable : true,
		showRemoveBtn: setRemoveBtn,
		showRenameBtn: true,
		removeTitle: '删除',
		renameTitle: '重命名'
	},
	callback: {
		beforeRemove: beforeRemove,
		beforeEditName: beforeEditName
	}
};
 
function setRemoveBtn(treeId, treeNode) {
	//return !treeNode.isParent;
	return true;
}
  

function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
function beforeRemove(treeId, treeNode) {
	$( "#dialog-confirm" ).removeClass('hide').dialog({
		resizable: false,
		width: '320',
		modal: true,
		title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 删除模型分类</h4></div>",
		title_html: true,
		buttons: [
			{
				html: "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; 删除",
				"class" : "btn btn-danger btn-minier",
				click: function() {
					$.ajax({
		                type: "POST",
		                url: ctx+"/admin/bimCategoryZtree/delete.json",
		                data:{"id" : treeNode.id},
		                success: function(data) {
		                	var status = data.invokerResult.content.status;
		        			if (status == true) {
								var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
								treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
		        				$("#dialog-confirm").dialog( "close" );
		        				$.message("success", "删除成功");
		        			}else{
		        				$("#dialog-confirm").dialog( "close" );
		        				$.message("warn", "删除失败,"+data.invokerResult.content.message);
		        			}
		                }
		            });
				}
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-minier",
				click: function() {
					$("#dialog-confirm").dialog( "close" );
				}
			}
		]
	}); 
	return false;
}
function beforeEditName(treeId, treeNode){
	$.ajax({
        type: "POST",
        url: ctx+"/admin/bimCategoryZtree/find.json",
        data: {"id":treeNode.id},
        success: function(data){
        	var bimCategoryBean = data.invokerResult.content.bimCategoryBean;
        	var name = bimCategoryBean.name;
			var icon = bimCategoryBean.icon;
			var url = bimCategoryBean.url;
			var catDir = bimCategoryBean.catDir;
			var listOrder = bimCategoryBean.listOrder;
			$("#catId_update").val(treeNode.id);
			$("#name_update").val(name);
			$("#oldName_update").val(name);
			$("#catDir_update").val(catDir); 
			$("#oldCatDir_update").val(catDir); 
			$("#listOrder_update").val(listOrder);
			$("#url_update").val(url);
			if(!isNullOrUndefined(icon)){
				$("#icon_update").val(icon);
				var icon_a = '<a href="'+fileServerImageDown+icon+'" target="_blank">图标</a>';
				var div_append = $("#iconFile_update_div");
				div_append.children().remove();
				div_append.append(icon_a);
			}else{
				$("#icon_update").val("");
				var div_append = $("#iconFile_update_div");
				div_append.children().remove();
				div_append.append('<span id="iconFile_update_update_fieldSet"></span>');
			}
        }
    });
	var dialog = $("#dialog-update").removeClass('hide').dialog({
		modal: true,
		width: 600,
		title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 修改模型分类</h4></div>",
		title_html: true,
		buttons: [ 
			{
				text: "取消",
				"class" : "btn btn-minier",
				click: function() {
					$("#dialog-update").dialog( "close" ); 
				} 
			},
			{
				text: "确定",
				"class" : "btn btn-primary btn-minier",
				click: function() {
					var resultStatus = $("#updateForm").valid();
					if(resultStatus){
						$.ajax({
			                type: "POST",
			                url: ctx+"/admin/bimCategoryZtree/update.json",
			                data:$("#updateForm").serialize(),
			                success: function(data){
			                	var status = data.invokerResult.success;
			        			if (status == true) {
			        				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			        				treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
				                	$("#dialog-update").dialog( "close" );
				                	$.message("success", "更新成功");
			        			}else{
			        				$.message("warn", "更新失败");
			        			}
			                }
			            });
					}
				} 
			}
		]
	});
	return false;
}

 

function addHoverDom(treeId, treeNode) {
	//0级,1级才能出现新增的按钮 2级及2级以上不能显示添加按钮
	if(treeNode.level > 1){
		return false;
	}
	var sObj = $("#"+treeNode.tId+"_span");
	if (treeNode.editNameFlag || $("#"+treeNode.tId+"_add").length > 0)
		return;
	var addStr = "<span class='button add' id='"+treeNode.tId+"_add' title='增加' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#"+treeNode.tId+"_add");
	if (btn){
		btn.bind("click", function(){
			var parentId = treeNode.id;
			$("#addForm input[name='parentId']").val(parentId);
			var dialog = $("#dialog-add").removeClass('hide').dialog({
				modal: true,
				width: 600,
				title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i>新增部门</h4></div>",
				title_html: true,
				buttons: [ 
					{
						text: "取消",
						"class" : "btn btn-minier",
						click: function() {
							$("#dialog-add").dialog( "close" ); 
						} 
					},
					{
						text: "确定",
						"class" : "btn btn-primary btn-minier",
						click: function() {
							var resultStatus = $("#addForm").valid();
							if(resultStatus){
								$.ajax({
					                type: "POST",
					                url: "/admin/department/addJson.do",
					                data:$("#addForm").serialize(),
					                success: function(data){
					                	var status = data.invokerResult.success;
					        			if (status == true){
						                	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
											var nodes = treeObj.getSelectedNodes();
											if (nodes.length>0) {
												treeObj.reAsyncChildNodes(nodes[0], "refresh");
											}
											$("#dialog-add").dialog( "close" ); 
											$.message("success", "增加成功");
					        			} else{
					        				$.message("warn", "增加失败");
					        			}
					                }
					            });
							}
						} 
					}
				]
			});
			 
		});
	}
};

function removeHoverDom(treeId, treeNode) {
	$("#"+treeNode.tId+"_add").unbind().remove();
};


$(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting);
	
	//新增一级分类节点
	clickNewAdd();
	//刷新树
	clickRefreshTree();
});




//刷新树
function clickRefreshTree(){
	$("#refreshTree").click( function() {
    	window.location.reload();
	});
}

//新增一级分类
function clickNewAdd(){
	$( "#newBimCategory" ).on('click', function(e) {
		var dialog = $("#dialog-new").removeClass('hide').dialog({
			modal: true,
			width: 600,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 新增一级分类</h4></div>",
			title_html: true,
			buttons: [ 
				{
					text: "取消",
					"class" : "btn btn-minier",
					click: function() {
						$("#dialog-new").dialog( "close" ); 
					} 
				},
				{
					text: "确定",
					"class" : "btn btn-primary btn-minier",
					click: function() {
						//开启验证
						var resultStatus = $("#newForm").valid();
						if(resultStatus)
						{
							//ajax提交
							$.ajax({
				                cache: true,
				                async: false,
				                type: "POST",
				                url: ctx+"/admin/bimCategoryZtree/add.json",
				                data: $("#newForm").serialize(),
				                success: function(data) {
				                	var status = data.invokerResult.content.status;
				        			if (status == true) {
					                	$("#dialog-new").dialog( "close" ); 
					                	$.message("success", "一级分类增加成功");
					                	setTimeout(function(){window.location.reload();}, 1000);
				        			}else{
				        				$("#dialog-new").dialog( "close" ); 
					                	$.message("warn", "一级分类增加失败");
				        			}
				                }
				            });
						}
					} 
				}
			]
		});
	});
}