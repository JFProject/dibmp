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
			}  ,
			"pid" : {
				required : true
			} ,
			"cid" : {
				required : true
			},
			"note" : {
				required : true
			}
		}
	});
	
	$(pid).on("change",function(){
		if (this.value != "") {	// 有内容，需要进行ajax异步加载
			$.post("pages/back/admin/warehouse/getCity.action",{"pid":this.value},
					function(data){
				$("#cid option:gt(0)").remove() ;
				for (x = 0 ; x < data.allCity.length ; x ++) {
					$("#cid").append("<option value='"+data.allCity[x].cid+"'>"+data.allCity[x].title+"</option>") ;
				}
			},"json") ;
		} else {
			$("#cid").remove() ;
			$("#cid").append("<option value=''>====== 请选择所在城市 ======</option>") ;
		}
	}) ;
})