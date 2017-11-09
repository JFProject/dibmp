package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;

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
}
