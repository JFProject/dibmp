package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.Province;

public interface IOutputStorageDAO {
	/**
	 * 查询出自己的入库申请单信息，模糊分页查询
	 * @param param 里面有一个当前登录人的mid
	 * @return 自己的入库申请单信息
	 */
	public List<Distribution> findSplitByMyself(Map<String,Object> param) ;
	/**
	 * 统计出自己的入库申请单数量
	 * @param param 里面有一个当前登录人的mid
	 * @return 	自己的入库申请单数量
	 */
	public Long getCountByMyself(Map<String,Object> param) ;
	/**
	 * 根据省份编号查询出省份信息
	 * @param pid 省份编号
	 * @return 省份信息
	 */
	public Province findProvinceByPid(Long pid) ;
	/**
	 * 根据城市编号查询出城市信息
	 * @param pid 城市编号
	 * @return 城市信息
	 */
	public City findCityByCid(Long cid) ;
	/**
	 * 根据客户编号查询出客户信息
	 * @param cuid 客户编号
	 * @return 客户信息
	 */
	public Customer findCustomerByCuid(Long cuid) ;
	/**
	 * 根据用户编号查询出用户信息
	 * @param mid 用户编号
	 * @return 用户信息
	 */
	public Member findMemberByMid(String mid) ;
	/**
	 * 删除出库申请单
	 * @param dsid 出库申请单编号
	 * @return 删除成功返回true
	 */
	public boolean doRemoveDistribution(Long dsid) ;
}
