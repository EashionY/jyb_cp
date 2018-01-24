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
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
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
	 * ����΢��ƽ̨���͵���Ϣ���������ݻظ���Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException{
		//��ȡ����
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
		//����xml��Ϊmap
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
//		for(Object keyValue : map.keySet()){
//			logger.error(keyValue+"="+map.get(keyValue));
//		}
		/*�ı���Ϣ����
		 * <xml>
		 *  <ToUserName><![CDATA[���ں�]]></ToUserName>
		 *  <FromUserName><![CDATA[��˿��]]></FromUserName>
		 *  <CreateTime>1460537339</CreateTime>
		 *  <MsgType><![CDATA[text]]></MsgType>
		 *  <Content><![CDATA[��ӭ�������ںſ�����ģʽ]]></Content>
		 *  <MsgId>6272960105994287618</MsgId>
		 * </xml> 
		 */
		String myId = map.get("ToUserName");//���ں�
		String fansId = map.get("FromUserName");//��˿��
		String msgType = map.get("MsgType");//��Ϣ����
		String result = "";//���ؽ��
		if("text".equals(msgType)){//�ı���Ϣ
			//δ���ùؼ��֣����յ�������Ϣ�󷵻ع̶����ı���Ϣ
			result = "<xml>"
					+ "<ToUserName><![CDATA["+fansId+"]]></ToUserName>"
				    + "<FromUserName><![CDATA["+myId+"]]></FromUserName>"
				    + "<CreateTime>"+String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)+"</CreateTime>"
				    + "<MsgType><![CDATA[text]]></MsgType>"
		            + "<Content><![CDATA[��л����ע���ױ�����š��ױ����֣��ݷ����У�]]></Content>"
				    + "</xml>";
		}else if("event".equals(msgType)){//�Զ���˵��¼�����
			String event = map.get("Event");//�¼�����
			String eventKey = map.get("EventKey");//�¼�KEYֵ
			if("CLICK".equals(event)){//�˵�����¼�
				if("V34_CONTACT_US".equals(eventKey)){//�Զ���˵�����ϵ���ǡ�
					result = "<xml>"
							+ "<ToUserName><![CDATA["+fansId+"]]></ToUserName>"
						    + "<FromUserName><![CDATA["+myId+"]]></FromUserName>"
						    + "<CreateTime>"+String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)+"</CreateTime>"
						    + "<MsgType><![CDATA[text]]></MsgType>"
				            + "<Content><![CDATA[��ϵ��ʽ��028-65231120\r\n��ַ���ɶ��и������츮���ݼ�ع���1��¥A��323]]></Content>"
						    + "</xml>";
				}else{
					result = "success";
				}
			}else{
				result = "success";
			}
		}else{//��������
			result = "success";
		}
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(result.getBytes());//������
		out.flush();
		out.close();
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
	 * @return ΢�ŷ��������ص���Ϣ
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
	/**
	 * ΢��jsǩ����֤
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
	 * ��ȡ�û���Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JsonResult getUserInfo(HttpServletRequest request){
		return new JsonResult(request.getSession().getAttribute("userInfo"));
	}
	
}
