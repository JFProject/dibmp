package cn.mldn.dibmp.service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.service.IGoodsServiceBack;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGoodsService extends TestCase {
	@Resource
	private IGoodsServiceBack goodsService ;
	@Test
	public void testGoodsList() {
		Map<String,Object> map = this.goodsService.list(2, 3, "", "") ;
		System.err.println(map);
	}
}
