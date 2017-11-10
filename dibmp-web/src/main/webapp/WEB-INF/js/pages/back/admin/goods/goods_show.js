loadFlag = false ;
$(function() {
/*	$("button[id^=storage-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#goodsRecordInfo").modal("toggle") ; 
		}) ;
	}) ;*/
	$('#storageDetails').on('show.bs.collapse', function () {
		if (loadFlag == false) {
			gid = $("#gid").text() ;
			$.post("pages/back/admin/goods/goodsStorageInfo.action",{"gid":gid},function(data){
				console.log(data) ;
				for(var i = 0 ; i < data.allStorageRecord.length ; i ++){
					storageRecord = data.allStorageRecord[i] ;
					trInfo = $("<tr class='text-primary'> " +
								"	<td class='text-center'><fmt:formatDate pattern='yyyy-MM-dd' value=" + storageRecord.auditDate + " />" + dateFormat_2(storageRecord.auditDate) + "</td> " +
								"	<td class='text-left'>" + data.province[storageRecord.srid] + " " + data.city[storageRecord.srid] + " " + data.warehouseName[storageRecord.srid] + "</td> " +
								"	<td class='text-center'><span id='mid-" + data.inMember[storageRecord.srid].mid + "'>" + data.inMember[storageRecord.srid].name + "</span></td> " +
								"	<td class='text-center'>" + storageRecord.num + "</td> " + 
								"</tr> ") ;
					$('#storageTable').append(trInfo) ;
				}
				operateAlert(true,"商品库存信息加载成功！","商品库存信息加载失败！") ;
				loadFlag = true ; // 数据已经加载完成
			},"json") ;
		}
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
function dateFormat_2(longTypeDate){ 
  var dateType = ""; 
  var date = new Date(); 
  date.setTime(longTypeDate); 
  dateType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);//yyyy-MM-dd格式日期
  return dateType;
}
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
function splitGet(data){
	var temp = data.split("-") ;
	var ret = "" ;
	for(var i = 1 ; i < temp.length ; i ++){
		ret += temp[i] + "-" ;
	}
	ret = ret.substr(0,ret.length-1) ;
	return ret ;
}