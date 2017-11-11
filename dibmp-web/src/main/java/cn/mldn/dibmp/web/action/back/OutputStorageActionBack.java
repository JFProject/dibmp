package cn.mldn.dibmp.web.action.back;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IOutputStorageServiceBack;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/outputstorage/*")
public class OutputStorageActionBack extends AbstractAction {
	private static final String TITLE = "商品出库" ;
	@Resource
	private IOutputStorageServiceBack outputStorageService ;
	@RequestMapping("list_myself") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("出库单编号:did|申请标题:title", super.getPage("outputstorage.list.myself.page")) ;
		ModelAndView mav = new ModelAndView(super.getPage("distribution.list.myself.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		mav.addAllObjects(this.outputStorageService.listMySelf(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord(), mid)) ;
		return mav;
	}
	@RequestMapping("clear") 
	public ModelAndView clear(long dsid) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(this.outputStorageService.clear(dsid)) {
			super.setMsgAndUrl(mav, "outputstorage.list.myself.page", "vo.delete.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "outputstorage.list.myself.page", "vo.delete.failure", TITLE);
		}
		return mav;
	}
}
