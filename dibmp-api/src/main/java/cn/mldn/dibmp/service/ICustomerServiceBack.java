package cn.mldn.dibmp.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Citem;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Critem;
import cn.mldn.dibmp.vo.Csource;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Province;

public interface ICustomerServiceBack {
	/**
	 * 查询所有客户来源的title
	 * @return
	 */
	public List<Csource> getCsourceTitle();
	/**
	 * 查询所所有的省份
	 * @return 所有的省份信息
	 */
	public List<Province> getProvince();
	public List<Citem> getCitemTitle();
	public List<City> getCityTitle(Long pid);
	public boolean add(Customer vo);
	public List<Customer> getAllCustomer();
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord,String mid);
	public List<Critem> getAllCritem();
	public boolean addCr(CustomerRecord vo);
	public Integer getCrNum(String cmid);
	public boolean editConnum(Long cuid);
	public List<CustomerRecord> getAllCustomerRecord(Long cmid);
	public String getPhomeByMid(String mid);
	public boolean band(Long cid,String mid);
}
