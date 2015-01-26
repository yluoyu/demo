$(function(){
	$(document).ajaxError(function(event,xhr,options){
	}).ajaxComplete(function(event,xhr,options){
		var location = xhr.getResponseHeader('Location');
    	if(location) {
    		window.location.reload();
    	}
	})
	
	
})

function isNull(value) {
    if (typeof(value) == "undefined" ||null == value|| "" == $.trim(value) || value == "null" || value.length == 0) {
        return true;
    } else {
        return false;
    }
}


//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
	if(isie6()){
		$('.window-mask').bgiframe();
	}
}
//弹出信息确认窗口 title:标题 msgString:提示信息 fn:当用户点击“确定”按钮的时侯将传递一个true值给回调函数，否则传递一个false值
function msgConfirm(title, msgString, fn) {
	$.messager.confirm(title, msgString, fn);
	if(isie6()){
		$('.window-mask').bgiframe();
	}
}
//操作提示信息小窗口:title:标题 msg:提示信息
function msgSlide(titile,msg){
	$.messager.show({
		title:titile,
		msg:msg,
		height:150,
		showType:'slide',
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
	
}

function isie6(){
	return $.browser.msie && /msie 6\.0/i.test(navigator.userAgent);
}
//兼容ie6对话框
function showDiag(id,options){
	$('#' + id).dialog(options);
	if(isie6()){
		$('.window-mask').bgiframe();
	}
}

function isSuccess(response){
	
	if(typeof(response) == 'object' && response && response.status == 1){
		return true;
	}
	
	return false;
}

function getMessage(response){
	if(typeof(response) == 'object' && response && response.hasOwnProperty('message')){
		
		return response.message;
	}
	
	if(typeof(response) == 'string'){
		return response;
	}
	return "";
}


function loadFilterCall(data){
	if(isSuccess(data)){
		return data;
	}else{
		msgShow("加载提示",getMessage(data),"error");
		return {total:1,rows:[]};
	}
}


/**
 * 发送ajax请求
 * @param url 请求地址
 * @param data  请求参数
 * @param successFunc 请求成功后需要调用的javascript函数
 * @param errorFunc 请求失败后需要调用的javascript函数
 * @param completeFunc 成功或失败的javascript函数执行后，需要执行的javascript函数
 * @param container 需要更新内容的元素
 */
function ajax_post(url, data, successFunc, errorFunc, completeFunc, container){
	$.ajax({
        type: "POST",
        dataType: "html",
        cache: false,
        url: url,
        data: data,
        success: function(result, textStatus, jqXHR) {
        	if(successFunc){
        		try{
        			result = $.parseJSON(result);
                }catch(e){}
            	successFunc.call(this , result);
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	if(errorFunc){
        		errorFunc.call();
        	}else{
        		alert("服务器异常！");
        	}
        },
        complete : function(){
        	if(completeFunc){
        		completeFunc.call();
        	}
        }
    });
}

/**
 * 如果为undefined，在调用改参数之前执行 typeof(value) == "undefined"
 * @param value
 * @returns {Boolean}
 */
function isNull(value) {
    if (null == value|| "" == $.trim(value) || value == "null" || value.length == 0) {
        return true;
    } else {
        return false;
    }
}

function openNewWindow(url, title, width, height){
	if(typeof title != 'string') {
		title = '';
	}
	if(typeof height != 'number') {
		height = screen.availHeight;
	}
	if(typeof width != 'number') {
		width = screen.availWidth;
	}
	window.open(url,title,'height='+height+',width='+width+',toolbar=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes');
}

function getRowIndex(target) {
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}

function editdatarow(id,target) {
    $('#' + id).datagrid('beginEdit', getRowIndex(target));
}

function savedatarow(id,target) {
    $('#' + id).datagrid('endEdit', getRowIndex(target));
}

function canceldatarow(id,target) {
    $('#' + id).datagrid('cancelEdit', getRowIndex(target));
}

function fixDataGridWith(percent){
	return (document.body.clientWidth-200)*percent;
}

function resetSearchForm(id){
	$("#"+id).form("reset");
}