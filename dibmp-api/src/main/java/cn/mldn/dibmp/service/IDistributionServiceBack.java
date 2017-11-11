package cn.mldn.dibmp.service;

import java.util.Map;

import cn.mldn.dibmp.vo.Distribution;

public interface IDistributionServiceBack {
	public Map<String,Object> getCustomers(String mid);
	public boolean adds(String mid,Long gid);
	public boolean indentAdd(String mid,Distribution vo);
	/**
	 * 批量修改redis中的数据 
	 * @param data 要修改的数据 格式：1：2|2：1|3：1|4：1|
	 * @param delGid 要删除的数据 格式：2，3，
	 * @param mid 当前登录人mid
	 * @return 修改成功返回true
	 */
	public boolean edit(String data,String delGid,String mid)  ;
	/**
	 * 批量删除redis中的数据
	 * @param data 要删除的数据格式 ： 2，3
	 * @param mid 当前登录人mid
	 * @return 修改成功返回true
	 */
	public boolean remove(String data,String mid) ;
}
