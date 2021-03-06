package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.ICustomerDAO;
import cn.mldn.dibmp.service.ICustomerServiceBack;
import cn.mldn.dibmp.vo.Citem;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Critem;
import cn.mldn.dibmp.vo.Csource;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class CustomerServiceBackImpl extends AbstractService implements ICustomerServiceBack {
	
	@Resource
	private ICustomerDAO CustomerDAO ;
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate ;
	
	@Override
	public boolean band(Long cid, String mid) {
		this.redisTemplate.opsForValue().set(mid, cid);
//		Set<Object> keys = redisTemplate.opsForHash().keys("1") ;
//		Iterator<Object> iter = keys.iterator() ;
//		while(iter.hasNext()){
//			Object gid = iter.next() ;
//			
//			int num = (Integer) redisTemplate.opsForHash().get("1", gid) ;
//		}
		return this.redisTemplate.opsForValue().get(mid) != null;
	}
	@Override
	public List<Csource> getCsourceTitle() {
		return CustomerDAO.findAllCustomerSourceTitle();
	}

	@Override
	public List<Province> getProvince() {
		return CustomerDAO.findAllProvinceTitle();
	}

	@Override
	public List<Citem> getCitemTitle() {
		return CustomerDAO.getCitemTitle();
	}

	@Override
	public List<City> getCityTitle(Long pid) {
		// TODO Auto-generated method stub
		return CustomerDAO.findCityByPid(pid);
	}

	@Override
	public boolean add(Customer vo) {
		// TODO Auto-generated method stub
		return CustomerDAO.doCreate(vo);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return CustomerDAO.findAllCustomer();
	}

	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord,String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,String> recorderName = new HashMap<Long,String>() ;
		Map<Long,String> citemMap = new HashMap<Long,String>() ;
		List<Customer> allCustomer = this.CustomerDAO.findSplitCustomer(super.paramToMap(currentPage, lineSize, column, keyWord));
		Iterator<Customer> iter = allCustomer.iterator() ;
		while(iter.hasNext()) {
			Customer customers = iter.next() ;
			recorderName.put(customers.getCuid(),CustomerDAO.findMemberNameById(customers.getRecorder()) ) ;
			citemMap.put(customers.getCuid(), CustomerDAO.getSplitCitem(customers.getCiid()));
		}
		map.put("allCustomers", allCustomer) ;
		map.put("allRecorderName", recorderName) ;
		map.put("allRecorders", this.CustomerDAO.getSplitCount(super.paramToMap(column, keyWord))) ;
		map.put("allCustomerItemTitles", citemMap);
		if(this.redisTemplate.opsForValue().get(mid) == null){//没有绑定客户
			map.put("flag", "id");//显示按钮标签
		}else{
			map.put("flag", "hidden");//不现实按钮
		}
		return map ;
	}

	@Override
	public List<Critem> getAllCritem() {
		return CustomerDAO.findAllCritem();
	}

	@Override
	public boolean addCr(CustomerRecord vo) {
		// TODO Auto-generated method stub
		return CustomerDAO.doCreateCr(vo);
	}

	@Override
	public Integer getCrNum(String cmid) {
		// TODO Auto-generated method stub
		return CustomerDAO.getCrNum(cmid);
	}

	@Override
	public boolean editConnum(Long cuid) {
		// TODO Auto-generated method stub
		return CustomerDAO.doEditConnum(cuid);
	}

	@Override
	public List<CustomerRecord> getAllCustomerRecord(Long cmid) {
		return CustomerDAO.findAllCustomerRecord(cmid);
	}

	@Override
	public String getPhomeByMid(String mid) {
		// TODO Auto-generated method stub
		return CustomerDAO.findPhoneByMid(mid);
	}

	


}
