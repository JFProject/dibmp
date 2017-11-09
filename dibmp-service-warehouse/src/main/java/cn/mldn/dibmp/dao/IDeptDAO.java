package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Dept;

public interface IDeptDAO {
	/**
	 * 查找指定的部门信息
	 * @param ids 指定的部门编号
	 * @return
	 */
	public List<Dept> findAllDept(List<Long> ids);
}