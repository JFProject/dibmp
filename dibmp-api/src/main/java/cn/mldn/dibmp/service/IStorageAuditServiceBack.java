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
	 */
	public Map<String,Object> listPrepare(long currentPage,int lineSize,String column,String keyWord) ;
}
