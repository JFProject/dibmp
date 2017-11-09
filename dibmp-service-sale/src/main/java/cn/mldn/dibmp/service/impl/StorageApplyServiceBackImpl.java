package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.service.IStorageApplyServiceBack;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class StorageApplyServiceBackImpl extends AbstractService implements IStorageApplyServiceBack {
	@Resource
	private IStorageApplyDAO storageApply ;
	@Resource
	private RedisTemplate<String,Object> redisTemplate ;
	@Override
	public Map<String, Object> addPre() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allProvince", this.storageApply.findAllProvince()) ;
		map.put("allWitem", this.storageApply.findAllWitem()) ;
		return map;
	}
	@Override
	public List<City> getCity(long pid) {
		return this.storageApply.findCityByPid(pid);
	}
	@Override
	public List<Warehouse> getWarehouse(long wiid) {
		return this.storageApply.findAllByWiid(wiid);
	}
	@Override
	public boolean add(StorageApply storageApply) {
		if(this.storageApply.findByTitle(storageApply.getTitle()) == null) {
			return this.storageApply.doCreate(storageApply) ;
		}
		return false ;
	}
	
	@Override
	public Map<String, Object> getByMyself(long currentPage, int lineSize, String column, String keyWord, String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,Object> allWarehouse = new HashMap<Long,Object>() ;
		Map<Long,Object> allWitem = new HashMap<Long,Object>() ;
		Map<Long,Long> allCount = new HashMap<Long,Long>() ;
		Map<String,Object> params = super.paramToMap(currentPage, lineSize, column, keyWord) ;
		params.put("mid", mid) ;
		Map<String,Object> param = super.paramToMap(column, keyWord) ;
		param.put("mid", mid) ;
		List<StorageApply> allStorageApply = this.storageApply.findByMyself(params) ;
		Iterator<StorageApply> iter = allStorageApply.iterator() ;
		while(iter.hasNext()) {
			long count = 0l ;
			StorageApply storageApply = iter.next() ;
			Set<Object> attrs = this.redisTemplate.opsForHash().keys(String.valueOf(storageApply.getSaid())) ;
			Iterator<Object> iterTemp = attrs.iterator() ;
			while(iterTemp.hasNext()) {
				Object obj = iterTemp.next() ;
				if(this.redisTemplate.opsForHash().get(String.valueOf(storageApply.getSaid()), obj) instanceof StorageApplyDetails) {
					StorageApplyDetails storageApplyDetails = (StorageApplyDetails)this.redisTemplate.opsForHash().get(String.valueOf(storageApply.getSaid()), obj) ;
					count += storageApplyDetails.getNum() ;
				}
				
			}
			allCount.put(storageApply.getSaid(), count) ;
			allWarehouse.put(storageApply.getSaid(), this.storageApply.findByWid(storageApply.getWid())) ; 
			allWitem.put(storageApply.getSaid(), this.storageApply.findWitemByWiid(storageApply.getWiid())) ;
		}
		map.put("allStorageApply", allStorageApply) ;
		map.put("allRecorders", this.storageApply.getMyselfCount(param)) ;
		map.put("allWarehouse", allWarehouse) ;
		map.put("allWitem", allWitem) ;
		map.put("allCount", allCount) ;
		return map;
	}
	@Override
	public Map<String, Object> editPre(long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		StorageApply storageApply = this.storageApply.findBySaid(said) ;
		List<Province> allProvince = this.storageApply.findAllProvince() ;
		List<Witem> allWitem = this.storageApply.findAllWitem() ;
		List<City> cityByPid = this.storageApply.findCityByPid(storageApply.getPid()) ;
		List<Warehouse> warehouseByWiid = this.storageApply.findAllByWiid(storageApply.getWiid()) ;
		map.put("storageApply", storageApply) ;
		map.put("allProvince", allProvince) ;
		map.put("allWitem", allWitem) ;
		map.put("cityByPid", cityByPid) ;
		map.put("warehouseByWiid", warehouseByWiid) ;
		return map;
	}
	@Override
	public boolean edit(StorageApply storageApply) {
		return this.storageApply.doEdit(storageApply) ;
	}
	@Override
	public boolean submit(int status, long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("said", said) ;
		if(this.storageApply.doEditStatus(map)) {
			Set<Object> attrs = this.redisTemplate.opsForHash().keys(String.valueOf(said)) ;
			Iterator<Object> iter = attrs.iterator() ;
			while(iter.hasNext()) {
				Object obj = iter.next() ;
				if(this.redisTemplate.opsForHash().get(String.valueOf(said), obj) instanceof StorageApplyDetails) {
					if(!this.storageApply.doCreateStorageApplyDetails((StorageApplyDetails)this.redisTemplate.opsForHash().get(String.valueOf(said), obj))) {
						return false ;
					} 
				}
			}
			this.redisTemplate.opsForHash().put(String.valueOf(said),"applyDate",new Date()) ;
			return true ;
		}else {
			return false ;
		}
	}
	@Override
	public boolean reset(int status, long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("said", said) ;
		if(this.storageApply.doEditStatus(map)) {
			return this.storageApply.doRemoveBySaid(said) ;
		}else {
			return false ;
		}
	}
	@Override
	public boolean remove(long said) {
		return this.storageApply.doRemove(said);
	}
	@Override
	public Map<String, Object> listDetails(long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		List<StorageApplyDetails> goodsTemps = new ArrayList<StorageApplyDetails>() ;
		Set<Object> attrs = this.redisTemplate.opsForHash().keys(String.valueOf(said)) ;
		Iterator<Object> iter = attrs.iterator() ;
		while(iter.hasNext()) {
			Object obj = iter.next() ;
			if(this.redisTemplate.opsForHash().get(String.valueOf(said), obj) instanceof StorageApplyDetails) {
				goodsTemps.add((StorageApplyDetails)this.redisTemplate.opsForHash().get(String.valueOf(said), obj)) ;
			}
		}
		StorageApply storageApply = this.storageApply.findBySaid(said) ;
		String address = this.storageApply.findByWid(storageApply.getWid()).getAddress() ;
		String title = this.storageApply.findWitemByWiid(storageApply.getWiid()).getTitle() ;
		map.put("storageApply", storageApply);
		map.put("address", address) ;
		map.put("title",title) ;
		map.put("goodsTemps",goodsTemps) ;
		return map;
	}
	@Override
	public Goods getGoodsByGid(long gid) {
		return this.storageApply.findGoodsByGid(gid);
	}
	@Override
	public boolean addGoodsTemp(long said, StorageApplyDetails storageApplyDetails) {
		this.redisTemplate.opsForHash().put(String.valueOf(said), String.valueOf(storageApplyDetails.getGid()), storageApplyDetails);
		return this.redisTemplate.opsForHash().get(String.valueOf(said), String.valueOf(storageApplyDetails.getGid())) != null;
	}
	@Override
	public boolean deleteGoodsTemp(long said, long gid) {
		Long delete = this.redisTemplate.opsForHash().delete(String.valueOf(said), String.valueOf(gid));
		return delete == 1;
	}
}
