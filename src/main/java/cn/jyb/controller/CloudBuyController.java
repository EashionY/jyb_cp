package cn.jyb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.Goods;
import cn.jyb.service.CloudBuyService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/cloudBuy")
public class CloudBuyController extends ExceptionController {

	@Resource
	private CloudBuyService cloudBuyService;
	
	@RequestMapping(value="/addGoods",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult addGoods(String goodsName, double price, int total_needs, String goods_info, int period,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		cloudBuyService.addGoods(goodsName, price, total_needs, goods_info, period, request);
		return new JsonResult(true);
	}
	
	@RequestMapping("/listAllGoods")
	@ResponseBody
	public JsonResult listAllGoods(String page,String pageSize){
		List<Goods> list = cloudBuyService.listAllGoods(Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/buyGoods")
	@ResponseBody
	public JsonResult buyGoods(int user_id, int goods_id, int period, int buy_amount){
		List<Integer> list = cloudBuyService.buyGoods(user_id, goods_id, period, buy_amount);
		return new JsonResult(list);
	}
	
	@RequestMapping("/lottery")
	@ResponseBody
	public JsonResult lottery(int goods_id,int period){
		Map<String,Object> result = cloudBuyService.lottery(goods_id, period);
		return new JsonResult(result);
	}
	
	@RequestMapping("/modifyGoodsInfo")
	@ResponseBody
	public JsonResult modifyGoodsInfo(int id, String goodsName, double price, int total_needs, String goods_info,
			int period, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		boolean tf = cloudBuyService.modifyGoodsInfo(id, goodsName, price, total_needs, goods_info, period);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/modifyGoodsCover")
	@ResponseBody
	public JsonResult modifyGoodsCover(int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.addHeader("Access-Control-Allow-Origin", "*");
		boolean tf = cloudBuyService.modifyGoodsCover(id, request);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/listGoodsByStatus")
	@ResponseBody
	public JsonResult listGoodsByStatus(String goods_status, int page, int pageSize, HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = cloudBuyService.listGoodsByStatus(goods_status, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/onShelves")
	@ResponseBody
	public JsonResult onShelves(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = cloudBuyService.onShelves(id);
		return new JsonResult(result);
	}
	
	@RequestMapping("/offShelves")  
	@ResponseBody
	public JsonResult offShelves(int id,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = cloudBuyService.offShelves(id);
		return new JsonResult(result);
	} 
	
	@RequestMapping("/listLotteryInfo")
	@ResponseBody
	public JsonResult listLotteryInfo(int page,int pageSize,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String, Object>> result = cloudBuyService.listLotteryInfo(page,pageSize);
		return new JsonResult(result);
	}
	
}
