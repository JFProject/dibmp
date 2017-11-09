package cn.mldn.dibmp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageAuditDAO;
import cn.mldn.dibmp.service.IStorageAuditServiceBack;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.util.MyMath;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class StorageAuditServiceBackImpl extends AbstractService implements IStorageAuditServiceBack {
	@Resource
	private IStorageAuditDAO storageAuditDAO ;
	@Resource
	private RedisTemplate<String, Object> redisTemplate ;
	private static final int TO_AUDIT = 1 ;
	@Override
	public Map<String, Object> listPrepare(long currentPage,int lineSize,String column,String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,String> allAppName = new HashMap<Long,String>() ;
		Map<Long,String> allAppDate = new HashMap<Long,String>() ;
		Map<Long,Integer> allCount = new HashMap<Long,Integer>() ;
		Map<Long,Double> allPrice = new HashMap<Long,Double>() ;
		Map<Long,String> allWarehouseName = new HashMap<Long,String>() ;
		Map<Long,String> allProvinceName = new HashMap<Long,String>() ;
		Map<Long,String> allCityName = new HashMap<Long,String>() ;
		Map<String,Object> params = new HashMap<String,Object>() ;
		Map<String,Object> param = new HashMap<String,Object>() ;
		params = super.paramToMap(currentPage, lineSize, column, keyWord) ;
		params.put("status", TO_AUDIT) ;
		param = super.paramToMap(column, keyWord) ;
		param.put("status", TO_AUDIT) ;
		List<StorageApply> allStorageApply = this.storageAuditDAO.findByStatus(params) ;
		Long allRecorders = this.storageAuditDAO.getCountByStatus(param) ;
		Iterator<StorageApply> storageApplyIter = allStorageApply.iterator() ;
		while(storageApplyIter.hasNext()) {
			int count = 0 ;
			double price = 0 ;
			StorageApply storageApply = storageApplyIter.next() ;
			String appName = this.storageAuditDAO.findMemberByMid(storageApply.getAppmid()).getName() ;
			List<StorageApplyDetails> allStorageApplyDetails = this.storageAuditDAO.findDetailsBySaid(storageApply.getSaid()) ;
			Iterator<StorageApplyDetails> storageApplyDetailsIter = allStorageApplyDetails.iterator() ;
			while(storageApplyDetailsIter.hasNext()) {
				StorageApplyDetails storageApplyDetails = storageApplyDetailsIter.next() ;
				count += storageApplyDetails.getNum() ;
				price += storageApplyDetails.getNum() * storageApplyDetails.getPrice() ;
			}
			allProvinceName.put(storageApply.getSaid(), this.storageAuditDAO.findProvinceByPid(storageApply.getPid()).getTitle()) ;
			allCityName.put(storageApply.getSaid(), this.storageAuditDAO.findCityByCid(storageApply.getCid()).getTitle()) ;
			allWarehouseName.put(storageApply.getSaid(), this.storageAuditDAO.findWarehouseByWid(storageApply.getWid()).getName()) ;
			allAppName.put(storageApply.getSaid(), appName) ;
			allAppDate.put(storageApply.getSaid(), new SimpleDateFormat("yyyy-MM-dd").format((Date)this.redisTemplate.opsForHash().get(String.valueOf(storageApply.getSaid()), "applyDate"))) ;
			allCount.put(storageApply.getSaid(), count) ;
			allPrice.put(storageApply.getSaid(), MyMath.round(price, 1)) ;
		}
		map.put("allStorageApplyDetails", allStorageApply) ;
		map.put("allRecorders",allRecorders) ;
		map.put("allAppName", allAppName) ;
		map.put("allAppDate", allAppDate) ;
		map.put("allCount", allCount) ;
		map.put("allPrice", allPrice) ;
		map.put("allWarehouseName", allWarehouseName) ;
		map.put("allProvinceName", allProvinceName) ;
		map.put("allCityName", allCityName) ;
		return map;
	}
	@Override
	public Map<String, Object> editPre(long said) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,Double> price = new HashMap<Long,Double>() ;
		List<StorageApplyDetails> list = new ArrayList<StorageApplyDetails>() ;
		StorageApply storageApply = this.storageAuditDAO.findStorageApplyBySaid(said) ;
		String provinceName = this.storageAuditDAO.findProvinceByPid(storageApply.getPid()).getTitle() ;
		String cityName = this.storageAuditDAO.findCityByCid(storageApply.getCid()).getTitle() ;
		String widName = this.storageAuditDAO.findWarehouseByWid(storageApply.getWid()).getName() ;
		String wiidName = this.storageAuditDAO.findWitemBywiid(storageApply.getWiid()).getTitle() ;
		String appName = this.storageAuditDAO.findMemberByMid(storageApply.getAppmid()).getName() ;
		double totalPrice = 0 ;
		List<StorageApplyDetails> allStorageApplyDetails = this.storageAuditDAO.findDetailsBySaid(said) ;
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
		map.put("appName", appName) ;
		map.put("price", price) ;
		map.put("totalPrice", MyMath.round(totalPrice, 1)) ;
		map.put("allStorageApplyDetails", list) ;
		return map;
	}
	
	@Override
	public Map<String, Object> warehouseShow(long wid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Warehouse warehouse = this.storageAuditDAO.findWarehouseByWid(wid) ;
		map.put("warehouse", warehouse) ;
		map.put("title", this.storageAuditDAO.findWitemBywiid(warehouse.getWiid()).getTitle()) ;
		return map;
	}
	
	@Override
	public boolean edit(long said, int status,String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("said", said) ;
		if(this.storageAuditDAO.doEditStatus(map)) {
			this.redisTemplate.opsForHash().put(String.valueOf(said), "auditMid", mid) ;
			return true ;
		}
		return false ;
	}
	@Override
	public Map<String, Object> listHistory(long currentPage,int lineSize,String column,String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,String> allAppName = new HashMap<Long,String>() ;
		Map<Long,String> allAppDate = new HashMap<Long,String>() ;
		Map<Long,Integer> allCount = new HashMap<Long,Integer>() ;
		Map<Long,Double> allPrice = new HashMap<Long,Double>() ;
		Map<Long,String> allWarehouseName = new HashMap<Long,String>() ;
		Map<Long,String> allProvinceName = new HashMap<Long,String>() ;
		Map<Long,String> allCityName = new HashMap<Long,String>() ;
		Map<Long,Member> allAuditInfo = new HashMap<Long,Member>() ;
		Map<String,Object> params = new HashMap<String,Object>() ;
		Map<String,Object> param = new HashMap<String,Object>() ;
		params = super.paramToMap(currentPage, lineSize, column, keyWord) ;
		param = super.paramToMap(column, keyWord) ;
		List<StorageApply> allStorageApply = this.storageAuditDAO.findHistoryByStatus(params) ;
		Long allRecorders = this.storageAuditDAO.getCountHistoryByStatus(param) ;
		Iterator<StorageApply> storageApplyIter = allStorageApply.iterator() ;
		while(storageApplyIter.hasNext()) {
			int count = 0 ;
			double price = 0 ;
			StorageApply storageApply = storageApplyIter.next() ;
			String appName = this.storageAuditDAO.findMemberByMid(storageApply.getAppmid()).getName() ;
			List<StorageApplyDetails> allStorageApplyDetails = this.storageAuditDAO.findDetailsBySaid(storageApply.getSaid()) ;
			Iterator<StorageApplyDetails> storageApplyDetailsIter = allStorageApplyDetails.iterator() ;
			while(storageApplyDetailsIter.hasNext()) {
				StorageApplyDetails storageApplyDetails = storageApplyDetailsIter.next() ;
				count += storageApplyDetails.getNum() ;
				price += storageApplyDetails.getNum() * storageApplyDetails.getPrice() ;
			}
			allAuditInfo.put(storageApply.getSaid(), this.storageAuditDAO.findMemberByMid((String)this.redisTemplate.opsForHash().get(String.valueOf(storageApply.getSaid()), "auditMid"))) ;
			allProvinceName.put(storageApply.getSaid(), this.storageAuditDAO.findProvinceByPid(storageApply.getPid()).getTitle()) ;
			allCityName.put(storageApply.getSaid(), this.storageAuditDAO.findCityByCid(storageApply.getCid()).getTitle()) ;
			allWarehouseName.put(storageApply.getSaid(), this.storageAuditDAO.findWarehouseByWid(storageApply.getWid()).getName()) ;
			allAppName.put(storageApply.getSaid(), appName) ;
			allAppDate.put(storageApply.getSaid(), new SimpleDateFormat("yyyy-MM-dd").format((Date)this.redisTemplate.opsForHash().get(String.valueOf(storageApply.getSaid()), "applyDate"))) ;
			allCount.put(storageApply.getSaid(), count) ;
			allPrice.put(storageApply.getSaid(), MyMath.round(price, 1)) ;
		}
		map.put("allStorageApplyDetails", allStorageApply) ;
		map.put("allRecorders",allRecorders) ;
		map.put("allAppName", allAppName) ;
		map.put("allAppDate", allAppDate) ;
		map.put("allCount", allCount) ;
		map.put("allPrice", allPrice) ;
		map.put("allWarehouseName", allWarehouseName) ;
		map.put("allProvinceName", allProvinceName) ;
		map.put("allCityName", allCityName) ;
		map.put("allAuditInfo", allAuditInfo) ;
		return map;
	}
}
