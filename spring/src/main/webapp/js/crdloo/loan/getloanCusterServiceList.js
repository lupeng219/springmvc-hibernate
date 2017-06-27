$(function () {
    $("#searcher").on("click", function () {
    	var applyState= $("#loanApplyState option:selected").val();
		$("#applyState").val(applyState);
		
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
        var applyState= $("#loanApplyState option:selected").val();
		$("#applyState").val(applyState);
		
		var formatBackCityID=$("#formatBackCityID option:selected").val();
		$("#custAddress").val(formatBackCityID);
		
		var custTypeDataID=$("#custTypeDataID option:selected").val();
		$("#custType").val(custTypeDataID);
		
        $("#myForm").submit();
    }
    //订单详情(客服)
	$(".btn-update").on("click",function(){
		var applyNo = $(this).attr("data-applyNo");
		if(applyNo ==''){
			return "系统错误";
		}
		window.location.href=root+"/service/loan/getCusterServiceLoanInfo?applyNo="+applyNo; 
	});
});