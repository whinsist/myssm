/*
 * Copyright 2015-2016 ems All rights reserved.
 * 
 * JavaScript - List
 * Version: 1.0
 */
//列表常规操作
function operationHtml(id)
{
	var div = 
		'<div class="hidden-sm hidden-xs action-buttons">'
			+'<a class="blue" href="javascript:detail(\''+id+'\');" title="查看">'
				+'<i class="ace-icon fa fa-search-plus bigger-130"></i>'
			+'</a>'
			+'<a class="green" href="javascript:edit(\''+id+'\');"  title="编辑">'
				+'<i class="ace-icon fa fa-pencil bigger-130"></i>'
			+'</a>'
		+'</div>'
		+'<div class="hidden-md hidden-lg">'
			+'<div class="inline pos-rel">'
				+'<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'
					+'<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>'
				+'</button>'
				+'<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'
					+'<li>'
						+'<a href="javascript:detail(\''+id+'\');" class="tooltip-info" data-rel="tooltip" title="详情">'
							+'<span class="blue">'
								+'<i class="ace-icon fa fa-search-plus bigger-120"></i>'
							+'</span>'
						+'</a>'
					+'</li>'
					+'<li>'
						+'<a href="javascript:edit(\''+id+'\');" class="tooltip-success" data-rel="tooltip" title="编辑">'
							+'<span class="green">'
								+'<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>'
							+'</span>'
						+'</a>'
					+'</li>'
				+'</ul>'
			+'</div>'
		+'</div>';
	return div;
}

//FROM表单序列化为JSON对象
$.fn.serializeObject = function()  
{  
    var o = {};  
    var a = this.serializeArray();  
    $.each(a, function() {  
        if (o[this.name]) {  
            if (!o[this.name].push) {  
                o[this.name] = [o[this.name]];  
            }  
            o[this.name].push(this.value || '');  
        } else {  
            o[this.name] = this.value || '';  
        }  
    });  
    return o;  
}; 

//登录绑定回车事件
$(document).keydown(function(event)
 { 
	if(event.keyCode == 13)
	{
		$('#searchButton').click();
		return false;
	} 
}); 

//搜索
$(document).on('click','#searchButton',function(){
	save2search();
	return false;
});

//重置
$(document).on('click','#refreshButton',function(){
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
	save2search();
	return false;
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
  	return false;
}

//搜索参数
function parameterMap()
{
	//根据Cookie赋值
	if("${source}" == '1')
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

/**
* 样式修改
*/
//行删除操作
function beforeDeleteCallback(e) {
	var form = $(e[0]);
	if(form.data('styled')) return false;
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
	style_delete_form(form);
	form.data('styled', true);
}
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

//


