package cn.mldn.dibmp.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Dept;
import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseServiceBack {
	/**
	 * 仓库追加前，回显数据
	 * @return
	 */
	public Map<String,Object> addPro();
	/**
	 * 仓库追加信息
	 * @param vo
	 * @return
	 */
	public boolean add(Warehouse vo);
	/**
	 * 根据省份ID 获取次省份的城市信息
	 * @param pid
	 * @return
	 */
	public Map<String,Object> getCity(Long pid);
	/**
	 * 商品清单分页模糊显示
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 以map集合的形式返回
	 * 1、key = "allWarehouse", value表示所有的商品
	 * 2、key = "allRecorders", value表示所有的记录数
	 */
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) ;
	/**
	 * 获取所有的部门信息。
	 * @return
	 */
	public List<Dept> getDept(List<Long> ids);
	/**
	 * 根据部门编号获取所有的雇员信息。
	 * @return
	 */
	public Map<String,Object> getMember(long currentPage, int lineSize,Long did);
	
	/**
	 * 根据指定编号修改仓库管理员
	 * @param wid 仓库编号
	 * @param Adminname 修改的名称
	 * @return
	 */
	public boolean EditByWid(Long wid,String admin);
	/**
	 * 根据指定编号修改仓库管理员
	 * @param wid 仓库编号
	 * @param Adminname 修改的名称
	 * @return
	 */
	public boolean Edit(Warehouse vo);
}
