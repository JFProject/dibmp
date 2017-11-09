wid = 0 ;
$(function(){
	$("#did").on("change",function(){
		loadData() ;
	}) ;
	
	$("span[id^=mid_]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("_")[1] ;
			loadmen(mid);
		}) ;
	}) ;
	
	$("button[id^=editadmin-]").each(function(){
		$(this).on("click",function(){
			wid = this.id.split("-")[1] ;
//			if (this.value == "") {	// 有内容，需要进行ajax异步加载
//				$.post("pages/back/admin/warehouse/getDept.action",
//						function(data){
//					$("#did option:gt(0)").remove() ;
//					for (x = 0 ; x < data.allDept.length ; x ++) {
//						$("#did").append("<option id = '"+data.allDept[x].did+"' value='"+data.allDept[x].did+"'>"+data.allDept[x].dname+"</option>") ;
//					}
//				},"json") ;
//			}else{
//				$("#did").remove() ;
//				$("#did").append("<option value=''>====== 请选择雇员所在部门 ======</option>") ;
//			}
			$("#memberDeptInfo").modal("toggle") ;
		}) ;
	}) ;
}) ;
function loadmen(mid) {
//	console.log(mid) ;
	$.post("pages/back/admin/member/memberModel.action",{"mid":mid},function(data){
		$("#name").text(data.member.name) ;
		$("#title").text(data.title) ;
		$("#dname").text(data.dname) ;
		$("#phone").text(data.member.phone) ;
		$("#note").text(data.member.note) ;
		console.log(mid) ;
	},"json") ;
	$("#memberInfo").modal("toggle") ;
}
function loadData() {	// 该函数名称一定要固定，不许修改
	did = $("#did").val() ;	// 取得指定组件的value
	tid = $("#tid").val() ;
	var flag = "false" ;
//	console.log("部门编号：" + did) ;
	$("#memberBasicInfo tr:gt(0)").remove() ; // 加载之前要进行原有数据删除
	if (this.value != "") {	// 有内容，需要进行ajax异步加载
		$.post("pages/back/admin/warehouse/getMember.action",{"did":did,"cp":jsCommonCp,"ls":jsCommonLs}, 
				function(data){
					members = data.allMember ;
					for(y = 0 ; y <members.length ;y++){
						 $("#memberBasicInfo tbody").append("<tr id='travel-"+members[y].mid+"'>" + 
									"	<td class='text-center'>" +
									"		<img src='upload/member/nophoto.png ' style='width:20px;'/> " +
									"	</td>" +
									"	<td class='text-center' id='memberName-"+members[y].name+" ' nameVal='"+members[y].name+"' >"+members[y].name+"</td>" +
									"	<td class='text-center'>"+members[y].lid+"</td>" +
									"	<td class='text-center'>"+members[y].phone+"</td>" +
									"	<td class='text-center'>"+
									"   	<button class='btn btn-danger btn-xs' id='addadmin_" + members[y].mid + "_"+members[y].name+"'> "+
									"    		<span class='glyphicon glyphicon-plus-sign'></span>&nbsp;设置为库管" +
									"  		</button> "  +
									"	</td>" +
									"</tr> ") ;
					}
					createSplitBar(data.allCount) ;
					$("button[id^=addadmin_]").each(function(){
						//console.log($("#memberName-mldn26").text()) ;
						$(this).on("click",function(){
							//console.log(this.id) ;
							admin = this.id.split("_")[1] ;
							mid = this.id.split("_")[2] ;
//							console.log("新的仓库管理员编号：" + mid) ;
							//console.log("仓库编号：" + wid) ;
							nameId = "memberName-"+admin ;
							//name = $("#"+nameId).attr("nameval") ;
							name = $("#"+nameId).html() ;
							$.post("pages/back/admin/warehouse/editAdmin.action",{"wid":wid,"admin":admin},function(data){
								ele = $("<span id='mid_"+admin+"' style='cursor:pointer;'>"+mid+"</span>") ;
								$("#admin-" + wid).html(ele) ;
								$("#memberDeptInfo").modal("toggle") ;
								operateAlert(data,"仓库管理员修改成功！","仓库管理员修改失败！") ;
								$("span[id^=mid_]").each(function(){
									$(this).on("click",function(){
										mid = this.id.split("_")[1] ;
										loadmen(mid);
									}) ;
								}) ;
							},"json") ;
						}) ;
					})
			},"json") ;
	}else {
		$("#did").remove() ;
	}
}