package cn.mldn.dibmp.service ;

import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.Member;


public interface IMemberService {
	/**
	 * 根据用户ID获得一个用户   的完整对象信息，包含有密码和锁定状态
	 * @param mid 要查询的用户ID
	 * @return 用户对象，如果用户不存在返回null  
	 */
	public Member get(String mid) ;
	/**
	 * 根据指定的用户编号获得其对应的所有授权信息（角色、权限）
	 * @param mid 用户ID
	 * @return 返回的信息里面包含有两类数据：
	 * 1、key = allRoles、value = 该用户具备的所有角色；
	 * 2、key = allActions、value = 该用户具备的所有权限
	 */
	public Map<String,Set<String>> getRoleAndActionByMember(String mid) ;
	/**
	 * 根据mid取得member信息，同时取得对应的职位以及部门名称
	 * @param mid 雇员ID
	 * @return 以map的形式返回
	 * 1、key = member，value = 雇员信息
	 * 2、key = title，value = 雇员等级名称
	 * 3、key = title，value = 雇员部门名称
	 */
	public Map<String,Object> getMemberAndTitleAndDname(String mid) ;
}
