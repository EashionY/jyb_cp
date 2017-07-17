package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.CloudBuyDao;
import cn.jyb.dao.GoodsDao;
import cn.jyb.entity.CloudBuy;
import cn.jyb.entity.Goods;

public class TestGoodsDao extends TestBase {

	private GoodsDao goodsDao;
	private CloudBuyDao cloudBuyDao;
	
	@Before
	public void init(){
		goodsDao = super.getContext().getBean("goodsDao", GoodsDao.class);
		cloudBuyDao = super.getContext().getBean("cloudBuyDao", CloudBuyDao.class);
	}
	
	@Test
	public void test1(){
		Goods goods = new Goods();
		goods.setGoods_info("测试商品的详细信息");
		goods.setName("测试商品");
		goods.setPeriod(1);
		goods.setTotal_needs(80);
		goods.setCover("测试商品的封面图片路径");
		goods.setPrice(80);
		goodsDao.save(goods);
	}
	
	@Test
	public void test2(){
		int i = goodsDao.delete(1);
		System.out.println(i);
	}
	
	@Test
	public void test3(){
		CloudBuy cloudBuy = new CloudBuy();
		cloudBuy.setBuy_amount(1);
		cloudBuy.setBuyer_code(10001624);
		cloudBuy.setGoods_id(2);
		cloudBuy.setPeriod(1);
		cloudBuy.setUser_id(9);
		cloudBuyDao.save(cloudBuy);
	}
	
	@Test
	public void test4(){
		List<Integer> list = cloudBuyDao.listCodes(2, 1);
		System.out.println(list);
	}
	
	@Test
	public void test5(){
		List<Date> list = cloudBuyDao.listTime(7, 1);
		DateFormat df = new SimpleDateFormat("HHmmss");
		System.out.println(df.format(list.get(0)));
	}
}
