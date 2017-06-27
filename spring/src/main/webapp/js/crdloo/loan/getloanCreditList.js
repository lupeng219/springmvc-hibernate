$(function () {
    $("#searcher").on("click", function () {
    	var creditState= $("#loanApplyState option:selected").val();
		$("#creditState").val(creditState);
		
		var formatBackCityID=$("#formatBackCityID option:selected").val();
		$("#custAddress").val(formatBackCityID);
		
		var custTypeDataID=$("#custTypeDataID option:selected").val();
		$("#custType").val(custTypeDataID);
		
        $("#myForm").submit();
    });

    var $ur_a = $(".pagination li a");
    $ur_a.on("click", function () {
        var pageNumber = $(this).attr("data-pagenum");
        searcher(pageNumber);
       
    });

    //提交方法,(参数数值，当前页数)
    function searcher(pageNumber) {
        $("#pageNumber").val(pageNumber);
        
        var creditState= $("#loanApplyState option:selected").val();
		$("#creditState").val(creditState);
		
		var formatBackCityID=$("#formatBackCityID option:selected").val();
		$("#custAddress").val(formatBackCityID);
		
		var custTypeDataID=$("#custTypeDataID option:selected").val();
		$("#custType").val(custTypeDataID);
		
        $("#myForm").submit();
    }
    //订单详情(信审)
	$(".btn-update").on("click",function(){
		var applyNo = $(this).attr("data-applyNo");
		if(applyNo ==''){
			return "系统错误";
		}
		window.location.href=root+"/service/loan/getCreditLoanInfo?applyNo="+applyNo; 
	});
	//导出
	$("#export").on("click",function(){
		if(confirm("确认导出操作?")){
		var creditState= $("#loanApplyState option:selected").val();
		$("#creditState").val(creditState);
		$("#myForm").attr("action",root+"/service/loan/exportLoanApply");
		$("#myForm").submit();
		$("#myForm").attr("action",root+"/service/loan/getLoanApplyCreditList");
		$.cookie('updateStatus', 'success'); 
		var timer =  setInterval(c,1000);
		function c(){
			if($.cookie('updateStatus')=="success"){
				clearInterval(timer);
				$.cookie('updateStatus', null); 
				window.location.href=root+"/service/loan/getLoanApplyCreditList?creditState="+creditState; 
			}
		}
		}	
	});
	
});