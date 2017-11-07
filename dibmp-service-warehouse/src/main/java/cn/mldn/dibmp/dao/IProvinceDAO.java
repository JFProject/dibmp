package cn.mldn.dibmp.dao;


import java.util.List;

import cn.mldn.dibmp.vo.Province;

public interface IProvinceDAO {
	/**
	 * 返回所有省份信息
	 * @return
	 */
	public List<Province> findAll();
}