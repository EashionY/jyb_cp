package cn.jyb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.Questions;
import cn.jyb.service.QuestionsService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/question")
public class QuestionsController extends ExceptionController {

	@Resource
	private QuestionsService questionsService;
	
	@RequestMapping("/getQuestions")
	@ResponseBody
	public JsonResult getQuestions(Integer subject, Integer page, Integer pageSize, String sort){
		List<Questions> result = questionsService.getQuestions(subject, page, pageSize, sort);
		return new JsonResult(result);
	}
	
	
}
