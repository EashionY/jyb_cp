package cn.jyb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
	
	@RequestMapping("/sign")
	@ResponseBody
	public JsonResult sign(String subject,String body,String total_amount,String payer_id,String receiver_id) throws UnsupportedEncodingException{
		String result = alipayService.sign(subject,body,total_amount,payer_id,receiver_id);
		return new JsonResult(result);
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
