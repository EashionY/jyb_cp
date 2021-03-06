package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.EnvironmentMapper;
import cn.jyb.dao.PackageMapper;
import cn.jyb.dao.SchoolDao;
import cn.jyb.dao.TeachFieldMapper;
import cn.jyb.entity.Environment;
import cn.jyb.entity.School;
import cn.jyb.entity.TeachField;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.exception.NoSchoolFoundException;
import cn.jyb.exception.SchoolException;
import cn.jyb.util.Distance;
import cn.jyb.util.Upload;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {

	@Resource
	private SchoolDao schoolDao;
	
	@Resource
	private TeachFieldMapper teachFieldMapper;
	
	@Resource
	private PackageMapper packageMapper;
	
	@Resource
	private EnvironmentMapper environmentMapper;
	
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
			schools = schoolDao.findSchool(offset, pageSize, school_area, 1);
			if(schools==null || schools.isEmpty()){
				throw new NoSchoolFoundException("未找到相关驾校");
			}
			System.out.println(schools);
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
			String school_weidu, double school_price, String school_tel, String school_area,HttpServletRequest request) throws IOException {
		School school = schoolDao.findSchoolByName(school_name);
		if(school != null){
			throw new SchoolException("驾校已存在");
		}
		School newSchool = new School();
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		if(!paths.isEmpty()){
			newSchool.setSchool_logo(paths.get(0));
			newSchool.setSchool_license(paths.get(1));
		}
		newSchool.setSchool_name(school_name);
		newSchool.setSchool_address(school_address);
		newSchool.setSchool_slogan(school_slogan);
		newSchool.setSchool_jingdu(school_jingdu);
		newSchool.setSchool_weidu(school_weidu);
		DecimalFormat df = new DecimalFormat("#.00");
		newSchool.setSchool_price(df.format(school_price));
		newSchool.setSchool_tel(school_tel);
		newSchool.setSchool_area(school_area);
		newSchool.setSchool_status(0);
		try {
			schoolDao.save(newSchool);
		} catch (Exception e) {
			throw new DataBaseException("新增驾校失败");
		}
		return true;
	}

	public boolean modifySchoolInfo(String school_id, String school_name, String school_address, String school_slogan,
			String school_jingdu, String school_weidu, double school_price, String school_tel, String school_area,
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
		DecimalFormat df = new DecimalFormat("#.00");
		school.setSchool_price(df.format(school_price));
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
//		System.out.println(school_logo);
		int i;
		try {
			i = schoolDao.modifySchoolLogo(school_logo,school_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		if(i!=1){
			throw new SchoolException("修改logo失败");
		}
		return true;
	}
	
	public boolean modifySchoolLicense(HttpServletRequest request,String school_name) throws IOException{
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		String school_license = paths.get(0);
//		System.out.println(school_license);
		int i;
		try {
			i = schoolDao.modifySchoolLicense(school_license,school_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		if(i!=1){
			throw new SchoolException("修改营业执照失败");
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

	public Map<String,Object> findAllSchool(int page, int pageSize, String school_area, Integer school_status) {
		int offset = (page-1)*pageSize;
		Map<String,Object> result = new HashMap<String, Object>();
		List<School> schools = new ArrayList<School>();
		try {
			schools = schoolDao.findSchool(offset, pageSize, school_area, school_status);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("查找驾校失败");
		}
		result.put("schools", schools);
		int schoolNum = schoolDao.getSchoolNum(school_area, school_status);
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

	public Map<String, Object> schoolDetail(int school_id,Double lon1,Double lat1) {
		//经纬度为空，则默认为0
		lon1 = lon1==null?0:lon1;
		lat1 = lat1==null?0:lat1;
		Map<String, Object> school;
		try {
			school = schoolDao.schoolDetail(school_id);
			double lon2 = Double.parseDouble((String)school.get("school_jingdu"));
			double lat2 = Double.parseDouble((String)school.get("school_weidu"));
			String distance = Distance.getDistance(lon1, lat1, lon2, lat2);
			school.put("school_distance", distance);
			List<Map<String,Object>> fields = teachFieldMapper.findBySchoolId(school_id);
			for(Map<String,Object> map : fields){
				double lon3 = Double.parseDouble((String)map.get("field_lon"));
				double lat3 = Double.parseDouble((String)map.get("field_lat"));
				String fieldDistance = Distance.getDistance(lon1, lat1, lon3, lat3);
				map.put("field_distance", fieldDistance);
			}
			List<Map<String,Object>> packages = packageMapper.findBySchoolId(school_id);
			Environment env = environmentMapper.findBySchoolId(school_id);
			school.put("fields", fields);
			school.put("packages", packages);
			school.put("environment",env);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return school;
	}

	public boolean addEnvironment(HttpServletRequest request, int school_id) throws IOException {
		List<String> list = Upload.uploadImg(request, ""+school_id, "environment");
		String[] paths = list.toArray(new String[list.size()]);
		paths = Arrays.copyOf(paths,12);
		Environment env = environmentMapper.findBySchoolId(school_id);
		int i = 0;
		if(env==null){
			env = new Environment();
			env.setSchoolId(school_id);
			env.setImg1(paths[0]);
			env.setImg2(paths[1]);
			env.setImg3(paths[2]);
			env.setImg4(paths[3]);
			env.setImg5(paths[4]);
			env.setImg6(paths[5]);
			env.setImg7(paths[6]);
			env.setImg8(paths[7]);
			env.setImg9(paths[8]);
			env.setImg10(paths[9]);
			env.setImg11(paths[10]);
			env.setImg12(paths[11]);
			i = environmentMapper.insertSelective(env);
		}else{
			env.setImg1(paths[0]);
			env.setImg2(paths[1]);
			env.setImg3(paths[2]);
			env.setImg4(paths[3]);
			env.setImg5(paths[4]);
			env.setImg6(paths[5]);
			env.setImg7(paths[6]);
			env.setImg8(paths[7]);
			env.setImg9(paths[8]);
			env.setImg10(paths[9]);
			env.setImg11(paths[10]);
			env.setImg12(paths[11]);
			i = environmentMapper.updateByPrimaryKey(env);
		}
		if(i!=1){
			throw new SchoolException("教学环境上传失败");
		}
		return i==1;
	}

	public boolean addPackage(Integer school_id, String packageName, String packageType, String packageIntro,
			String packagePrice, String packageContent) {
		cn.jyb.entity.Package pkg = packageMapper.findPackage(school_id, packageName);
		int i = 0;
		if(pkg == null){
			pkg = new cn.jyb.entity.Package();
			pkg.setSchoolId(school_id);
			pkg.setPackageName(packageName);
			pkg.setPackageType(packageType);
			pkg.setPackageIntro(packageIntro);
			pkg.setPackagePrice(packagePrice);
			pkg.setPackageContent(packageContent);
			i = packageMapper.insert(pkg);
			if(i != 1){
				throw new SchoolException("增加报名套餐失败");
			}
		} else {
			pkg.setSchoolId(school_id);
			pkg.setPackageName(packageName);
			pkg.setPackageType(packageType);
			pkg.setPackageIntro(packageIntro);
			pkg.setPackagePrice(packagePrice);
			pkg.setPackageContent(packageContent);
			i = packageMapper.updateByPrimaryKeySelective(pkg);
			if(i != 1){
				throw new SchoolException("增加报名套餐失败");
			}
		}
		return i==1;
	}

	public boolean deletePackage(int packageId) {
		int i = packageMapper.deleteByPrimaryKey(packageId);
		if(i!=1){
			throw new SchoolException("删除报名套餐失败");
		}
		return i==1;
	}

	public boolean modifyPackage(int packageId, int school_id, String packageName, String packageType,
			String packageIntro, String packagePrice, String packageContent) {
		cn.jyb.entity.Package pkg = new cn.jyb.entity.Package();
		pkg.setPackageId(packageId);
		pkg.setSchoolId(school_id);
		//如果传过来的为""，则替换为null
		pkg.setPackageName("".equals(packageName)?null:packageName);
		pkg.setPackageType("".equals(packageType)?null:packageType);
		pkg.setPackageIntro("".equals(packageIntro)?null:packageIntro);
		pkg.setPackagePrice("".equals(packagePrice)?null:packagePrice);
		pkg.setPackageContent("".equals(packageContent)?null:packageContent);
		int i = packageMapper.updateByPrimaryKeySelective(pkg);
		if(i!=1){
			throw new SchoolException("修改报名套餐失败");
		}
		return i==1;
	}

	public boolean addTeachField(Integer school_id,String school_name, String fieldName, String fieldAddress, String fieldLon,
			String fieldLat, HttpServletRequest request) throws IOException {
		TeachField teachField = new TeachField();
		//上传场地的封面
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		if(!paths.isEmpty()){
			String fieldImg = paths.get(0); 
			teachField.setFieldImg(fieldImg);
		}
		teachField.setSchoolId(school_id);
		teachField.setFieldName(fieldName);
		teachField.setFieldAddress(fieldAddress);
		teachField.setFieldLon(fieldLon);
		teachField.setFieldLat(fieldLat);
		int i = teachFieldMapper.insertSelective(teachField);
		if(i!=1){
			throw new SchoolException("新增训练场地失败");
		}
		return i==1;
	}

	public boolean modifyTeachFieldInfo(Integer fieldId, String school_name, String fieldName, String fieldAddress, String fieldLon, String fieldLat,HttpServletRequest request) throws IOException {
		TeachField teachField = teachFieldMapper.selectByPrimaryKey(fieldId);
		if(teachField == null){
			throw new SchoolException("未找到对应的训练场地");
		}
		//上传场地的封面
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		if(!paths.isEmpty()){
			String fieldImg = paths.get(0); 
			teachField.setFieldImg(fieldImg);
		}
		teachField.setFieldName(fieldName);
		teachField.setFieldAddress(fieldAddress);
		teachField.setFieldLon(fieldLon);
		teachField.setFieldLat(fieldLat);
		int i = teachFieldMapper.updateByPrimaryKeySelective(teachField);
		if(i!=1){
			throw new SchoolException("修改训练场地信息失败");
		}
		return i==1;
	}

	public boolean deleteTeachField(Integer fieldId) {
		int i = teachFieldMapper.deleteByPrimaryKey(fieldId);
		if(i!=1){
			throw new SchoolException("删除训练场地失败");
		}
		return i==1;
	}

	public Integer dealSchool(Integer school_id, Integer schoolStatus) {
		School school = new School();
		school.setSchool_id(school_id);
		school.setSchool_status(schoolStatus);
		int i = schoolDao.updateByPrimaryKeySelective(school);
		if(i!=1){
			throw new SchoolException("审核失败");
		}
		return schoolStatus;
	}

	public boolean modifyTeachFieldImg(Integer fieldId, String school_name, HttpServletRequest request) throws IOException{
		TeachField teachField = teachFieldMapper.selectByPrimaryKey(fieldId);
		if(teachField == null){
			throw new SchoolException("未找到对应的训练场地");
		}
		List<String> paths = Upload.uploadImg(request, "logos", school_name);
		if(paths.isEmpty()){
			throw new ImgpathException("图片上传失败");
		}
		teachField.setFieldImg(paths.get(0));
		System.out.println(teachField);
		int i = teachFieldMapper.updateByPrimaryKeySelective(teachField);
		if(i != 1){
			throw new SchoolException("修改训练场地封面失败");
		}
		return i==1;
	}

	
}
