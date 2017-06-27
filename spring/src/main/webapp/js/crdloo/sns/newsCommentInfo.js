$(function(){
	
	//启用禁用
	$(".btn-disable").on("click",function(){
		var id = $(this).attr("data-id");
		var newsid = $(this).attr("data-newsid");
		var status = $(this).attr("data-state");
		var userid = $(this).attr("data-userid");
		$.post(root+"/service/sns/updateCommentStatus",{"id":id,"newsid":newsid,"status":status,"userid":userid},function(data){
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