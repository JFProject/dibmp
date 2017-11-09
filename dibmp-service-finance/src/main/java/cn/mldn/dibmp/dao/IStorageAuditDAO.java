package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;

public interface IStorageAuditDAO {
	/**
	 * 根据申请状态查找所有的入库申请单,分页模糊
	 * @param params 里面有一个申请状态
	 * @return 符合条件的入库申请单
	 */
	public List<StorageApply> findByStatus(Map<String,Object> params) ;
	/**
	 * 根据申请状态取得对应的数据量
	 * @param params 里面有一个申请状态
	 * @return 数据量
	 */
	public Long getCountByStatus(Map<String,Object> params) ;
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
	 * 根据申请状态查找所有的入库申请单,分页模糊
	 * @param params 里面有一个申请状态
	 * @return 符合条件的入库申请单
	 */
	public List<StorageApply> findHistoryByStatus(Map<String,Object> params) ;
	/**
	 * 根据申请状态取得对应的数据量
	 * @param params 里面有一个申请状态
	 * @return 数据量
	 */
	public Long getCountHistoryByStatus(Map<String,Object> params) ;
}
