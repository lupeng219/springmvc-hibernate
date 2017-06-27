$(function(){
	$("#searcher").on("click",function(){
		var tempUserid = $("#tempUserid").val();
		var tempName = $("#tempName").val();
		var tempIdCardNo = $("#tempIdCardNo").val();
		var tempPhone = $("#tempPhone").val();
		$("#loanUserid").val(tempUserid);
		$("#loanName").val(tempName);
		$("#loanPhone").val(tempPhone);
		$("#loanIdCardNo").val(tempIdCardNo);
		$("#myForm").submit();
	});
	
	//导出
	$("#export").on("click",function(){
		var temp = $("#tempParameName").val();
		$("#parameName").val(temp);
		$("#myForm").attr("action",root+"/service/employee/export?isCert=0");
		$("#myForm").submit();
		$("#myForm").attr("action",root+"/service/employee/getAllUsers");
	});
	
	var $ur_a = $(".pagination li a");
	$ur_a.on("click",function(){
		var tempUserid = $("#tempUserid").val();
		var tempName = $("#tempName").val();
		var tempIdCardNo = $("#tempIdCardNo").val();
		var tempPhone = $("#tempPhone").val();
		var pageNumber = $(this).attr("data-pagenum");
		searcher(tempUserid,tempName,tempPhone,tempIdCardNo,pageNumber);
		$("#myForm").submit();
	});
	
	//提交方法,(参数数值，当前页数)
	function searcher(tempUserid,tempName,tempPhone,tempIdCardNo,pageNumber ){
		$("#loanUserid").val(tempUserid);
		$("#loanName").val(tempName);
		$("#loanPhone").val(tempPhone);
		$("#loanIdCardNo").val(tempIdCardNo);
		$("#pageNumber").val(pageNumber);
		$("#myForm").submit();
	}
	
	$(".user-disable").on("click",function(){
		var state = $(this).data("state");
		var custNo = $(this).data("custno");
		var postdata = {
				"state":state,
				"custNo":custNo
		};
		$.post(root+"/service/user/modifyUserEnable",postdata,function(data){
			if(data.retCode){
				alert("操作成功!");
				window.location.reload(true);
			}else{
				alert("操作失败!");
			}
		});
	});
});
$(".user-edit").on("click", function () {
        $(".modal-body").find("input[type=text]").val("");
        var userid = $(this).data("custno");

        $('#user-editor').data("userid", userid);

        $.get(root + "/service/user/findById", {
            userid: userid
        }).then(function (data) {
            if (!data.retCode) {
                console.log(data.retMsg);
                return;
            }

            var userInfo = data.result;
			
            $("#user-editor .user-field").each(function () {
                var $this = $(this);

                var fieldName = $this.attr("id");
                var fieldValue = userInfo[fieldName];

                if (fieldValue == null)
                    return;

                $input = $this.find("input");

                if ($input.length == 1) {
                    $input.val(fieldValue);

                } else {
                    if ($input.attr("type") == "radio") {
                        $input.each(function () {
                            var $thisInput = $(this);

                            if ($thisInput.val() == fieldValue) {
                                $thisInput.attr("checked", "checked");
                            }

                        });
                    }
                }
            });
			 var imgurl=$("#imgurl").val();
			 $("#img").attr("src",imgurl);
            $('#user-editor').modal({
                keyboard: false,
                backdrop: "static"
            });
        });
        return false;
       
    });

 
    
    $(function(){
    	$("#delImg").on("click",function(){
    		 $("#imgurl").val(" ");
			 $("#img").attr("src","");
    	})
    });
   
    