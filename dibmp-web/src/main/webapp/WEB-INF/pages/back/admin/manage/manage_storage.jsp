<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%!
	public static final String STORAGE_AUDIT_URL = "pages/back/admin/manage/storage_audit.action" ;
%>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/manage/manage_storage.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="6"/>
			<jsp:param name="msi" value="61"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;入库清单</strong>
			</div>
			<div class="panel-body">
				<div>
					<table class="table table-striped table-bordered table-hover">
						<tr> 
							<td style="width:150px;"><strong>入库单编号：</strong></td>
							<td>${storageApply.said }</td>
						</tr>
						<tr> 
							<td style="width:150px;"><strong>入库标题：</strong></td>
							<td>${storageApply.title }</td>
						</tr>
						<tr>
							<td><strong>存入仓库名称：</strong></td>
							<td><span id="showWarehouse-${storageApply.wid }">${provinceName } ${cityName} ${widName }</span></td>
						</tr>
						<tr>
							<td><strong>仓库类型：</strong></td>
							<td>${wiidName }</td>
						</tr>
						<tr>
							<td><strong>入库商品总价：</strong></td>
							<td>￥${totalPrice }</td>
						</tr>
						<tr>
							<td><strong>入库单备注信息：</strong></td>
							<td>${storageApply.note }</td>
						</tr>
					</table>
				</div>
				<c:if test="${storageApply.status == 2}">
					<div style="text-align:center">
						<a href="<%=STORAGE_AUDIT_URL %>?flag=1&said=${storageApply.said}" class="btn btn-primary btn-xs">
							<span class="glyphicon glyphicon-edit"></span>&nbsp;允许入库</a>
						<a href="<%=STORAGE_AUDIT_URL %>?flag=2&said=${storageApply.said}" class="btn btn-danger btn-xs">
							<span class="glyphicon glyphicon-edit"></span>&nbsp;拒绝入库</a>
					</div>
				</c:if>
				<c:if test="${storageApply.status == 0}">
					<div style="text-align:center;color:red;text-size:20px">用户未提交</div>
				</c:if>
				<c:if test="${storageApply.status == 1}">
					<div style="text-align:center;color:red;text-size:20px">财务未审核</div>
				</c:if>
				<c:if test="${storageApply.status == 3}">
					<div style="text-align:center;color:red;text-size:20px">审核未通过</div>
				</c:if>
				<c:if test="${storageApply.status == 4}">
					<div style="text-align:center;color:green;text-size:20px">订单已完成</div>
				</c:if>
				<div>&nbsp;</div>
				<div>
					<table class="table table-condensed" id="detailsTab">
						<thead>
							<tr>
								<th class="text-center" style="width:10%;">商品编号</th> 
								<th class="text-left" style="width:40%;">商品名称</th> 
								<th class="text-center" style="width:10%;">入库数量</th>
								<th class="text-center" style="width:15%;">商品单价（￥）</th>
								<th class="text-center" style="width:15%;">单位重量（g）</th>
								<th class="text-center" style="width:10%;">总价（￥）</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allStorageApplyDetails }" var="storageApplyDetails">
								<tr class="text-primary">
									<td class="text-center">${storageApplyDetails.gid}</td>
									<td class="text-left">${storageApplyDetails.name}</td>
									<td class="text-center">${storageApplyDetails.num}</td>
									<td class="text-center">${storageApplyDetails.price}</td>
									<td class="text-center">${storageApplyDetails.weight}</td>
									<td class="text-center">${price[storageApplyDetails.sadid] }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
