package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IManageDAO;
import cn.mldn.dibmp.service.IManageServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.util.MyMath;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class ManageServiceBackImpl extends AbstractService implements IManageServiceBack{
	
	@Resource
	private IManageDAO manageDAO ;
	@Resource
	private RedisTemplate<String, Object> redisTemplate ;
	
	@Override
	public Map<String, Object> storageInput(long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,Double> price = new HashMap<Long,Double>() ;
		List<StorageApplyDetails> list = new ArrayList<StorageApplyDetails>() ;
		StorageApply storageApply = this.manageDAO.findStorageApplyBySaid(said) ;
		String provinceName = this.manageDAO.findProvinceByPid(storageApply.getPid()).getTitle() ;
		String cityName = this.manageDAO.findCityByCid(storageApply.getCid()).getTitle() ;
		String widName = this.manageDAO.findWarehouseByWid(storageApply.getWid()).getName() ;
		String wiidName = this.manageDAO.findWitemBywiid(storageApply.getWiid()).getTitle() ;
		double totalPrice = 0 ;
		List<StorageApplyDetails> allStorageApplyDetails = this.manageDAO.findDetailsBySaid(said) ;
		Iterator<StorageApplyDetails> storageApplyDetailsIter = allStorageApplyDetails.iterator() ;
		while(storageApplyDetailsIter.hasNext()) {
			StorageApplyDetails storageApplyDetails = storageApplyDetailsIter.next() ;
			list.add(storageApplyDetails) ;
 			double temp = storageApplyDetails.getNum() * storageApplyDetails.getPrice() ;
			price.put(storageApplyDetails.getSadid(), MyMath.round(temp, 1)) ;
			totalPrice += temp ;
		}
		map.put("storageApply", storageApply) ;
		map.put("provinceName", provinceName) ;
		map.put("cityName", cityName) ;
		map.put("widName", widName) ;
		map.put("wiidName", wiidName) ;
		map.put("price", price) ;
		map.put("totalPrice", MyMath.round(totalPrice, 1)) ;
		map.put("allStorageApplyDetails", list) ;
		return map;
	}

	@Override
	public boolean storageAuditSuccess(long said,String mid) {
		StorageApply storageApply = this.manageDAO.findStorageApplyBySaid(said) ;
		List<StorageApplyDetails> list = this.manageDAO.findDetailsBySaid(said) ;
		int totalNum = 0 ;
		Iterator<StorageApplyDetails> iter = list.iterator() ;
		while(iter.hasNext()) {
			StorageApplyDetails storageApplyDetails = iter.next() ;
			totalNum += storageApplyDetails.getNum() ;
			StorageRecord storageRecord = new StorageRecord() ;
			storageRecord.setSaid(said);
			storageRecord.setGid(storageApplyDetails.getGid()) ;
			storageRecord.setWid(storageApply.getWid());
			storageRecord.setName(storageApplyDetails.getName());
			storageRecord.setNum(storageApplyDetails.getNum());
			storageRecord.setPrice(storageApplyDetails.getPrice());
			storageRecord.setWeight(storageApplyDetails.getWeight());
			storageRecord.setInmid(mid);
			storageRecord.setAuditDate(new Date());
			if(!this.manageDAO.doCreateStorageRecord(storageRecord)) {
				return false ;
			}
			Goods goods = new Goods() ;
			goods.setGid(storageApplyDetails.getGid());
			goods.setLastin(new Date());
			goods.setStornum(this.manageDAO.findGoodsByGid(storageApplyDetails.getGid()).getStornum() + storageApplyDetails.getNum());
			if(!this.manageDAO.doEditGoodsByGid(goods)) {
				return false ;
			}
		}
		Warehouse warehouse = new Warehouse() ;
		warehouse.setWid(storageApply.getWid());
		Warehouse oldWarehouse = this.manageDAO.findWarehouseByWid(storageApply.getWid()) ;
		int oldCurr ;
		if(oldWarehouse.getCurrnum() == null) {
			oldCurr = 0 ;
		}else {
			oldCurr = oldWarehouse.getCurrnum() ;
		}
		if((oldCurr + totalNum) > oldWarehouse.getMaximum()) {
			return false ;
		}else {
			warehouse.setCurrnum(oldCurr + totalNum);
			if(this.manageDAO.doEditWarehouseByWid(warehouse)) {
				return this.storageAuditFailure(said, 4) ;
			}
		}
		return false;
	}

	@Override
	public boolean storageAuditFailure(long said, int status) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("said", said) ;
		map.put("status", status) ;
		return this.manageDAO.doEditStatus(map);
	}
	
}
