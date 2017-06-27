<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!-- class="open",当前选项添加open类 -->
<div class="lever-one clearfix">
    <ul id="qzbnav">
        <#list result as indexMenu>
			<!-- 一级菜单 -->
			<@security.authorize ifAnyGranted="${indexMenu.powerName}">
					<#list indexMenu.subMenuName as sub>
						<#if sub_index == 0>
							<#if sub.powerUrl ??>
								<li><a href="javascript:void(0);" ><i class="${indexMenu.className}"></i><span>${indexMenu.menuName}</span></a></li>
							<#else>
								<li><a href="javascript:void(0);" ><i class="${indexMenu.className}"></i><span>${indexMenu.menuName}</span></a></li>
							</#if>
						</#if>
					</#list>
			</@security.authorize>
  		</#list>
    </ul>
</div>
<#list result as indexMenu>
	<!-- 一级菜单权限-->
	<@security.authorize ifAnyGranted="${indexMenu.powerName}">
		<div class="sidebar">
		    <div class="sidebar-dropdown"><a href="#">导航</a></div>
		    	 <div id="nav">
			        <ul>
			        	<#list indexMenu.subMenuName as sunMenu>
			        		<!--二级菜单权限-->
			        		<@security.authorize ifAnyGranted="${sunMenu.powerName}">
			        			<#if sunMenu.menuName!='index'>
				        				<#if sunMenu.powerUrl ??>
				        					<#if (sunMenu.powerName?substring(sunMenu.powerName?last_index_of('_'))!='BUT')>
				           						<li><a href="${root}${sunMenu.powerUrl}">${sunMenu.menuName}</a></li>
				           					</#if>
					           			<#else>
					           				<#if (sunMenu.powerName?substring(sunMenu.powerName?last_index_of('_'))!='BUT')>
					           					<li><a href="">${sunMenu.menuName}</a></li>
					           				</#if>
					           			</#if>
			        			</#if>
			           		</@security.authorize>
			           	</#list>
			        </ul>
		    	</div>
		</div>
	</@security.authorize>
</#list>
