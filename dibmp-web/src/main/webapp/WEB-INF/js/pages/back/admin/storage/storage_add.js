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
			"title" : {
				required : true
			} ,
			"pid" : {
				required : true 
			},
			"cid" : {
				required : true 
			},
			"wid" : {
				required : true 
			},
			"wiid" : {
				required : true 
			},
			"note" : {
				required : true
			}
		}
	});

	$("#pid").on("change",function(){
		id = $(this).val() ;
		if(id != ""){
			$("#cid").empty() ;
			$("#cid").append("<option value=''>====== 请选择所在城市 ======</option>") ;
			$("#wiid").empty() ;
			$("#wiid").append("<option value=''>====== 请选择库存商品类型 ======</option>") ;
			$("#wid").empty() ;
			$("#wid").append("<option value=''>====== 请选择要存储的仓库  ======</option>") ;
			$.post("pages/back/admin/storage/getCity.action",{"pid":id},function(data){
				for(var i = 0 ; i < data.length ; i++){
					$("#cid").append("<option value=" + data[i].cid + ">" + data[i].title + "</option>") ;
				}
			},"json") ;
		}else{
			$("#cid").append("<option value=''>====== 请选择所在城市 ======</option>") ;
		}
	}) ;
	
	$("#cid").on("change",function(){
		cid = $(this).val() ;
		pid = $("#pid option:selected").val() ;
		if(cid != "" && pid != ""){
			$("#wiid").empty() ;
			$("#wiid").append("<option value=''>====== 请选择库存商品类型 ======</option>") ;
			$("#wid").empty() ;
			$("#wid").append("<option value=''>====== 请选择要存储的仓库  ======</option>") ;
			$.post("pages/back/admin/storage/getWiid.action",{"pid":pid,"cid":cid},function(data){
				for(var i = 0 ; i < data.length ; i++){
					$("#wiid").append("<option value=" + data[i].wiid + ">" + data[i].title + "</option>") ;
				}
			},"json") ;
		}else{
			$("#wiid").append("<option value=''>====== 请选择库存商品类型 ======</option>") ;
		}
	}) ;
	
	$("#wiid").on("change",function(){
		wiid = $(this).val() ;
		pid = $("#pid option:selected").val() ;
		cid = $("#cid option:selected").val() ;
		if(wiid != "" && pid != "" && cid != ""){
			$("#wid").empty() ;
			$("#wid").append("<option value=''>====== 请选择要存储的仓库  ======</option>") ;
			$.post("pages/back/admin/storage/getWarehouse.action",{"wiid":wiid,"pid":pid,"cid":cid},function(data){
				for(var i = 0 ; i < data.length ; i++){
					$("#wid").append("<option value=" + data[i].wid + ">" + data[i].name + "</option>") ;
				}
			},"json") ;
		}else{
			$("#wid").append("<option value=''>====== 请选择要存储的仓库  ======</option>") ;
		}
	})
	
})