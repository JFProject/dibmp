package cn.mldn.dibmp.service;

import java.util.Map;

public interface IStorageAuditServiceBack {
	/**
	 * 待审核入库清单列表显示
	 * @param currentPage 当前行
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 以map形式返回
	 * 1、key = allStorageApplyDetails，value = 所有的待审核申请
	 * 2、key = allRecorders ，value = 待审核的记录数
	 * 3、key = allAppName ，value = 申请人姓名
	 * 4、key = allAppDate ，value = 申请日期
	 * 5、key = allCount ，value = 商品数量
	 * 6、key = allPrice ，value = 商品价格
	 * 7、key = allWarehouseName ，value = 仓库名称
	 * 8、key = allProvinceName ，value = 省份名称
	 * 9、key = allCityName ，value = 城市名称
	 */
	public Map<String,Object> listPrepare(long currentPage,int lineSize,String column,String keyWord) ;
	/**
	 * 根据申请单编号取出申请信息，以及商品清单信息
	 * @param said 申请编号
	 * @return 以map形式返回
	 * 1、key = storageApply ，value = 申请单编号
	 * 2、key = provinceName ，value = 省份名称
	 * 3、key = cityName ，value = 城市名称
	 * 4、key = widName  ，value = 仓库名称
	 * 5、key = wiidName ，value = 商品分类
	 * 6、key = appName ，value = 申请人姓名
	 * 7、key = price ，value = 每条商品记录的价格
	 * 8、key = totalPrice ，value = 商品总价  
	 * 9、key = allStorageApplyDetails ，value = 商品信息 
	 */
	public Map<String,Object> editPre(long said) ;
	/**
	 * 根据仓库编号取得仓库信息
	 * @param wid 仓库编号
	 * @return 以map形式返回
	 * 1、key = warehouse ，value = 仓库信息
	 * 2、key = title ，value = 仓库类型
	 */
	public Map<String,Object> warehouseShow(long wid) ;
	/**
	 * 修改申请表的状态
	 * @param said 入库申请单编号
 	 * @param status 要修改的状态
 	 * @param mid 修改人的mid
	 * @return 修改成功返回true
	 */
	public boolean edit(long said,int status,String mid) ;
	/**
	 * 历史审核入库清单列表显示
	 * @param currentPage 当前行
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 以map形式返回
	 * 1、key = allStorageApplyDetails，value = 所有的待审核申请
	 * 2、key = allRecorders ，value = 待审核的记录数
	 * 3、key = allAppName ，value = 申请人姓名
	 * 4、key = allAppDate ，value = 申请日期
	 * 5、key = allCount ，value = 商品数量
	 * 6、key = allPrice ，value = 商品价格
	 * 7、key = allWarehouseName ，value = 仓库名称
	 * 8、key = allProvinceName ，value = 省份名称
	 * 9、key = allCityName ，value = 城市名称
	 * 10、key = allAuditInfo ，value = 审核人员信息
	 */
	public Map<String,Object> listHistory(long currentPage,int lineSize,String column,String keyWord) ;

}
