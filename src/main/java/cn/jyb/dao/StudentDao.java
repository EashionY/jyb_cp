package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Student;

public interface StudentDao {

	public Student findStudent(String user_id, String school_id);

	
	public void save(Student student);
	
	/**
	 * 通过id查找学员
	 * @param student_id
	 * @return
	 */
	public Student findById(int student_id);
	
	/**
	 * 列出所有学员
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllStudent(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 列出所有学员(按照驾校地区分类)
	 * @param school_area
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listStudentByArea(@Param("school_area")String school_area,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 通过学员名查找学员(模糊查询)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentByName(String student_name);
	
	/**
	 * 通过驾校名查找学员(模糊查询)
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySchoolName(String school_name);
	
	/**
	 * 通过报名时间查找学员(模糊查询)
	 * @param signup_time
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySignupTime(String signup_time);
	
	/**
	 * 通过用户id查找学员
	 * @param user_id
	 * @return
	 */
	public Student findByUserId(int user_id);
}
