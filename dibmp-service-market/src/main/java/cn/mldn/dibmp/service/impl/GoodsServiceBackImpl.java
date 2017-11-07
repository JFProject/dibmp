package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IGoodsDAO;
import cn.mldn.dibmp.service.IGoodsServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class GoodsServiceBackImpl extends AbstractService implements IGoodsServiceBack {
	@Resource
	private IGoodsDAO goodsDAO ;
	
	@Override
	public List<Witem> addPre() {
		List<Witem> allWitem = this.goodsDAO.findAllWitem() ;
		return allWitem;
	}
	
	@Override
	public List<Subtype> getSubtypeByWiid(long wiid) {
		List<Subtype> allSubtype = this.goodsDAO.findSubtypeByWiid(wiid) ;
		return allSubtype ;
	}
	
	@Override
	public boolean add(Goods goods) {
		if(this.goodsDAO.findByGname(goods.getName()) == null) {
			return this.goodsDAO.doCreate(goods) ;
		}
		return false;
	}
	
	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> memberName = new HashMap<String,String>() ;
		List<Goods> allGoods = this.goodsDAO.findSplitOrderByGid(super.paramToMap(currentPage, lineSize, column, keyWord)) ;
		Iterator<Goods> iter = allGoods.iterator() ;
		while(iter.hasNext()) {
			Goods goods = iter.next() ;
			memberName.put(goods.getRecorder(), this.goodsDAO.findNameByMid(goods.getRecorder())) ;
		}
		map.put("allGoods", allGoods) ;
		map.put("allRecorders", this.goodsDAO.getSplitCount(super.paramToMap(column, keyWord))) ;
		map.put("memberName", memberName) ;
		return map ;
	}
	
	@Override
	public Map<String, Object> editPre(long gid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Goods goods = this.goodsDAO.findByGid(gid) ;
		map.put("goods", goods) ;
		map.put("allWitem", this.goodsDAO.findAllWitem()) ;
		map.put("title", this.goodsDAO.findTitleByStid(goods.getStid())) ;
		return map;
	}

}
