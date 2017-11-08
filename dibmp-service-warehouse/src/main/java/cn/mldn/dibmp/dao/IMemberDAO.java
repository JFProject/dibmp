package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Member;

public interface IMemberDAO {
	
	/**
	 * 根据部门编号，查找所属雇员
	 * @param did
	 * @return
	 */
	public List<Member> findAllByDept(Map<String,Object> params);
	/**
	 * 统计所有雇员数量
	 * @return
	 */
	public long getCount(Long did );
	/**
	 * 查询全部的雇员数据，返回雇员表的两列数据mid 和 name
	 * @return
	 */
	public List<Member> findAllMem();
}