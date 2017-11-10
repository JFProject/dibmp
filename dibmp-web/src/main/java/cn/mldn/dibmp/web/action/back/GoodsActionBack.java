package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IGoodsServiceBack;
import cn.mldn.dibmp.vo.Goods;
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
			super.setMsgAndUrl(mav, "goods.list.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "goods.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	} 
	@RequestMapping("show") 
	public ModelAndView show(long gid) { 
		ModelAndView mav = new ModelAndView(super.getPage("goods.show.page"));
		Map<String,Object> map = this.goodsService.show(gid) ;
		Goods goods = (Goods)map.get("goods") ;
		String authPhoto = FastDFSUtil.getPhotoPath(goods.getPhoto()) ;
		mav.addObject("goods", goods) ;
		mav.addObject("authPhoto", authPhoto) ;
		return mav;
	} 
	@RequestMapping("edit_pre")   
	public ModelAndView editPre(Long gid) { 
		ModelAndView mav = new ModelAndView(super.getPage("goods.edit.page"));
		Map<String,Object> map = this.goodsService.editPre(gid) ;
		Goods goods = (Goods)map.get("goods") ;
		String authPhoto = FastDFSUtil.getPhotoPath(goods.getPhoto());
		mav.addObject("goods", goods) ;
		mav.addObject("allWitem", map.get("allWitem")) ;
		mav.addObject("allSubtype", map.get("allSubtype")) ;
		mav.addObject("authPhoto", authPhoto) ;
		return mav;
	} 
	@RequestMapping("edit")
	public ModelAndView edit(Goods goods,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(pic.getSize() != 0) {
			String photo = FastDFSUtil.upload(pic) ;
			goods.setPhoto(photo);
		}
		goods.setRecorder((String)super.getRequest().getSession().getAttribute("mid"));
		goods.setDelflag(1);
		if(this.goodsService.edit(goods)) {
			super.setMsgAndUrl(mav, "goods.list.action", "vo.edit.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "goods.list.action", "vo.edit.failure", TITLE);
		}
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
	@RequestMapping("goodsStorageInfo")
	@ResponseBody
	public Object goodsStorageInfo(long gid) {
		return this.goodsService.goodsStorageInfo(gid) ;
	}
	
}
