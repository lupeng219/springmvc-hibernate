$(function(){
	init ();
	   function init (){
	    	$("#tabBanner").show();
	    	$(".tabIndex").hide();
	    };
	$("#upload").change(function(){
		var filepath =/^.*\.(jpg|gif|png|bmp)$/i;
		var fileValue=$(this).val();
		if(!filepath.test(fileValue)){
			alert("请选择正确的图片!");
			return false;
		}else{
			var options = {
					"url":root+"/service/index/uploadPic",
					"type":"POST",
					"data":{file:fileValue},
					"dataType":"json",
					"success":function(data){
						if(!data.retCode){
							alert(data.retMsg);
							window.location.reload(true);
							return;
						}else{
							window.location.reload(true);
						}
						
					},
					"error":function(err,errmsg){
	//					console.info(err);
	//					console.info(errmsg);
					}
			};
			$("#indexPicture").ajaxSubmit(options);
		}
	});
	
	$("input[name='indexSort']").on("change",function(){
		var indexSort = $(this).val();
		var pictureId = $(this).attr("data-pictureid");
		$.post(root+"/service/index/updatePic",{"indexSort":indexSort,"id":pictureId},function(data){
			if(data.retCode){
				window.location.reload(true);
			}
		});
		
	});
	
	$("input[name='activityUrl']").on("change",function(){
		var activityUrl = $(this).val();
		var pictureId = $(this).attr("data-pictureid");
		$.post(root+"/service/index/updatePic",{"actionUrl":activityUrl,"id":pictureId},function(data){
			if(data.retCode){
				window.location.reload(true);
			}
		});
	});
	
	$(".pictureType").on("change",function(){
		var type = $(this).val();
		var pictureId = $(this).attr("data-pictureid");
		$.post(root+"/service/index/updatePic",{"type":type,"id":pictureId},function(data){
			if(data.retCode){
				window.location.reload(true);
			}
		});
	});
	
	$(".deleteSapn").on("click",function(){
		var pictureId = $(this).attr("data-pictureid");
		var pathUrl = $("#pathUrl"+pictureId).val();
		var flag = confirm("确定要清空数据吗？");
		if(flag){
			$.post(root+"/service/index/deletePicture",{"id":pictureId,},function(data){
				if(data.retCode){
					window.location.reload(true);
				}
			});
		}
	});
});