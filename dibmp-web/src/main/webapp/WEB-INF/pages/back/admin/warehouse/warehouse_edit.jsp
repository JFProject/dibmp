<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String WAREHOUSE_EDIT_URL = "pages/back/admin/warehouse/edit.action" ;
%>
<script type="text/javascript" src="js/pages/back/admin/warehouse/warehouse_edit.js"></script>
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
					<strong><span class="glyphicon glyphicon-user"></span>&nbsp;编辑仓库信息</strong>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" action="<%=WAREHOUSE_EDIT_URL%>" id="myform" method="post" enctype="multipart/form-data">
						<fieldset>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="nameDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="name">仓库名称：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="name" name="name" class="form-control" value="${Warehouse.name}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="nameMsg"></div>
							</div>
							<div class="form-group" id="pidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="pid">所在省份：</label>
								<div class="col-md-5">
									<select id="pid" name="pid" class="form-control">
										<option value="">====== 请选择所在省份 ======</option>
									<c:forEach items="${allProvince}" var="province">
										<option value="${province.pid}" ${Warehouse.pid == province.pid?"selected":""}>${province.title}</option>
									</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="pidMsg"></div>
							</div>
							<div class="form-group" id="cidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="cid">所在城市：</label>
								<div class="col-md-5">
									<select id="cid" name="cid" class="form-control">
										<option value="">====== 请选择所在城市 ======</option>
										<c:forEach items="${allCity}" var="city">
										<option value="${city.cid}" ${Warehouse.cid == city.cid?"selected":""}>${city.title}</option>
									</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="cidMsg"></div>
							</div>
							<div class="form-group" id="addressDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="address">仓库地址：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="address" name="address" class="form-control" value="${Warehouse.address}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="addressMsg"></div>
							</div>
							<div class="form-group" id="areaDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="area">仓库面积：</label>
								<div class="col-md-5">
									<input type="text" id="area" name="area" class="form-control" value="${Warehouse.area}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="areaMsg"></div>
							</div>
							<div class="form-group" id="iidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="iid">仓库用途：</label>
								<div class="col-md-5">
									<select id="wiid" name="wiid" class="form-control">
										<option value="">====== 请选择库存商品类型 ======</option>
										<c:forEach items="${allWitem }" var="witem">
											<option value="${witem.wiid }" ${Warehouse.wiid == witem.wiid?"selected":""}>${witem.title }</option>
										</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="iidMsg"></div>
							</div>
							<div class="form-group" id="maximumDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="maximum">最大存储数量：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="maximum" name="maximum" class="form-control" value="${Warehouse.maximum}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="maximumMsg"></div>
							</div>
							<div class="form-group" id="picDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="pic">仓库照片：</label>
								<div class="col-md-5">
									<img src="${authPhoto }">
									<!-- 定义表单输入组件 -->
									<input type="file" id="pic" name="pic" class="form-control"
										placeholder="请上传该仓库照片">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="picMsg"></div>
							</div>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="noteDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="note">仓库备注信息：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<textarea id="note" name="note"
										class="form-control" placeholder="请输入仓库的详细信息" rows="10">${Warehouse.note}</textarea>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="noteMsg"></div>
							</div> 
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<input type="hidden" name="photo" value="${Warehouse.photo}">
									<input type="hidden" name="wid" id="wid" value="${Warehouse.wid}">
									<input type="hidden" name="currnum" id="currnum" value="${Warehouse.currnum}">
									<input type="hidden" name="admin" id="admin" value="${Warehouse.admin}">
									<button type="submit" class="btn btn-primary">修改</button>
									<button type="reset" class="btn btn-warning">重置</button>
								</div>
							</div>
						</fieldset>
					</form>
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
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
