package cn.mldn.dibmp.service;

import java.util.Map;

public interface IDistributionServiceBack {
	public Map<String,Object> getCustomer(String mid);
	public boolean add(String mid,Long gid);
}
