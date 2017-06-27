$(function(){
	
	$("#passIn").on("click",function(){
		var me=$("input[name='resultIn']:checked").val();
		if(me=='pass'){
			$("#pass").show();
			$("#over").hide();
		}else{
			$("#over").show();
			$("#pass").hide();
		}
	});
	$("#overIn").on("click",function(){
		var me=$("input[name='resultIn']:checked").val();
		if(me=='over'){
			$("#over").show();
			$("#pass").hide();
		}else{
			$("#pass").show();
			$("#over").hide();
		}
	});
	function isPositiveNum(s){//是否为正整数  
	    var re = /^[0-9]*[1-9][0-9]*$/ ;  
	    return re.test(s)  
	} 
	$("#btn-danger").on("click",function(){
		if(confirm("确认提交?")){
		var remarks = $("#remarks").val();
		var applyNo = $("#applyNo").val();
		var type = 1;//拒绝
		if(remarks =='' || remarks==null || applyNo==null || applyNo==''){
			alert("缺少参数");
			return false;
		}
		var postdata = {
				"remarks":remarks,
				"applyNo":applyNo,
				"type":type
		};
		//进行更新操作
		$.post(root+"/service/loan/creditCheck",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.href=root+"/service/loan/getLoanApplyCreditList?creditState="+1; 
			}else{
				alert("更新失败!");
				return false;
			}
		});
		}	
	});
	
	$("#btn-success").on("click",function(){
		if(confirm("确认提交?")){
		var money = $("#money").val();
		var loanTerm =$("#loanTerm").val();
		var applyNo = $("#applyNo").val();
		var type = 2;//通过
		var postdata = {
				"money":money,
				"loanTerm":loanTerm,
				"applyNo":applyNo,
				"type":type
		};
		if(money =='' || money==null || loanTerm==null || loanTerm=='' || applyNo =='' || applyNo==null){
			alert("缺少参数");
			return false;
		}
		if(!isPositiveNum(money)){
			alert("金额必须为正整数");
			return false;
		}
		if(!isPositiveNum(loanTerm) || loanTerm<3 || loanTerm>36){
			alert("期限必须为3~36之间");
			return false;
		}
		
		//进行更新操作
		$.post(root+"/service/loan/creditCheck",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.href=root+"/service/loan/getLoanApplyCreditList"; 
			}else{
				alert("更新失败!");
				return false;
			}
		});
		}	
	});
});