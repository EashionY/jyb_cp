package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.PackageMapper;
import cn.jyb.dao.SchoolDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.entity.Student;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.StudentException;
import cn.jyb.util.IDCardUtil;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	
	@Resource
	private SchoolDao schoolDao;
	
	@Resource
	private PackageMapper packageMapper;
	
	public String addStudent(HttpServletRequest req,String user_id, String school_id, String student_name, String student_license,
			String student_idcard, String student_recommend, String student_tel, String packageName) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		//解决乱码
//		student_name = new String(student_name.getBytes("ISO-8859-1"),"utf-8");
//		student_name = req.getParameter("student_name");
//		System.out.println("student_name:"+student_name);
		//验证身份证号码的有效性
		if(student_idcard != null){
			boolean success = IDCardUtil.isIDCard(student_idcard);
			if(!success){
				throw new StudentException("身份证号码不合法");
			}
		}
		Student student = studentDao.findByUserId(Integer.parseInt(user_id));
		//已报名某驾校
		if(student != null){
			//付款成功
			if(student.getPay_status() == 1){
				//学员报名成功的驾校id
				Integer schoolId = student.getSchool_id();
				Map<String,Object> school = schoolDao.schoolDetail(schoolId);
				String schoolName = (String) school.get("school_name");
				throw new StudentException("已报名驾校："+schoolName);
			}else{//付款未成功
				student.setSchool_id(Integer.parseInt(school_id));
				student.setStudent_name(student_name);
				student.setStudent_license(student_license);
				student.setStudent_idcard(student_idcard);
				student.setStudent_recommend(student_recommend);
				student.setStudent_tel(student_tel);
				student.setPackageName(packageName);
				try {
					studentDao.updateByPrimaryKeySelective(student);
				} catch (Exception e) {
					e.printStackTrace();
					throw new StudentException("报名失败");
				}
			}
		}else{//未报名
			student = new Student();
			student.setUser_id(Integer.parseInt(user_id));
			student.setSchool_id(Integer.parseInt(school_id));
			student.setStudent_name(student_name);
			student.setStudent_license(student_license);
			student.setStudent_idcard(student_idcard);
			student.setStudent_recommend(student_recommend);
			student.setStudent_tel(student_tel);
			student.setPackageName(packageName);
			try {
				studentDao.save(student);
			} catch (Exception e) {
				e.printStackTrace();
				throw new StudentException("报名失败");
			}
		}
		//通过驾校id和套餐名字获得套餐
		cn.jyb.entity.Package pkg = packageMapper.findPackage(Integer.parseInt(school_id), packageName);
		//返回套餐价格(精确到小数点后两位)
		return pkg.getPackagePrice()+".00";
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

	public Student findStudentByUserId(Integer user_id) {
		return studentDao.findByUserId(user_id);
	}
	
	
	
}
