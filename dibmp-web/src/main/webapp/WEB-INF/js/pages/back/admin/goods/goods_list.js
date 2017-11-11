$(function(){
	$("button[id^=out-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			console.log("部门编号：" + gid) ;
			$.post("pages/back/admin/goods/add_customer.action",{"gid":gid},function(data){
				operateAlert(data,"待出库商品添加成功！","待出库商品添加失败！") ;
			},"json") ;
			
		}) ;
	}) ;
	/*$("span[id^=storage-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#goodsRecordInfo").modal("toggle") ; 
		}) ;
	}) ;*/
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