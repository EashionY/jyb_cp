package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.CoachDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.dao.TeachRecordDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Coach;
import cn.jyb.entity.Student;
import cn.jyb.entity.TeachRecord;
import cn.jyb.entity.User;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.EvalException;
import cn.jyb.exception.NoTeachRecordFoundException;
import cn.jyb.exception.TeachRecordException;
import cn.jyb.util.Message;
@Service("teachRecordService")
@Transactional
public class TeachRecordServiceImpl implements TeachRecordService {

	@Resource
	private TeachRecordDao teachRecordDao;
	
	@Resource
	private CoachDao coachDao;
	
	@Resource
	private StudentDao studentDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private CoachService coachService;

	public boolean addTeachRecord(int student_id, int coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips) throws UnsupportedEncodingException{
//		teach_subject = new String(teach_subject.getBytes("ISO-8859-1"),"utf-8");
//		teach_field = new String(teach_field.getBytes("ISO-8859-1"),"utf-8");
//		shuttle_place = new String(shuttle_place.getBytes("ISO-8859-1"),"utf-8");
//		tips = new String(tips.getBytes("ISO-8859-1"),"utf-8");
		TeachRecord record = new TeachRecord();
		record.setStudent_id(student_id);
		record.setCoach_id(coach_id);
		record.setTeach_subject(teach_subject);
		record.setTeach_time(teach_time);
		record.setTeach_field(teach_field);
		record.setShuttle(shuttle);
		record.setShuttle_time(shuttle_time);
		record.setShuttle_place(shuttle_place);
		record.setTips(tips);
		try {
			teachRecordDao.saveTeachRecord(record);
		} catch (Exception e) {
			throw new TeachRecordException("增加约教记录失败");
		}
		return true;
	}
	
	public int findStudyRecordNumber(int student_id){
		int i = teachRecordDao.findStudyRecordNumber(student_id);
		return i;
	}

	public List<Map<String,String>> findStudyRecords(int student_id, int page, int pageSize){
		int offset = (page-1)*pageSize;
		List<TeachRecord> list = teachRecordDao.findStudyRecords(student_id, offset, pageSize);
		if(list.isEmpty()){
			throw new NoTeachRecordFoundException("未找到对应的约教记录");
		}
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(TeachRecord record : list){
			Map<String,String> map = new HashMap<String, String>();
			int coach_id = record.getCoach_id();
			Coach coach = coachDao.findById(coach_id);
			String coach_name = coach.getCoach_name();
			map.put("coach_name", coach_name);
			//约教记录id
			int teach_id = record.getTeach_id();
			map.put("teach_id", ""+teach_id);
			String subject = record.getTeach_subject();
			map.put("subject", subject);
			String teach_time = record.getTeach_time();
			map.put("teach_time", teach_time);
			String teach_field = record.getTeach_field();
			map.put("teach_field", teach_field);
			String teach_state = record.getTeach_state();
			if("0".equals(teach_state)){
				map.put("teach_state", "等待教练确认");
			}else if("1".equals(teach_state)){
				map.put("teach_state", "教练已接单");
			}else if("2".equals(teach_state)){
				map.put("teach_state", "已完成训练");
			}else if("3".equals(teach_state)){
				map.put("teach_state", "已完成评价");
			}else if("-1".equals(teach_state)){
				map.put("teach_state", "教练拒绝了你的预约");
			}else if("-2".equals(teach_state)){
				map.put("teach_state", "已取消");
			}
			result.add(map);
		}
		return result;
	}

	public int findTeachRecordNumber(int coach_id){
		int i = teachRecordDao.findTeachRecordNumber(coach_id);
		return i;
	}

