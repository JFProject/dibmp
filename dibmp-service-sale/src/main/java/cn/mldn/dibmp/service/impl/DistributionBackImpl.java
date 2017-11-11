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
import cn.mldn.dibmp.service.IDistributionServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class DistributionBackImpl extends AbstractService implements IDistributionServiceBack {
	
	@Resource
	private ICustomerDAO CustomerDAO ;
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate ;

	@Override
	public Map<String, Object> getCustomer(String mid) {
		Map<String, Object> map = new HashMap<>();
		Map<Long, Object> count = new HashMap<>();
		String cuid = this.redisTemplate.opsForValue().get(mid).toString();
		if(cuid != null){
			map.put("Customer", this.CustomerDAO.findById(Long.parseLong(cuid)));
		}else{
			map.put("Customer", null);
		}
		String cid = this.redisTemplate.opsForValue().get(mid).toString();
		List<Goods> all = new ArrayList<>();
		Set<Long> gids = SetToLong.ObjectValToLong(this.redisTemplate.opsForHash().keys(cid));
		Iterator<Long> it = gids.iterator();
		while(it.hasNext()){
			Long gid = it.next();
			count.put(gid, this.redisTemplate.opsForHash().get(cuid, gid.toString()));
			all.add(this.CustomerDAO.findByGid(gid));
		}
		map.put("allGoods", all);
		map.put("allCount", count);
		return map;
	}

	@Override
	public boolean add(String mid, Long gid) {
		String id = gid.toString();
		String cid = this.redisTemplate.opsForValue().get(mid).toString();
		if(this.redisTemplate.opsForHash().get(cid, id) == null){//没有数据,可以新建
			this.redisTemplate.opsForHash().put(cid, id, 1);
		}else{
			Integer count = (Integer)this.redisTemplate.opsForHash().get(cid, id);
			this.redisTemplate.opsForHash().put(cid, id, count + 1);
		}
		return this.redisTemplate.opsForHash().get(cid, id) != null;
	}
	
	
}
