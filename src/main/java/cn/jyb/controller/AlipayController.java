package cn.jyb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.AlipayService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/alipay")
public class AlipayController extends ExceptionController {

	@Resource
	private AlipayService alipayService;
	/**
	 * 支付宝APP支付信息签名
	 * @param out_trade_no
	 * @param subject
	 * @param body
	 * @param total_amount
	 * @param payer_id
	 * @param receiver_id
	 * @param address
	 * @param orderType
	 * @return
	 */
	@RequestMapping("/sign")
	@ResponseBody
	public JsonResult sign(String out_trade_no,String subject,String body,String total_amount,String payer_id,String receiver_id,String address,String orderType) {
		String result = alipayService.sign(out_trade_no,subject,body,total_amount,payer_id,receiver_id,address,orderType);
		return new JsonResult(result);
	}
	/**
	 * 支付宝WEB支付信息签名
	 * @param out_trade_no
	 * @param subject
	 * @param body
	 * @param total_amount
	 * @param payer_id
	 * @param receiver_id
	 * @param address
	 * @param orderType
	 * @return
	 */
	@RequestMapping("/webSign")
	public void webSign(String out_trade_no,String subject,String body,String total_amount,String payer_id,String receiver_id,String address,String orderType,HttpServletResponse response) throws IOException {
		String form = alipayService.webSign(out_trade_no,subject,body,total_amount,payer_id,receiver_id,address,orderType);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(form);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String result = alipayService.notify(request);
		System.out.println(result);
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
	}
	
}
