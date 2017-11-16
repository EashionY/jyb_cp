package cn.jyb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.CoachDao;
import cn.jyb.dao.SchoolDao;
import cn.jyb.dao.TeachFieldMapper;
import cn.jyb.dao.TeachRecordDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Coach;
import cn.jyb.entity.User;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.NoCoachFoundException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.exception.NumberParseException;
import cn.jyb.util.Distance;
import cn.jyb.util.Message;
import cn.jyb.util.Upload;
@Service("coachService")
@Transactional
public class CoachServiceImpl implements CoachService {

	@Resource
	private CoachDao coachDao;
	
	@Resource
	private UserDao userDao;

	@Resource
	private TeachRecordDao teachRecordDao;
	
	@Resource
	private SchoolDao schoolDao;
	
	@Resource
	private TeachFieldMapper teachFieldMapper;
	
	public List<Map<String, Object>> findCoachByName(String coach_name,String coach_area) {
		coach_name = "%"+coach_name+"%";
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		if(coach_area==null || coach_area.trim().isEmpty()){
			result = coachDao.findCoachByName(coach_name);
			if(result==null || result.isEmpty()){
				throw new NoCoachFoundException("未找到对应的教练");
			}
			return result;
		}
		List<Coach> coachs;
		try {
			coachs = coachDao.findByName(coach_name, coach_area);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		result = getMoreInfo(coachs);
		return result;
		
	}

	public List<Map<String,Object>> hotCoach(int page,int pageSize,String coach_area) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		List<Coach> coachs = coachDao.hotCoach(offset,pageSize, coach_area); 
		for(Coach coach : coachs){
			//获取教练头像路径
			int user_id = coach.getUser_id();
			User user = userDao.findById(user_id);
			if(user==null){
				throw new NoUserFoundException("user_id不存在");
			}
			String headImg = user.getImgpath();
			int coach_id = coach.getCoach_id();
			//获取约教数
			int teachNum = teachRecordDao.findTeachRecordNumber(coach_id);
			//获取评价数
			int evalNum = teachRecordDao.findTeachEvaluationNumber(coach_id);
			map.put("headImg", headImg);
			map.put("teachNum", teachNum);
			map.put("evalNum", evalNum);
			map.put("coach", coach);
			result.add(map);
		}
		return result;
	}

	public void insertCoach(int user_id,String phone,String coach_name,String coach_sex,
			String coach_birthday,String school_name, String school_address,
			String coach_license, String coach_car, String coach_area, HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");
		Coach coach = coachDao.findByUserId(user_id);
		String folder = "qualification";
		List<String> paths = Upload.uploadImg(request, phone, folder);
		if(coach != null){
			//若教练已存在，则为修改教练审核资料
			if(!paths.isEmpty() && paths.size()==4){
				coach.setCoach_qualification(paths.get(0));
				coach.setCoach_idcardfront(paths.get(1));
				coach.setCoach_idcardback(paths.get(2));
				coach.setSchool_imgpath(paths.get(3));
			}
			coach.setUser_id(user_id);
			coach.setCoach_name(coach_name);
			coach.setCoach_sex(coach_sex);
			coach.setCoach_birthday(coach_birthday);
			coach.setSchool_name(school_name);
			coach.setSchool_address(school_address);
			coach.setCoach_license(coach_license);
			coach.setCoach_car(coach_car);
			coach.setCoach_area(coach_area);
			coach.setCoach_status("0");
			try {
				coachDao.modifyCoachinfo(coach);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库连接失败");
			}
		}else{
			//教练为null时，新增教练
			coach = new Coach();
			if(!paths.isEmpty() && paths.size()==4){
				coach.setCoach_qualification(paths.get(0));
				coach.setCoach_idcardfront(paths.get(1));
				coach.setCoach_idcardback(paths.get(2));
				coach.setSchool_imgpath(paths.get(3));
			}
			coach.setUser_id(user_id);
			coach.setCoach_name(coach_name);
			coach.setCoach_sex(coach_sex);
			coach.setCoach_birthday(coach_birthday);
			coach.setSchool_name(school_name);
			coach.setSchool_address(school_address);
			coach.setCoach_license(coach_license);
			coach.setCoach_car(coach_car);
			coach.setCoach_area(coach_area);
			coach.setCoach_status("0");
			//教练评分 ，默认为0
			coach.setCoach_score(0);
			try {
				coachDao.insertCoach(coach);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库连接失败");
			}
		}
	}

