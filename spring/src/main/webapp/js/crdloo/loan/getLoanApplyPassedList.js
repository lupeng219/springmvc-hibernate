$(function () {
    $("#searcher").on("click", function () {
    	var formatBackCityID=$("#formatBackCityID option:selected").val();
		$("#custAddress").val(formatBackCityID);
    	
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
        var formatBackCityID=$("#formatBackCityID option:selected").val();
		$("#custAddress").val(formatBackCityID);
        $("#myForm").submit();
    }
    //订单详情(财务)
	$(".btn-update").on("click",function(){
		var applyNo = $(this).attr("data-applyNo");
		if(applyNo ==''){
			return "系统错误";
		}
		window.location.href=root+"/service/loan/getLoanPassedInfo?applyNo="+applyNo; 
	});
});