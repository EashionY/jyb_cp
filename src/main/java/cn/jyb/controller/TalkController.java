package cn.jyb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.CommentService;
import cn.jyb.service.TalkService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/talk")
public class TalkController extends ExceptionController {

	@Resource
	private TalkService talkService;
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/addTalks")
	@ResponseBody
	public JsonResult addTalks(String phone,String user_id,String talk,HttpServletRequest request) throws NumberFormatException, IOException{
		boolean tf = talkService.saveTalks(phone, Integer.parseInt(user_id), talk, request);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/addComments")
	@ResponseBody
	public JsonResult addComments(String talk_id, String user_id, String comment){
		Map<String,Object> map = commentService.addComments(Integer.parseInt(talk_id), Integer.parseInt(user_id), comment);
		return new JsonResult(map);
	}
	
	@RequestMapping("/addLikes")
	@ResponseBody
	public JsonResult addLikes(String talk_id, String user_id){
		boolean tf = commentService.addLikes(Integer.parseInt(talk_id), Integer.parseInt(user_id));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/listComments")
	@ResponseBody
	public JsonResult listComments(String talk_id){
		List<Map<String, String>> list = commentService.listComments(Integer.parseInt(talk_id));
		return new JsonResult(list);
	}
	
	@RequestMapping("/listTalks")
	@ResponseBody
	public JsonResult listTalks(String user_id,String page,String pageSize){
		List<Map<String,Object>> list = talkService.listTalks(Integer.parseInt(user_id),Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/listAllTalks")
	@ResponseBody
	public JsonResult listAllTalks(String currentUser_id,String page,String pageSize){
		List<Map<String, Object>> list = talkService.listAllTalks(Integer.parseInt(currentUser_id),Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/talksDetail")
	@ResponseBody
	public JsonResult talksDetail(String talk_id,String currentUser_id){
		Map<String,Object> map = talkService.talksDetail(Integer.parseInt(talk_id), Integer.parseInt(currentUser_id));
		return new JsonResult(map);
	}
	
	@RequestMapping("/deleteTalks")
	@ResponseBody
	public JsonResult deleteTalks(String talk_id){
		talkService.deleteTalks(Integer.parseInt(talk_id));
		return new JsonResult("");
	}
}
