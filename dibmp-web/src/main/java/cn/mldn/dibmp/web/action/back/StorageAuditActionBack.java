package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IStorageAuditServiceBack;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.fastdfs.util.FastDFSUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/storageaudit/*")
public class StorageAuditActionBack extends AbstractAction {
	private static final String TITLE = "入库审核" ;
	@Resource
	private IStorageAuditServiceBack storageAuditService ;
	@RequestMapping("list_prepare") 
	public ModelAndView listDetails() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title", super.getPage("storage.audit.list.prepare.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.prepare.page"));
		mav.addAllObjects(this.storageAuditService.listPrepare(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord())) ;
		return mav;
	}
	@RequestMapping("edit_pre") 
	public ModelAndView editPre(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.edit.page"));
		mav.addAllObjects(this.storageAuditService.editPre(said)) ;
		return mav;
	}
	@RequestMapping("edit") 
	public ModelAndView edit(long said,long flag) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		int status ;
		if(flag == 1) {
			status = 2 ;
		}else {
			status = 3 ;
		}
		if(this.storageAuditService.edit(said, status, mid)) {
			super.setMsgAndUrl(mav, "storage.audit.list.prepare.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "storage.audit.list.prepare.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("list_history") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title", super.getPage("storage.audit.list.history.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.history.page"));
		mav.addAllObjects(this.storageAuditService.listHistory(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord())) ;
		return mav;
	}
	@RequestMapping("warehouse_show")
	@ResponseBody
	public Object warehouseShow(long wid) {
		Map<String,Object> map = this.storageAuditService.warehouseShow(wid) ;
		Warehouse warehouse = (Warehouse)map.get("warehouse") ;
		String authPhoto = FastDFSUtil.getPhotoPath(warehouse.getPhoto()) ;
		map.put("authPhoto", authPhoto) ;
		return map ;
	}
	@RequestMapping("storage_details_show")
	@ResponseBody
	public Object storageDetailsShow(long said) {
		return this.storageAuditService.editPre(said) ;
	}
}
