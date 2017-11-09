package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;

public interface IStorageApplyDAO {
	public List<Province> findAllProvince() ;
	public List<Witem> findAllWitem() ;
	public List<City> findCityByPid(Long pid) ;
	/**
	 * 根据商品分类查找对应的所有仓库信息
	 * @param wiid 商品分类
	 * @return 对应的所有仓库信息
	 */
	public List<Warehouse> findAllByWiid(Long wiid) ;
	public boolean doCreate(StorageApply storageApply) ;
	/**
	 * 根据入库申请单标题查找入库申请信息
	 * @param title 申请单标题 
	 * @return 入库申请信息
	 */
	public StorageApply findByTitle(String title) ;
	/**
	 * 分页模糊 查找自己的入库申请
	 * @param params 有个当前登录人的mid
	 * @return 属于自己的入库申请
	 */
	public List<StorageApply> findByMyself(Map<String,Object> params) ;
	/**
	 * 查找出自己申请的入库单数量
	 * @param params 
	 * @return 数据量
	 */
	public Long getMyselfCount(Map<String,Object> params) ;
	/**
	 * 根据仓库编号查找仓库信息
	 * @param wid 仓库编号
	 * @return 仓库信息
	 */
	public Warehouse findByWid(Long wid) ;
	public Witem findWitemByWiid(Long wiid) ;
	/**
	 * 根据入库申请单id取得入库申请单信息
	 * @param said 入库申请单id
	 * @return 入库申请单信息
	 */
	public StorageApply findBySaid(Long said) ;
	/**
	 * 编辑商品入库信息单
	 * @param storageApply 入库信息单
	 * @return 修改成功返回true
	 */
	public boolean doEdit(StorageApply storageApply) ;
	/**
	 * 修改申请状态
	 * @param map 申请状态和申请编号
	 * @return 修改成功返回true
	 */
	public boolean doEditStatus(Map<String,Object> map) ;
	/**
	 * 删除入库申请单
	 * @param said 要删除的入库申请单编号
	 * @return 删除成功返回true
	 */
	public boolean doRemove(long said) ;
	
	public Goods findGoodsByGid(long gid) ;
	
	public boolean doCreateStorageApplyDetails(StorageApplyDetails storageApplyDetails) ;
	
	public boolean doRemoveBySaid(long said) ;
}
