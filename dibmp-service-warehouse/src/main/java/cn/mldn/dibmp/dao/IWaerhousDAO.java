package cn.mldn.dibmp.dao;


import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWaerhousDAO {
	public boolean doCreate(Warehouse vo);
	public List<Warehouse> findSplit(Map<String,Object> params);
	public Long getSplitCount(Map<String,Object> params);
}
