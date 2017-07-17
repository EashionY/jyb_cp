package cn.jyb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jyb.dao.CoachScheduleDao;
import cn.jyb.dao.StudentScheduleDao;
import cn.jyb.entity.CoachSchedule;
import cn.jyb.entity.StudentSchedule;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.DataErrorException;
import cn.jyb.exception.ScheduleException;
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	private ObjectMapper objectMapper;
	
	@Resource
	private CoachScheduleDao coachScheduleDao;
	
	@Resource
	private StudentScheduleDao studentScheduleDao;
	
	@SuppressWarnings("unchecked")
	public boolean setCoachSchedule(String data) {
		System.out.println("接收数据:"+data);
		int i = 0;
		objectMapper = new ObjectMapper();
		List<Map<String, String>> params;
		try {
			params = objectMapper.readValue(data, List.class);
			System.out.println(11111);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataErrorException("数据解析异常");
		}
		System.out.println(params);
		for(Map<String,String> param : params){
			int coach_id = Integer.parseInt(param.get("coach_id"));
			String appoint_time = param.get("appoint_time");
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
			CoachSchedule coachSchedule = coachScheduleDao.findSchedule(appoint_time,coach_id);
			CoachSchedule coachSchedule1 = new CoachSchedule();
			coachSchedule1.setCoach_id(coach_id);
			coachSchedule1.setAppoint_time(appoint_time);
			coachSchedule1.setTime1(time1);
			coachSchedule1.setTime2(time2);
			coachSchedule1.setTime3(time3);
			coachSchedule1.setTime4(time4);
			coachSchedule1.setTime5(time5);
			coachSchedule1.setTime6(time6);
			coachSchedule1.setTime7(time7);
			coachSchedule1.setTime8(time8);
			coachSchedule1.setTime9(time9);
			coachSchedule1.setTime10(time10);
			if(coachSchedule==null){
				try {
					i = coachScheduleDao.saveCoachSchedule(coachSchedule1);
				} catch (Exception e) {
					throw new DataBaseException("数据库异常");
				}
			}else{
				try {
					i = coachScheduleDao.updateCoachSchedule(coachSchedule1);
				} catch (Exception e) {
					throw new DataBaseException("数据库异常");
				}
			}
			if(i != 1){
				throw new ScheduleException("设置日程失败");
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> listCoachSchedule(String data) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> dates = new ArrayList<String>();
		objectMapper = new ObjectMapper();
		//将Json字符串解析map
		int coach_id;
		int student_id;
		String subtype;
		try {
			Map<String,String> params = objectMapper.readValue(data, Map.class);
			System.out.println(params);
			coach_id = Integer.parseInt(params.get("coach_id"));
			student_id = Integer.parseInt(params.get("student_id"));
			subtype = params.get("subtype");
			String date1 = params.get("date1");
			dates.add(date1);
			String date2 = params.get("date2");
			dates.add(date2);
			String date3 = params.get("date3");
			dates.add(date3);
		} catch (Exception e) {
			throw new DataErrorException("传入数据有误");
		}
		for(String appoint_time : dates){
			Map<String,String> map = new HashMap<String,String>();
			map.put("coach_id", ""+coach_id);
			map.put("appoint_time", appoint_time);
			CoachSchedule coachSchedule = coachScheduleDao.findSchedule(appoint_time, coach_id);
			StudentSchedule studentSchedule = studentScheduleDao.findSchedule(appoint_time,coach_id,subtype,student_id);
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
			}else if(studentSchedule==null){
				//该学员当天还没有预约该教练
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
			}else{
				//该学生已经预约的时段，状态用"1"表示;若未预约，则保存原状态不变
				map.put("time1","1".equals(studentSchedule.getTime1())?"1":coachSchedule.getTime1());
				map.put("time2","1".equals(studentSchedule.getTime2())?"1":coachSchedule.getTime2());
				map.put("time3","1".equals(studentSchedule.getTime3())?"1":coachSchedule.getTime3());
				map.put("time4","1".equals(studentSchedule.getTime4())?"1":coachSchedule.getTime4());
				map.put("time5","1".equals(studentSchedule.getTime5())?"1":coachSchedule.getTime5());
				map.put("time6","1".equals(studentSchedule.getTime6())?"1":coachSchedule.getTime6());
				map.put("time7","1".equals(studentSchedule.getTime7())?"1":coachSchedule.getTime7());
				map.put("time8","1".equals(studentSchedule.getTime8())?"1":coachSchedule.getTime8());
				map.put("time9","1".equals(studentSchedule.getTime9())?"1":coachSchedule.getTime9());
				map.put("time10","1".equals(studentSchedule.getTime10())?"1":coachSchedule.getTime10());
			}
			list.add(map);
		}
		System.out.println(list);
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean saveStudentSchedule(String data) {
		int i = 0;
		objectMapper = new ObjectMapper();
		List<Map<String, String>> params;
		try {
			params = objectMapper.readValue(data, List.class);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataErrorException("数据解析异常");
		}
		for(Map<String,String> map : params){
			int student_id = Integer.parseInt(map.get("student_id"));
			int coach_id = Integer.parseInt(map.get("coach_id"));
			String appoint_time = map.get("appoint_time");
			String time1 = map.get("time1");
			String time2 = map.get("time2");
			String time3 = map.get("time3");
			String time4 = map.get("time4");
			String time5 = map.get("time5");
			String time6 = map.get("time6");
			String time7 = map.get("time7");
			String time8 = map.get("time8");
			String time9 = map.get("time9");
			String time10 = map.get("time10");
			String subtype = map.get("subtype");
			StudentSchedule studentSchedule = studentScheduleDao.findSchedule(appoint_time, coach_id, subtype, student_id);
			StudentSchedule studentSchedule2 = new StudentSchedule();
			studentSchedule2.setStudent_id(student_id);
			studentSchedule2.setCoach_id(coach_id);
			studentSchedule2.setAppoint_time(appoint_time);
			studentSchedule2.setTime1(time1);
			studentSchedule2.setTime2(time2);
			studentSchedule2.setTime3(time3);
			studentSchedule2.setTime4(time4);
			studentSchedule2.setTime5(time5);
			studentSchedule2.setTime6(time6);
			studentSchedule2.setTime7(time7);
			studentSchedule2.setTime8(time8);
			studentSchedule2.setTime9(time9);
			studentSchedule2.setTime10(time10);
			if(studentSchedule==null){
				try {
					i = studentScheduleDao.saveStudentSchedule(studentSchedule2);
				} catch (Exception e) {
					throw new DataBaseException("数据库异常");
				}
			}else{
				try {
					i = studentScheduleDao.updateStudentSchedule(studentSchedule2);
				} catch (Exception e) {
					throw new DataBaseException("数据库异常");
				}
			}
			if(i != 1){
				throw new ScheduleException("保存日程失败");
			}
		}
		return true;
	}

	public List<Map<String, Object>> listAllCoachSchedule(int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleDao.listAllCoachSchedule(offset, pageSize);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		int num = coachScheduleDao.getCoachScheduleNum();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("num", num);
		result.add(map);
		return result;
	}

	public List<Map<String, Object>> findCoachScheduleByName(String coach_name) {
		coach_name = "%"+coach_name+"%";
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleDao.findCoachScheduleByName(coach_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findCoachScheduleByDate(String appoint_time) {
		appoint_time = "%"+appoint_time+"%";
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = coachScheduleDao.findCoachScheduleByDate(appoint_time);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

}
