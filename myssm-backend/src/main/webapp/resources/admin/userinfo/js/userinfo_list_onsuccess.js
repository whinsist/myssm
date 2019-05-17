var ctx = "";
var source = "";
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
	if(!isNullOrUndefined(jsonData))
	{
		$(jsonData).each(function(index,element){
			addCookie($(element).attr("name"),$(element).attr("value"), {expires: 7 * 24 * 60 * 60});
		});
	}
	
	//执行查询
	var grid_selector = "#grid-table";
	jQuery(grid_selector).jqGrid("clearGridData");
	var postData = $("#grid-table").jqGrid("getGridParam", "postData");
  	$.extend(postData,parameterMap());
  	jQuery(grid_selector).jqGrid("setGridParam", { search: true }).trigger("reloadGrid", [{ page: 1}]);  //重载JQGrid
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
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var parent_column = $(grid_selector).closest('[class*="col-"]');
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
	
	/**
	* jqGrid
	*/ 
	jQuery(grid_selector).jqGrid({
		caption: "用户列表",
		editurl: "/admin/user/deleteOrUpdate.do",
		url: "/admin/user/listJson.do",
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
					delOptions: {recreateForm:true, beforeShowForm:deleteBeforeCallback, afterSubmit:deleteAfterCallback, afterComplete:deleteCompleteCallback},
					editformbutton:false, //编辑时弹出form按钮
					onSuccess: function(response) {
                        var json = $.parseJSON(response.responseText);
                        var result = json.result;
                        var message = json.message;
                        if(result == "0001"){
                        	$.message("warn", message);
                            return [false, message];
                        } else {
                        	$.message("success", "操作成功");
                            return [true];
                        }
					},
                    onError :function(rowid, response, textStatus) {
                    	//alert("onError==="+textStatus);
                    } 
				}
			}, 
			{label:'ID',name:'userId',index:'userId'},
			{label:'用户名',name:'userName',index:'userName',editable:true,sortable:false,formatter:userNameFormat},
			{label:'联系电话',name:'userPhone',index:'userPhone',editable:false},
			{label:'用户类型',name:'userType',index:'userType',editable:false,sortable:false},
			{label:'状态',name:'status',index:'status',editable:true,edittype:'select',editoptions:{value:"Publish:发布;Delete:删除"},formatter:statusFormat},
			{label:'新增时间',name:'createTime_str',index:'createTime_str',editable:false,sortable:false},
			{label:'更新时间',name:'updateTime_str',index:'updateTime_str',editable:false,sortable:false},
			{label:'ID',name:'userId',index:'userId', hidden:true, key:true, editable: false,sortable:false}
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
		    page:"currentPage",    // 表示请求页码的参数名称  
		    rows:"pageSize"    // 表示请求行数的参数名称  
		}, 
		multiselect: true,
        multiboxonly: false,
		loadComplete: function(data) {
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
	
	 
	//navButtons
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 
			add: true,
			addicon : 'ace-icon fa fa-plus-circle purple',
			addfunc : function4Adding,
			del: true,
			delicon : 'ace-icon fa fa-trash-o red',
			edit: true,
			editicon : 'ace-icon fa fa-pencil blue',
			editfunc : function4Updating,
			search: true,
			searchicon : 'ace-icon fa fa-search orange',
			view: true,
			viewicon : 'ace-icon fa fa-search-plus grey',
			viewfunc : function4Viewing,
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
				//alert(22);
			}
		}
	).navButtonAdd(pager_selector,{
		caption : '反选',
		title : '反选表格',
		buttonicon : 'ace-icon fa fa-leaf green',
		onClickButton : function4Select
	});
	
	
	
	
	
	 
	//行删除操作
	function deleteBeforeCallback(e) {
		var form = $(e[0]);
		if(form.data('styled')) return false;
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_delete_form(form);
		form.data('styled', true);
	}
	function deleteAfterCallback(data, postdata){  
        var responseText = data.responseText;
        var obj = eval('(' + responseText + ')');
        var result = obj.result;
        var message = obj.message;
        if(result == "0001"){
        	$.message("warn", message);
        }else{
        	$.message("warn", "操作成功");
        }
        return [true,""]; //返回0表示正常  
    }
	function deleteCompleteCallback(data,postdata){  
        //alert("deleteCompleteCallback");  
    }
	//名称格式化
	function userNameFormat(cellvalue, options, rowObject){  
		//return '<a href="'+ctx+'/resources/id='+rowObject.id+'" target="_blank">'+cellvalue+'</a>';  
		return cellvalue;  
	}    
	//状态格式化
	function statusFormat(cellvalue, options, rowObject){
		var statusMap = {Publish:"已发布", Delete:"已删除"};
		return statusMap[cellvalue];
	}
	//时间格式化
	function timestampFormat(cellvalue, options, rowObject){
		return formatDate(new Date(cellvalue),"yyyy.MM.dd hh:mm:ss");
	}
	function createTimeFormat(cellvalue, options, rowObject){
		return formatDate(new Date(cellvalue),"yyyy.MM.dd hh:mm:ss");
	}
	
	//新增
	var function4Adding = function(e){
		window.location.href = ctx+"/admin/user/add.do";
		return;
		$("#aFacilitatorId_search_chosen").css({'width': 274});
		$("#aFacilitatorId").trigger("chosen:updated");
		var dialog = $("#dialog-addtemplet").removeClass('hide').dialog({
			modal: true,
			width: 600,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'>添加专题</h4></div>",
			title_html: true,
			buttons: [{
				text: "取消",
				"class" : "btn btn-minier",
				click: function() {
					$("#dialog-addtemplet").dialog( "close" ); 
				} 
			},
			{
				text: "确定",
				"class" : "btn btn-primary btn-minier",
				click: function() { 
					//验证专题Id
					var reg = new RegExp("[\\u4E00-\\u9FFF]+","g"); 
					if(reg.test($("#subjectId").val())) { 
						//$.message("warn", '专题Id不能有汉字');
						alert('专题Id不能有汉字');
						return;
					}
					//开启验证
					var resultStatus = $("#addForm").valid();
					var resultStatusOther = checkForm();
					if(resultStatus&&resultStatusOther){
						$.ajax({
					        cache: true,
					        async: false,
					        type: "POST",
					        url: ctx+'/special/zsMessage/add.json',
					        data: $("#addForm").serialize(),
					        success: function(data)
					        {
					        	var result = data.invokerResult.content;
			        			if (result.status == true) 
			        			{
			        				//关闭层
				                	$("#dialog-addtemplet").dialog( "close" ); 
			        				//重置页面window.location.reload();
				                	//重置
				        			$("#aFacilitatorId_search_chosen").css({'width': 274});
				        			$("#aFacilitatorId").trigger("chosen:updated"); 
				        			$.message("success", result.message);
				                	save2search();
			        			}else{
			        				alert(result.message);
			        				//$.message("warn", result.message);
			        			}
					        }
					    });
					}
				}
			}]
		});
	};  
	 
	
	
	//弹出层标题(title)支持HTML
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	//修改
	var function4Updating = function(rowid){
		alert(11);
		window.location.href = ctx+"/admin/goods/edit/"+rowid+".do";
	};
	//查看
	var function4Viewing = function(rowid){
		window.open(ctx+"/bim/0/"+rowid+".html", '_blank');
	}; 
	//反选
	var function4Select = function(e) {
		//全部ID行
		var allIds = jQuery(grid_selector).jqGrid('getDataIDs');
		//选中的ID行
		var selectIds = jQuery(grid_selector).jqGrid('getGridParam','selarrrow');
		//反选ID数组
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
		//反选
		for(var i=0;i<allIds.length;i++){
			jQuery(grid_selector).jqGrid('resetSelection',allIds[i]);
		}
		if(arrayIds.length>0){
			for(var i=0;i<arrayIds.length;i++){
				jQuery(grid_selector).jqGrid('setSelection',arrayIds[i]);
			}
		}
	};  
	//样式修改
	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
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
