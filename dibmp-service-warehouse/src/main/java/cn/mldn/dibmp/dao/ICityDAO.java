package cn.mldn.dibmp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.dibmp.vo.City;

public interface ICityDAO {
	/**
	 * 根据省份查询出对应的城市信息
	 * @param pid 省份编号
	 * @return 城市信息
	 * @throws SQLException SQL 
	 */
	public List<City> findAllByProvince(Long pid);
}