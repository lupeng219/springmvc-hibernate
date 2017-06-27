<!DOCTYPE html>
<html lang="en">
<head>
  	<#include "./common/common_src.ftl">
    <title>后台管理系统</title>
    <style>
    	.form-inline .form-control{
    		display: inline-block;
			width: auto;
			vertical-align: middle;
    	}
    </style>
</head>
<body>
	<#include "./common/common_header.ftl">
	<#include "./common/common_menu.ftl">                                       
	<div class="col-md-12">
        <div class="widget">
            
            <div class="widget-content">
               
            </div>
        </div>
       
	</div>
	<#include "./common/common_footer.ftl">
	<script>seajs.use("index/index");</script>
	<script type="text/javascript" src="${root}/js/public/echarts-all.js"></script>
	
</body>
</html>