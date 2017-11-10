<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>

<script type="text/javascript" src="js/pages/back/admin/customer/customer_list.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="2"/>
			<jsp:param name="msi" value="22"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;客户信息列表</strong>
			</div>
			<div class="panel-body">
				<div>
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_search_bar.jsp"/>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class="text-center" style="width:10%;">客户姓名</th> 
							<th class="text-left" style="width:10%;">客户电话</th>
							<th class="text-left" style="width:10%;">重要性</th>
							<th class="text-left" style="width:20%;">联系地址</th>
							<th class="text-left" style="width:10%;">记录日期</th>
							<th class="text-center" style="width:10%;">联系次数</th>
							<th class="text-center" style="width:10%;">记录者</th>
							<th class="text-left" style="width:20%;">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${allCustomers}" var="customer">
						<tr>
							<td class="text-center"><span id="cid-${customer.cuid}" style="cursor:pointer;" title="查看联系记录">${customer.name}</span></td>
							<td class="text-left">${customer.phone}</td>
							<td class="text-left"><span class="text-danger">${allCustomerItemTitles[customer.cuid]}</span></td>
							<td class="text-left">${customer.address}</td>
							<td class="text-left"><fmt:formatDate value="${customer.indate}" /></td>  
							<td class="text-center">${customer.connum}</td>
							<td class="text-center"><span id="mid-${customer.recorder}" style="cursor:pointer;">${allRecorderName[customer.cuid]}</span></td> 
							<td class="text-left">
								<button class="btn btn-primary btn-xs" id="input-${customer.cuid}">
										<span class="glyphicon glyphicon-floppy-save"></span>&nbsp;追加记录</button>
								<button class="btn btn-danger btn-xs" id="out-1">
										<span class="glyphicon glyphicon-log-out"></span>&nbsp;商品出库</button>
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
	<div class="modal fade" id="customerRecordInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1300px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<h3><strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;客户联系记录</strong></h3>
				</div>
			</div>
			<div class="modal-body">
				<div id="customerRecordInfo">
					<table class="table table-condensed t	able-hover" id="recordTable">
						<thead>
							<tr>
								<th class="text-center" style="width:20%"><strong>联系日期</strong></th>
								<th class="text-left" style="width:10%"><strong>联系业务员</strong></th>
								<th class="text-left" style="width:10%"><strong>业务员电话</strong></th>
								<th class="text-left" style="width:60%"><strong>联系记录</strong></th>
							</tr>
						</thead>
						<tbody id="1044316902">
							
						</tbody>
					</table>
				</div>
				<div id="pageDiv" class="text-right">
					<ul class="pagination pagination-sm" id="pagecontrol"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_info_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/customer_record_input_modal.jsp"/> 
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
