package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Student;

public interface StudentDao {

	public Student findStudent(String user_id, String school_id);

	
	public void save(Student student);
	
	/**
	 * ͨ��id����ѧԱ
	 * @param student_id
	 * @return
	 */
	public Student findById(int student_id);
	
	/**
	 * �г�����ѧԱ
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllStudent(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �г�����ѧԱ(���ռ�У��������)
	 * @param school_area
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listStudentByArea(@Param("school_area")String school_area,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ͨ��ѧԱ������ѧԱ(ģ����ѯ)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentByName(String student_name);
	
	/**
	 * ͨ����У������ѧԱ(ģ����ѯ)
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySchoolName(String school_name);
	
	/**
	 * ͨ������ʱ�����ѧԱ(ģ����ѯ)
	 * @param signup_time
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySignupTime(String signup_time);
	
	/**
	 * ͨ���û�id����ѧԱ
	 * @param user_id
	 * @return
	 */
	public Student findByUserId(int user_id);
}
