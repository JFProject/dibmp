package cn.mldn.dibmp.web.action.back;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IStorageApplyServiceBack;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/storage/*")
public class StorageActionBack extends AbstractAction {
	@Resource
	private IStorageApplyServiceBack storageApplyService ;
	private static final String TITLE = "商品入库" ;
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("storage.add.page"));
		mav.addAllObjects(this.storageApplyService.addPre()) ;
		return mav;
	}
	@RequestMapping("getCity")
	@ResponseBody
	public Object getCity(long pid) {
		return this.storageApplyService.getCity(pid) ;
	}
	@RequestMapping("getWarehouse")
	@ResponseBody
	public Object getWarehouse(long wiid) {
		return this.storageApplyService.getWarehouse(wiid) ;
	}
	@RequestMapping("add")
	public ModelAndView add(StorageApply storageApply) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		storageApply.setStatus(0);
		storageApply.setAppmid((String)super.getRequest().getSession().getAttribute("mid"));
		if(this.storageApplyService.add(storageApply)) {
			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "storage.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("storage.edit.page"));
		mav.addAllObjects(this.storageApplyService.editPre(said)) ;
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit(StorageApply storageApply) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		storageApply.setStatus(0);
		storageApply.setAppmid((String)super.getRequest().getSession().getAttribute("mid"));
		if(this.storageApplyService.edit(storageApply)) {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "vo.edit.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "vo.edit.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("list_details") 
	public ModelAndView listDetails(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("storage.list.details.page"));
		mav.addAllObjects(this.storageApplyService.listDetails(said)) ;
 		return mav;
	}
	@RequestMapping("list_myself") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title", super.getPage("storage.list.myself.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.list.myself.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		mav.addAllObjects(this.storageApplyService.getByMyself(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord(), mid)) ;
		return mav;
	}
	@RequestMapping("submit") 
	public ModelAndView submit(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		int status = 1 ;
		if(this.storageApplyService.editStatus(status, said)) {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "submit.success");
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "submit.failure");
		}
		return mav;
	}
	@RequestMapping("reset")
	public ModelAndView reset(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		int status = 0 ;
		if(this.storageApplyService.editStatus(status, said)) {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "reset.success");
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "reset.failure");
		}
		return mav;
	}
	@RequestMapping("remove") 
	public ModelAndView remove(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(this.storageApplyService.remove(said)) {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "delete.success");
		}else {
			super.setMsgAndUrl(mav, "storage.list.myself.action", "delete.failure");
		}
		return mav;
	}
	@RequestMapping("getGoodsByGid")
	@ResponseBody
	public Object getGoodsByGid(long gid) {
		return this.storageApplyService.getGoodsByGid(gid) ;
	}
	@RequestMapping("addGoodsTemp")
	@ResponseBody
	public Object addGoodsTemp(long said,StorageApplyDetails storageApplyDetails) {
		return this.storageApplyService.addGoodsTemp(said, storageApplyDetails);
	}
	@RequestMapping("deleteGoodsTemp")
	@ResponseBody
	public Object deleteGoodsTemp(long said,long gid) {
		return this.storageApplyService.deleteGoodsTemp(said, gid);
	}
}
