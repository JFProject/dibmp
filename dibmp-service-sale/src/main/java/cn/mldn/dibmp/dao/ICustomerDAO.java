 package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Citem;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Critem;
import cn.mldn.dibmp.vo.Csource;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Province;

public interface ICustomerDAO { 
	/**
	 * 
	 * 查询所有的用户来源title
	 * @return 所有的用户来源
	 */
	public List<Csource> findAllCustomerSourceTitle();
	/**
	 * 获取所有的省份名称
	 * @return
	 */
	public List<Province> findAllProvinceTitle();
	public List<Citem> getCitemTitle();
	public List<City> findCityByPid(Long pid);
	public boolean doCreate(Customer vo);
	/**
	 * 查询所有的客户信息
	 * @return 客户信息
	 */
	public List<Customer> findAllCustomer();
	public List<Customer> findSplitCustomer(Map<String,Object> params);
	public String findMemberNameById(String mid);
	public Long getSplitCount(Map<String,Object> params);
	public String getSplitCitem(Long ciid);
	public List<Critem> findAllCritem();
	public boolean doCreateCr(CustomerRecord vo);
	public boolean doEditConnum(Long cuid);
	public Integer getCrNum(String cmid);
	public List<CustomerRecord> findAllCustomerRecord(Long cmid);  
	public String findPhoneByMid(String mid);
	public Customer findById(Long cuid);
	public Goods findByGid(Long gid);
}
