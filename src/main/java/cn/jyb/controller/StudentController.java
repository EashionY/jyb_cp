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

import cn.jyb.service.StudentService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/student")
public class StudentController extends ExceptionController {

	@Resource
	private StudentService studentService;
	
	@RequestMapping("/enrollStudent")
	@ResponseBody
	public JsonResult enrollStudent(HttpServletRequest req, String user_id, String school_id, String student_name, String student_license,
			String student_idcard, String student_recommend, String student_tel, String packageName) throws UnsupportedEncodingException{
		boolean tf = studentService.addStudent(req, user_id, school_id, student_name, student_license, 
				student_idcard, student_recommend, student_tel, packageName);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/listAllStudent")
	@ResponseBody
	public JsonResult listAllStudent(String school_area,int page,int pageSize,HttpServletRequest req,
			HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = studentService.listAllStudent(school_area, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findStudentByName")
	@ResponseBody
	public JsonResult findStudentByName(String student_name,HttpServletRequest req,
			HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = studentService.findStudentByName(student_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findStudentBySchoolName")
	@ResponseBody
	public JsonResult findStudentBySchoolName(String school_name,HttpServletRequest req,
			HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = studentService.findStudentBySchoolName(school_name);
		return new JsonResult(result);
	}
	
	@RequestMapping("/findStudentBySignupTime")
	@ResponseBody
	public JsonResult findStudentBySignupTime(String signup_time,HttpServletRequest req,
			HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> result = studentService.findStudentBySignupTime(signup_time);
		return new JsonResult(result);
	}
	
}
