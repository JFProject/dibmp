package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.dibmp.vo.Witem;

public interface IGoodsDAO { 
	public List<Witem> findAllWitem() ;
	public List<Subtype> findSubtypeByWiid(Long wiid) ;
	public boolean doCreate(Goods goods) ;
	public Goods findByGname(String Gname) ;
	public List<Goods> findSplitOrderByGid(Map<String,Object> params) ;
	public Long getSplitCount(Map<String,Object> params) ;
	/**
	 * 根据记录者的编号取得记录者的姓名
	 * @param mid 记录者编号
	 * @return 记录者姓名
	 */
	public String findNameByMid(String mid) ;
}
