package cn.jyb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jyb.dao.CoachDao;
import cn.jyb.dao.CoachScheduleMapper;
import cn.jyb.dao.SchoolDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.entity.Coach;
import cn.jyb.entity.CoachSchedule;
import cn.jyb.entity.Student;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.DataErrorException;
import cn.jyb.exception.StudentException;
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	private ObjectMapper objectMapper;
	
	@Resource
	private CoachScheduleMapper coachScheduleMapper;
	
	@Resource
	private StudentDao studentDao;
	
	@Resource
	private SchoolDao schoolDao;
	
	@Resource
	private CoachDao coachDao;
	
	public boolean setCoachSchedule(String data) {
//		System.out.println("接收数据:"+data);
		objectMapper = new ObjectMapper();
		List<Map<String, String>> params;
		try {
			params = objectMapper.readValue(data, List.class);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataErrorException("数据解析异常");
		}
		System.out.println(params);
		for(Map<String,String> param : params){
			Integer coachId = Integer.parseInt(param.get("coach_id"));
			String appointTime = param.get("appoint_time");
			//教练日程表的各个时间有两个状态("-1"不可 预约，"0"可以预约)
			String time1 = param.get("time1");
			String time2 = param.get("time2");
			String time3 = param.get("time3");
			String time4 = param.get("time4");
			String time5 = param.get("time5");
			String time6 = param.get("time6");
			String time7 = param.get("time7");
			String time8 = param.get("time8");
			String time9 = param.get("time9");
			String time10 = param.get("time10");
			CoachSchedule coachSchedule = coachScheduleMapper.findSchedule(appointTime,coachId);
			if(coachSchedule == null){//创建日程
				System.out.println("新增日程");
				coachSchedule = new CoachSchedule();
				coachSchedule.setCoachId(coachId);
				coachSchedule.setAppointTime(appointTime);
				coachSchedule.setTime1(time1);
				coachSchedule.setTime2(time2);
				coachSchedule.setTime3(time3);
				coachSchedule.setTime4(time4);
				coachSchedule.setTime5(time5);
				coachSchedule.setTime6(time6);
				coachSchedule.setTime7(time7);
				coachSchedule.setTime8(time8);
				coachSchedule.setTime9(time9);
				coachSchedule.setTime10(time10); 
				try {
					coachScheduleMapper.insert(coachSchedule);
				} catch (Exception e) {
					e.printStackTrace();
					throw new DataBaseException("数据库异常");
				}
			}else{//修改日程
				coachSchedule.setTime1(time1);
				coachSchedule.setTime2(time2);
				coachSchedule.setTime3(time3);
				coachSchedule.setTime4(time4);
				coachSchedule.setTime5(time5);
				coachSchedule.setTime6(time6);
				coachSchedule.setTime7(time7);
				coachSchedule.setTime8(time8);
				coachSchedule.setTime9(time9);
				coachSchedule.setTime10(time10);
				try {
					coachScheduleMapper.updateByPrimaryKeySelective(coachSchedule);
				} catch (Exception e) {
					e.printStackTrace();
					throw new DataBaseException("数据库异常");
				}
			}
		}
		return true;
	}

	public List<Map<String, String>> listCoachSchedule(String data) {
		//返回的结果集
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		//存储日期的集合
		List<String> dates = new ArrayList<String>();
		//将Json字符串解析map
		objectMapper = new ObjectMapper();
		int coach_id;
		int student_id;
		try {
			//解析传入的data数据
			Map<String,String> params = objectMapper.readValue(data, Map.class);
			System.out.println(params);
			//教练id
			coach_id = Integer.parseInt(params.get("coach_id"));
			//学员id
			student_id = Integer.parseInt(params.get("student_id"));
			//日期1
			String date1 = params.get("date1");
			dates.add(date1);
			//日期2
			String date2 = params.get("date2");
			dates.add(date2);
			//日期3
			String date3 = params.get("date3");
			dates.add(date3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataErrorException("数据解析异常");
		}
		System.out.println(dates);
		//通过学员id找到对应的学员
		Student student = studentDao.findById(student_id);
		if(student == null){
			throw new StudentException("无效的学员id");
		}else{
			//学员的付款状态
			Integer pay_status = student.getPay_status();
			if(pay_status.equals(0)){
				//未付款成功
				throw new StudentException("请先完成驾校报名，再预约教练");
			}else{
				//驾校的id
				Integer school_id = student.getSchool_id();
				Map<String,Object> school = schoolDao.schoolDetail(school_id);
				//获得驾校名字
				String school_name = (String) school.get("school_name");
				//获得该教练所属驾校名字
				Coach coach = coachDao.findById(coach_id);
				String school_name2 = coach.getSchool_name();
				//报名的驾校和预约教练所属驾校不对应
				if(!school_name.equals(school_name2)){
					throw new StudentException("请预约对应报名驾校的教练");
				}
			}
		}
		for(String appoint_time : dates){
			//用于存储教练每天的日程安排
			Map<String,String> map = new HashMap<String,String>();
			map.put("coach_id", ""+coach_id);
			//预约日期
			map.put("appoint_time", appoint_time);
			System.out.println(appoint_time);
			//找到该教练当天的日程设置情况
			CoachSchedule coachSchedule = coachScheduleMapper.findSchedule(appoint_time, coach_id);
			if(coachSchedule==null){
				//教练未设置当天的日程,所有时段设置为-1(不可约)
				map.put("time1","-1");
				map.put("time2","-1");
				map.put("time3","-1");
				map.put("time4","-1");
				map.put("time5","-1");
				map.put("time6","-1");
				map.put("time7","-1");
				map.put("time8","-1");
				map.put("time9","-1");
				map.put("time10","-1");
			}else{
				map.put("time1",coachSchedule.getTime1());
				map.put("time2",coachSchedule.getTime2());
				map.put("time3",coachSchedule.getTime3());
				map.put("time4",coachSchedule.getTime4());
				map.put("time5",coachSchedule.getTime5());
				map.put("time6",coachSchedule.getTime6());
				map.put("time7",coachSchedule.getTime7());
				map.put("time8",coachSchedule.getTime8());
				map.put("time9",coachSchedule.getTime9());
				map.put("time10",coachSchedule.getTime10());
			}
			list.add(map);
		}
		System.out.println(list);
		return list;
	}

	public List<Map<String, Object>> listAllCoachSchedule(int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleMapper.listAllCoachSchedule(offset, pageSize);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		int num = coachScheduleMapper.getCoachScheduleNum();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("num", num);
		result.add(map);
		return result;
	}

	public List<Map<String, Object>> findCoachScheduleByName(String coach_name) {
		coach_name = "%"+coach_name+"%";
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleMapper.findCoachScheduleByName(coach_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findCoachScheduleByDate(String appoint_time) {
		appoint_time = "%"+appoint_time+"%";
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleMapper.findCoachScheduleByDate(appoint_time);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	@Override
	public List<Map<String, String>> checkCoachSchedule(String data) {
		//返回的结果集
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		//存储日期的集合
		List<String> dates = new ArrayList<String>();
		//将Json字符串解析map
		objectMapper = new ObjectMapper();
		int coach_id;
		try {
			//解析传入的data数据
			Map<String,String> params = objectMapper.readValue(data, Map.class);
			System.out.println(params);
			//教练id
			coach_id = Integer.parseInt(params.get("coach_id"));
			//日期1
			dates.add(params.get("date1"));
			//日期2
			dates.add(params.get("date2"));
			//日期3
			dates.add(params.get("date3"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataErrorException("数据解析异常");
		}
		for(String appoint_time : dates){
			//用于存储教练每天的日程安排
			Map<String,String> map = new HashMap<String,String>();
			map.put("coach_id", ""+coach_id);
			//预约日期
			map.put("appoint_time", appoint_time);
			//找到该教练当天的日程设置情况
			CoachSchedule coachSchedule = coachScheduleMapper.findSchedule(appoint_time, coach_id);
			if(coachSchedule == null){
				//教练未设置当天的日程,所有时段设置为-1(不可约)
				map.put("time1","-1");
				map.put("time2","-1");
				map.put("time3","-1");
				map.put("time4","-1");
				map.put("time5","-1");
				map.put("time6","-1");
				map.put("time7","-1");
				map.put("time8","-1");
				map.put("time9","-1");
				map.put("time10","-1");
			}else{
				map.put("time1",coachSchedule.getTime1());
				map.put("time2",coachSchedule.getTime2());
				map.put("time3",coachSchedule.getTime3());
				map.put("time4",coachSchedule.getTime4());
				map.put("time5",coachSchedule.getTime5());
				map.put("time6",coachSchedule.getTime6());
				map.put("time7",coachSchedule.getTime7());
				map.put("time8",coachSchedule.getTime8());
				map.put("time9",coachSchedule.getTime9());
				map.put("time10",coachSchedule.getTime10());
			}
			result.add(map);
		}
		System.out.println(result);
		return result;
	}

}
