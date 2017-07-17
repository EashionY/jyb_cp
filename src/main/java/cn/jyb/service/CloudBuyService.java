package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Goods;

/**
 * 云购平台
 * @author Eashion
 *
 */
public interface CloudBuyService {

	/**
	 * 添加商品(后台)
	 * @param goodsName
	 * @param price
	 * @param total_needs
	 * @param goods_info
	 * @param period
	 * @param request
	 */
	public void addGoods(String goodsName,double price,int total_needs,String goods_info,int period,HttpServletRequest request);
	
	/**
	 * 列出所有已上架商品(goods_status=1)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Goods> listAllGoods(int page,int pageSize);
	
	/**
	 * 用户云购商品
	 * @param user_id
	 * @param goods_id
	 * @param period
	 * @param buy_amount
	 * @return 该用户购买商品之后的云购码
	 */
	public List<Integer> buyGoods(int user_id,int goods_id,int period,int buy_amount);
	
	/**
	 * 揭晓云购结果
	 * @param goods_id
	 * @param period
	 * @return 中奖详细信息
	 */
	public Map<String,Object> lottery(int goods_id,int period);
	
	/**
	 * 修改商品信息(后台管理系统)
	 * @param goodsName
	 * @param price
	 * @param total_needs
	 * @param goods_info
	 * @param period
	 * @return
	 */
	public boolean modifyGoodsInfo(int id,String goodsName,double price,int total_needs,String goods_info,int period);
	
	/**
	 * 修改商品封面图片(后台管理系统)
	 * @param id 商品id
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyGoodsCover(int id,HttpServletRequest request) throws IOException;
	
	/**
	 * 列出所有商品(后台)
	 * @param goods_status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listGoodsByStatus(String goods_status,int page,int pageSize);
	
	/**
	 * 上架商品(后台)
	 * @param id 商品id
	 * @return 上架商品之后的状态码和上架时间
	 */
	public Map<String, Object> onShelves(int id);
	
	/**
	 * 下架商品(后台)
	 * @param id 商品id
	 * @return 下架商品之后的状态码和下架时间
	 */
	public Map<String, Object> offShelves(int id);
	
	/**
	 * 列出所有中奖信息(后台)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listLotteryInfo(int page,int pageSize);
	
	
}
