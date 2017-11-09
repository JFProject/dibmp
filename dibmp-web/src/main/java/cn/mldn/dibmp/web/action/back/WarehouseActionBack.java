package cn.mldn.dibmp.web.action.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IGoodsServiceBack;
import cn.mldn.dibmp.service.IWarehouseServiceBack;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.fastdfs.util.FastDFSUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;

@Controller
@RequestMapping("/pages/back/admin/warehouse/*")
public class WarehouseActionBack extends AbstractAction {
	private static final String TITLE = "仓库" ;
	
	@Resource
	private IWarehouseServiceBack iw ;
	@Resource
	private IGoodsServiceBack goodsService ;
	
	
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.add.page"));
		mav.addAllObjects(this.iw.addPro());
		mav.addObject("allWitem", this.goodsService.addPre()) ;
		return mav; 
	}
	@RequestMapping("add")
	public ModelAndView add(Warehouse warehouse,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.success", TITLE);
		String photo = FastDFSUtil.upload(pic) ;
		warehouse.setPhoto(photo);
		warehouse.setCurrnum(0);
		warehouse.setRecorder((String)super.getRequest().getSession().getAttribute("mid"));
		if(this.iw.add(warehouse)) {
			super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "warehouse.add.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre(long wid) {
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.edit.page"));
		Map<String,Object> map = this.iw.editPro(wid) ;
		Warehouse vo = (Warehouse)map.get("Warehouse");
		mav.addAllObjects(map);
		mav.addObject("allWitem", this.goodsService.addPre()) ;
		String authPhoto = FastDFSUtil.getPhotoPath(vo.getPhoto());
		mav.addObject("authPhoto", authPhoto) ;
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit(Warehouse vo,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getPage("forward.page"));
		if(pic.getSize() != 0) {
			String photo = FastDFSUtil.upload(pic) ;
			vo.setPhoto(photo);
		}
		vo.setRecorder((String)super.getRequest().getSession().getAttribute("mid"));
		if(this.iw.Edit(vo)) {
			super.setMsgAndUrl(mav, "warehouse.list.action", "vo.add.success", TITLE);
		}else {
			super.setMsgAndUrl(mav, "warehouse.list.action", "vo.add.failure", TITLE);
		}
		return mav;
	}
	@RequestMapping("list")
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("仓库名称:name|仓库地址:address", super.getPage("warehouse.list.action")) ;
		ModelAndView mav = new ModelAndView(super.getPage("warehouse.list.page"));
		mav.addAllObjects(this.iw.List(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyWord()));
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(7L);
		ids.add(8L);
		mav.addObject("allDept", this.iw.getDept(ids));
		return mav;
	}
	@RequestMapping("getCity")
	@ResponseBody
	public Object getSubtypeByWiid(long pid) {
		return this.iw.getCity(pid) ;
	}
	@RequestMapping("editAdmin")
	@ResponseBody
	public boolean editAdmin(long wid,String admin) {
		System.out.println(wid);
		System.out.println(admin);
		return this.iw.EditByWid(wid, admin);
	}
	@RequestMapping("getDept")
	public Object getDept(){
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(7L);
		ids.add(8L);
		return this.iw.getDept(ids);
	}
	@RequestMapping("getMember")
	@ResponseBody
	public Object getMember(Long did){
		SplitPageUtil spu = new SplitPageUtil(null, super.getPage("warehouse.list.action")) ;
		return this.iw.getMember(spu.getCurrentPage(), spu.getLineSize(),did);
	}
}
