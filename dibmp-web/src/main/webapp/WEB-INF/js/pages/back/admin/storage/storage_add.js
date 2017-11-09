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
		$("#cid").empty() ;
		if(id != ""){
			$.post("pages/back/admin/storage/getCity.action",{"pid":id},function(data){
				for(var i = 0 ; i < data.length ; i++){
					$("#cid").append("<option value=" + data[i].cid + ">" + data[i].title + "</option>") ;
				}
			},"json") ;
		}else{
			$("#cid").append("<option value=''>====== 请选择所在城市 ======</option>") ;
		}
	}) ;
	
	$("#wiid").on("change",function(){
		id = $(this).val() ;
		$("#wid").empty() ;
		if(id != ""){
			$.post("pages/back/admin/storage/getWarehouse.action",{"wiid":id},function(data){
				for(var i = 0 ; i < data.length ; i++){
					$("#wid").append("<option value=" + data[i].wid + ">" + data[i].name + "</option>") ;
				}
			},"json") ;
		}else{
			$("#wid").append("<option value=''>====== 请选择要存储的仓库  ======</option>") ;
		}
	})
	
})