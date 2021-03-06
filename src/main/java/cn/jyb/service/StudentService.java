package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Student;

public interface StudentService {

	/**
	 * 报名驾校（每个学员只能报名一个驾校）
	 * @param user_id
	 * @param school_id
	 * @param student_name
	 * @param student_license
	 * @param student_idcard
	 * @param student_recommend
	 * @param student_tel
	 * @return 返回学生选择的套餐价格
	 * @throws UnsupportedEncodingException
	 */
	public String addStudent(HttpServletRequest req,String user_id,String school_id,String student_name,String student_license,
			String student_idcard,String student_recommend,String student_tel,String packageName) throws UnsupportedEncodingException;
	
	/**
	 * 列出所有学员(后台)
	 * @param school_area 驾校地区
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllStudent(String school_area,int page,int pageSize);
	
	/**
	 * 通过学员名查找(后台，模糊查询)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentByName(String student_name);
	
	/**
	 * 通过驾校名查找学员(后台，模糊查询)
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySchoolName(String school_name);
	
	/**
	 * 通过报名时间查找学员(后台，模糊查询)
	 * @param signup_time
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySignupTime(String signup_time);
	
	/**
	 * 通过用户id查找学员
	 * @param user_id
	 * @return
	 */
	public Student findStudentByUserId(Integer user_id);
	
	
}
