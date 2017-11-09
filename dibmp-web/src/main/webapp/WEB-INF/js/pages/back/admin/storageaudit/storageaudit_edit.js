$(function(){
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"flag" : {
				required : true,
			}
		}
	});
	$("span[id^=showWarehouse-]").on("click",function(){
		wid = splitGet(this.id) ;
		$.post("pages/back/admin/storageaudit/warehouse_show.action",{"wid":wid},function(data){
			$("#warehouseName").text(data.warehouse.name) ;
			$("#warehouseAddress").text(data.warehouse.address) ;
			$("#warehouseTitle").text(data.title) ;
			$("#warehouseMaxinum").text(data.warehouse.maximum) ;
			$("#warehouseCurrnum").text(data.warehouse.currnum) ;
			$("#warehouseNote").text(data.warehouse.note) ;
			$("#warehousePic").attr("src",data.authPhoto) ;
			$("#warehouseInfo").modal("toggle") ;
		},"json") ;
	}) ;
	$("span[id^=showMember-]").on("click",function(){
		mid = splitGet(this.id) ;
		$.post("pages/back/admin/member/memberModel.action",{"mid":mid},function(data){
			$("#name").text(data.member.name) ;
			$("#title").text(data.title) ;
			$("#dname").text(data.dname) ;
			$("#phone").text(data.member.phone) ;
			$("#note").text(data.member.note) ;
			$("#memberInfo").modal("toggle") ;
		},"json") ;
	}) ;
})

function splitGet(data){
	var temp = data.split("-") ;
	var ret = "" ;
	for(var i = 1 ; i < temp.length ; i ++){
		ret += temp[i] + "-" ;
	}
	ret = ret.substr(0,ret.length-1) ;
	return ret ;
}