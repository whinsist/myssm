var ctx = $("#baseUrl").val();
var source = "";
var fileServerBrowseUrl = $("#fileServerBrowseUrl").val();
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var parent_column = $(grid_selector).closest('[class*="col-"]');


$(function(){
	//类型切换
	$('#specailType_search').on('change', function(){
		save2search();
	});
	//状态切换
	$('#statusType_search').on('change', function(){
		save2search();
	});
	//bimarea_search
	$('#bimarea_search').on('change', function(){
		save2search();
	});
	//品牌切换
	$('#bimFacilitatorId_search').on('change', function(){
		save2search();
	});
	//搜索
	 $('#searchButton').on('click', function() {
		//保存和搜索
		save2search();
	});
	
	//重置
	 $('#refreshButton').on('click', function() {
		//重置查询条件
		$("#searchFrom").find("input,select").each(function(index,element){
			var defaultVal = $(element).attr("defaultVal");
			if(!isNullOrUndefined(defaultVal))
			{
				$(element).val(defaultVal);
			}else
			{
				$(element).val("");
			}
			if($(element).hasClass("chosen-select"))
			{
				$(element).trigger("chosen:updated");
			}
		});
		
		//保存和搜索
		save2search();
	});
	
	//带搜索的下拉列表
	if(!ace.vars['touch']) {
		$('.chosen-select').chosen({allow_single_deselect:true}); 
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 //$this.next().css({'width': 274});
			})
		}).trigger('resize.chosen');
		$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
			if(event_name != 'sidebar_collapsed') return;
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': $this.parent().width()});
			})
		});

		$('#chosen-multiple-style .btn').on('click', function(e){
			var target = $(this).find('input[type=radio]');
			var which = parseInt(target.val());
			if(which == 2)
			{
				$('#form-field-select-add').addClass('tag-input-style');
				$('#form-field-select-update').addClass('tag-input-style');
			}
			else
			{
				$('#form-field-select-add').removeClass('tag-input-style');
				$('#form-field-select-update').removeClass('tag-input-style');
			}
		});
	};
});

//保存查询条件并且搜索
function save2search()
{
	//查询条件放入Cookie
	var jsonData = $("#searchFrom").serializeArray();
	if(!isNullOrUndefined(jsonData)){
		$(jsonData).each(function(index,element){
			addCookie($(element).attr("name"),$(element).attr("value"), {expires: 7 * 24 * 60 * 60});
		});
	}
	//执行查询
	jQuery(grid_selector).jqGrid("clearGridData");
	var postData = $("#grid-table").jqGrid("getGridParam", "postData");
  	$.extend(postData, parameterMap());
  	jQuery(grid_selector).jqGrid("setGridParam", {search: true}).trigger("reloadGrid", [{ page: 1}]);  //重载JQGrid
}

//搜索参数
function parameterMap()
{
	//根据Cookie赋值
	if(source == '1')
	{
		$("#searchFrom").find("input,select").each(function(index,element){
			var name = $(element).attr("name");
			$(element).val(getCookie(name));
			if($(element).hasClass("chosen-select"))
			{
				$(element).trigger("chosen:updated");
			}
		});
	}
	
	//返回JSON格式参数
	return $('#searchFrom').serializeObject();  
}

