$(function(){
	$("#searcher").on("click",function(){
		var cityCode= $("#cityCode option:selected").val();
		$("#city").val(cityCode);
		$("#myForm").submit();
	});
	
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		searcher(pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(pageNumber ){
		$("#pageNumber").val(pageNumber);
		$("#myForm").submit();
	}

	
});