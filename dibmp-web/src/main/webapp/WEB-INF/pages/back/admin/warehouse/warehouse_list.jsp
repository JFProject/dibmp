<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/warehouse/warehouse_list.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<%!
	public static final String WAREHOUSE_EDIT_URL = "pages/back/admin/warehouse/edit_pre.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="1"/>
			<jsp:param name="msi" value="12"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;仓库信息列表</strong>
			</div>
			<div class="panel-body">
				<div>
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_search_bar.jsp"/>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class="text-left" style="width:15%;">仓库名称</th> 
							<th class="text-left" style="width:25%;">仓库地址</th>
							<th class="text-center" style="width:10%;">存储分类</th>
							<th class="text-center" style="width:10%;">存储上限</th>
							<th class="text-center" style="width:10%;">当前存储量</th>
							<th class="text-center" style="width:10%;">管理员姓名</th>
							<th class="text-left" style="width:20%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allWarehouse}" var="warehouse">
							<tr>
								<td class="text-left">${warehouse.name}</td>
								<td class="text-left">${warehouse.address}</td>
								<td class="text-center">
								<c:forEach items="${allWitem }" var="witem">
									${witem.wiid == warehouse.wiid?witem.title:""}
								</c:forEach>
								</td>
								<td class="text-center">${warehouse.maximum}</td>
								<td class="text-center">${warehouse.currnum}</td>
								<td class="text-center" id="admin-${warehouse.wid}"><span id="mid_${warehouse.admin}" style="cursor:pointer;">${allMember[warehouse.admin] }</span></td> 
								<td class="text-left">
									<button id="editadmin-${warehouse.wid}" class="btn btn-primary btn-xs">
											<span class="glyphicon glyphicon-plus-sign"></span>&nbsp;编辑库管</button>
											
									<a href="<%=WAREHOUSE_EDIT_URL%>?wid=${warehouse.wid}" id="editinfo-${warehouse.wid}" class="btn btn-warning btn-xs">
											<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑信息</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="splitBarDiv" style="float:right">
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_page_bar.jsp"/> 
				</div>
			</div>
			<div class="panel-footer" style="height:100px;">
				<jsp:include page="/WEB-INF/pages/plugins/include_alert.jsp"/>
			</div>
		</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_info_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_dept_list_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
