package cn.mldn.dibmp.dao;

import cn.mldn.dibmp.vo.Member;

public interface IMemberDAO {
	public Member findById(String id);
	public String findTitleByLid(Long lid) ;
	public String findDnameByDid(Long did) ;
}
