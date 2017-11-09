temp_did = 1 ;
$(function(){
	$(addbut).on("click",function(){
		addDetails("temp" + temp_did ++) ; 
	}) ;
	$("input[id^=gid-]").each(function(){
		$(this).on("blur",function(){
			tdid = this.id.split("-") [1] ;
			gid = $(this).val() ;
			wiid = $("#wiid").text() ;
			$.post("pages/back/admin/storage/getGoodsByGid.action",{"gid":gid},function(data){
				if(data.wiid == wiid){
					$("#name-" + tdid).val(data.name) ;
					$("#price-" + tdid).val(data.price) ;
					$("#weight-" + tdid).val(data.weight) ;
				}else{
					$("#name-" + tdid).val("你选择的不是" + $("#title").text() + "的商品") ;
					$("#amount-" + tdid).val("") ;
					$("#price-" + tdid).val("") ;
					$("#weight-" + tdid).val("") ;
				}
			},"json")
		}) ;
	})
	$("button[id^=save-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-") [1] ;
			saveDetails(did) ;
		}) ;
	}) ;
	$("button[id^=remove-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-") [1] ;
			deleteDetails(did) ;
		}) ;
	}) ;
})
function addDetails(tdid) {
	trInfo = 	$("<tr id='dettr-"+tdid+"' class='text-danger'>" +
				"	<td><input type='text' id='gid-"+tdid+"' value='0'/></td>" + 
				"	<td><input type='text' id='name-"+tdid+"' value='等待查询...'/></td>" +
				"	<td><input type='text' id='amount-"+tdid+"' value='0' maxlength='7' size='8'/></td>" +
				"	<td><input type='text' id='price-"+tdid+"' value='0.0' maxlength='7' size='8'/></td>" +
				"	<td><input type='text' id='weight-"+tdid+"' value='0' maxlength='7' size='8'/></td>" +
				"	<td>" +
				"		<button id='save-"+tdid+"' class='btn btn-primary btn-xs'>" +
				"			<span class='glyphicon glyphicon-edit'></span>&nbsp;保存</button>" +
				"		<button id='remove-"+tdid+"' class='btn btn-danger btn-xs'>" +
				"			<span class='glyphicon glyphicon-edit'></span>&nbsp;移除</button>" +
				"	</td>" +
				"</tr>") ;
	$(detailsTab).append(trInfo) ;
	$("#gid-" + tdid).on("blur",function(){
		gid = $(this).val() ;
		wiid = $("#wiid").text() ;
		$.post("pages/back/admin/storage/getGoodsByGid.action",{"gid":gid},function(data){
			if(data.wiid == wiid){
				$("#name-" + tdid).val(data.name) ;
				$("#price-" + tdid).val(data.price) ;
				$("#weight-" + tdid).val(data.weight) ;
			}else{
				$("#name-" + tdid).val("你选择的不是" + $("#title").text() + "的商品") ;
				$("#amount-" + tdid).val("") ;
				$("#price-" + tdid).val("") ;
				$("#weight-" + tdid).val("") ;
			}
		},"json")
	}) ;
	$("#save-" + tdid).on("click",function(){
		saveDetails(tdid) ;
	}) ;
	$("#remove-" + tdid).on("click",function(){
		deleteDetails(tdid) ;
	}) ;
}
function saveDetails(did) {
	said = $("#said").text() ;
	gid = $("#gid-" + did).val() ;
	name = $("#name-" + did).val() ;
	price = $("#price-" + did).val() ;
	weight = $("#weight-" + did).val() ;
	amount = $("#amount-" + did).val() ;
	if(validateNumber(price) && validateNumber(weight) && validateInteger(gid) && validateInteger(amount)){
		$.post("pages/back/admin/storage/addGoodsTemp.action",{"said":said,"gid":gid,"name":name,"price":price,"weight":weight,"num":amount},function(data){
			if(data){
				operateAlert(true,"入库商品信息添加成功！","入库商品信息添加失败！") ;
				$("#dettr-" + did).attr("class","text-success") ;
			}else{
				operateAlert(false,"入库商品信息添加成功！","入库商品信息添加失败！") ;
			}
		},"json") ;
	}else{
		operateAlert(false,"入库商品信息添加成功！","入库商品信息添加失败！") ;
	}
	
}
function deleteDetails(did) {
	said = $("#said").text() ;
	gid = $("#gid-" + did).val() ;
	if(validateInteger(gid)){
		$.post("pages/back/admin/storage/deleteGoodsTemp.action",{"said":said,"gid":gid},function(data){
			if(data){
				operateAlert(true,"入库商品信息删除成功！","入库商品信息删除失败！") ;
				$("#dettr-" + did).remove() ;
			}else{
				operateAlert(false,"入库商品信息删除成功！","入库商品信息删除失败！") ;
			}
		},"json") ;
	}else{
		operateAlert(false,"入库商品信息删除成功！","入库商品信息删除失败！") ;
	}
}
function validateNumber(data){
	return /^\d+(\.\d+)?$/.test(data) && parseFloat(data) > 0 ;
}
function validateInteger(data){
	return /^\d+$/.test(data) && parseFloat(data) > 0;
}