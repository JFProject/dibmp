package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IActionDAO;
import cn.mldn.dibmp.dao.IMemberDAO;
import cn.mldn.dibmp.dao.IRoleDAO;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Member;
@Service
public class MemberServiceImpl implements IMemberService {
	@Resource
	private IMemberDAO memberDAO ;
	@Resource
	private IRoleDAO roleDAO ;
	@Resource
	private IActionDAO actionDAO ;
	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Set<String>> getRoleAndActionByMember(String mid) {
		Map<String, Set<String>> map = new HashMap<>() ;
		map.put("allRoles", this.roleDAO.findAllByMember(mid)) ;
		map.put("allActions", this.actionDAO.findAllByMember(mid)) ;
		return map;
	}

	@Override
	public Map<String, Object> getMemberAndTitleAndDname(String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Member member = this.memberDAO.findById(mid) ;
		String title = this.memberDAO.findTitleByLid(member.getLid()) ;
		String dname = this.memberDAO.findDnameByDid(member.getDid()) ;
		map.put("member", member) ;
		map.put("title", title) ;
		map.put("dname", dname) ;
		return map;
	}

}