	public boolean teachSet(String coach_id, String coach_paying_two, String coach_paying_three,
			String coach_freeshuttle) {
		try {
			coachDao.teachSet(Integer.parseInt(coach_id), Double.parseDouble(coach_paying_two),
					Double.parseDouble(coach_paying_three), Integer.parseInt(coach_freeshuttle));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberParseException("字符串解析错误");
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return true;
	}

	public boolean updateCoachBrowse(String coach_id) {
		try {
			coachDao.updateCoachBrowse(coach_id);
		} catch (Exception e) {
			throw new DataBaseException("更新教练浏览数失败");
		}
		return true;
	}

	public Map<String,Object> listAllCoach(int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list = coachDao.listAllCoach(offset, pageSize);
		if(list==null || list.isEmpty()){
			throw new NoCoachFoundException("未找到相关教练");
		}
		int coachNum = coachDao.coachTotalNum();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("coachNum", coachNum);
		map.put("coachList", list);
		return map;
	}

	public Map<String,Object> listCoachByStatus(String coach_status, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list = coachDao.listCoachByStatus(coach_status, offset, pageSize);
		int coachNum = coachDao.coachNum(coach_status);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("coachNum", coachNum);
		map.put("coachList", list);
		return map;
	}

	public String dealCoach(String coach_id, String coach_status) {
		int i;
		try {
			i = coachDao.dealCoach(Integer.parseInt(coach_id), coach_status);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("操作失败");
		}
		if(i!=1){
			throw new DataBaseException("操作失败");
		}
		Coach coach = coachDao.findById(Integer.parseInt(coach_id));
		String status = coach.getCoach_status();
		//获取教练姓名
		String name = coach.getCoach_name();
		int user_id = coach.getUser_id();
		User user = userDao.findById(user_id);
		//获取教练的手机号
		String phone = user.getPhone();
		if("1".equals(status)){
			//短信模板(阿里大于)
			String templateCode = "SMS_71845185";
			//审核通过，发送通知短信
			Message.sendNotifyMsg(phone, name, templateCode);
		}else if("2".equals(status)){
			String templateCode = "SMS_71845183";
			//审核未通过，发送通知短信
			Message.sendNotifyMsg(phone, name, templateCode);
		}
		return status;
	}

	public List<Map<String, Object>> coachNearby(String lon, String lat, int page, int pageSize, 
			String coach_area, String coach_sex, String range) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Coach> coachs = coachDao.listPassedCoach();
		//若用户禁止获取位置信息(经纬度为空),则返回热门的教练表
		if(lon==null||lon.trim().isEmpty() || lat==null||lat.trim().isEmpty()){
			try {
				coachs = coachDao.hotCoach(offset, pageSize, coach_area);
			} catch (Exception e) {
				throw new DataBaseException("查找附近教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}
		//更新用户与教练之间的距离
		for(Coach coach : coachs){
			//获得已过审教练的经纬度
			String lon2 = coach.getField_jingdu();
			String lat2 = coach.getField_weidu();
			String distance = Distance.getDistance(Double.parseDouble(lon), Double.parseDouble(lat),
					Double.parseDouble(lon2), Double.parseDouble(lat2));
			int coach_id = coach.getCoach_id();
			int i = coachDao.updateDistance(coach_id, distance);
			if(i!=1){
				throw new DataBaseException("距离更新失败");
			}
		}
		//教练性别和距离范围均为空
		if((coach_sex==null||coach_sex.trim().isEmpty()) && (range==null||range.trim().isEmpty())){
			System.out.println("性别和距离范围均为空");
			try {
				coachs = coachDao.coachNearby(offset, pageSize, coach_area);
			} catch (Exception e) {
				throw new DataBaseException("查找附近教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(coach_sex==null||coach_sex.trim().isEmpty()){//教练性别为空
			System.out.println("性别为空");
			try {
				coachs = coachDao.coachNearbyRange(offset, pageSize, coach_area, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找附近教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(range==null||range.trim().isEmpty()){//距离范围为空
			System.out.println("距离范围为空");
			try {
				coachs = coachDao.coachNearbySex(offset, pageSize, coach_area, coach_sex);
			} catch (Exception e) {
				throw new DataBaseException("查找附近教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else{//距离，范围，性别三个限制条件
			System.out.println("三个判断条件");
			try {
				coachs = coachDao.coachNearbySexRange(offset, pageSize, coach_area, coach_sex, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找附近教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}
	}

	/**
	 * 获得教练头像，约教数，评价数，训练场地以及教练的个人信息
	 * @param coachs
	 * @return
	 */
	public List<Map<String,Object>> getMoreInfo(List<Coach> coachs){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Coach coach : coachs){
			Map<String,Object> map = new HashMap<String, Object>();
			//获取教练头像路径
			int user_id = coach.getUser_id();
			User user = userDao.findById(user_id);
			String headImg = user.getImgpath();
			//获取约教数
			int coach_id = coach.getCoach_id();
			int teachNum = teachRecordDao.findTeachRecordNumber(coach_id);
			//获取评价数
			int evalNum = teachRecordDao.findTeachEvaluationNumber(coach_id);
			//获得教练所在驾校的训练场地
			String schoolName = coach.getSchool_name();
			Integer schoolId = schoolDao.findSchoolByName(schoolName).getSchool_id();
			List<Map<String,Object>> fields = teachFieldMapper.findBySchoolId(schoolId);
			map.put("headImg", headImg);
			map.put("teachNum", teachNum);
			map.put("evalNum", evalNum);
			map.put("coach", coach);
			map.put("teachFields", fields);
			result.add(map);
		}
		return result;
	}

	public List<Map<String, Object>> listCoachByScore(String lon, String lat, int page, int pageSize, String coach_area,
			String coach_sex, String range) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Coach> coachs = coachDao.listPassedCoach();
		//更新用户与教练之间的距离
		for(Coach coach : coachs){
			//获得已过审教练的经纬度
			String lon2 = coach.getField_jingdu();
			String lat2 = coach.getField_weidu();
			String distance = Distance.getDistance(Double.parseDouble(lon), Double.parseDouble(lat),
					Double.parseDouble(lon2), Double.parseDouble(lat2));
			int coach_id = coach.getCoach_id();
			int i = coachDao.updateDistance(coach_id, distance);
			if(i!=1){
				throw new DataBaseException("距离更新失败");
			}
		}
		//教练性别和距离范围均为空
		if((coach_sex==null||coach_sex.trim().isEmpty()) && (range==null||range.trim().isEmpty())){
			System.out.println("性别和距离范围均为空");
			try {
				coachs = coachDao.listCoachByScore(offset, pageSize, coach_area);
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(coach_sex==null||coach_sex.trim().isEmpty()){//教练性别为空
			System.out.println("性别为空");
			try {
				coachs = coachDao.listCoachByScoreRange(offset, pageSize, coach_area, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(range==null||range.trim().isEmpty()){//距离范围为空
			System.out.println("距离范围为空");
			try {
				coachs = coachDao.listCoachByScoreSex(offset, pageSize, coach_area, coach_sex);
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else{//距离，范围，性别三个限制条件
			System.out.println("三个判断条件");
			try {
				coachs = coachDao.listCoachByScoreSexRange(offset, pageSize, coach_area, coach_sex, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}
	}

	public List<Map<String, Object>> listCoachDefault(String lon, String lat, int page, int pageSize, String coach_area,
			String coach_sex, String range) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Coach> coachs = coachDao.listPassedCoach();
		//更新用户与教练之间的距离
		for(Coach coach : coachs){
			//获得已过审教练的经纬度
			String lon2 = coach.getField_jingdu();
			String lat2 = coach.getField_weidu();
			String distance = Distance.getDistance(Double.parseDouble(lon), Double.parseDouble(lat),
					Double.parseDouble(lon2), Double.parseDouble(lat2));
			int coach_id = coach.getCoach_id();
			int i = coachDao.updateDistance(coach_id, distance);
			if(i != 1){
				throw new DataBaseException("距离更新失败");
			}
		}
		//教练性别和距离范围均为空
		if((coach_sex==null||coach_sex.trim().isEmpty()) && (range==null||range.trim().isEmpty())){
			System.out.println("性别和距离范围均为空");
			try {
				coachs = coachDao.listCoachDefault(offset, pageSize, coach_area);
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(coach_sex==null||coach_sex.trim().isEmpty()){//教练性别为空
			System.out.println("性别为空");
			try {
				coachs = coachDao.listCoachDefaultRange(offset, pageSize, coach_area, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else if(range==null||range.trim().isEmpty()){//距离范围为空
			System.out.println("距离范围为空");
			try {
				coachs = coachDao.listCoachDefaultSex(offset, pageSize, coach_area, coach_sex);
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}else{
			try {
				coachs = coachDao.listCoachDefaultSexRange(offset, pageSize, coach_area, coach_sex, Integer.parseInt(range));
			} catch (Exception e) {
				throw new DataBaseException("查找教练失败");
			}
			result = getMoreInfo(coachs);
			return result;
		}
	}

	public List<Map<String,Object>> listRecomdCoach(String school_name) {
		List<Map<String, Object>> result;
		try {
			result = coachDao.listRecomdCoach(school_name,"1",null,null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public Coach findByUserId(Integer user_id) {
		return coachDao.findByUserId(user_id);
	}

	public List<Map<String, Object>> listBySchool(String school_name, String coach_status, Integer page, Integer pageSize) {
		Integer offset = null;
		if(page != null){
			offset = (page-1)*pageSize;
		}
		List<Map<String, Object>> result;
		try {
			//总条数
			result = coachDao.listRecomdCoach(school_name, coach_status, null, null);
			Map<String,Object> map = new HashMap<String, Object>();
			//添加总条数
			map.put("totalNum", result.size());
			result = coachDao.listRecomdCoach(school_name,coach_status, offset, pageSize);
			result.add(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return result;
	}
	
	
}
