<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/storage/storage_list_details.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
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
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;我的入库申请单</strong>
			</div>
			<div style="display:none" id="said">${storageApply.said }</div>
			<div style="display:none" id="wiid">${storageApply.wiid }</div>
			<div class="panel-body">
				<div>
					<table class="table table-striped table-bordered table-hover">
						<tr> 
							<td style="width:150px;"><strong>入库标题：</strong></td>
							<td>${storageApply.title }</td>
						</tr>
						<tr>
							<td><strong>存入仓库名称：</strong></td>
							<td>${address }</td>
						</tr>
						<tr>
							<td><strong>仓库类型：</strong></td>
							<td>${title }</td>
						</tr>
						<tr>
							<td><strong>备注信息：</strong></td>
							<td>${storageApply.note }</td>
						</tr>
						<c:if test="${storageApply.status == 0 }">
							<tr>
								<td><strong>入库操作：</strong></td>
								<td>
									<button id="addbut" class="btn btn-danger btn-xs">
											<span class="glyphicon glyphicon-edit"></span>&nbsp;追加商品</button>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
				<c:if test="${storageApply.status == 0 }">
					<div><font>请添加<span id="title" style="color:red;font-size:16px">${title }</span>类型的商品</font></div>
					<div>&nbsp;</div>
				</c:if>
				<div>
					<table class="table table-condensed" id="detailsTab">
						<thead>
							<tr>
								<th class="text-left" style="width:15%;">商品编号</th> 
								<th class="text-left" style="width:20%;">商品名称</th> 
								<th class="text-left" style="width:15%;">入库数量</th>
								<th class="text-left" style="width:15%;">商品单价（元）</th>
								<th class="text-left" style="width:15%;">单位重量（g）</th>
								<c:if test="${storageApply.status == 0 }">
									<th class="text-left" style="width:20%;">操作</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${goodsTemps }" var="storageApplyDetails">
								<tr id='dettr-${storageApplyDetails.gid }' class='text-success'>
									<td><input type='text' id='gid-${storageApplyDetails.gid }' value='${storageApplyDetails.gid }'/></td>
									<td><input type='text' id='name-${storageApplyDetails.gid }' value='${storageApplyDetails.name }'/></td>
									<td><input type='text' id='amount-${storageApplyDetails.gid }' value='${storageApplyDetails.num }' maxlength='7' size='8'/></td>
									<td><input type='text' id='price-${storageApplyDetails.gid }' value='${storageApplyDetails.price }' maxlength='7' size='8'/></td>
									<td><input type='text' id='weight-${storageApplyDetails.gid }' value='${storageApplyDetails.weight }' maxlength='7' size='8'/></td>
									<c:if test="${storageApply.status == 0 }">
										<td>
											<button id='save-${storageApplyDetails.gid }' class='btn btn-primary btn-xs'>
												<span class='glyphicon glyphicon-edit'></span>&nbsp;保存</button>
											<button id='remove-${storageApplyDetails.gid }' class='btn btn-danger btn-xs'>
											<span class='glyphicon glyphicon-edit'></span>&nbsp;移除</button>
										</td>
									</c:if>
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
