$(function(){
	$("#searcher").on("click",function(){
		var tempUserid = $("#tempUserid").val();
		var me=$("input[name='iscert']:checked").val();
		$("#Userid").val(tempUserid);
		$("#isCert").val(me);
		$("#myForm").submit();
	});
	
	//导出
	$("#export").on("click",function(){
		var temp = $("#tempParameName").val();
		$("#parameName").val(temp);
		$("#myForm").attr("action",root+"/service/employee/export?isCert=0");
		$("#myForm").submit();
		$("#myForm").attr("action",root+"/service/employee/getAllUsers");
	});
	
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var tempUserid = $("#tempUserid").val();
		var me=$("input[name='iscert']:checked").val();
		var pageNumber = $(this).attr("data-pagenum");
		searcher(tempUserid,me,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(tempUserid,me,pageNumber ){
		$("#Userid").val(tempUserid);
		$("#isCert").val(me);
		$("#pageNumber").val(pageNumber);
		$("#myForm").submit();
	}
	
	$(".user-disable").on("click",function(){
		var state = $(this).data("state");
		var custNo = $(this).data("custno");
		var postdata = {
				"state":state,
				"custNo":custNo
		};
		$.post(root+"/service/focus/updateFocus",postdata,function(data){
			if(data.retCode){
				alert("操作成功!");
				window.location.reload(true);
			}else{
				alert("操作失败!");
			}
		});
	});
});

    