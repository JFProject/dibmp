package cn.mldn.dibmp.service.impl;

import java.text.SimpleDateFormat;
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
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
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
		return map;
	}
	
}
