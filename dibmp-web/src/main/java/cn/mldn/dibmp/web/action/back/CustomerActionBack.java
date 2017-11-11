package cn.mldn.dibmp.web.action.back;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.ICustomerServiceBack;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/customer/*")
public class CustomerActionBack extends AbstractAction {
	private static final String TITLE = "客户" ;
	@Resource
	private ICustomerServiceBack customerService;
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		
		ModelAndView mav = new ModelAndView(super.getPage("customer.add.page"));
		mav.addObject("allProvinces", customerService.getProvince());
		mav.addObject("allCitemTitles", customerService.getCitemTitle());
		mav.addObject("AllCsources", customerService.getCsourceTitle());
		return mav;
	}

	@RequestMapping("add")
	public ModelAndView add(Customer customer) {
		customer.setIndate(new Date());
		customer.setRecorder((String)super.getRequest().getSession().getAttribute("mid"));
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(this.customerService.add(customer)) {
			super.setMsgAndUrl(mav, "customer.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "customer.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre(long wid) {
		ModelAndView mav = new ModelAndView(super.getPage("customer.edit.page"));
		mav.addObject("allCritems", customerService.getAllCritem());
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit(CustomerRecord vo) {
		vo.setCdate(new Date());
		Long cuid = vo.getCmid();
		customerService.editConnum(cuid);
		vo.setRecordername((String)super.getRequest().getSession().getAttribute("mid"));
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "customer.list.action", "vo.edit.success", TITLE);

		if(this.customerService.addCr(vo)) {
			super.setMsgAndUrl(mav, "customer.list.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "customer.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("list") 
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("客户姓名:name|联系电话:phone|客户地址:address", super.getPage("customer.list.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("customer.list.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid");
		mav.addAllObjects(this.customerService.list(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord(),mid)) ;
		return mav;
	}
	@RequestMapping("getCity")
	public Object getAllCity(long pid) {
		System.err.println();
		return customerService.getCityTitle(pid);
		
	}
	@RequestMapping("band")
	public ModelAndView band(long cid) {
		String mid = (String)super.getRequest().getSession().getAttribute("mid");
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "customer.list.action", "vo.edit.success", TITLE);

		if(this.customerService.band(cid, mid)) {
			super.setMsgAndUrl(mav, "customer.list.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "customer.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("customer_record")
	@ResponseBody
	public Object getAllCustomerRecord(long cmid) {
		Map<String,Object> map = new HashMap<>();
		Map<Long,String> phoneMap = new HashMap<>();
		List<CustomerRecord> all = customerService.getAllCustomerRecord(cmid);
		Iterator<CustomerRecord> iter = all.iterator();
		while(iter.hasNext()) {
			CustomerRecord vo =  iter.next();
			String mid = vo.getRecordername();
			phoneMap.put(vo.getCrid(),customerService.getPhomeByMid(mid));
		}
		map.put("allPhone", phoneMap);
		map.put("allCr",all );
		return map;
	}
	
}