	public List<Map<String,String>> findTeachRecords(int coach_id,int page,int pageSize){
		int offset = (page-1)*pageSize;
		List<TeachRecord> list = teachRecordDao.findTeachRecords(coach_id, offset, pageSize);
		if(list.isEmpty()){
			throw new NoTeachRecordFoundException("未找到对应的约教记录");
		}
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(TeachRecord record : list){
			Map<String,String> map = new HashMap<String, String>();
			int student_id = record.getStudent_id();
			//获得学员端的姓名
			Student student = studentDao.findById(student_id);
			String student_name = student.getStudent_name();
			map.put("student_name", student_name);
			//学员电话
			String student_tel = student.getStudent_tel();
			map.put("student_tel", student_tel);
			//约教记录id
			int teach_id = record.getTeach_id();
			map.put("teach_id", ""+teach_id);
			//预约科目
			String subject = record.getTeach_subject();
			map.put("subject", subject);
			//预约时间
			String teach_time = record.getTeach_time();
			map.put("teach_time", teach_time);
			//练习场地
			String teach_field = record.getTeach_field();
			map.put("teach_field", teach_field);
			//是否需要接送
			String shuttle = record.getShuttle();
			map.put("shuttle", shuttle);
			//接送地址
			String shuttle_place = record.getShuttle_place();
			map.put("shuttle_place", shuttle_place);
			//接送时间
			String shuttle_time = record.getShuttle_time();
			map.put("shuttle_time", shuttle_time);
			//留言
			String tips = record.getTips();
			map.put("tips", tips);
			String teach_state = record.getTeach_state();
			if("0".equals(teach_state)){
				map.put("teach_state", "等待确认");
			}else if("1".equals(teach_state)){
				map.put("teach_state", "已接单");
			}else if("2".equals(teach_state)){
				map.put("teach_state", "已完成训练");
			}else if("3".equals(teach_state)){
				map.put("teach_state", "学员已完成评价");
			}else if("-1".equals(teach_state)){
				map.put("teach_state", "已拒绝");
			}else if("-2".equals(teach_state)){
				map.put("teach_state", "学员已取消预约");
			}
			result.add(map);
		}
		return result;
	}

	public boolean addEvaluation(int teach_id, String evaluation, int evaltype, int evalstar) throws UnsupportedEncodingException{
//		evaluation = new String(evaluation.getBytes("ISO-8859-1"),"utf-8");
//		System.out.println("评价内容："+evaluation);
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if(record==null){
			throw new NoTeachRecordFoundException("未找到对应的约教记录");
		}
		//取得约教状态，完成训练state="2"
		String state = record.getTeach_state();
		if(state.equals("0")||state.equals("1")){
			throw new EvalException("未完成训练，不可评价");
		}else if(state.equals("3")){
			throw new EvalException("已完成评价，请勿重复评价");
		}
		if(evalstar<1){
			throw new EvalException("评价至少给一星");
		}
		try {
			teachRecordDao.addEvaluation(teach_id, evaluation, evaltype, evalstar);
		} catch (Exception e) {
			throw new EvalException("添加评价失败");
		}
		int coach_id = record.getCoach_id();
		//获得教练的综合评分
		double coach_score = teachRecordDao.getCoachScore(coach_id);
		//更新教练评分
		coachDao.updateCoachScore(coach_score,coach_id);
		return true;
	}

