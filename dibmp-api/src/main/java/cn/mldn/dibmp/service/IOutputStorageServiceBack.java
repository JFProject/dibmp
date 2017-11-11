package cn.mldn.dibmp.service;

import java.util.Map;

public interface IOutputStorageServiceBack {
	/**
	 * 我的出库申请列表显示，分页模糊查询
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @param mid 当前登录人编号
	 * @return 以map形式返回
	 * 1、allDistribution ，value = 我的所有出库申请单
	 * 2、allRecorders ，value = 记录数
	 * 3、province ，value = 省份信息
	 * 4、city ，value = 城市信息
	 * 5、customer ， value = 客户信息
	 * 6、applyMember ，value = 申请人信息
	 * 7、outMember ， value = 审核人信息
	 */
	public Map<String,Object> listMySelf(long currentPage,int lineSize,String column,String keyWord,String mid) ;
	/**
	 * 出库申请单删除操作
	 * @param dsid 出库申请单编号
	 * @return 删除成功返回true 
	 */
	public boolean clear(long dsid) ;
}
