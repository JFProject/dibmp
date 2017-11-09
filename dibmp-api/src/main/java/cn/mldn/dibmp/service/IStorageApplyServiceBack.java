package cn.mldn.dibmp.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;

public interface IStorageApplyServiceBack {
	/**
	 * 入库申请前省份和商品类型的列表显示
	 * @return 以map形式返回
	 * 1、key = allProvince，value = 所有的省份信息
	 * 2、key = allWitem，value = 所有的商品类型
	 */
	public Map<String,Object> addPre() ;
	/**
	 * 根据省份编号查找对应的所有城市信息
	 * @param pid 省份编号
	 * @return 城市信息
	 */
	public List<City> getCity(long pid) ;
	/**
	 * 根据商品类型找到所有的仓库
	 * @param wiid 商品类型
	 * @return 对应的所有的仓库
	 */
	public List<Warehouse> getWarehouse(long wiid) ;
	/**
	 * 入库申请单
	 * @param storageApply 申请单
	 * @return 增加成功返回true
	 */
	public boolean add(StorageApply storageApply) ;
	/**
	 * 我的入库申请界面的列表显示
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @param mid 当前登录人的mid
	 * @return 以map形式返回
	 * 1、key = allStorageApply，属于当前登录人的申请
	 * 2、key = allRecorders，数据量
	 * 3、key = allWarehouse，该申请所在的仓库信息
	 * 4、key = allWitem，商品类型名称
	 */
	public Map<String,Object> getByMyself(long currentPage,int lineSize,String column,String keyWord,String mid) ;
	/**
	 * 编辑商品入库单前的回显操作
	 * @param said 商品入库单编号
	 * @return 以map形式返回
	 * 1、key = "storageApply"， value = 商品入库单信息
	 * 2、key = allProvince，value = 所有的省份信息
	 * 3、key = cityByPid，value = 省份对应的所有城市信息
	 * 4、key = allWitem，value = 所有的商品类型
	 * 5、key = warehouseByWiid，value = 商品类型对应的所有仓库
	 */
	public Map<String,Object> editPre(long said) ;
	/**
	 * 修改商品入库单
	 * @param storageApply 商品入库单
	 * @return 修改成功返回true
	 */
	public boolean edit(StorageApply storageApply) ;
	/**
	 * 提交申请，修改状态同时在storage_apply_details表中追加记录
	 * @param status 入库单状态
	 * @param said 入库单编号
	 * @return 修改成功返回true
	 */
	public boolean submit(int status,long said) ;
	/**
	 * 取消入库申请单
	 * @param status 入库单状态
	 * @param said 入库单编号
	 * @return 修改成功返回true
	 */
	public boolean reset(int status,long said) ;
	/**
	 * 删除入库申请单
	 * @param said 要删除的入库申请单编号
	 * @return 删除成功返回true
	 */
	public boolean remove(long said) ;
	/**
	 * 入库申请单详情
	 * @param said 入库申请单编号
	 * @return 以map形式返回
	 * 1、key = storageApply，value = 入库申请单信息
	 * 2、key = address，value = 根据仓库编号取得仓库名称
	 * 3、key = title，value = 根据仓库类型编号取得仓库类型名称
	 */
	public Map<String,Object> listDetails(long said) ;
	/**
	 * 根据商品编号取得商品信息
	 * @param gid 商品编号
	 * @return 商品信息
	 */
	public Goods getGoodsByGid(long gid) ;
	/**
	 * 入库申请单中商品的添加操作，放redis中
	 * @param said 入库申请单编号
	 * @param storageApplyDetails 要添加的商品信息
	 * @return 增加成功返回true
	 */
	public boolean addGoodsTemp(long said,StorageApplyDetails storageApplyDetails) ;
	/**
	 * 入库申请单中商品的删除操作
	 * @param said 入库申请单编号
	 * @param gid 删除的属性名称
	 * @return 删除成功返回true
	 */
	public boolean deleteGoodsTemp(long said,long gid) ;
}
