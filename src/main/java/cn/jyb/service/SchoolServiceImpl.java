package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.SchoolDao;
import cn.jyb.entity.School;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.NoSchoolFoundException;
import cn.jyb.exception.SchoolException;
import cn.jyb.util.Distance;
import cn.jyb.util.Upload;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {

	@Resource
	private SchoolDao schoolDao;
	
	public boolean updateSchoolBrowse(String school_id) {
		try {
			schoolDao.updateSchoolBrowse(school_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("浏览数更新失败");
		}
		return true;
	}

	public List<School> findSchoolByDistance(String lon1, String lat1, int page, int pageSize, String school_area) {
		int offset = (page-1)*pageSize;
		List<School> schools;
		if(lon1==null||lon1.trim().isEmpty() || lat1==null||lat1.trim().isEmpty()){
			schools = schoolDao.findSchool(offset, pageSize, school_area);
			if(schools==null || schools.isEmpty()){
				throw new NoSchoolFoundException("未找到相关驾校");
			}
			return schools;
		}
		List<School> list = schoolDao.findJWdu();
		if(list==null || list.isEmpty()){
			throw new DataBaseException("驾校表数据异常");
		}
		for(School school : list){
			String lon2 = school.getSchool_jingdu();
			String lat2 = school.getSchool_weidu();
			int school_id = school.getSchool_id();
			String school_distance = Distance.getDistance(Double.parseDouble(lon1), Double.parseDouble(lat1),
					Double.parseDouble(lon2), Double.parseDouble(lat2));
			schoolDao.updateSchoolDistance(""+school_id, school_distance);
		}
		schools = schoolDao.findSchoolByDistance(offset, pageSize, school_area);
		if(schools==null || schools.isEmpty()){
			throw new NoSchoolFoundException("未找到相关驾校");
		}
		return schools;
	}

	public List<School> findSchoolByBrowse(int page, int pageSize, String school_area) {
		int offset = (page-1)*pageSize;
		List<School> schools = schoolDao.findSchoolByBrowse(offset, pageSize, school_area);
		if(schools==null || schools.isEmpty()){
			throw new NoSchoolFoundException("未找到相关驾校");
		}
		return schools;
	}

	public boolean addSchool(String school_name, String school_address, String school_slogan, String school_jingdu,
			String school_weidu, String school_price, String school_tel, String school_area,HttpServletRequest request) throws IOException {
		School school = schoolDao.findSchoolByName(school_name);
		if(school != null){
			throw new SchoolException("驾校已存在");
		}
		School newSchool = new School();
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		if(!paths.isEmpty()){
			newSchool.setSchool_logo(paths.get(0));
		}
		newSchool.setSchool_name(school_name);
		newSchool.setSchool_address(school_address);
		newSchool.setSchool_slogan(school_slogan);
		newSchool.setSchool_jingdu(school_jingdu);
		newSchool.setSchool_weidu(school_weidu);
		newSchool.setSchool_price(Integer.parseInt(school_price));
		newSchool.setSchool_tel(school_tel);
		newSchool.setSchool_area(school_area);
		try {
			schoolDao.save(newSchool);
		} catch (Exception e) {
			throw new DataBaseException("新增驾校失败");
		}
		return true;
	}

	public boolean modifySchoolInfo(String school_id, String school_name, String school_address, String school_slogan,
			String school_jingdu, String school_weidu, String school_price, String school_tel, String school_area,
			HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		School school;
		try {
			school = schoolDao.findSchoolByName(school_name);
		} catch (Exception e1) {
			//如果存在两个一样的驾校名，则会抛该异常
			throw new SchoolException("该驾校已存在");
		}
		school = new School();
		school.setSchool_id(Integer.parseInt(school_id));
		school.setSchool_name(school_name);
		school.setSchool_address(school_address);
		school.setSchool_slogan(school_slogan);
		school.setSchool_jingdu(school_jingdu);
		school.setSchool_weidu(school_weidu);
		school.setSchool_price(Integer.parseInt(school_price));
		school.setSchool_tel(school_tel);
		school.setSchool_area(school_area);
		int i;
		try {
			i = schoolDao.modifySchoolInfo(school);
		} catch (Exception e) {
			throw new DataBaseException("修改驾校信息失败");
		}
		if(i!=1){
			throw new SchoolException("修改驾校信息失败");
		}
		return true;
	}

	public boolean modifySchoolLogo(HttpServletRequest request,String school_name) throws IOException{
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		String school_logo = paths.get(0);
		System.out.println(school_logo);
		int i;
		try {
			i = schoolDao.modifySchoolLogo(school_logo,school_name);
		} catch (Exception e) {
			throw new DataBaseException("修改logo失败");
		}
		if(i!=1){
			throw new SchoolException("修改logo失败");
		}
		return true;
	}
	
	public List<School> searchSchool(String school_name) {
		school_name = "%"+school_name+"%";
		List<School> schools = new ArrayList<School>();
		try {
			schools = schoolDao.searchSchoolByName(school_name);
		} catch (Exception e) {
			throw new DataBaseException("查找驾校失败");
		}
		return schools;
	}

	public Map<String,Object> findAllSchool(int page, int pageSize, String school_area) {
		int offset = (page-1)*pageSize;
		Map<String,Object> result = new HashMap<String, Object>();
		List<School> schools = new ArrayList<School>();
		try {
			schools = schoolDao.findSchool(offset, pageSize, school_area);
		} catch (Exception e) {
			throw new DataBaseException("查找驾校失败");
		}
		result.put("schools", schools);
		int schoolNum = schoolDao.getSchoolNum(school_area);
		result.put("schoolNum", schoolNum);
		return result;
	}

	public boolean deleteSchool(String school_id) {
		int i;
		try {
			i = schoolDao.deleteSchool(school_id);
		} catch (Exception e) {
			throw new DataBaseException("删除驾校失败");
		}
		if(i!=1){
			throw new SchoolException("删除驾校失败");
		}
		return true;
	}

	
}
