package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IGoodsDAO;
import cn.mldn.dibmp.service.IGoodsServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Subtype;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class GoodsServiceBackImpl extends AbstractService implements IGoodsServiceBack {
	@Resource
	private IGoodsDAO goodsDAO ;
	@Resource
	private RedisTemplate<String, Object> redisTemplate ;
	
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
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord,String mid) {
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
		if(this.redisTemplate.opsForValue().get(mid) != null){//没有绑定客户
			map.put("flag", "id");//显示按钮标签
		}else{
			map.put("flag", "hidden");//不现实按钮
		}
		return map ;
	}
	
	@Override
	public Map<String, Object> editPre(long gid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Goods goods = this.goodsDAO.findByGid(gid) ;
		map.put("goods", goods) ;
		map.put("allWitem", this.goodsDAO.findAllWitem()) ;
		map.put("allSubtype", this.goodsDAO.findSubtypeByWiid(goods.getWiid())) ;
		return map;
	}

	@Override
	public boolean edit(Goods goods) {
		return this.goodsDAO.doEdit(goods);
	}

	@Override
	public Map<String, Object> show(long gid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Goods goods = this.goodsDAO.findByGid(gid) ;
		map.put("goods", goods) ;
		return map;
	}

	@Override
	public Map<String, Object> goodsStorageInfo(long gid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		List<StorageRecord> allStorageRecord = new ArrayList<StorageRecord>() ;
		Map<Long,String> province = new HashMap<Long,String>() ;
		Map<Long,String> city = new HashMap<Long,String>() ;
		Map<Long,String> warehouseName = new HashMap<Long,String>() ;
		Map<Long,Member> applyMember = new HashMap<Long,Member>() ;
		Map<Long,Member> auditMember = new HashMap<Long,Member>() ;
		Map<Long,Member> inMember = new HashMap<Long,Member>() ;
		List<StorageRecord> list = this.goodsDAO.findStorageRecordByGid(gid) ;
		Iterator<StorageRecord> iter = list.iterator() ;
		while(iter.hasNext()) {
			StorageRecord storageRecord = iter.next() ;
			allStorageRecord.add(storageRecord) ;
 			Warehouse warehouse = this.goodsDAO.findWarehouseByWid(storageRecord.getWid()) ;
			province.put(storageRecord.getSrid(), this.goodsDAO.findProvinceByPid(warehouse.getPid()).getTitle()) ;
			city.put(storageRecord.getSrid(), this.goodsDAO.findCityByCid(warehouse.getCid()).getTitle()) ;
			warehouseName.put(storageRecord.getSrid(), warehouse.getName()) ;
			if(this.redisTemplate.opsForHash().get(String.valueOf(storageRecord.getSaid()),"applyMid") instanceof Member) {
				applyMember.put(storageRecord.getSrid(), this.goodsDAO.findMemberByMid((String)this.redisTemplate.opsForHash().get(String.valueOf(storageRecord.getSaid()),"applyMid"))) ;
			}
			if(this.redisTemplate.opsForHash().get(String.valueOf(storageRecord.getSaid()),"auditMid") instanceof Member) {
				applyMember.put(storageRecord.getSrid(), this.goodsDAO.findMemberByMid((String)this.redisTemplate.opsForHash().get(String.valueOf(storageRecord.getSaid()),"auditMid"))) ;
			}
			inMember.put(storageRecord.getSrid(), this.goodsDAO.findMemberByMid(storageRecord.getInmid())) ;
		}
		map.put("allStorageRecord", allStorageRecord) ;
		map.put("province", province) ;
		map.put("city", city) ;
		map.put("warehouseName", warehouseName) ;
		map.put("applyMember", applyMember) ;
		map.put("auditMember", auditMember) ;
		map.put("inMember", inMember) ;
		return map ;
	}
	
}
