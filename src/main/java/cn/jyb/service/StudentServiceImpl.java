package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.StudentDao;
import cn.jyb.entity.Student;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.StudentException;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	
	public boolean addStudent(String user_id, String school_id, String student_name, String student_license,
			String student_idcard, String student_recommend, String student_tel) throws UnsupportedEncodingException {
		Student student = new Student();
//		if(studentDao.findStudent(user_id,school_id)!=null){
//			throw new StudentException("已报名该驾校，请勿重复操作");
//		}
		student_name = new String(student_name.getBytes("ISO-8859-1"),"utf-8");
		student.setUser_id(Integer.parseInt(user_id));
		student.setSchool_id(Integer.parseInt(school_id));
		student.setStudent_name(student_name);
		student.setStudent_license(student_license);
		student.setStudent_idcard(student_idcard);
		student.setStudent_recommend(student_recommend);
		student.setStudent_tel(student_tel);
		try {
			studentDao.save(student);
		} catch (Exception e) {
			throw new StudentException("报名失败");
		}
		return true;
	}

	public List<Map<String, Object>> listAllStudent(String school_area, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//如果不传入参数school_area，则查找全部学员
		if(school_area==null || school_area.trim().isEmpty()){
			try {
				result = studentDao.listAllStudent(offset, pageSize);
			} catch (Exception e) {
				throw new DataBaseException("数据库异常");
			}
			return result;
		}
		//按照驾校地区查找学员
		try {
			result = studentDao.listStudentByArea(school_area, offset, pageSize);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findStudentByName(String student_name) {
		student_name = "%"+student_name+"%";
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = studentDao.findStudentByName(student_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findStudentBySchoolName(String school_name) {
		school_name = "%"+school_name+"%";
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = studentDao.findStudentBySchoolName(school_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findStudentBySignupTime(String signup_time) {
		signup_time = "%"+signup_time+"%";
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = studentDao.findStudentBySignupTime(signup_time);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	
}
