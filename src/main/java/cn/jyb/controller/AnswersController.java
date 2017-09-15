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
	
	@RequestMapping("/saveAnswer")
	@ResponseBody
	public JsonResult saveAnswer(Integer userId,Integer questionId){
		answersService.saveAnswer(userId, questionId);
		return new JsonResult("");
	}
	
	
	
}
