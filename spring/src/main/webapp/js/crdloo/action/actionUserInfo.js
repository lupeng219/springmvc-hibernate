$(function(){
	
	//启用禁用
	$(".btn-disable").on("click",function(){
		var uid = $(this).attr("data-uid");
		var actid = $(this).attr("data-actid");
		var status = $(this).attr("data-state");
		$.post(root+"/service/action/deleteActionUser",{"uid":uid,"actid":actid,"status":status},function(data){
			if(data.retCode){
				alert("修改成功!");
				window.location.reload(true);
			}else{
				alert("修改失败!");
				return false;
			}
		});
	});
});