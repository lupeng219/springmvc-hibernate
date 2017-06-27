$(function(){
	$("#searcher").on("click",function(){
		var cityCode= $("#cityCode option:selected").val();
		$("#city").val(cityCode);
		var provinceCode= $("#provinceCode option:selected").val();
		$("#province").val(provinceCode);
		$("#myForm").submit();
	});
	
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var pageNumber = $(this).attr("data-pagenum");
		searcher(pageNumber);
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(pageNumber ){
		$("#pageNumber").val(pageNumber);
		var cityCode= $("#cityCode option:selected").val();
		$("#city").val(cityCode);
		var provinceCode= $("#provinceCode option:selected").val();
		$("#province").val(provinceCode);
		$("#myForm").submit();
	}
	
$(function(){
	
	$("#provinceCode").change(function(){
		var provinceCode= $("#provinceCode option:selected").val();
		
		$.post(root+"/service/action/getCityInfo",{"code":provinceCode},function(data){
			var city="";
			if(data.retCode){
				var c=data.cityAll;
				$.each(c,function(i,v){
					city+="<option value='"+c[i].code+"'>"+c[i].chName+"</option>";
					
				});
				$("#cityCode").html(city);
				console.log($("#cityCode").text());
				
			}else{
				city+="<option value=''>请选择城市</option>";
				$("#cityCode").html(city);
				console.log($("#cityCode").text());
			}
		});
	})
	
});	
	//修改
	$(".btn-update").on("click",function(){
		//$("#updateEmployee").show();
		var id = $(this).attr("data-custno");
		$.post(root+"/service/action/getActionInfo",{"id":id},function(data){
			if(data.retCode){
				$("#updateEmployee").show();
				var e = data.activity;
				var title = $("#title").val(e.title);
				var content = $("#content").val(e.content);
				var location = $("#location").val(e.location);
				var begin_time = $("#begin_time").val(e.begin_time);
				var phone = $("#cityname").val(e.cityname);
				var id = $("#id").val(e.activity_id);
			}
		});
	});
	//添加
	$("#saveEmployee").on("click",function(){
		var id = $("#id").val();
		var content = $("#content").val();
		var begin_time = $("#begin_time").val();
		var cityname = $("#cityname").val();
		var title = $("#title").val();
		var location =$("#location").val();
		var postdata = {
				"id":id,
				"content":content,
				"begin_time":begin_time,
				"cityname":cityname,
				"location":location,
				"title":title
				
		};
		//进行跟新操作
		$.post(root+"/service/action/updateAction",postdata,function(data){
			if(data.retCode){
				alert(data.retMsg);
				window.location.reload(true);
			}else{
				alert("更新失败!");
				return false;
			}
		});
		
	});
	//启用禁用
	$(".btn-disable").on("click",function(){
		var id = $(this).attr("data-custno");
		var status = $(this).attr("data-state");
		$.post(root+"/service/action/deleteAction",{"id":id,"status":status},function(data){
			if(data.retCode){
				alert("修改成功!");
				window.location.reload(true);
			}else{
				alert("修改失败!");
				return false;
			}
		});
	});
	$(".btn-userInfo").on("click",function(){
		var id = $(this).attr("data-custNo");
		$.post(root+"/service/action/getActionMemberByActionId",{"id":id},function(data){
			$(".matter").html(data);
			productNos();
		});
	});
	function productNos(){
		var $ur_a = $(".paginationTwo li a");
		$ur_a.on("click",function(){
			var pageNumber = $(this).attr("data-pagenum");
			var id = $("#id").val();
			$.post(root+"/service/action/getActionMemberByActionId",{"id":id,"pageNumber":pageNumber},function(data){
				$(".matter").html(data);
				productNos();
			});
		});
	}
	
});