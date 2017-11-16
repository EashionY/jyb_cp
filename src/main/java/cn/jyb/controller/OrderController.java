package cn.jyb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.OrderService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/order")
public class OrderController extends ExceptionController{

	@Resource
	private OrderService orderService;
	
	@RequestMapping("/listOrders")
	@ResponseBody
	public JsonResult listOrders(HttpServletResponse resp,String tradeStatus,String orderType,Integer page,Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(orderService.listOrders(tradeStatus, orderType, page, pageSize));
	}
	
}
