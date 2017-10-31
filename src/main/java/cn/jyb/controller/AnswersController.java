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
	
	/**
	 * 保存错题
	 * @param userId
	 * @param questionId
	 * @param subject
	 * @return
	 */
	@RequestMapping("/saveWrong")
	@ResponseBody
	public JsonResult saveAnswer(Integer userId,Integer questionId,Integer subject){
		answersService.saveAnswer(userId, questionId, subject);
		return new JsonResult("");
	}
	
	/**
	 * 查看错题
	 * @param userId
	 * @param subject
	 * @return
	 */
	@RequestMapping("/viewWrong")
	@ResponseBody
	public JsonResult viewWrong(Integer userId,Integer subject,String chapter){
		return new JsonResult(answersService.viewWrong(userId, subject, chapter));
	}
	
	/**
	 * 获得用户的错题数
	 * @param userId
	 * @param subject
	 * @return
	 */
	@RequestMapping("/getWrongNum")
	@ResponseBody
	public JsonResult getWrongNum(Integer userId,Integer subject){
		return new JsonResult(answersService.getWrongNum(userId, subject));
	}
	
}
