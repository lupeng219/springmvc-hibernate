$(function () {
    $("#searcher").on("click", function () {
        $("#myForm").submit();
    });

    var $ur_a = $(".pagination li a");
    $ur_a.on("click", function () {
        var pageNumber = $(this).attr("data-pagenum");
        searcher(pageNumber);
        $("#myForm").submit();
    });

    //提交方法,(参数数值，当前页数)
    function searcher(pageNumber) {
        $("#pageNumber").val(pageNumber);
        $("#myForm").submit();
    }


    //修改动态
    var items;
    $(".btn-update").on("click", function () {
        var id = $(this).attr("data-custno");
        $.post(root + "/service/sns/getNewsInfo", {
            "id": id
        }, function (data) {
            if (data.retCode) {
                $("#updateNews").show();
                var e = data.news;
                $("#title").val(e.title);
                $("#id").val(e.id);
                $("#content").val(e.content);
                $("#begin_time").val(e.created);
                var str = e.pic;
                var iniPic =e.initialPic;
                $(".addPics").html("");
                if(str!=null && str!=''){
            	     items = str.split(",");
            	     pics = iniPic.split(",");
                	 for (var i = 0; i < items.length; i++) {
                         $(".addPics").append("<div class='contentPic'><label class='control-label col-lg-3'>图片" + (i + 1) + "</label><div class='col-xs-8 text-left' style='width:75%;margin-bottom: 20px;'><input type='text' name ='"+pics[i]+"' class='form-control addressPic' value='" + items[i] + "'/><img src='" + items[i] + "' id='img' alt='暂无图片' width='200px' height='200px' style='margin-top: 10px;'><button class='btn btn-primary delBtn' type='button' style='margin:10px 30px'>删除图片</button></div></div>");
                         var delBtn = $(".delBtn");
                         delBtn.on('click', function () {
                             $(this).parents(".contentPic").html("");
                         })
                     }
                }else{
                	items=[];
                	$(".addPics").append("<div class='contentPic'><label class='control-label col-lg-3'>图片" + "</label><div class='col-xs-8 text-left' style='width:75%;margin-bottom: 20px;'><input type='text' class='form-control addressPic' value='"  + "'/><img src='" + "' id='img' alt='暂无图片' width='200px' height='200px' style='margin-top: 10px;'><button class='btn btn-primary delBtn' type='button' style='margin:10px 30px'>删除图片</button></div></div>");
                }
            }
        });
    });
    //保存动态
    $("#saveEmployee").on("click", function () {
        var addressPic = $(".addressPic");
        var addressPics = [];
       
    	 for (var j = 0; j < items.length; j++) {
         	
             addressPics.push(addressPic.eq(j).attr("name"));
             
          }
        
        
        var pic = addressPics.join(",").replace(/\,$/, "");
        var id = $("#id").val();
        var content = $("#content").val();
        var created = $("#begin_time").val();
        var title = $("#title").val();
        var postdata = {
            "id": id,
            "content": content,
            "created_time": created,
            "pic": pic,
            "title": title

        };
        //进行跟新操作
        $.post(root + "/service/sns/updateNews", postdata, function (data) {
            if (data.retCode) {
                alert(data.retMsg);
                window.location.reload(true);
            } else {
                alert("更新失败!");
                return false;
            }
        });

    });
    //启用禁用
    $(".btn-disable").on("click", function () {
        var id = $(this).attr("data-custno");
        var status = $(this).attr("data-state");
        $.post(root + "/service/sns/deleteNews", {
            "id": id,
            "status": status
        }, function (data) {
            if (data.retCode) {
                alert("修改成功!");
                window.location.reload(true);
            } else {
                alert("修改失败!");
                return false;
            }
        });
    });
    //点赞记录
    $(".btn-goodInfo").on("click", function () {
        var id = $(this).attr("data-custNo");
        $.post(root + "/service/sns/getGoodInfoByNewsId", {
            "id": id
        }, function (data) {
            $(".matter").html(data);
            goodpage();
        });
    });

    function goodpage() {

        var $ur_a = $(".paginationTwo li a");
        $ur_a.on("click", function () {
            var pageNumber = $(this).attr("data-pagenum");
            var id = $("#id").val();
            $.post(root + "/service/sns/getGoodInfoByNewsId", {
                "id": id,
                "pageNumber": pageNumber
            }, function (data) {
                $(".matter").html(data);
                goodpage();
            });
        });
    }
    //评论记录
    $(".btn-commentInfo").on("click", function () {
        var id = $(this).attr("data-custNo");
        $.post(root + "/service/sns/getCommentListInfoByNewsId", {
            "id": id
        }, function (data) {
            $(".matter").html(data);
            commentpage();
        });
    });

    function commentpage() {

        var $ur_a = $(".paginationComment li a");
        $ur_a.on("click", function () {
            var pageNumber = $(this).attr("data-pagenum");
            var id = $("#id").val();
            $.post(root + "/service/sns/getCommentListInfoByNewsId", {
                "id": id,
                "pageNumber": pageNumber
            }, function (data) {
                $(".matter").html(data);
                commentpage();
            });
        });
    }

});