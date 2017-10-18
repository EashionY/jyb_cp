package cn.jyb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.AnswersService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/answer")
public class AnswersController extends ExceptionController {

	@Resource
	private AnswersService answersService;
	
	@RequestMapping("/saveWrong")
	@ResponseBody
	public JsonResult saveAnswer(Integer userId,Integer questionId,Integer subject){
		answersService.saveAnswer(userId, questionId, subject);
		return new JsonResult("");
	}
	
	@RequestMapping("/viewWrong")
	@ResponseBody
	public JsonResult viewWrong(Integer userId,Integer subject){
		return new JsonResult(answersService.viewWrong(userId, subject));
	}
	
	
	
}
