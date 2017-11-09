<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/storage/storage_list_myself.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<%!
	public static final String STORAGE_SUBMIT_URL = "pages/back/admin/storage/submit.action" ;
	public static final String STORAGE_EDIT_URL = "pages/back/admin/storage/edit_pre.action" ;
	public static final String STORAGE_LIST_DETAILS_URL = "pages/back/admin/storage/list_details.action" ;
	public static final String STORAGE_DELETE_URL = "pages/back/admin/storage/remove.action" ;
	public static final String STORAGE_RESET_URL = "pages/back/admin/storage/reset.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="3"/>
			<jsp:param name="msi" value="32"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;编辑入库清单</strong>
			</div>
			<div class="panel-body">
				<div>
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_search_bar.jsp"/>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class="text-center" style="width:10%;">入库单编号 </th> 
							<th class="text-left" style="width:15%;">申请标题</th> 
							<th class="text-left" style="width:15%;">入库仓库</th>
							<th class="text-center" style="width:10%;">商品类型</th>
							<th class="text-center" style="width:10%;">申请状态</th>
							<th class="text-center" style="width:10%;">商品数量</th>
							<th class="text-center" style="width:30%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allStorageApply }" var="storageApply">
							<tr>
								<th class="text-center" style="width:10%;">${storageApply.said }</th> 
								<td class="text-left">
									<c:if test="${storageApply.status == 0 or storageApply.status == 3}">
										<a href="<%=STORAGE_EDIT_URL%>?said=${storageApply.said }">${storageApply.title }</a>
									</c:if>
									<c:if test="${storageApply.status == 1 or storageApply.status == 2 or storageApply.status == 4}">
										${storageApply.title }
									</c:if>
								</td>
								<td class="text-left">${allWarehouse[storageApply.said].address }</td>
								<td class="text-center">${allWitem[storageApply.said].title }</td>
								<c:if test="${storageApply.status == 0}">
									<td class="text-center"><span class="text-muted">未提交</span></td>
								</c:if>
								<c:if test="${storageApply.status == 1}">
									<td class="text-center"><span class="text-primary">待财务审核</span></td>
								</c:if>
								<c:if test="${storageApply.status == 2}">
									<td class="text-center"><span class="text-info">待库管审核</span></td>
								</c:if>
								<c:if test="${storageApply.status == 3}">
									<td class="text-center"><span class="text-danger">未通过</span></td>
								</c:if>
								<c:if test="${storageApply.status == 4}">
									<td class="text-center"><span class="text-success">已完成</span></td>
								</c:if>
								<td class="text-center">${allCount[storageApply.said] }</td>
								<td class="text-center">
									<c:if test="${storageApply.status == 0}">
										<a href="<%=STORAGE_SUBMIT_URL%>?said=${storageApply.said }" class="btn btn-primary btn-xs">
											<span class="fa fa-rocket"></span>&nbsp;提交申请</a>
										<a href="<%=STORAGE_LIST_DETAILS_URL%>?said=${storageApply.said }" class="btn btn-warning btn-xs">
											<span class="fa fa-th-list"></span>&nbsp;入库清单</a>
										<a href="<%=STORAGE_DELETE_URL%>?said=${storageApply.said }" class="btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-trash"></span>&nbsp;删除申请</a>
									</c:if>
									<c:if test="${storageApply.status != 0}">
										<a href="<%=STORAGE_LIST_DETAILS_URL%>?said=${storageApply.said }" class="btn btn-primary btn-xs">
											<span class="glyphicon glyphicon-th"></span>&nbsp;查看详情</a>
									</c:if>
									<c:if test="${storageApply.status == 1 or storageApply.status == 2}">
										<a href="<%=STORAGE_RESET_URL%>?said=${storageApply.said }" class="btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-share"></span>&nbsp;取消申请</a>
									</c:if>
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
