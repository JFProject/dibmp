package cn.mldn.dibmp.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.dibmp.vo.Witem;

public interface IGoodsServiceBack {
	/**
	 * 商品增加前的商品分类列表显示
	 * @return 所有的商品分类
	 */
	public List<Witem> addPre() ;
	/**
	 * 根据商品分类id取得商品子分类信息
	 * @param wiid 商品分类id
	 * @return 商品子分类
	 */
	public List<Subtype> getSubtypeByWiid(long wiid) ;
	/**
	 * 增加商品
	 * @param goods 要增加的商品
	 * @return 增加成功返回true
	 */
	public boolean add(Goods goods) ;
	/**
	 * 商品清单分页模糊显示
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 以map集合的形式返回
	 * 1、key = "allGoods", value表示所有的商品
	 * 2、key = "allRecorders", value表示所有的记录数
	 * 3、key = "memberName", value表示记录者姓名
	 */
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord,String mid) ;
	/**
	 * 编辑前的回显操作
	 * @param gid 商品编号
	 * @return 以map形式返回
	 * 1、key = goods，value = 商品信息
	 * 2、key = allWitem，value = 所有商品分类
	 * 3、key = title，value = 子分类名称
	 */
	public Map<String,Object> editPre(long gid) ;
	/**
	 * 商品信息的修改
	 * @param goods 商品信息
	 * @return 修改成功返回true
	 */
	public boolean edit(Goods goods) ;
	/**
	 * 进行商品的详细显示
	 * @param gid 商品的id
	 * @return 以map形式返回
	 * 1、key = goods，value = 商品信息
	 */
	public Map<String,Object> show(long gid) ;
	/**
	 * 商品库存详情列表显示
	 * @param gid 商品编号
	 * @return 以map形式返回
	 * 1、key = storageRecord ，value = 入库单详情
	 * 2、key = province ，value = 省份信息
	 * 3、key = city ，value = 城市信息
	 * 4、key = warehouseName ，value = 仓库地址
	 * 5、key = applyMember ，申请人
	 * 6、key = auditMember ，审核人
	 * 7、key = inMember ，入库人
	 */
	public Map<String,Object> goodsStorageInfo(long gid) ;
}