	public Map<String,Integer> findStudyEvaluationNumber(int student_id){
		Map<String,Integer> result = new HashMap<String, Integer>();
		try {
			int all = teachRecordDao.findStudyEvaluationNumber(student_id);
			result.put("all", all);
			int good = teachRecordDao.findStudyEvaluationNumByType(student_id, 1);
			result.put("good", good);
			int medium = teachRecordDao.findStudyEvaluationNumByType(student_id, 2);
			result.put("medium", medium);
			int worse = teachRecordDao.findStudyEvaluationNumByType(student_id, 3);
			result.put("worse", worse);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return result;
	}
	
	public List<Map<String,String>> findStudyEvaluation(int student_id,int page,int pageSize){
		int offset = (page-1)*pageSize;
		List<TeachRecord> list = teachRecordDao.findStudyEvaluation(student_id, offset, pageSize);
		if(list==null){
			throw new EvalException("未找到相关数据");
		}
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(TeachRecord record : list){
			Map<String,String> map = new HashMap<String, String>();
			Student student = studentDao.findById(student_id);
			int user_id = student.getUser_id();
			User user = userDao.findById(user_id);
			String nickname = user.getNickname();
			String imgpath = user.getImgpath();
			String evaluation = record.getEvaluation();
			int evalstar = record.getEvalstar();
			String evaltime = df.format(record.getEvaltime());
			int teach_id = record.getTeach_id();
			map.put("nickname", nickname);
			map.put("imgpath", imgpath);
			map.put("evalstar", ""+evalstar);
			map.put("evaluation", evaluation);
			map.put("evaltime", evaltime);
			map.put("teach_id", ""+teach_id);
			result.add(map);
		}
		return result;
	}

	public Map<String,Integer> findTeachEvaluationNumber(int coach_id){
		Map<String,Integer> result = new HashMap<String,Integer>();
		try {
			int all = teachRecordDao.findTeachEvaluationNumber(coach_id);
			result.put("all", all);
			//好评数量
			int good = teachRecordDao.findTeachEvaluationNumByType(coach_id, 1);
			result.put("good", good);
			int medium = teachRecordDao.findTeachEvaluationNumByType(coach_id, 2);
			result.put("medium", medium);
			int worse = teachRecordDao.findTeachEvaluationNumByType(coach_id, 3);
			result.put("worse", worse);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return result;
	}

	public boolean acceptTeach(int teach_id){
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if(record==null){
			throw new TeachRecordException("无对应的约教请求");
		}
		String state = record.getTeach_state();
		if("-2".equals(state)){
			throw new TeachRecordException("约教请求已取消");
		}else if("1".equals(state)){
			throw new TeachRecordException("已接受的请求，请勿重复操作");
		}
		try {
			teachRecordDao.acceptTeach(teach_id);
		} catch (Exception e) {
			throw new DataBaseException("接受约教请求失败");
		}
		//获取学员电话
		Student student = studentDao.findById(record.getStudent_id());
		User user = userDao.findById(student.getUser_id());
		String phone = user.getPhone();
		//获取教练姓名
		Coach coach = coachDao.findById(record.getCoach_id());
		String name = coach.getCoach_name();
		//给学员发送通知短信
		String templateCode = "SMS_71335566";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public boolean finishTeach(int teach_id){
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if(record==null){
			throw new TeachRecordException("无对应的约教请求");
		}
		String state = record.getTeach_state();
		if(state.equals("2")){
			throw new TeachRecordException("已完成的训练，请勿重复操作");
		}
		try {
			teachRecordDao.finishTeach(teach_id);
		} catch (Exception e) {
			throw new DataBaseException("完成训练失败");
		}
		return true;
	}

	public boolean refuseTeach(int teach_id){
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if(record==null){
			throw new EvalException("无对应的约教请求");
		}
		String state = record.getTeach_state();
		if("-2".equals(state)){
			throw new TeachRecordException("学员已取消该请求");
		}else if("-1".equals(state)){
			throw new TeachRecordException("已拒绝的请求，请勿重复操作");
		}else if("1".equals(state)){
			throw new TeachRecordException("已接受该请求，无法拒绝");
		}
		try {
			teachRecordDao.refuseTeach(teach_id);
		} catch (Exception e) {
			throw new DataBaseException("拒绝请求失败");
		}
		//获取学员电话
		Student student = studentDao.findById(record.getStudent_id());
		User user = userDao.findById(student.getUser_id());
		String phone = user.getPhone();
		//获取教练姓名
		Coach coach = coachDao.findById(record.getCoach_id());
		String name = coach.getCoach_name();
		//给学员发送通知短信
		String templateCode = "SMS_71335569";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public boolean cancelTeach(int teach_id){
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if(record==null){
			throw new TeachRecordException("无对应的约教请求");
		}
		String state = record.getTeach_state();
		//约教状态(0等待确认，1已确认，2已完成训练，3已完成评价，-1教练拒绝订单，-2学员取消订单)
		if("1".equals(state)){
			throw new TeachRecordException("教练已确认该请求，无法取消");
		}else if("-1".equals(state)){
			throw new TeachRecordException("教练已拒绝该请求");
		}else if("-2".equals(state)){
			throw new TeachRecordException("已取消的请求，请勿重复操作");
		}
		try {
			teachRecordDao.cancelTeach(teach_id);
		} catch (Exception e) {
			throw new DataBaseException("取消约教请求失败");
		}
		//获取教练的手机号
		int coach_id = record.getCoach_id();
		Coach coach = coachDao.findById(coach_id);
		int user_id = coach.getUser_id();
		User user = userDao.findById(user_id);
		String phone = user.getPhone();
		//获取学员的姓名
		int student_id = record.getStudent_id();
		Student student = studentDao.findById(student_id);
		String name = student.getStudent_name();
		//给教练发送取消短信
		String templateCode = "SMS_71330512";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public List<Map<String,String>> findTeachEvaluations(int coach_id, int evaltype, int page, int pageSize){
		int offset = (page-1)*pageSize;
		List<TeachRecord> list;
		if(evaltype == 0){
			System.out.println(true);
			//查看全部评价
			list = teachRecordDao.findTeachEvaluation(coach_id, offset, pageSize);
		}else{
			//查看对应类型的评价
			list = teachRecordDao.findTeachEvaluations(coach_id, evaltype, offset, pageSize);
		}
		System.out.println("list:"+list);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(TeachRecord record : list){
			Map<String,String> map = new HashMap<String, String>();
			int student_id = record.getStudent_id();
			Student student = studentDao.findById(student_id);
			int user_id = student.getUser_id();
			User user = userDao.findById(user_id);
			String nickname = user.getNickname();
			String imgpath = user.getImgpath();
			String evaluation = record.getEvaluation();
			int evalstar = record.getEvalstar();
			String evaltime = df.format(record.getEvaltime());
			map.put("nickname", nickname);
			map.put("imgpath", imgpath);
			map.put("evalstar", ""+evalstar);
			map.put("evaluation", evaluation);
			map.put("evaltime", evaltime);
			result.add(map);
		}
		return result;
	}

	public List<Map<String, Object>> listAllTeachRecord(String teach_subject, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//单独的一个map，用来保存记录条数
		Map<String,Object> map1 = new HashMap<String, Object>();
		int recordNum = teachRecordDao.getTeachRecordNum(teach_subject);
		map1.put("recordNum", recordNum);
		result.add(map1);
		List<TeachRecord> records = teachRecordDao.listAllTeachRecord(teach_subject, offset, pageSize);
		for(TeachRecord record : records){
			Map<String,Object> map2 = new HashMap<String, Object>();
			try {
				int student_id = record.getStudent_id();
				String student_name = studentDao.findById(student_id).getStudent_name();
				map2.put("student_name", student_name);
				int coach_id = record.getCoach_id();
				String coach_name = coachDao.findById(coach_id).getCoach_name();
				map2.put("coach_name", coach_name);
			} catch (Exception e) {
				throw new DataBaseException("查询失败");
			}
			map2.put("teachRecord", record);
			result.add(map2);
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByCoachName(String coach_name) {
		//模糊查询(字符串处理)
		coach_name = "%"+coach_name+"%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByCoachName(coach_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByStudentName(String student_name) {
		//模糊查询(字符串处理)
		student_name = "%"+student_name+"%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByStudentName(student_name);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByTeachTime(String teach_time) {
		//模糊查询(字符串处理)
		teach_time = "%"+teach_time+"%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByTeachTime(teach_time);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordBySubject(String teach_subject) {
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordBySubject(teach_subject);
		} catch (Exception e) {
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public Map<String, Object> listSchoolEval(String school_name, int evaltype, int page, int pageSize) {
		List<Map<String,String>> evals = new ArrayList<Map<String,String>>();
		List<Map<String, Object>> list = coachService.listRecomdCoach(school_name);
		//全部评价条数
		int all = 0;
		//好评条数
		int good = 0;
		//中评条数
		int medium = 0;
		//差评条数
		int worse = 0;
		try {
			for(Map<String,Object> coach : list){
				int coach_id = (Integer) coach.get("coach_id");
				//将每个教练获得的评价，添加到驾校评价里面
				evals.addAll(findTeachEvaluations(coach_id, evaltype, page, pageSize));
				all += teachRecordDao.findTeachEvaluationNumber(coach_id);
				good += teachRecordDao.findTeachEvaluationNumByType(coach_id, 1);
				medium += teachRecordDao.findTeachEvaluationNumByType(coach_id, 2);
				worse += teachRecordDao.findTeachEvaluationNumByType(coach_id, 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("all", all);
		map.put("good", good);
		map.put("medium", medium);
		map.put("worse", worse);
		map.put("evalList", evals);
		return map;
	}
	
	
}
