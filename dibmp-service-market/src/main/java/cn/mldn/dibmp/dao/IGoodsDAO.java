package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;

public interface IGoodsDAO { 
	public List<Witem> findAllWitem() ;
	public List<Subtype> findSubtypeByWiid(Long wiid) ;
	public boolean doCreate(Goods goods) ;
	public boolean doEdit(Goods goods) ;
	public Goods findByGname(String Gname) ;
	public List<Goods> findSplitOrderByGid(Map<String,Object> params) ;
	public Long getSplitCount(Map<String,Object> params) ;
	/**
	 * 根据记录者的编号取得记录者的姓名
	 * @param mid 记录者编号
	 * @return 记录者姓名
	 */
	public String findNameByMid(String mid) ;
	/**
	 * 根据商品子分类编号查找商品子分类名称
	 * @param stid 子分类编号
	 * @return 子分类名称
	 */
	public String findTitleByStid(Long stid) ;
	public Goods findByGid(Long gid) ;
	public Goods findByName(String name) ;
	/**
	 * 根据商品编号查找入库申请单详情
	 * @param gid 商品编号
	 * @return 入库申请单详情
	 */
	public List<StorageRecord> findStorageRecordByGid(Long gid) ;
	/**
	 * 根据省份编号查找省份信息
	 * @param pid 省份编号
	 * @return 省份信息
	 */
	public Province findProvinceByPid(Long pid) ;
	/**
	 * 根据城市编号查找城市信息
	 * @param cid 城市编号
	 * @return 城市信息
	 */
	public City findCityByCid(Long cid) ;
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
}