jQuery(function($) {
	//大小以适合页面大小
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
    });
    //调整在侧边栏折叠/展开
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			//setTimeout是WebKit只是给DOM变化时间然后重绘！！！
			setTimeout(function() {
				$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
			}, 0);
		}
    });
	
	//弹出层标题(title)支持HTML
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	
	/**
	* jqGrid
	*/ 
	jQuery(grid_selector).jqGrid({
		caption: "用户列表",
        contentType: 'application/json; charset=UTF-8',
		editurl: ctx + "/v1/back/users/deleteOrUpdate",
		url: ctx + "/v1/back/users/list",
		mtype: "POST",
		datatype: "json",
		postData: parameterMap(),
		height: 560,
		colModel:[
			{label:'操作',name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
				formatter:'actions', 
				formatoptions:{ 
					keys:true,
					delbutton: true,
					editbutton: true,
					//delOptions:{recreateForm: true, beforeShowForm:deleteBeforeCallback},
					delOptions: {recreateForm:true, beforeShowForm:deleteBeforeCallback, afterSubmit:deleteAfterCallback, afterComplete:deleteCompleteCallback},
					onSuccess: updateAfterCallback
				}
			},
			{label:'userSid',name:'userSid',index:'userSid'},
			{label:'账号',name:'account',index:'account',editable:false},
			{label:'用户名',name:'realName',index:'account',editable:true,sortable:true},
			{label:'联系手机',name:'mobile',index:'mobile',editable:true},
			{label:'用户类型',name:'userType',index:'userType',editable:false,sortable:false},
			{label:'状态',name:'status',index:'status',editable:true,edittype:'select', editoptions:{value:"0:禁用;1:有效;2:锁定"},formatter:statusFormat},
			{label:'新增时间',name:'createdDt',index:'createdDt',editable:false,sortable:false},
			{label:'更新时间',name:'updatedDt',index:'updatedDt',editable:false,sortable:false},
            // 常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
            {name:'userSid',index:'id',hidden:true, key:true}
		],
		viewrecords:true,
		rowNum:15,
		rowList:[10,15,20,25,30],
		pager:pager_selector,
		jsonReader: {  
              root: "rows",
		      page: "pageNo",
		      total: "total",
		      records: "records"
		},
		altRows: true,
		prmNames : {  
		    page:"pageNum",    // 表示请求页码的参数名称
		    rows:"pageSize"    // 表示请求行数的参数名称  
		}, 
		multiselect: true,
        multiboxonly: false,
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		}
	});
	
	//触发窗大小，使电网得到正确的尺寸
	$(window).triggerHandler('resize.jqGrid');
	$("#grid-table").parent().parent().parent().css("z-index",0);
	
	 

	//导航按钮
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 
			add: true,
			addicon : 'ace-icon fa fa-plus-circle purple',
			addfunc : function(e){
				window.location.href = ctx+"/admin/user/add.do";
			},
			del: true,
			delicon : 'ace-icon fa fa-trash-o red',
			delfunc : function(e){
				var selectIds = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				var ids = "";
				for (var i = 0; i < selectIds.length; i++) {
					ids += selectIds[i]+",";
				}
				$("#dialog-confirm-content").html("<i class='ace-icon fa fa-hand-o-right blue bigger-120'></i>你确定要删除选择的"+selectIds.length+"个用户?");
				$("#dialog-confirm").removeClass('hide').dialog({
					resizable: false,
					width: '320',
					modal: true,
					title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>提示</h4></div>",
					title_html: true,
					buttons: [
						{
							html: "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; 确定",
							"class" : "btn btn-danger btn-minier",
							click: function() {
								$.ajax({
							        type: "POST",
							        url: ctx + "/v1/back/users/deleteOrUpdate",
							        data: {"oper":"del","id": ids},
							        success: function(data){
							        	if (data.code == 200){
							        		$.message("success", "操作成功");
							        		save2search();
							        	} else{
							        		$.message("warn", data.message);
							        	}
				        				$("#dialog-confirm").dialog( "close" );
							        },
									error: function (xhr, textStatus, errorThrown) {
							        	//alert("xhr.status="+xhr.status +" xhr.readyState="+xhr.readyState+"  textStatus="+textStatus);
                                        $.message("warn", JSON.parse(xhr.responseText).message);
                                        $("#dialog-confirm").dialog( "close" );
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
				
				
			},
			edit: true,
			editicon : 'ace-icon fa fa-pencil blue',
			editfunc : function(rowid){
				window.location.href = ctx+"/admin/u/edit/"+rowid+".do";
			},
			search: false,
			searchicon : 'ace-icon fa fa-search orange',
			view: true,
			viewicon : 'ace-icon fa fa-search-plus grey',
			viewfunc :  function(rowid){
				window.open(ctx+"/view/0/"+rowid+".html",'_blank');
			},
			refresh: true,
			refreshicon : 'ace-icon fa fa-refresh green'
		},
		{
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				if(form.data('styled')) return false;
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				form.data('styled', true);
			},
			onClick : function(e) {
			}
		}
	).navButtonAdd(pager_selector,{
		caption : '反选',
		title : '反选表格',
		buttonicon : 'ace-icon fa fa-leaf green',
		onClickButton : function(e){
			// 全部ID行
			var allIds = jQuery(grid_selector).jqGrid('getDataIDs');
			// 选中的ID行
			var selectIds = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
			// 反选ID数组
			var arrayIds = new Array();
			if ((allIds != '' && allIds != "") && (selectIds != '' && selectIds != "")) {
				for (var i = 0; i < allIds.length; i++) {
					var allId = allIds[i];
					var selectFlag = true;
					for (var j = 0; j < selectIds.length; j++) {
						var selectId = selectIds[j];
						if (allId == selectId) {
							selectFlag = false;
						}
					}
					if (selectFlag) {
						arrayIds.push(allId);
					}
				}
			} else {
				if (allIds != '' && allIds != "") {
					arrayIds = allIds;
				}
			}
			// 反选
			for (var i = 0; i < allIds.length; i++) {
				jQuery(grid_selector).jqGrid('resetSelection', allIds[i]);
			}
			if (arrayIds.length > 0) {
				for (var i = 0; i < arrayIds.length; i++) {
					jQuery(grid_selector).jqGrid('setSelection', arrayIds[i]);
				}
			}
		}
	});
	
	 
	//名称格式化
	function userNameFormat(cellvalue, options, rowObject){  
		//return '<a href="'+ctx+'/resources/id='+rowObject.id+'" target="_blank">'+cellvalue+'</a>';  
		return cellvalue;  
	}    
	//状态格式化
	function statusFormat(cellvalue, options, rowObject){
		var statusMap = {"0":"禁用", "1":"有效", "2":"锁定"};
		return statusMap[cellvalue];
	}
	//时间格式化
	function timestampFormat(cellvalue, options, rowObject){
		return formatDate(new Date(cellvalue),"yyyy.MM.dd hh:mm:ss");
	}
	function createTimeFormat(cellvalue, options, rowObject){
		return formatDate(new Date(cellvalue),"yyyy.MM.dd hh:mm:ss");
	}
	function picFormat(cellvalue, options, rowObject){  
		return '<img src="'+fileServerBrowseUrl+cellvalue+'" style="width:30px;height:30px;" class="imagetip"/>';  
	} 
	//删除一行数据开始函数
	function deleteBeforeCallback(e) {
		var form = $(e[0]);
		if(form.data('styled')) return false;
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_delete_form(form);
		form.data('styled', true);
	}
	//删除一行数据回调函数
	function deleteAfterCallback(data, postdata){  
		var json = $.parseJSON(data.responseText);
        var result = json.result;
        if(result == 200){
        	$.message("success", json.message);
        } else {
        	$.message("warn", json.message);
        }
        return [true];
    }
	//删除一行数据结束函数
	function deleteCompleteCallback(data,postdata){  
        //alert("deleteCompleteCallback");  
    }
	//跟新一行数据回调函数
	function updateAfterCallback(response){
		 var json = $.parseJSON(response.responseText);
         var result = json.result;
         var message = json.message;
         if(result == "0000"){
         	$.message("success", "操作成功");
             return [true];
         } else {
         	$.message("warn", message);
             return [false, message];
         }
	}
	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
	}
	
	function beforeEditCallback(e) {
		//alert(1);
	}
	function afterEditCallback(e) {
		//alert(2);
	}
	function afterSubmitCallback(e) {
		alert(3);
	}
	function afterCompleteCallback(e) {
		alert(4);
	}
	//分页样式
	function updatePagerIcons(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	}
	
	//按钮提示
	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}
	
	$('#spinner4').ace_spinner({value:1,min:1,max:100,step:1, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
});
