package cn.mldn.dibmp.service;

import java.util.Map;

import cn.mldn.dibmp.vo.Distribution;

public interface IDistributionServiceBack {
	public Map<String,Object> getCustomers(String mid);
	public boolean adds(String mid,Long gid);
	public boolean indentAdd(String mid,Distribution vo);
}
