package cn.mldn.dibmp.web.action.back;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IManageServiceBack;
import cn.mldn.util.action.abs.AbstractAction;

@Controller
@RequestMapping("/pages/back/admin/manage/*")
public class ManageActionBack extends AbstractAction {
	@Resource
	private IManageServiceBack manageService ;
	private static final String TITLE = "仓库管理" ;
	@RequestMapping("storage_input_pre")
	public ModelAndView storageInputPre() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.storage.input.page"));
		return mav;
	}
	@RequestMapping("storage_input")
	public ModelAndView storageInput(long said) {
		ModelAndView mav = new ModelAndView(super.getPage("manage.storage.page"));
		mav.addAllObjects(manageService.storageInput(said)) ;
		return mav;
	}
	@RequestMapping("storage_audit")
	public ModelAndView storageAudit(int flag ,long said) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String mid = (String)super.getRequest().getSession().getAttribute("mid") ;
		if(flag == 1) { //库管审核通过
			if(this.manageService.storageAuditSuccess(said, mid)) {
				super.setMsgAndUrl(mav, "manage.storage.action", "vo.add.success", TITLE);
			}else {
				super.setMsgAndUrl(mav, "manage.storage.action", "vo.add.failure", TITLE);
			}
		}else {
			if(this.manageService.storageAuditFailure(said, 3)) {
				super.setMsgAndUrl(mav, "manage.storage.action", "vo.add.success", TITLE);
			} else {
				super.setMsgAndUrl(mav, "manage.storage.action", "vo.add.failure", TITLE);
			}
		}
		return mav;
	}
	@RequestMapping("distribution_input_pre")
	public ModelAndView distributionInputPre() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.distribution.input.page"));
		return mav;
	}
	@RequestMapping("distribution_input")
	public ModelAndView distributionInput() {
		ModelAndView mav = new ModelAndView(super.getPage("manage.distribution.page"));
		return mav;
	}
}
