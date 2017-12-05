package cn.jyb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.WxpublicService;
import cn.jyb.util.JsonResult;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.WxpublicUtil;

/**
 * 微信公众号Controller
 * @author Eashion
 *
 */
@Controller
@RequestMapping("/wxpublic")
public class WxpublicController extends ExceptionController {

	@Resource
	private WxpublicService wxpublicService;
	
	/**
	 * 验证服务器地址的有效性
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/verify")
	public void verify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//排序
		String sortString = WxpublicUtil.sort(WxpublicConfig.TOKEN,timestamp,nonce);
		//加密
		String mytoken = WxpublicUtil.SHA1(sortString);
		//校验签名
		if(mytoken != null && mytoken != "" && mytoken.equals(signature)){
			//校验通过，将echostr返回微信服务器
			response.getWriter().println(echostr);
			System.out.println("签名校验通过");
		}else{
			System.out.println("签名校验失败");
		}
	}
	/**
	 * 微信公众号，菜单跳转之前静默授权，获得openid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/check")
	public void check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		wxpublicService.check(request, response);
	}
	/**
	 * 创建微信公众号的菜单
	 * @return 微信公众号服务器返回的信息
	 */
	@RequestMapping("/createMenu")
	@ResponseBody 
	public String createMenu(){
		return wxpublicService.createMenu(WxpublicUtil.getMenu());
	}
	/**
	 * 从session中获取openid
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOpenid")
	@ResponseBody
	public JsonResult getOpenid(HttpServletRequest request){
		return new JsonResult(wxpublicService.getOpenid(request));
	}
	
}
