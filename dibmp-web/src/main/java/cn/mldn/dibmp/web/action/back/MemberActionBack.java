package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mldn.dibmp.service.IMemberService;

@Controller
@RequestMapping("/pages/back/admin/member/*")
public class MemberActionBack {
	@Resource
	private IMemberService memberService ;
	@RequestMapping("memberModel")
	@ResponseBody
	public Object memberModel(String mid) {
		Map<String,Object> map = this.memberService.getMemberAndTitleAndDname(mid) ;
		return map ;
	}
}
