package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.ICityDAO;
import cn.mldn.dibmp.dao.IProvinceDAO;
import cn.mldn.dibmp.dao.IWaerhousDAO;
import cn.mldn.dibmp.service.IWarehouseServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class WaerhouseServiceImpl extends AbstractService implements IWarehouseServiceBack {
	
	
	@Resource
	private IWaerhousDAO waerhousDAO ;
	@Resource
	private ICityDAO city ;
	@Resource
	private IProvinceDAO pro ;

	@Override
	public Map<String, Object> addPro() {
		Map<String,Object> map = new HashMap<>();
		map.put("allProvince", this.pro.findAll());
		return map;
	}

	@Override
	public boolean add(Warehouse vo) {
		return this.waerhousDAO.doCreate(vo);
	}

	@Override
	public Map<String, Object> getCity(Long pid) {
		Map<String,Object> map = new HashMap<>();
		map.put("allCity", this.city.findAllByProvince(pid));
		return map;
	}

	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allWarehouse", this.waerhousDAO.findSplit(super.paramToMap(currentPage, lineSize, column, keyWord)));
		map.put("allRecorders", this.waerhousDAO.getSplitCount(super.paramToMap(column, keyWord)));
		return map ;
	}
	
}
