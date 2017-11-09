package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.ICityDAO;
import cn.mldn.dibmp.dao.IDeptDAO;
import cn.mldn.dibmp.dao.IMemberDAO;
import cn.mldn.dibmp.dao.IProvinceDAO;
import cn.mldn.dibmp.dao.IWaerhousDAO;
import cn.mldn.dibmp.service.IWarehouseServiceBack;
import cn.mldn.dibmp.vo.Dept;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.member.util.ConvertToMap;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class WaerhouseServiceImpl extends AbstractService implements IWarehouseServiceBack {
	
	
	@Resource
	private IWaerhousDAO waerhousDAO ;
	@Resource
	private ICityDAO city ;
	@Resource
	private IProvinceDAO pro ;
	@Resource
	private IDeptDAO dept ;
	@Resource
	private IMemberDAO mem ;

	@Override
	public Map<String, Object> addPro() {
		Map<String,Object> map = new HashMap<>();
		map.put("allProvince", this.pro.findAll());
		return map;
	}

	@Override
	public boolean add(Warehouse vo) {
		if(this.waerhousDAO.findByName(vo.getName()) != null){
			return false;
		}
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
//		map.put("allWarehouse", this.waerhousDAO.findSplit(super.paramToMap(currentPage, lineSize, column, keyWord)));
//		map.put("allRecorders", this.waerhousDAO.getSplitCount(super.paramToMap(column, keyWord)));
//		map.put("allMember",ConvertToMap.ListToMap(this.mem.findAllMem()));
		map.put("allMember",this.mem.findAllMem());
		return map ;
	}

	@Override
	public List<Dept> getDept(List<Long> ids) {
		return this.dept.findAllDept(ids) ;
	}

	@Override
	public Map<String, Object> getMember(long currentPage, int lineSize,Long did) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,Object> parm = new HashMap<String,Object>() ;
		parm = super.paramToMap(currentPage, lineSize, null,null);
		parm.put("did", did);
		map.put("allMember", this.mem.findAllByDept(parm));
		map.put("allCount", this.mem.getCount(did));
		return map ;
	}

	@Override
	public boolean Edit(Warehouse vo) {
		return this.waerhousDAO.doUpdate(vo); 
	}

	@Override
	public boolean EditByWid(Long wid, String admin) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("wid", wid);
		map.put("admin", admin);
		return this.waerhousDAO.doUpdateByWid(map);
	}
	
}
