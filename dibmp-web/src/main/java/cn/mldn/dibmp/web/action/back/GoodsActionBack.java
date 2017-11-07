package cn.mldn.dibmp.web.action.back;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IGoodsServiceBack;
import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.fastdfs.util.FastDFSUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/goods/*")
public class GoodsActionBack extends AbstractAction {
	@Resource
	private IGoodsServiceBack goodsService ;
	private static final String TITLE = "商品" ;
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("goods.add.page"));
		mav.addObject("allWitem", this.goodsService.addPre()) ;
		return mav;
	}
	@RequestMapping("add")
	public ModelAndView add(Goods goods,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		String photo = FastDFSUtil.upload(pic) ;
		goods.setPhoto(photo);
		goods.setRecorder((String)super.getRequest().getSession().getAttribute("mid"));
		goods.setDelflag(1);
		if(this.goodsService.add(goods)) {
			super.setMsgAndUrl(mav, "goods.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "goods.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	} 
	@RequestMapping("show")
	public ModelAndView show() { 
		ModelAndView mav = new ModelAndView(super.getPage("goods.show.page"));
		return mav;
	} 
	@RequestMapping("edit_pre")
	public ModelAndView editPre(Long gid) { 
		ModelAndView mav = new ModelAndView(super.getPage("goods.edit.page"));
		Goods goods = (Goods)(this.goodsService.editPre(gid).get("goods")) ;
		List<Witem> allWitem = (List<Witem>)(this.goodsService.editPre(gid).get("allWitem")) ;
		String title = (String)(this.goodsService.editPre(gid).get("title")) ;
		goods.setPhoto(FastDFSUtil.getPhotoPath(goods.getPhoto()));
		mav.addObject("goods", goods) ;
		mav.addObject("allWitem", allWitem) ;
		mav.addObject("title", title) ;
		return mav;
	} 
	@RequestMapping("edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "goods.list.action", "vo.edit.success", TITLE);
		return mav;
	}
	@RequestMapping("list") 
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("商品编号:gid|商品名称:name", super.getPage("goods.list.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("goods.list.page"));
		mav.addAllObjects(this.goodsService.list(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord())) ;
		return mav;
	}
	@RequestMapping("getSubtypeByWiid")
	@ResponseBody
	public Object getSubtypeByWiid(long wiid) {
		return this.goodsService.getSubtypeByWiid(wiid) ;
	}
}
