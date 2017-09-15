package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {

	/**
	 * ������У
	 * @param user_id
	 * @param school_id
	 * @param student_name
	 * @param student_license
	 * @param student_idcard
	 * @param student_recommend
	 * @param student_tel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean addStudent(HttpServletRequest req,String user_id,String school_id,String student_name,String student_license,
			String student_idcard,String student_recommend,String student_tel,String packageName) throws UnsupportedEncodingException;
	
	/**
	 * �г�����ѧԱ(��̨)
	 * @param school_area ��У����
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllStudent(String school_area,int page,int pageSize);
	
	/**
	 * ͨ��ѧԱ������(��̨��ģ����ѯ)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentByName(String student_name);
	
	/**
	 * ͨ����У������ѧԱ(��̨��ģ����ѯ)
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySchoolName(String school_name);
	
	/**
	 * ͨ������ʱ�����ѧԱ(��̨��ģ����ѯ)
	 * @param signup_time
	 * @return
	 */
	public List<Map<String,Object>> findStudentBySignupTime(String signup_time);
}
