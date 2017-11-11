package cn.mldn.dibmp.web.action.back;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IDistributionServiceBack;
import cn.mldn.dibmp.service.IWarehouseServiceBack;
import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.fastdfs.util.FastDFSUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/distribution/*")
public class DistributionActionBack extends AbstractAction {
	private static final String TITLE = "客户订单" ;
	
	@Resource
	private IDistributionServiceBack distributionService ;
	@Resource
	private IWarehouseServiceBack iw ;
	
	
	@RequestMapping("goods_list") 
	public ModelAndView listGoods() {
		ModelAndView mav = new ModelAndView(super.getPage("distribution.goods.list.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid");
		Map<Long,String> authPhoto = new HashMap<Long,String>() ;
		List<Goods> list = (List<Goods>)this.distributionService.getCustomers(mid).get("allGoods") ;
		if(list != null) {
			Iterator<Goods> iter = list.iterator() ;
			while(iter.hasNext()) {
				Goods goods = iter.next() ;
				authPhoto.put(goods.getGid(), FastDFSUtil.getPhotoPath(goods.getPhoto())) ;
			}
			mav.addObject("authPhoto", authPhoto) ;
		}
		mav.addAllObjects(this.distributionService.getCustomers(mid));
		return mav;
	}
	@RequestMapping("add_pre") 
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("distribution.add.page"));
		mav.addAllObjects(this.iw.addPro());
		return mav;
	}
	@RequestMapping("add") 
	public ModelAndView add(Distribution vo) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		mav.addAllObjects(this.iw.addPro());
		super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.success", TITLE);
		String mid = (String)super.getRequest().getSession().getAttribute("mid");
		if(this.distributionService.indentAdd(mid,vo)) {
			super.setMsgAndUrl(mav, "distribution.list.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "distribution.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("list_myself") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("出库单编号:did|申请标题:title", "customer.list.myself.action") ;
		ModelAndView mav = new ModelAndView(super.getPage("distribution.list.myself.page"));
		return mav;
	}
	@RequestMapping("list_details") 
	public ModelAndView listDetails() {
		SplitPageUtil spu = new SplitPageUtil("出库单编号:did|申请标题:title", "customer.list.myself.action") ;
		ModelAndView mav = new ModelAndView(super.getPage("distribution.list.details.page"));
		return mav;
	}
	@RequestMapping("edit")
	@ResponseBody
	public Object edit(String data,String delGid) {
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		return this.distributionService.edit(data, delGid, mid) ;
	}
	@RequestMapping("remove")
	@ResponseBody
	public Object remove(String data) {
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		return this.distributionService.remove(data, mid) ;
	}
}
