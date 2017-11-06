package cn.jyb.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.exception.PhoneException;
import cn.jyb.exception.VerCodeException;
import cn.jyb.service.MsgService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/message")
public class MsgController extends ExceptionController {

	@Resource
	private MsgService msgService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/sendRegCode")
	@ResponseBody
	public JsonResult sendRegCode(String phone){
		String code = msgService.sendRegCode(phone);
		return new JsonResult(code);
	}
	
	@RequestMapping("/sendPwdCode")
	@ResponseBody
	public JsonResult sendPwdCode(String phone){
		String code = msgService.sendPwdCode(phone);
		return new JsonResult(code);
	}
	
	@RequestMapping("/sendPayCode")
	@ResponseBody
	public JsonResult sendPayCode(String phone){
		String code = msgService.sendPayCode(phone);
		return new JsonResult(code);
	}
	/**
	 * ���ͱ���ֻ���֤��
	 * @param phone
	 * @return
	 */
	@RequestMapping("/sendPhoneCode")
	@ResponseBody
	public JsonResult sendPhoneCode(String phone){
		return new JsonResult(msgService.sendPhoneCode(phone));
	}
	/**
	 * ����һ��Ų������
	 * @param userId Ҫ��Ų���û�id
	 * @param moveUserId Ų���û�id
	 * @return
	 */
	@RequestMapping("/sendMoveCarMsg")
	@ResponseBody
	public JsonResult sendMoveCarMsg(Integer userId,Integer moveUserId,String vehicleNo){
		return new JsonResult(msgService.sendMoveCarMsg(userId, moveUserId, vehicleNo));
	}
	
	@ExceptionHandler(PhoneException.class)
	@ResponseBody
	public JsonResult phoneexp(PhoneException e){
		logger.error(e.getMessage(), e);
		return new JsonResult(0,e);
	}
	
	@ExceptionHandler(VerCodeException.class)
	@ResponseBody
	public JsonResult codeexp(VerCodeException e){
		logger.error(e.getMessage(), e);
		return new JsonResult(0,e);
	}
}
