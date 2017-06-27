$(function(){
	$("#btn").on("click",function(){
		if(confirm("确认提交?")){
		var feedback = $("#feedback option:selected").val();
		var applyNo = $("#applyNo").val();
		if(feedback==""){
			alert("请选择订单状态");
			return false;
		}
		var postdata = {
				"feedback":feedback,
				"applyNo":applyNo
		};
		//进行更新操作
		$.post(root+"/service/loan/custfeedback",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.href=root+"/service/loan/getLoanApplyCusterServiceList"; 
			}else{
				alert(data.retMsg);
				return false;
			}
		});
		}	
	});
});