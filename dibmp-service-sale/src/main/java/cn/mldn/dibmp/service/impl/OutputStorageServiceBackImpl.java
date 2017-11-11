package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IOutputStorageDAO;
import cn.mldn.dibmp.service.IOutputStorageServiceBack;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.Province;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class OutputStorageServiceBackImpl extends AbstractService implements IOutputStorageServiceBack{
	@Resource
	private IOutputStorageDAO outputStorageDAO ;
	@Override
	public Map<String, Object> listMySelf(long currentPage, int lineSize, String column, String keyWord, String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<Long,Province> province = new HashMap<Long,Province>() ;
		Map<Long,City> city = new HashMap<Long,City>() ;
		Map<Long,Customer> customer = new HashMap<Long,Customer>() ;
		Map<Long,Member> applyMember = new HashMap<Long,Member>() ;
		Map<Long,Member> outMember = new HashMap<Long,Member>() ;
		Map<String,Object> params = super.paramToMap(currentPage, lineSize, column, keyWord) ;
		Map<String,Object> param = super.paramToMap(column, keyWord) ;
		params.put("appmid", mid) ;
		param.put("appmid", mid) ;
		List<Distribution> allDistribution = this.outputStorageDAO.findSplitByMyself(params) ;
		Iterator<Distribution> iter = allDistribution.iterator() ;
		while(iter.hasNext()) {
			Distribution distribution = iter.next() ;
			province.put(distribution.getDsid(), this.outputStorageDAO.findProvinceByPid(distribution.getPid())) ;
			city.put(distribution.getDsid(), this.outputStorageDAO.findCityByCid(distribution.getCid())) ;
			customer.put(distribution.getDsid(), this.outputStorageDAO.findCustomerByCuid(distribution.getCuid())) ;
			applyMember.put(distribution.getDsid(), this.outputStorageDAO.findMemberByMid(distribution.getAppmid())) ;
			outMember.put(distribution.getDsid(), this.outputStorageDAO.findMemberByMid(distribution.getOutmid())) ;
		}
		map.put("allDistribution", allDistribution) ;
		map.put("allRecorders", this.outputStorageDAO.getCountByMyself(param)) ;
		map.put("province", province) ;
		map.put("city", city) ;
		map.put("customer", customer) ;
		map.put("applyMember", applyMember) ;
		map.put("outMember", outMember) ;
		return map;
	}
	
	@Override
	public boolean clear(long dsid) {
		return this.outputStorageDAO.doRemoveDistribution(dsid);
	}
}
