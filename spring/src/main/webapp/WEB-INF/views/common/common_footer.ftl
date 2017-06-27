<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- Copyright info -->
                <p class="copy">Copyright &copy; 2016 | <a href="#">Your Site</a></p>
            </div>
        </div>
    </div>
</footer>
<div class="projectWindowBg" id="system-alert-bg" style="display:none;"></div>
<div class="projectWindowBg" style="display:none;"></div>
<div id="contentLoading" style="display:none;position:fixed;z-index:99999;width:100%;height:100%;top:0;bottom:0;left:0;right:0;
	overflow: hidden;text-align:center;">
	<img src="${root}/img/loading.gif" style="margin:auto;margin-top:20%;"/>
</div>

<script src="${root}/js/public/jquery.min.js"></script>
<script src="${root}/js/public/bootstrap.js"></script>
<script src="${root}/js/public/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${root}/js/public/jquery.rateit.min.js"></script>
<script src="${root}/js/public/jquery.prettyPhoto.js"></script>
<script src="${root}/js/public/jqueryValidation/js/jquery.validationEngine.js"></script>
<script src="${root}/js/public/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
<script src="${root}/js/public/sparklines.js"></script>
<script src="${root}/js/public/jquery.cleditor.min.js"></script>
<script src="${root}/js/public/bootstrap-switch.min.js"></script>
<script src="${root}/js/public/filter.js"></script>
<script src="${root}/js/public/custom.js"></script>
<script src="${root}/js/public/sea.js" type="text/javascript" ></script>
<script src="${root}/js/public/jquery.form.js" type="text/javascript"></script>
<script src="${root}/js/public/jquery.cookie.pack.js" type="text/javascript"></script>
<script src="${root}/js/public/other.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="${root }/js/public/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    //Set configuration--------------引入seajs之前需引入jQuery库，该代码可放入公有页面
    seajs.config({
        base: root+"/js/crdloo/",				//base表示基址寻址时的基址路径
        alias: {							//alias可以对较长的常用路径设置缩写
            //"jquery": "jquery.min.js"
        },
        charset: 'utf-8',					//charset表示下载js时script标签的charset属性
        timeout: 20000,						//timeout表示下载文件的最大时长，以毫秒为单位
        debug: false						//debug表示是否工作在调试模式下
    });
</script>