package cn.jyb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.Coach;
import cn.jyb.service.CoachService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/coach")
public class CoachController extends ExceptionController {

	@Resource
	private CoachService coachService;
	
	@RequestMapping("/hotCoach")
	@ResponseBody
	public JsonResult hotCoach(String page,String pageSize,String coach_area){
		List<Map<String, Object>> result = coachService.hotCoach(Integer.parseInt(page),Integer.parseInt(pageSize),coach_area);
		return new JsonResult(result);
	}

	@RequestMapping("/insertCoach")
	@ResponseBody
	public JsonResult insertCoach(String user_id,String phone,String coach_name,String coach_sex,
			String coach_birthday,String school_name, String school_address,String coach_license,String coach_car,
			String coach_area,HttpServletRequest request) throws NumberFormatException, IOException{
		coachService.insertCoach(Integer.parseInt(user_id), phone, coach_name, coach_sex, coach_birthday,
				school_name, school_address, coach_license, coach_car, coach_area, request);
		return new JsonResult("");
	}
	
	@RequestMapping("/teachSet")
	@ResponseBody
	public JsonResult teachSet(String coach_id, String coach_paying_two, String coach_paying_three,
			String coach_freeshuttle){
		boolean tf = coachService.teachSet(coach_id, coach_paying_two, coach_paying_three, coach_freeshuttle);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/updateCoachBrowse")
	@ResponseBody
	public JsonResult updateCoachBrowse(String coach_id){
		boolean tf = coachService.updateCoachBrowse(coach_id);
		return new JsonResult(tf);
	}
	
	/**
	 * 后台管理系统，处理跨域请求
	 * @param page
	 * @param pageSize
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value="/listAllCoach",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult listAllCoach(String page, String pageSize,HttpServletRequest req,HttpServletResponse res){
		res.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = coachService.listAllCoach(Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(map);
	}
	
	@RequestMapping(value="/listCoachByStatus",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult listCoachByStatus(String coach_status, String page, String pageSize,HttpServletRequest req,HttpServletResponse res){
		res.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = coachService.listCoachByStatus(coach_status, Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(map);
	}
	
	@RequestMapping(value="/dealCoach",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult dealCoach(String coach_id,String coach_status,HttpServletRequest req,HttpServletResponse res){
		res.addHeader("Access-Control-Allow-Origin", "*");
		String status = coachService.dealCoach(coach_id, coach_status);
		return new JsonResult(status);
	}
	
	@RequestMapping("/coachNearby")
	@ResponseBody
	public JsonResult coachNearby(String lon, String lat, String page, String pageSize, String coach_area,
			String coach_sex, String range){
		List<Map<String, Object>> result = coachService.coachNearby(lon, lat, Integer.parseInt(page),
				Integer.parseInt(pageSize),coach_area, coach_sex, range);
		return new JsonResult(result);
	}
	
	@RequestMapping("/listCoachByScore")
	@ResponseBody
	public JsonResult listCoachByScore(String lon, String lat, String page, String pageSize, String coach_area,
			String coach_sex, String range){
		List<Map<String,Object>> result = coachService.listCoachByScore(lon, lat, Integer.parseInt(page),
				Integer.parseInt(pageSize), coach_area, coach_sex, range);
		return new JsonResult(result);
	}
	
	@RequestMapping("/listCoachDefault")
	@ResponseBody
	public JsonResult listCoachDefault(String lon, String lat, String page, String pageSize, String coach_area, String coach_sex, String range){
		List<Map<String,Object>> result = coachService.listCoachDefault(lon, lat, Integer.parseInt(page), Integer.parseInt(pageSize), coach_area, coach_sex, range);
		return new JsonResult(result);
	}
	
	@RequestMapping("/listRecomdCoach")
	@ResponseBody
	public JsonResult listRecomdCoach(String school_name){
		List<Map<String, Object>> result = coachService.listRecomdCoach(school_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findByUserId")
	@ResponseBody
	public JsonResult findByUserId(Integer user_id){
		Coach coach = coachService.findByUserId(user_id);
		return new JsonResult(coach);
	}
	
	@RequestMapping("/listBySchool")
	@ResponseBody
	public JsonResult listBySchool(HttpServletResponse resp,String school_name,String coach_status,Integer page,Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String, Object>> result = coachService.listBySchool(school_name, coach_status,page,pageSize);
		return new JsonResult(result);
	}
}
