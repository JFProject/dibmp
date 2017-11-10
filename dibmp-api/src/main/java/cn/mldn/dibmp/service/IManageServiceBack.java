package cn.mldn.dibmp.service;

import java.util.Map;

public interface IManageServiceBack {
	/**
	 * 根据申请单编号取出申请信息，以及商品清单信息
	 * @param said 申请编号
	 * @return 以map形式返回
	 * 1、key = storageApply ，value = 申请单编号
	 * 2、key = provinceName ，value = 省份名称
	 * 3、key = cityName ，value = 城市名称
	 * 4、key = widName  ，value = 仓库名称
	 * 5、key = wiidName ，value = 商品分类
	 * 6、key = price ，value = 每条商品记录的价格
	 * 7、key = totalPrice ，value = 商品总价  
	 * 8、key = allStorageApplyDetails ，value = 商品信息 
	 */
	public Map<String,Object> storageInput(long said) ;
	/**
	 * 仓储同意操作
	 * 1、在storage_record表中加数据
	 * 2、修改goods表中的lastin和stornum字段
	 * 3、修改warehouse中的currnum字段
	 * 4、修改storage_apply中的状态
	 * @param said 入库申请单编号
	 * @param mid 库管审核人编号
	 * @return 一系列操作成功返回true
	 */
	public boolean storageAuditSuccess(long said,String mid) ;
	/**
	 * 仓储拒绝，修改storage_apply中的状态
	 * @param said 入库申请单编号
	 * @param status 入库申请单状态
	 * @return 修改成功返回true
	 */
	public boolean storageAuditFailure(long said,int status) ;
}
