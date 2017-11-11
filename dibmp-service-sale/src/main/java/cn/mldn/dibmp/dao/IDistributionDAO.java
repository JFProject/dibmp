 package cn.mldn.dibmp.dao;

import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.DistributionDetails;

public interface IDistributionDAO { 
	public boolean doCreate(Distribution vo);
	public boolean doCreateByDistributionDetails(DistributionDetails vo);
	public Long findMaxId();
	public Long findWid(Long gid);
}
