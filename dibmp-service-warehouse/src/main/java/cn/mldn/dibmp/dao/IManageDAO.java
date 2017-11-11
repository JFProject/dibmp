package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.WarehouseGoods;
import cn.mldn.dibmp.vo.Witem;

public interface IManageDAO {
	/**
	 * 根据入库申请单编号查找所有的入库商品
	 * @param said 入库申请单编号
	 * @return 符合条件的所有入库商品信息
	 */
	public List<StorageApplyDetails> findDetailsBySaid(Long said) ;
	/**
	 * 根据mid查找雇员信息
	 * @param mid 雇员编号
	 * @return 雇员信息
	 */
	public Member findMemberByMid(String mid) ;
	/**
	 * 根据仓库编号查找仓库信息
	 * @param wid 仓库编号
	 * @return 仓库信息
	 */
	public Warehouse findWarehouseByWid(Long wid) ;
	/**
	 * 根据入库申请单编号取得入库申请单信息
	 * @param said 入库申请单编号
	 * @return 入库申请单信息
	 */
	public StorageApply findStorageApplyBySaid(Long said) ;
	/**
	 * 根据省份编号取得省份信息
	 * @param pid 省份编号
	 * @return 省份信息
	 */
	public Province findProvinceByPid(Long pid) ;
	/**
	 * 根据城市编号取得城市信息
	 * @param pid 城市编号
	 * @return 城市信息
	 */
	public City findCityByCid(Long cid) ;
	/**
	 * 根据商品分类编号取得商品分类信息
	 * @param wiid 商品分类
	 * @return 商品分类信息
	 */
	public Witem findWitemBywiid(Long wiid) ; 
	/**
	 * 修改申请状态
	 * @param map 申请状态和申请编号
	 * @return 修改成功返回true
	 */
	public boolean doEditStatus(Map<String,Object> map) ;
	/**
	 * 增加入库记录
	 * @param storageRecord 入库记录信息
	 * @return 增加成功返回true
	 */
	public boolean doCreateStorageRecord(StorageRecord storageRecord) ;
	/**
	 * 根据商品编号取得商品信息
	 * @param gid 商品编号
	 * @return 商品信息
	 */
	public Goods findGoodsByGid(Long gid) ;
	/**
	 * 根据商品编号修改商品中的lastin和stornum字段
	 * @param goods 商品信息
	 * @return 修改成功返回true
	 */
	public boolean doEditGoodsByGid(Goods goods) ;
	/**
	 * 修改仓库中的currnum数量
	 * @param warehouse 仓库信息
	 * @return 修改成功返回true
	 */
	public boolean doEditWarehouseByWid(Warehouse warehouse) ;
	/**
	 * 根据出库单编号查找出库单信息
	 * @param dsid 出库单编号
	 * @return 出库单信息
	 */
	public Distribution findDistributionByDsid(Long dsid) ;
	/**
	 * 根据出库单编号查找出库单详细信息
	 * @param dsid 出库单编号
	 * @return 出库单详细信息
	 */
	public List<DistributionDetails> findDistributionDetailsByDsid(Long dsid) ;
	/**
	 * 根据客户编号查询出客户信息
	 * @param cuid 客户编号
	 * @return 客户信息
	 */
	public Customer findCustomerByCuid(Long cuid) ;
	/**
	 * 根据出库单编号修改状态以及审核人mid
	 * @param params 参数
	 * @return 修改成功返回true
	 */
	public boolean doEditDistributionStatus(Map<String,Object> params) ;
	/**
	 * 根据仓库编号和商品编号取得仓库商品表信息
	 * @param params 仓库编号和商品编号
	 * @return 仓库商品表信息
	 */
	public WarehouseGoods findWarehouseGoodsByWidAndGid(Map<String,Object> params) ;
	/**
	 * 增加仓库商品表信息
	 * @param warehouseGoods 仓库商品表
	 * @return 增加成功返回true
	 */
	public boolean doCreateWarehouseGoods(WarehouseGoods warehouseGoods) ;
	/**
	 * 修改仓库商品表信息
	 * @param warehouseGoods 仓库商品表
	 * @return 修改成功返回true
	 */
	public boolean doEditWarehouseGoods(WarehouseGoods warehouseGoods) ;
	/**
	 * 根据商品编号修改商品库存
	 * @param goods 商品编号
	 * @return 修改成功返回true 
	 */
	public boolean doEditGoodsStornumByGid(Goods goods) ;
	/**
	 * 根据商品编号取得商品库存
	 * @param gid 商品编号
	 * @return 商品库存
	 */
	public Integer findGoodsStornumByGid(Long gid) ;
	/**
	 * 根据仓库编号查找仓库当前库存
	 * @param wid 仓库编号
	 * @return 仓库当前库存
	 */
	public Integer findWarehouseCurrnumByWid(Long wid) ;
	/**
	 * 根据仓库编号修改仓库当前库存
	 * @param warehouse 仓库编号
	 * @return 修改成功返回true
	 */
	public boolean doEditWarehouseStornumByWid(Warehouse warehouse) ;
}
