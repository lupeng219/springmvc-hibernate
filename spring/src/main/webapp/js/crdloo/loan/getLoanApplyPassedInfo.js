$(function(){
	function isPositiveNum(s){//是否为正整数  
	    var re = /^[0-9]*[1-9][0-9]*$/ ;  
	    return re.test(s)  
	} 
	$("#saveInfo").on("click",function(){
		if(confirm("确认提交?")){
		var applyNo = $("#applyNo").val();
		var infactMoney = $("#infactMoney").val();
		var postdata = {
				"applyNo":applyNo,
				"infactMoney":infactMoney
		};
		if(infactMoney =='' || infactMoney==null || applyNo==null || applyNo==''){
			alert("缺少参数");
			return false;
		}
		if(!isPositiveNum(infactMoney)){
			alert("金额必须为正整数");
			return false;
		}
		//进行更新操作
		$.post(root+"/service/loan/savePlayInfo",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.href=root+"/service/loan/getLoanApplyPassedList"; 
			}else{
				alert(data.retMsg);
				return false;
			}
		});
		}
	});
});