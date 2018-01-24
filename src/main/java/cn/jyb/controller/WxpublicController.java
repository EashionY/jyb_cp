package cn.jyb.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.WxpublicService;
import cn.jyb.util.JsonResult;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.WxpublicUtil;
import cn.jyb.util.XMLUtil;

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
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
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
	 * 接收微信平台推送的信息，根据内容回复消息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException{
		//读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while((s = in.readLine()) != null){
			sb.append(s);
		}
		in.close();
		inputStream.close();
		//解析xml成为map
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
//		for(Object keyValue : map.keySet()){
//			logger.error(keyValue+"="+map.get(keyValue));
//		}
		/*文本消息范例
		 * <xml>
		 *  <ToUserName><![CDATA[公众号]]></ToUserName>
		 *  <FromUserName><![CDATA[粉丝号]]></FromUserName>
		 *  <CreateTime>1460537339</CreateTime>
		 *  <MsgType><![CDATA[text]]></MsgType>
		 *  <Content><![CDATA[欢迎开启公众号开发者模式]]></Content>
		 *  <MsgId>6272960105994287618</MsgId>
		 * </xml> 
		 */
		String myId = map.get("ToUserName");//公众号
		String fansId = map.get("FromUserName");//粉丝号
		String msgType = map.get("MsgType");//消息类型
		String result = "";//返回结果
		if("text".equals(msgType)){//文本消息
			//未设置关键字，接收到文字消息后返回固定的文本消息
			result = "<xml>"
					+ "<ToUserName><![CDATA["+fansId+"]]></ToUserName>"
				    + "<FromUserName><![CDATA["+myId+"]]></FromUserName>"
				    + "<CreateTime>"+String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)+"</CreateTime>"
				    + "<MsgType><![CDATA[text]]></MsgType>"
		            + "<Content><![CDATA[感谢您关注驾易宝服务号。易宝在手，驾服尽有！]]></Content>"
				    + "</xml>";
		}else if("event".equals(msgType)){//自定义菜单事件推送
			String event = map.get("Event");//事件类型
			String eventKey = map.get("EventKey");//事件KEY值
			if("CLICK".equals(event)){//菜单点击事件
				if("V34_CONTACT_US".equals(eventKey)){//自定义菜单“联系我们”
					result = "<xml>"
							+ "<ToUserName><![CDATA["+fansId+"]]></ToUserName>"
						    + "<FromUserName><![CDATA["+myId+"]]></FromUserName>"
						    + "<CreateTime>"+String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)+"</CreateTime>"
						    + "<MsgType><![CDATA[text]]></MsgType>"
				            + "<Content><![CDATA[联系方式：028-65231120\r\n地址：成都市高新区天府五街菁蓉国际1号楼A座323]]></Content>"
						    + "</xml>";
				}else{
					result = "success";
				}
			}else{
				result = "success";
			}
		}else{//其他推送
			result = "success";
		}
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(result.getBytes());//输出结果
		out.flush();
		out.close();
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
	 * @return 微信服务器返回的信息
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
	/**
	 * 微信js签名验证
	 * @param request
	 * @return
	 */
	@RequestMapping("/jsSign")
	@ResponseBody
	public JsonResult jsSign(String url){
//		logger.error("url="+url);
		return new JsonResult(wxpublicService.jsSign(url));
	}
	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JsonResult getUserInfo(HttpServletRequest request){
		return new JsonResult(request.getSession().getAttribute("userInfo"));
	}
	
}
