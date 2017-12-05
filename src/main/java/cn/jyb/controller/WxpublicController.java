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
 * ΢�Ź��ں�Controller
 * @author Eashion
 *
 */
@Controller
@RequestMapping("/wxpublic")
public class WxpublicController extends ExceptionController {

	@Resource
	private WxpublicService wxpublicService;
	
	/**
	 * ��֤��������ַ����Ч��
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
		//����
		String sortString = WxpublicUtil.sort(WxpublicConfig.TOKEN,timestamp,nonce);
		//����
		String mytoken = WxpublicUtil.SHA1(sortString);
		//У��ǩ��
		if(mytoken != null && mytoken != "" && mytoken.equals(signature)){
			//У��ͨ������echostr����΢�ŷ�����
			response.getWriter().println(echostr);
			System.out.println("ǩ��У��ͨ��");
		}else{
			System.out.println("ǩ��У��ʧ��");
		}
	}
	/**
	 * ΢�Ź��ںţ��˵���ת֮ǰ��Ĭ��Ȩ�����openid
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
	 * ����΢�Ź��ںŵĲ˵�
	 * @return ΢�Ź��ںŷ��������ص���Ϣ
	 */
	@RequestMapping("/createMenu")
	@ResponseBody 
	public String createMenu(){
		return wxpublicService.createMenu(WxpublicUtil.getMenu());
	}
	/**
	 * ��session�л�ȡopenid
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOpenid")
	@ResponseBody
	public JsonResult getOpenid(HttpServletRequest request){
		return new JsonResult(wxpublicService.getOpenid(request));
	}
	
}
