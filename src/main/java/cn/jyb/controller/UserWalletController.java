package cn.jyb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.UserWalletService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/wallet")
public class UserWalletController extends ExceptionController {

	@Resource
	private UserWalletService userWalletService;
	
	@RequestMapping("/checkBalance")
	@ResponseBody
	public JsonResult checkBalance(Integer userId){
		return new JsonResult(userWalletService.checkBalance(userId));
	}
	
	
}
