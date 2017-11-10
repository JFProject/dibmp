<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String GOODS_EDIT_URL = "" ;
%>
<script type="text/javascript" src="js/pages/back/admin/goods/goods_show.js"></script>
<body class="hold-transition skin-blue sidebar-mini"> 
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="5"/>
			<jsp:param name="msi" value="52"/>
		</jsp:include>
		<div class="content-wrapper text-left">
					<div class="panel panel-info">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-user"></span>&nbsp;编辑商品信息</strong>
				</div>
				<div class="panel-body">
					<div>
						<table class="table table-condensed" >
							<tr>
								<td><strong>商品编号：</strong></td> 
								<td><span id="gid">${goods.gid }</span></td>
								<td><strong>商品图片：</strong></td>
							</tr>
							<tr>
								<td><strong>商品名称：</strong></td>
								<td><span>${goods.name }</span></td>
								<td rowspan="7"><img src="${authPhoto }" style="height:300px;"></td>
							</tr>
							<!-- <tr>
								<td><strong>入库次数：</strong></td>
								<td></td>
							</tr> -->
							<tr>
								<td><strong>商品库存量：</strong></td>
								<td>${goods.stornum }
									<!-- （<button id="storage-1" class="btn btn-danger btn-xs">
										<span class="glyphicon glyphicon-edit"></span>&nbsp;库存详情</button>） --></td>
							</tr>
							<tr>
								<td><strong>当前商品价格（￥）：</strong></td>
								<td>${goods.price }</td>
							</tr>
							<tr>
								<td><strong>当前商品重量（G）：</strong></td>
								<td>${goods.weight }</td>
							</tr>
							<tr>
								<td><strong>最后入库日期：</strong></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${goods.lastin }" /></td>
							</tr>
							<tr>
								<td><strong>备注信息：</strong></td>
								<td><pre class="pre-scrollable" style="width:550px;height:150px;">${goods.note }</pre></td>
							</tr>
						</table>
					</div>
					<div class="panel-group" id="storageDetails">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title"> 
									<a data-toggle="collapse" data-parent="news" href="#contentOne">
										<strong><span class="glyphicon glyphicon-user"></span>&nbsp;商品库存详情：</strong>
									</a>
								</h4>
							</div>
							<div id="contentOne" class="panel-collapse collapse"> 
								<div class="panel-body">
									<table class="table table-condensed">
										<thead>
											<tr>
												<th class="text-center" style="width:10%;">入库日期</th> 
												<th class="text-left" style="width:40%;">仓库</th> 
												<th class="text-center" style="width:10%;">入库人员</th> 
												<th class="text-center" style="width:10%;">库存数量</th>
											</tr>
										</thead>
										<tbody id="storageTable">
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
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
	<jsp:include page="/WEB-INF/pages/plugins/back/info/goods_storage_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
