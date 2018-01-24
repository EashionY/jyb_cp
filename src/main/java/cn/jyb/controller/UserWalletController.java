package cn.jyb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.UserWalletDetailService;
import cn.jyb.service.UserWalletService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/wallet")
public class UserWalletController extends ExceptionController {

	@Resource
	private UserWalletService userWalletService;
	@Resource
	private UserWalletDetailService userWalletDetailService;
	/**
	 * �鿴���
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkBalance")
	@ResponseBody
	public JsonResult checkBalance(Integer userId){
		return new JsonResult(userWalletService.checkBalance(userId));
	}
	/**
	 * �鿴Ǯ����֧��ϸ
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listDetail")
	@ResponseBody
	public JsonResult listDetail(Integer userId, Integer page, Integer pageSize){
		return new JsonResult(userWalletDetailService.listDetail(userId, page, pageSize));
	}
	
}
