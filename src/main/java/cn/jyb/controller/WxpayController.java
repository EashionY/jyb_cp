package cn.jyb.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.SortedMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.WxpayService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/wxpay")
public class WxpayController extends ExceptionController {

	@Resource
	private WxpayService wxpayService;
	/**
	 * ΢��APP֧��ͳһ�µ��ӿ�
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/prepay")
	@ResponseBody
	public JsonResult wxPrepay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		SortedMap<Object, Object> result = wxpayService.wxPrePay(request, response);
		return new JsonResult(result);
	}
	/**
	 * ΢����ҳ֧��ͳһ�µ��ӿ�
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/webPrepay")
	@ResponseBody
	public JsonResult webPrepay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		SortedMap<Object, Object> result = wxpayService.wxWebPrePay(request, response);
		return new JsonResult(result);
	}
	/**
	 * ΢�Ź��ں�ͳһ�µ��ӿ�
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/jsPrepay")
	@ResponseBody
	public JsonResult jsPrepay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		SortedMap<Object, Object> result = wxpayService.wxJSPrePay(request, response);
		return new JsonResult(result);
	}
	/**
	 * ΢��֧���첽֪ͨ
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JDOMException
	 */
	@RequestMapping("/notify")
	public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException{
		String resXml = wxpayService.wxNotify(request, response);
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();
        out.close();
	}
	/**
	 * ΢�Ź��ں�֧���첽֪ͨ
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JDOMException
	 */
	@RequestMapping("/jsNotify")
	public void wxJsNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException{
		String resXml = wxpayService.wxJsNotify(request, response);
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();
        out.close();
	}
}
