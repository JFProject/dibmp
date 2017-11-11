<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/distribution/distribution_list_myself.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<%!
	public static final String DISTRIBUTION_EDIT_URL = "pages/back/admin/distribution/edit_pre.action" ;
	public static final String DISTRIBUTION_LIST_DETAILS_URL = "pages/back/admin/distribution/list_details.action" ;
	public static final String DISTRIBUTION_DELETE_URL = "pages/back/admin/distribution/list_details.action" ;
	public static final String DISTRIBUTION_CLEAR_URL = "pages/back/admin/outputstorage/clear.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="7"/>
			<jsp:param name="msi" value="72"/> 
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;我的出库单</strong>
			</div>
			<div class="panel-body">
				<div>
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_search_bar.jsp"/>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class="text-center" style="width:10%;">出库单编号 </th> 
							<th class="text-left" style="width:18%;">申请标题</th> 
							<th class="text-center" style="width:8%;">省份</th>
							<th class="text-center" style="width:8%;">城市</th>
							<th class="text-center" style="width:8%;">商品数量</th>
							<th class="text-center" style="width:8%;">商品总价</th>
							<th class="text-center" style="width:8%;">客户姓名</th>
							<th class="text-center" style="width:8%;">提交人员</th>
							<th class="text-center" style="width:8%;">审核人员</th>
							<th class="text-center" style="width:8%;">状态</th>
							<th class="text-center" style="width:8%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allDistribution }" var="distribution">
							<tr>
								<th class="text-center" style="width:10%;">${distribution.dsid }</th> 
								<td class="text-left">${distribution.title }</td>
								<td class="text-center">${province[distribution.dsid].title }</td>
								<td class="text-center">${city[distribution.dsid].title }</td>
								<td class="text-center">${distribution.gnum }</td>
								<td class="text-center">${distribution.price }</td>
								<td class="text-center">${customer[distribution.dsid].name }</td>
								<td class="text-center">${applyMember[distribution.dsid].name }</td>
								<td class="text-center">${outMember[distribution.dsid].name }</td>
								<c:if test="${distribution.status == 1}">
									<td class="text-center"><span class="text-primary">待审核</span></td>
								</c:if>
								<c:if test="${distribution.status == 2}">
									<td class="text-center"><span class="text-danger">未通过</span></td>
								</c:if>
								<c:if test="${distribution.status == 3}">
									<td class="text-center"><span class="text-success">已完成</span></td>
								</c:if>
								<td class="text-center">
									<a href="<%=DISTRIBUTION_CLEAR_URL %>?dsid=${distribution.dsid }" class="btn btn-danger btn-xs">
										<span class="glyphicon glyphicon-share"></span>&nbsp;取消申请</a>
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
