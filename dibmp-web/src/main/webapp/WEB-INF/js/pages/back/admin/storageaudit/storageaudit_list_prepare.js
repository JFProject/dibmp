$(function(){
	$("span[id^=said-]").each(function(){
		$(this).on("click",function(){
			said = splitGet(this.id) ;
			$.post("pages/back/admin/storageaudit/storage_details_show.action",{"said":said},function(data){
				$("#storageApplyTitle").text(data.storageApply.title) ;
				$("#storageDetailsWarehouseName").text(data.provinceName + " " + data.cityName + " " +data.widName) ;
				$("#storageApplyWiid").text(data.wiidName) ;
				$("#totalProce").text(data.totalPrice) ;
				$("#storageApplyNote").text(data.storageApply.note) ;
				for(var i = 0 ; i < data.allStorageApplyDetails.length ; i ++){
					storageApplyDetails = data.allStorageApplyDetails[i] ;
					trInfo = 	$("<tr class='text-primary'>" +
							"	<td class='text-center'>" + storageApplyDetails.gid + "</td>" + 
							"	<td class='text-left'>" + storageApplyDetails.name + "</td>" +
							"	<td class='text-center'>" + storageApplyDetails.num + "</td>" +
							"	<td class='text-center'>" + storageApplyDetails.price + "</td>" +
							"	<td class='text-center'>" + storageApplyDetails.weight + "</td>" +
							"	<td class='text-center'>" + data.price[storageApplyDetails.sadid] + "</td>" +
							"</tr>") ;
					$("#detailsTab").append(trInfo) ;
				}
				$("#storageDetailsInfo").modal("toggle") ;
			},"json") ;
		}) ;
	}) ;
	$("span[id^=wid-]").each(function(){
		$(this).on("click",function(){
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
	}) ;
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
