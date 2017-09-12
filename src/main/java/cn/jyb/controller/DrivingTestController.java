package cn.jyb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.CoachService;
import cn.jyb.service.ScheduleService;
import cn.jyb.service.TeachRecordService;
import cn.jyb.service.UserService;
import cn.jyb.util.JsonResult;
@Controller
@RequestMapping("/drivingTest")
public class DrivingTestController extends ExceptionController {

	@Resource
	private UserService userService;
	
	@Resource
	private TeachRecordService teachRecordService;
	
	@Resource
	private CoachService coachService;
	
	@Resource
	private ScheduleService scheduleService;
	
	/**
	 * 通过教练名模糊查询（前端后台公用）
	 * @param coach_name
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/findCoachByName",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult findCoachByName(String coach_name,String coach_area,HttpServletRequest req,HttpServletResponse res) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String, Object>> coachs = coachService.findCoachByName(coach_name,coach_area);
		return new JsonResult(coachs);
	}
	
	@RequestMapping("/findPhoneById")
	@ResponseBody
	public JsonResult findPhoneById(String user_id){
		String phone = userService.findPhoneById(Integer.parseInt(user_id));
		return new JsonResult(phone);
	}
	
	@RequestMapping("/saveTeachRecord")
	@ResponseBody
	public JsonResult saveTeachRecord(String student_id, String coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips) throws NumberFormatException, UnsupportedEncodingException{
		boolean tf = teachRecordService.addTeachRecord(Integer.parseInt(student_id), Integer.parseInt(coach_id),
				teach_subject, teach_time, teach_field, shuttle, shuttle_time, shuttle_place, tips);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/findStudyRecordNumber")
	@ResponseBody
	public JsonResult findStudyRecordNumber(String student_id){
		int n = teachRecordService.findStudyRecordNumber(Integer.parseInt(student_id));
		return new JsonResult(n);
	}
	
	@RequestMapping("/findStudyRecords")
	@ResponseBody
	public JsonResult findStudyRecords(String student_id,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findStudyRecords(Integer.parseInt(student_id),
				Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/findTeachRecordNumber")
	@ResponseBody
	public JsonResult findTeachRecordNumber(String coach_id){
		int n = teachRecordService.findTeachRecordNumber(Integer.parseInt(coach_id));
		return new JsonResult(n);
	}
	
	@RequestMapping("/findTeachRecords")
	@ResponseBody
	public JsonResult findTeachRecords(String coach_id,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findTeachRecords(Integer.parseInt(coach_id),
				Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/addEvaluation")
	@ResponseBody
	public JsonResult addEvaluation(String teach_id,String evaluation,String evaltype,String evalstar) throws NumberFormatException, UnsupportedEncodingException{
		boolean tf = teachRecordService.addEvaluation(Integer.parseInt(teach_id), evaluation,
				Integer.parseInt(evaltype), Integer.parseInt(evalstar));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/findStudyEvaluationNumber")
	@ResponseBody
	public JsonResult findStudyEvaluationNumber(String student_id){
		Map<String, Integer> result = teachRecordService.findStudyEvaluationNumber(Integer.parseInt(student_id));
		return new JsonResult(result);
	}
	
	@RequestMapping("/findStudyEvaluation")
	@ResponseBody
	public JsonResult findStudyEvaluation(String student_id,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findStudyEvaluation(Integer.parseInt(student_id),
				Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/findTeachEvaluationNumber")
	@ResponseBody
	public JsonResult findTeachEvaluationNumber(String coach_id){
		Map<String, Integer> result = teachRecordService.findTeachEvaluationNumber(Integer.parseInt(coach_id));
		return new JsonResult(result);
	}
	
	@RequestMapping("/acceptTeach")
	@ResponseBody
	public JsonResult acceptTeach(String teach_id){
		boolean tf = teachRecordService.acceptTeach(Integer.parseInt(teach_id));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/finishTeach")
	@ResponseBody
	public JsonResult finishTeach(String teach_id){
		boolean tf = teachRecordService.finishTeach(Integer.parseInt(teach_id));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/refuseTeach")
	@ResponseBody
	public JsonResult refuseTeach(String teach_id){
		boolean tf = teachRecordService.refuseTeach(Integer.parseInt(teach_id));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/cancelTeach")
	@ResponseBody
	public JsonResult cancelTeach(String teach_id){
		boolean tf = teachRecordService.cancelTeach(Integer.parseInt(teach_id));
		return new JsonResult(tf);
	}
	
	@RequestMapping("/findTeachEvaluations")
	@ResponseBody
	public JsonResult findTeachEvalutions(String coach_id,String evaltype,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findTeachEvaluations(Integer.parseInt(coach_id),
				Integer.parseInt(evaltype), Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	
	@RequestMapping("/setCoachSchedule")
	@ResponseBody
	public JsonResult setCoachSchedule(String data){
		boolean tf = scheduleService.setCoachSchedule(data);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/listCoachSchedule")
	@ResponseBody
	public JsonResult listCoachSchedule(String data){
		List<Map<String,String>> list = scheduleService.listCoachSchedule(data);
		return new JsonResult(list);
	}
	
	@RequestMapping("/saveStudentSchedule")
	@ResponseBody
	public JsonResult saveStudentSchedule(String data){
		boolean tf = scheduleService.saveStudentSchedule(data);
		return new JsonResult(tf);
	}
	
	/**
	 * 所有约教记录(后台管理系统)
	 * @param page
	 * @param pageSize
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/listAllTeachRecord")
	@ResponseBody
	public JsonResult listAllTeachRecord(String teach_subject,String page,String pageSize,HttpServletRequest req,HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.listAllTeachRecord(teach_subject, Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(result);
	}
	
	@RequestMapping("/findRecordByCoachName")
	@ResponseBody
	public JsonResult findRecordByCoachName(String coach_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByCoachName(coach_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findRecordByStudentName")
	@ResponseBody
	public JsonResult findRecordByStudentName(String student_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByStudentName(student_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findRecordByTeachTime")
	@ResponseBody
	public JsonResult findRecordByTeachTime(String teach_time,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByTeachTime(teach_time);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findRecordBySubject")
	@ResponseBody
	public JsonResult findRecordBySubject(String teach_subject,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordBySubject(teach_subject);
		return new JsonResult(result);
	}
	
	@RequestMapping("/listAllCoachSchedule")
	@ResponseBody
	public JsonResult listAllCoachSchedule(int page,int pageSize,HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.listAllCoachSchedule(page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findCoachScheduleByName")
	@ResponseBody
	public JsonResult findCoachScheduleByName(String coach_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.findCoachScheduleByName(coach_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findCoachScheduleByDate")
	@ResponseBody
	public JsonResult findCoachScheduleByDate(String appoint_time,HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.findCoachScheduleByDate(appoint_time);
		return new JsonResult(result);
	}
	
}
