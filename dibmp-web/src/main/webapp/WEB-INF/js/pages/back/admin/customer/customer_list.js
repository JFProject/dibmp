cid = 0 ;
$(function(){
	
	$("span[id^=mid-]").each(function(){
		$(this).on("click",function(){
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
	}) ;
	
	

	
	
	$("span[id^=cid-]").each(function(){
		$(this).on("click",function(){
			var  date= new Date();
			var data ;
			cmid = this.id.split("-")[1] ;
			$.post("pages/back/admin/customer/customer_record.action",{"cmid":cmid},function(data){
				$("#1044316902").empty() ;
				for(var i = 0 ; i < data.allCr.length ; i ++){
					cr = data.allCr[i] ;
					trInfo = 	$("<tr id='record-1'>"+
								"<td class='text-center'>"+datetimeFormat_1(cr.cdate)+"</td>"+
								"<td class='text-left'>"+cr.recordername+"</td>"+
								"<td class='text-left'>"+data.allPhone[cr.crid]+"</td>"+
								"<td class='text-left'>"+
									"<pre class='pre-scrollable' style='width:700px;height:60px;'>"+cr.note+"</pre>"+
								"</td>"+
							"</tr> ") ;
					$("#1044316902").append(trInfo) ;
				}
				loadData() ;
				$("#customerRecordInfo").modal("toggle") ;
			},"json") ;
		}) ;
	}) ;
//	$("button[id^=out-]").each(function(){
//		$(this).on("click",function(){
//			cid = this.id.split("-")[1] ;
//			
//			console.log(cid) ;
//			
//			operateAlert(true,"客户订单追加成功！","客户订单追加失败！") ;
//		}) ;
//	}) ;

	$("button[id^=input-]").each(function(){
		$(this).on("click",function(){
			cmid = this.id.split("-")[1] ;
			$(myform).append("<input name='cmid' value="+cmid+" type='hidden'></input>") ;
			$("#customerRecordInputInfo").modal("toggle") ;
		}) ;
	}) ;
//	$("#myform").validate({
//		debug : true, // 取消表单的提交操作
//		submitHandler : function(form) {
//			// 发送ajax请求进行异步数据处理操作
//			var pid = $("#pid").val();
//			alert(pid);
//			var note = $("#note").text();
//			alert(note);
//			$("#customerRecordInputInfo").modal("toggle") ;
//			operateAlert(true,"客户联系记录追加成功！","客户联系记录追加失败！") ;
//		},
//		errorPlacement : function(error, element) {
//			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
//		},
//		highlight : function(element, errorClass) {
//			$(element).fadeOut(1,function() {
//				$(element).fadeIn(1, function() {
//					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
//				});
//
//			})
//		},
//		unhighlight : function(element, errorClass) {
//			$(element).fadeOut(1,function() {
//				$(element).fadeIn(1,function() {
//						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
//				});
//			})
//		},
//		errorClass : "text-danger",
//		rules : {
//			"title" : {
//				required : true
//			} ,
//			"bid" : {
//				required : true
//			} ,
//			"note" : { 
//				required : true 
//			}
//		}
//	});
}) ;
function loadData() {	// 该函数名称一定要固定，不许修改
	// 如果要想进行分页的处理列表前首先查询出部门编号
	console.log("客户编号：" + cid) ;
	// $("#memberBasicInfo tr:gt(0)").remove() ; // 加载之前要进行原有数据删除
	createSplitBar(10) ;	// 创建分页控制项
}
function splitGet(data){
	var temp = data.split("-") ;
	var ret = "" ;
	for(var i = 1 ; i < temp.length ; i ++){
		ret += temp[i] + "-" ;
	}
	ret = ret.substr(0,ret.length-1) ;
	return ret ;
}
function datetimeFormat_1(longTypeDate){ 
	  var datetimeType = ""; 
	  var date = new Date(); 
	  date.setTime(longTypeDate); 
	  datetimeType+= date.getFullYear();  //年 
	  datetimeType+= "-" + getMonth(date); //月  
	  datetimeType += "-" + getDay(date);  //日 
	  datetimeType+= "  " + getHours(date);  //时 
	  datetimeType+= ":" + getMinutes(date);   //分
	  datetimeType+= ":" + getSeconds(date);   //分
	  return datetimeType;
	} 
	//返回 01-12 的月份值  
	function getMonth(date){ 
	  var month = ""; 
	  month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
	  if(month<10){ 
	    month = "0" + month; 
	  } 
	  return month; 
	} 
	//返回01-30的日期 
	function getDay(date){ 
	  var day = ""; 
	  day = date.getDate(); 
	  if(day<10){ 
	    day = "0" + day; 
	  } 
	  return day; 
	}
	//返回小时
	function getHours(date){
	  var hours = "";
	  hours = date.getHours();
	  if(hours<10){ 
	    hours = "0" + hours; 
	  } 
	  return hours; 
	}
	//返回分
	function getMinutes(date){
	  var minute = "";
	  minute = date.getMinutes();
	  if(minute<10){ 
	    minute = "0" + minute; 
	  } 
	  return minute; 
	}
	//返回秒
	function getSeconds(date){
	  var second = "";
	  second = date.getSeconds();
	  if(second<10){ 
	    second = "0" + second; 
	  } 
	  return second; 
	}