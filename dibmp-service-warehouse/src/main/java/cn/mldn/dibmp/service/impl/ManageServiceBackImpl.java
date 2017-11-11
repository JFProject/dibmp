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
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.WarehouseGoods;
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
		if(storageApply == null) {
			return null ;
		}
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

	/* (non-Javadoc)
	 * @see cn.mldn.dibmp.service.IManageServiceBack#storageAuditSuccess(long, java.lang.String)
	 */
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
			Map<String,Object> widGid = new HashMap<String,Object>() ;
			widGid.put("wid", storageApply.getWid()) ;
			widGid.put("gid", storageApplyDetails.getGid()) ;
			WarehouseGoods temp = this.manageDAO.findWarehouseGoodsByWidAndGid(widGid) ;
			if(temp == null) {
				WarehouseGoods warehouseGoods = new WarehouseGoods() ;
				warehouseGoods.setWid(storageApply.getWid());
				warehouseGoods.setGid(storageApplyDetails.getGid());
				warehouseGoods.setNum(storageApplyDetails.getNum());
				this.manageDAO.doCreateWarehouseGoods(warehouseGoods) ;
			}else {
				WarehouseGoods warehouseGoods = new WarehouseGoods() ;
				warehouseGoods.setWgid(temp.getWgid()) ;
				warehouseGoods.setNum(temp.getNum() + storageApplyDetails.getNum());
				this.manageDAO.doEditWarehouseGoods(warehouseGoods) ;
			}
			Goods goods = new Goods() ;
			goods.setGid(storageApplyDetails.getGid());
			goods.setLastin(new Date());
			if(this.manageDAO.findGoodsByGid(storageApplyDetails.getGid()).getStornum() == null) {
				goods.setStornum(storageApplyDetails.getNum());
			}else {
				goods.setStornum(this.manageDAO.findGoodsByGid(storageApplyDetails.getGid()).getStornum() + storageApplyDetails.getNum());
			}
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

	@Override
	public Map<String, Object> distributionInput(long dsid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,String> goodsDistributionWarehouse = new HashMap<Long,String>() ;
		Map<Long,Double> goodsprice = new HashMap<Long,Double>() ;
		Distribution distribution = this.manageDAO.findDistributionByDsid(dsid) ;
		if(distribution == null) {
			return null ;
		}
		List<DistributionDetails> allDistributionDetails = this.manageDAO.findDistributionDetailsByDsid(dsid) ;
		Iterator<DistributionDetails> iter = allDistributionDetails.iterator() ;
		while(iter.hasNext()) {
			DistributionDetails distributionDetails = iter.next() ;
			Warehouse warehouse = this.manageDAO.findWarehouseByWid(distributionDetails.getWid()) ;
			goodsDistributionWarehouse.put(distributionDetails.getDsdid(), this.manageDAO.findProvinceByPid(warehouse.getPid()).getTitle() + " " + this.manageDAO.findCityByCid(warehouse.getCid()).getTitle() + " " + warehouse.getName()) ;
			goodsprice.put(distributionDetails.getDsdid(), MyMath.round(distributionDetails.getNum() * distributionDetails.getPrice(), 1)) ;
		}
		map.put("distribution", distribution) ;
		map.put("provinceName", this.manageDAO.findProvinceByPid(distribution.getPid()).getTitle()) ;
		map.put("cityName", this.manageDAO.findCityByCid(distribution.getCid()).getTitle()) ;
		map.put("allDistributionDetails", allDistributionDetails) ;
		map.put("goodsDistributionWarehouse", goodsDistributionWarehouse) ;
		map.put("goodsPrice", goodsprice) ;
		map.put("customer", this.manageDAO.findCustomerByCuid(distribution.getCuid())) ;
		map.put("appMember", this.manageDAO.findMemberByMid(distribution.getAppmid())) ;
		return map;
	}
	
	@Override
	public boolean distributionSuccess(long dsid, String mid) {
		List<DistributionDetails> allDistributionDetails = this.manageDAO.findDistributionDetailsByDsid(dsid) ;
		Iterator<DistributionDetails> iter = allDistributionDetails.iterator() ;
		while(iter.hasNext()) {
			DistributionDetails distributionDetails = iter.next() ;
			Map<String,Object> widGid = new HashMap<String,Object>() ;
			widGid.put("wid", distributionDetails.getWid()) ;
			widGid.put("gid", distributionDetails.getGid()) ;
			WarehouseGoods warehouseGoods = this.manageDAO.findWarehouseGoodsByWidAndGid(widGid) ;
			if(warehouseGoods == null || warehouseGoods.getNum() - distributionDetails.getNum() < 0) {
				return false ;
			}
			warehouseGoods.setNum(warehouseGoods.getNum() - distributionDetails.getNum());
			if(!this.manageDAO.doEditWarehouseGoods(warehouseGoods)) {
				return false ;
			}
			Goods goods = new Goods() ;
			Integer stornum = this.manageDAO.findGoodsStornumByGid(distributionDetails.getGid()) ;
			if(stornum == null || stornum - distributionDetails.getNum() < 0) {
				return false ;
			}
			goods.setGid(distributionDetails.getGid());
			goods.setStornum(stornum - distributionDetails.getNum());
			if(!this.manageDAO.doEditGoodsStornumByGid(goods)) {
				return false ;
			}
			Warehouse warehouse = new Warehouse() ;
			Integer currnum = this.manageDAO.findWarehouseCurrnumByWid(distributionDetails.getWid()) ;
			if(currnum == null || currnum - distributionDetails.getNum() < 0) {
				return false ;
			}
			warehouse.setWid(distributionDetails.getWid()) ;
			warehouse.setCurrnum(currnum - distributionDetails.getNum());
			if(!this.manageDAO.doEditWarehouseStornumByWid(warehouse)) {
				return false ;
			}
		}
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("dsid", dsid) ;
		map.put("outmid", mid) ;
		map.put("status", 1) ;
		return this.manageDAO.doEditDistributionStatus(map);
	}
	
	@Override
	public boolean distributionFailure(long dsid, String mid , int status) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("dsid", dsid) ;
		map.put("outmid", mid) ;
		map.put("status", status) ;
		return this.manageDAO.doEditDistributionStatus(map);
	}
}
