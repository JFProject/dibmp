package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.convert.util.SetToLong;
import cn.mldn.dibmp.dao.ICustomerDAO;
import cn.mldn.dibmp.dao.IDistributionDAO;
import cn.mldn.dibmp.service.IDistributionServiceBack;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class DistributionBackImpl extends AbstractService implements IDistributionServiceBack {
	
	@Resource
	private ICustomerDAO CustomerDAO ;
	@Resource
	private IDistributionDAO distributionDAO ;
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate ;
	
	@Override
	public Map<String, Object> getCustomers(String mid) {
		Map<String, Object> map = new HashMap<>();
		Map<Long, Object> count = new HashMap<>();
		String cuid = String.valueOf(this.redisTemplate.opsForValue().get(mid));
		if(cuid == null || "null".equals(cuid)){
			map.put("Customer", null);
			return map;
		}else{
			map.put("Customer", this.CustomerDAO.findById(Long.parseLong(cuid)));
			
		}
		String cid = this.redisTemplate.opsForValue().get(mid).toString();
		List<Goods> all = new ArrayList<>();
		Set<Object> keys = this.redisTemplate.opsForHash().keys(cid);
		if(keys == null || keys.size() == 0){
			return map;
		}
		Set<Long> gids = SetToLong.ObjectValToLong(keys);
		Iterator<Long> it = gids.iterator();
		while(it.hasNext()){
			Long gid = it.next();
			count.put(gid, this.redisTemplate.opsForHash().get(cuid.toString(), gid.toString()));
			all.add(this.CustomerDAO.findByGid(gid));
		}
		map.put("allGoods", all);
		map.put("allCount", count);
		return map;
	}

	@Override
	public boolean adds(String mid, Long gid) {
		String id = gid.toString();
		String cid = String.valueOf(this.redisTemplate.opsForValue().get(mid));
		if(this.redisTemplate.opsForHash().get(cid, id) == null){//没有数据,可以新建
			this.redisTemplate.opsForHash().put(cid, id, 1);
		}else{
			Integer count = (Integer)this.redisTemplate.opsForHash().get(cid, id);
			this.redisTemplate.opsForHash().put(cid, id, count + 1);
		}
		return this.redisTemplate.opsForHash().get(cid, id) != null;
	}

	@Override
	public boolean indentAdd(String mid,Distribution vo) {
		Double prive = 0.0;
		Integer sum = 0;
		String cuid = String.valueOf(this.redisTemplate.opsForValue().get(mid));
		String cid = this.redisTemplate.opsForValue().get(mid).toString();
		Set<Long> gids = SetToLong.ObjectValToLong(this.redisTemplate.opsForHash().keys(cid));
		if(gids == null || gids.size() == 0){
			return false;
		}
		List<Goods> all = new ArrayList<>();
		Iterator<Long> it = gids.iterator();
		while(it.hasNext()){
			Long gid = it.next();
			Goods gvo = this.CustomerDAO.findByGid(gid);
			Integer count = (Integer)this.redisTemplate.opsForHash().get(cuid, String.valueOf(gid));
			this.redisTemplate.opsForHash().delete(cuid, String.valueOf(gid));
			prive = count * gvo.getPrice() + prive;
			sum = sum + gvo.getStornum();
			all.add(gvo);
			this.redisTemplate.opsForHash().delete(cuid, String.valueOf(gid));
		}
		vo.setPrice(prive);
		vo.setGnum(sum);
		vo.setAppmid(mid);
		vo.setStatus(1);
		vo.setCuid(Long.parseLong(cuid));
		if(this.distributionDAO.doCreate(vo)){
			DistributionDetails dvo = new DistributionDetails() ;
			Long maxDsId = this.distributionDAO.findMaxId();
			dvo.setDsid(maxDsId);
			Iterator<Goods> iterator = all.iterator();
			while(iterator.hasNext()){
				Goods gtvo = iterator.next();
				dvo.setGid(gtvo.getGid());
				dvo.setName(gtvo.getName());
				dvo.setNum(gtvo.getStornum());
				dvo.setPrice(prive);
				Long wid = this.distributionDAO.findWid(gtvo.getGid());
				dvo.setWid(wid);
			}
			this.redisTemplate.delete(mid);
			return this.distributionDAO.doCreateByDistributionDetails(dvo);
		}
		return true;
	}
}
