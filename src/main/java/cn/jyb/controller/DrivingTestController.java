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

import cn.jyb.entity.TeachRecord;
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
	
	/**
	 * 通过用户id查找电话号码
	 * @param user_id the user id
	 * @return the json result
	 */
	@RequestMapping("/findPhoneById")
	@ResponseBody
	public JsonResult findPhoneById(String user_id){
		String phone = userService.findPhoneById(Integer.parseInt(user_id));
		return new JsonResult(phone);
	}
	
	/**
	 * 增加约教记录(学员预约教练时，将对应时段的状态改为1)
	 * @param student_id
	 * @param coach_id
	 * @param teach_subject
	 * @param teach_time
	 * @param teach_field
	 * @param shuttle
	 * @param shuttle_time
	 * @param shuttle_place
	 * @param tips
	 * @return
	 * @throws NumberFormatException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/saveTeachRecord")
	@ResponseBody
	public JsonResult saveTeachRecord(String student_id, String coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips) 
			throws NumberFormatException, UnsupportedEncodingException{
		TeachRecord teachRecord = teachRecordService.addTeachRecord(Integer.parseInt(student_id), Integer.parseInt(coach_id),
				teach_subject, teach_time, teach_field, shuttle, shuttle_time, shuttle_place, tips);
		return new JsonResult(teachRecord);
	}
	
	/**
	 * 学员的约教记录条数
	 * @param student_id
	 * @return
	 */
	@RequestMapping("/findStudyRecordNumber")
	@ResponseBody
	public JsonResult findStudyRecordNumber(String student_id){
		int n = teachRecordService.findStudyRecordNumber(Integer.parseInt(student_id));
		return new JsonResult(n);
	}
	/**
	 * 学员的所有约教记录
	 * @param student_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findStudyRecords")
	@ResponseBody
	public JsonResult findStudyRecords(String student_id,String page,String pageSize){
		List<Map<String, Object>> list = teachRecordService.findStudyRecords(Integer.parseInt(student_id),
				Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	/**
	 * 教练的所有教学记录条数
	 * @param coach_id
	 * @return
	 */
	@RequestMapping("/findTeachRecordNumber")
	@ResponseBody
	public JsonResult findTeachRecordNumber(String coach_id){
		int n = teachRecordService.findTeachRecordNumber(Integer.parseInt(coach_id));
		return new JsonResult(n);
	}
	/**
	 * 教练的所有教学记录
	 * @param coach_id
	 * @param teach_state
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findTeachRecords")
	@ResponseBody
	public JsonResult findTeachRecords(String coach_id,String teach_state,String page,String pageSize){
		List<Map<String, Object>> list = teachRecordService.findTeachRecords(Integer.parseInt(coach_id),
				teach_state, Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	/**
	 * 查看教练处理过的所有约教记录
	 * @param coach_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listDealedRecord")
	@ResponseBody
	public JsonResult listDealedRecord(int coach_id,int page,int pageSize){
		return new JsonResult(teachRecordService.listDealedRecord(coach_id, page, pageSize));
	}
	/**
	 * 为已完成的训练添加评价
	 * @param teach_id
	 * @param evaluation
	 * @param evaltype
	 * @param evalstar
	 * @return 
	 * @throws NumberFormatException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addEvaluation")
	@ResponseBody
	public JsonResult addEvaluation(String teach_id,String evaluation,String evaltype,String evalstar) throws NumberFormatException, UnsupportedEncodingException{
		boolean tf = teachRecordService.addEvaluation(teach_id, evaluation,
				Integer.parseInt(evaltype), Integer.parseInt(evalstar));
		return new JsonResult(tf);
	}
	/**
	 * 查找学员给出的所有评价数量
	 * @param student_id
	 * @return
	 */
	@RequestMapping("/findStudyEvaluationNumber")
	@ResponseBody
	public JsonResult findStudyEvaluationNumber(String student_id){
		Map<String, Integer> result = teachRecordService.findStudyEvaluationNumber(Integer.parseInt(student_id));
		return new JsonResult(result);
	}
	/**
	 * 查找学员给出的所有评价
	 * @param student_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findStudyEvaluation")
	@ResponseBody
	public JsonResult findStudyEvaluation(String student_id,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findStudyEvaluation(Integer.parseInt(student_id),
				Integer.parseInt(page),Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	/**
	 * 查找教练收到的所有评价数量
	 * @param coach_id
	 * @return
	 */
	@RequestMapping("/findTeachEvaluationNumber")
	@ResponseBody
	public JsonResult findTeachEvaluationNumber(String coach_id){
		Map<String, Integer> result = teachRecordService.findTeachEvaluationNumber(Integer.parseInt(coach_id));
		return new JsonResult(result);
	}
	/**
	 * 教练接受约教请求
	 * @param teach_id
	 * @return
	 */
	@RequestMapping("/acceptTeach")
	@ResponseBody
	public JsonResult acceptTeach(String teach_id){
		boolean tf = teachRecordService.acceptTeach(teach_id);
		return new JsonResult(tf);
	}
	/**
	 * 学员完成约教训练
	 * @param teach_id
	 * @return
	 */
	@RequestMapping("/finishTeach")
	@ResponseBody
	public JsonResult finishTeach(String teach_id){
		boolean tf = teachRecordService.finishTeach(teach_id);
		return new JsonResult(tf);
	}
	/**
	 * 教练拒绝约教请求
	 * @param teach_id
	 * @return
	 */
	@RequestMapping("/refuseTeach")
	@ResponseBody
	public JsonResult refuseTeach(String teach_id){
		boolean tf = teachRecordService.refuseTeach(teach_id);
		return new JsonResult(tf);
	}
	/**
	 * 学员取消约教请求
	 * @param teach_id
	 * @return
	 */
	@RequestMapping("/cancelTeach")
	@ResponseBody
	public JsonResult cancelTeach(String teach_id){
		boolean tf = teachRecordService.cancelTeach(teach_id);
		return new JsonResult(tf);
	}
	/**
	 * 查找教练收到的好评/中评/差评
	 * @param coach_id
	 * @param evaltype
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findTeachEvaluations")
	@ResponseBody
	public JsonResult findTeachEvalutions(String coach_id,String evaltype,String page,String pageSize){
		List<Map<String, String>> list = teachRecordService.findTeachEvaluations(Integer.parseInt(coach_id),
				Integer.parseInt(evaltype), Integer.parseInt(page), Integer.parseInt(pageSize));
		return new JsonResult(list);
	}
	/**
	 * 教练设置日程表
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/setCoachSchedule")
	@ResponseBody
	public JsonResult setCoachSchedule(HttpServletRequest request,String data) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		boolean tf = scheduleService.setCoachSchedule(data);
		return new JsonResult(tf);
	}
	/**
	 * 列出教练最近三天的日程表供学员查看
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/listCoachSchedule")
	@ResponseBody
	public JsonResult listCoachSchedule(HttpServletRequest request,String data) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		List<Map<String,String>> list = scheduleService.listCoachSchedule(data);
		return new JsonResult(list);
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
	/**
	 * 通过教练名查找记录(后台)
	 * @param coach_name
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findRecordByCoachName")
	@ResponseBody
	public JsonResult findRecordByCoachName(String coach_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByCoachName(coach_name);
		return new JsonResult(result);
	}
	/**
	 * 通过学员名查找记录(后台)
	 * @param student_name
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findRecordByStudentName")
	@ResponseBody
	public JsonResult findRecordByStudentName(String student_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByStudentName(student_name);
		return new JsonResult(result);
	}
	/**
	 * 通过约教时间查找记录(后台)
	 * @param teach_time
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findRecordByTeachTime")
	@ResponseBody
	public JsonResult findRecordByTeachTime(String teach_time,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordByTeachTime(teach_time);
		return new JsonResult(result);
	}
	/**
	 * 通过约教科目查找记录(后台)
	 * @param teach_subject
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findRecordBySubject")
	@ResponseBody
	public JsonResult findRecordBySubject(String teach_subject,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = teachRecordService.findRecordBySubject(teach_subject);
		return new JsonResult(result);
	}
	/**
	 * 获取全部教练的日程(后台)
	 * @param page
	 * @param pageSize
	 * @param resp
	 * @return
	 */
	@RequestMapping("/listAllCoachSchedule")
	@ResponseBody
	public JsonResult listAllCoachSchedule(int page,int pageSize,HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.listAllCoachSchedule(page, pageSize);
		return new JsonResult(result);
	}
	/**
	 * 通过教练名查找日程(后台)
	 * @param coach_name
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findCoachScheduleByName")
	@ResponseBody
	public JsonResult findCoachScheduleByName(String coach_name,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.findCoachScheduleByName(coach_name);
		return new JsonResult(result);
	}
	/**
	 * 通过预约日期查找日程(后台)
	 * @param appoint_time
	 * @param resp
	 * @return
	 */
	@RequestMapping("/findCoachScheduleByDate")
	@ResponseBody
	public JsonResult findCoachScheduleByDate(String appoint_time,HttpServletResponse resp){
		resp.addHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = scheduleService.findCoachScheduleByDate(appoint_time);
		return new JsonResult(result);
	}
	/**
	 * 教练查看自己最近三天的日程安排
	 * @param data
	 * @return
	 */
	@RequestMapping("/checkCoachSchedule")
	@ResponseBody
	public JsonResult checkCoachSchedule(String data){
		return new JsonResult(scheduleService.checkCoachSchedule(data));
	}
}
