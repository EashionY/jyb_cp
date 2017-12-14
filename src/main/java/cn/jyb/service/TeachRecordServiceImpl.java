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
import cn.jyb.dao.CoachScheduleMapper;
import cn.jyb.dao.OrdersDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.dao.TeachRecordDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Coach;
import cn.jyb.entity.CoachSchedule;
import cn.jyb.entity.Student;
import cn.jyb.entity.TeachRecord;
import cn.jyb.entity.User;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.EvalException;
import cn.jyb.exception.NoTeachRecordFoundException;
import cn.jyb.exception.TeachRecordException;
import cn.jyb.util.DateUtil;
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

	@Resource
	private CoachScheduleMapper coachScheduleMapper;
	
	@Resource
	private OrdersDao ordersDao;

	public TeachRecord addTeachRecord(int student_id, int coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips) {
		// teach_subject = new
		// String(teach_subject.getBytes("ISO-8859-1"),"utf-8");
		// teach_field = new String(teach_field.getBytes("ISO-8859-1"),"utf-8");
		// shuttle_place = new
		// String(shuttle_place.getBytes("ISO-8859-1"),"utf-8");
		// tips = new String(tips.getBytes("ISO-8859-1"),"utf-8");
		TeachRecord record = new TeachRecord();
		String teach_id = DateUtil.getOrderNum() + DateUtil.getThree();
		record.setTeach_id(teach_id);
		record.setStudent_id(student_id);
		record.setCoach_id(coach_id);
		record.setTeach_subject(teach_subject);
		record.setTeach_time(teach_time);
		record.setTeach_field(teach_field);
		record.setShuttle(shuttle);
		record.setShuttle_time(shuttle_time);
		record.setShuttle_place(shuttle_place);
		record.setTips(tips);
		//֧��״̬Ĭ��Ϊ0
		record.setPayStatus(0);
		try {
			teachRecordDao.saveTeachRecord(record);
		} catch (Exception e) {
			throw new TeachRecordException("����Լ�̼�¼ʧ��");
		}
		// ��������Ӧ��Լ��ʱ��״̬��Ϊ1
		updateCoachSchedule(teach_time, coach_id, "1");
		return teachRecordDao.findByTeachId(teach_id);
	}

	public int findStudyRecordNumber(int student_id) {
		return teachRecordDao.findStudyRecordNumber(student_id);
	}

	public List<Map<String, Object>> findStudyRecords(int student_id, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<TeachRecord> list = teachRecordDao.findStudyRecords(student_id, offset, pageSize);
		if (list.isEmpty()) {
			throw new NoTeachRecordFoundException("δ�ҵ���Ӧ��Լ�̼�¼");
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (TeachRecord record : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			int coach_id = record.getCoach_id();
			Coach coach = coachDao.findById(coach_id);
			String coach_name = coach.getCoach_name();//��������
			map.put("coach_name", coach_name);
			String teach_id = record.getTeach_id();//Լ�̼�¼id
			map.put("teach_id", teach_id);
			String subject = record.getTeach_subject();//Լ�̿�Ŀ
			map.put("subject", subject);
			String teach_time = record.getTeach_time();//Լ��ʱ��
			map.put("teach_time", teach_time);
			String teach_field = record.getTeach_field();//ѵ������
			map.put("teach_field", teach_field);
			String teach_state = record.getTeach_state();//Լ��״̬
			map.put("teach_state", teach_state);
			map.put("payStatus", record.getPayStatus());//Լ��֧��״̬
			map.put("creatime", record.getCreatime());//Լ�̼�¼����ʱ��
			result.add(map);
		}
		return result;
	}

	public int findTeachRecordNumber(int coach_id) {
		return teachRecordDao.findTeachRecordNumber(coach_id);
	}

	public List<Map<String, Object>> findTeachRecords(int coach_id, String teach_state, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<TeachRecord> list = teachRecordDao.findTeachRecords(coach_id, teach_state, offset, pageSize);
		if (list.isEmpty()) {
			throw new NoTeachRecordFoundException("δ�ҵ���Ӧ��Լ�̼�¼");
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (TeachRecord record : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			int student_id = record.getStudent_id();
			// ���ѧԱ������
			Student student = studentDao.findById(student_id);
			String student_name = student.getStudent_name();
			map.put("student_name", student_name);
			// ѧԱ�绰
			String student_tel = student.getStudent_tel();
			map.put("student_tel", student_tel);
			// Լ�̼�¼id
			String teach_id = record.getTeach_id();
			map.put("teach_id", teach_id);
			// ԤԼ��Ŀ
			String subject = record.getTeach_subject();
			map.put("subject", subject);
			// ԤԼʱ��
			String teach_time = record.getTeach_time();
			map.put("teach_time", teach_time);
			// ��ϰ����
			String teach_field = record.getTeach_field();
			map.put("teach_field", teach_field);
			// �Ƿ���Ҫ����
			String shuttle = record.getShuttle();
			map.put("shuttle", shuttle);
			// ���͵�ַ
			String shuttle_place = record.getShuttle_place();
			map.put("shuttle_place", shuttle_place);
			// ����ʱ��
			String shuttle_time = record.getShuttle_time();
			map.put("shuttle_time", shuttle_time);
			// ����
			String tips = record.getTips();
			map.put("tips", tips);
			// Լ�̼�¼״̬
			teach_state = record.getTeach_state();
			map.put("teach_state", teach_state);
			//��Լ�̶���֧���ɹ���ʱ����Ϊ�����ӵ�����ʱ����ʼʱ��
			map.put("creatime", ordersDao.findByNo(teach_id).getFinishtime());
			map.put("payStatus", record.getPayStatus());
			result.add(map);
		}
		return result;
	}

	public List<Map<String, Object>> listDealedRecord(int coach_id, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<TeachRecord> list = teachRecordDao.listDealedRecord(coach_id, offset, pageSize);
		if (list.isEmpty()) {
			throw new NoTeachRecordFoundException("δ�ҵ���Ӧ��Լ�̼�¼");
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (TeachRecord record : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			int student_id = record.getStudent_id();
			// ���ѧԱ������
			Student student = studentDao.findById(student_id);
			map.put("student_name", student.getStudent_name());
			// ѧԱ�绰
			map.put("student_tel", student.getStudent_tel());
			// Լ�̼�¼id
			String teach_id = record.getTeach_id();
			map.put("teach_id", teach_id);
			// ԤԼ��Ŀ
			map.put("subject", record.getTeach_subject());
			// ԤԼʱ��
			map.put("teach_time", record.getTeach_time());
			// ��ϰ����
			map.put("teach_field", record.getTeach_field());
			// �Ƿ���Ҫ����
			map.put("shuttle", record.getShuttle());
			// ���͵�ַ
			map.put("shuttle_place", record.getShuttle_place());
			// ����ʱ��
			map.put("shuttle_time", record.getShuttle_time());
			// ����
			map.put("tips", record.getTips());
			// Լ�̼�¼״̬
			map.put("teach_state", record.getTeach_state());
			//��Լ�̶���֧���ɹ���ʱ����Ϊ�����ӵ�����ʱ����ʼʱ��
			map.put("creatime", ordersDao.findByNo(teach_id).getFinishtime());
			map.put("payStatus", record.getPayStatus());
			result.add(map);
		}
		return result;
	}

	public boolean addEvaluation(String teach_id, String evaluation, int evaltype, int evalstar)
			throws UnsupportedEncodingException {
		// evaluation = new String(evaluation.getBytes("ISO-8859-1"),"utf-8");
		// System.out.println("�������ݣ�"+evaluation);
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if (record == null) {
			throw new NoTeachRecordFoundException("δ�ҵ���Ӧ��Լ�̼�¼");
		}
		// ȡ��Լ��״̬�����ѵ��state="2"
		String state = record.getTeach_state();
		if (state.equals("0") || state.equals("1")) {
			throw new EvalException("δ���ѵ������������");
		} else if (state.equals("3")) {
			throw new EvalException("��������ۣ������ظ�����");
		}
		if (evalstar < 1) {
			throw new EvalException("�������ٸ�һ��");
		}
		try {
			teachRecordDao.addEvaluation(teach_id, evaluation, evaltype, evalstar);
		} catch (Exception e) {
			throw new EvalException("�������ʧ��");
		}
		int coach_id = record.getCoach_id();
		// ��ý������ۺ�����
		double coach_score = teachRecordDao.getCoachScore(coach_id);
		// ���½�������
		coachDao.updateCoachScore(coach_score, coach_id);
		return true;
	}

	public Map<String, Integer> findStudyEvaluationNumber(int student_id) {
		Map<String, Integer> result = new HashMap<String, Integer>();
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
			throw new DataBaseException("���ݿ�����ʧ��");
		}
		return result;
	}

	public List<Map<String, String>> findStudyEvaluation(int student_id, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<TeachRecord> list = teachRecordDao.findStudyEvaluation(student_id, offset, pageSize);
		if (list == null) {
			throw new EvalException("δ�ҵ��������");
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (TeachRecord record : list) {
			Map<String, String> map = new HashMap<String, String>();
			Student student = studentDao.findById(student_id);
			int user_id = student.getUser_id();
			User user = userDao.findById(user_id);
			String nickname = user.getNickname();
			String imgpath = user.getImgpath();
			String evaluation = record.getEvaluation();
			int evalstar = record.getEvalstar();
			String evaltime = df.format(record.getEvaltime());
			String teach_id = record.getTeach_id();
			map.put("nickname", nickname);
			map.put("imgpath", imgpath);
			map.put("evalstar", "" + evalstar);
			map.put("evaluation", evaluation);
			map.put("evaltime", evaltime);
			map.put("teach_id", teach_id);
			result.add(map);
		}
		return result;
	}

	public Map<String, Integer> findTeachEvaluationNumber(int coach_id) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		try {
			int all = teachRecordDao.findTeachEvaluationNumber(coach_id);
			result.put("all", all);
			// ��������
			int good = teachRecordDao.findTeachEvaluationNumByType(coach_id, 1);
			result.put("good", good);
			int medium = teachRecordDao.findTeachEvaluationNumByType(coach_id, 2);
			result.put("medium", medium);
			int worse = teachRecordDao.findTeachEvaluationNumByType(coach_id, 3);
			result.put("worse", worse);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ�����ʧ��");
		}
		return result;
	}

	public boolean acceptTeach(String teach_id) {
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if (record == null) {
			throw new TeachRecordException("�޶�Ӧ��Լ������");
		}
		String state = record.getTeach_state();
		if ("-2".equals(state)) {
			throw new TeachRecordException("Լ��������ȡ��");
		} else if ("1".equals(state)) {
			throw new TeachRecordException("�ѽ��ܵ����������ظ�����");
		}
		try {
			teachRecordDao.updateTeachState(teach_id, "1");
		} catch (Exception e) {
			throw new DataBaseException("����Լ������ʧ��");
		}
		// ��ȡѧԱ�绰
		Student student = studentDao.findById(record.getStudent_id());
		User user = userDao.findById(student.getUser_id());
		String phone = user.getPhone();
		// ��ȡ��������
		Coach coach = coachDao.findById(record.getCoach_id());
		String name = coach.getCoach_name();
		// ��ѧԱ����֪ͨ����
		String templateCode = "SMS_71335566";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public boolean finishTeach(String teach_id) {
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if (record == null) {
			throw new TeachRecordException("�޶�Ӧ��Լ������");
		}
		String state = record.getTeach_state();
		if (state.equals("2")) {
			throw new TeachRecordException("����ɵ�ѵ���������ظ�����");
		}
		try {
			teachRecordDao.updateTeachState(teach_id, "2");
		} catch (Exception e) {
			throw new DataBaseException("���ѵ��ʧ��");
		}
		return true;
	}

	public boolean refuseTeach(String teach_id) {
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if (record == null) {
			throw new EvalException("�޶�Ӧ��Լ������");
		}
		String state = record.getTeach_state();
		if ("-2".equals(state)) {
			throw new TeachRecordException("ѧԱ��ȡ��������");
		} else if ("-1".equals(state)) {
			throw new TeachRecordException("�Ѿܾ������������ظ�����");
		} else if ("1".equals(state)) {
			throw new TeachRecordException("�ѽ��ܸ������޷��ܾ�");
		}
		try {
			teachRecordDao.updateTeachState(teach_id, "-1");
		} catch (Exception e) {
			throw new DataBaseException("�ܾ�����ʧ��");
		}
		// �����ܾ��������������ճ̱��Ӧ��ʱ���״̬��Ϊ0
		String teach_time = record.getTeach_time();
		Integer coach_id = record.getCoach_id();
		updateCoachSchedule(teach_time, coach_id, "0");
		// ��ȡѧԱ�绰
		Student student = studentDao.findById(record.getStudent_id());
		User user = userDao.findById(student.getUser_id());
		String phone = user.getPhone();
		// ��ȡ��������
		Coach coach = coachDao.findById(coach_id);
		String name = coach.getCoach_name();
		// ��ѧԱ����֪ͨ����
		String templateCode = "SMS_71335569";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public boolean cancelTeach(String teach_id) {
		TeachRecord record = teachRecordDao.findByTeachId(teach_id);
		if (record == null) {
			throw new TeachRecordException("�޶�Ӧ��Լ������");
		}
		String state = record.getTeach_state();
		// Լ��״̬(0�ȴ�ȷ�ϣ�1��ȷ�ϣ�2�����ѵ����3��������ۣ�-1�����ܾ�������-2ѧԱȡ������)
		if ("1".equals(state)) {
			throw new TeachRecordException("������ȷ�ϸ������޷�ȡ��");
		} else if ("-1".equals(state)) {
			throw new TeachRecordException("�����Ѿܾ�������");
		} else if ("-2".equals(state)) {
			throw new TeachRecordException("��ȡ�������������ظ�����");
		}
		try {
			teachRecordDao.updateTeachState(teach_id, "-2");
		} catch (Exception e) {
			throw new DataBaseException("ȡ��Լ������ʧ��");
		}
		String teach_time = record.getTeach_time();
		int coach_id = record.getCoach_id();
		// ѧԱȡ���������������ճ̱��Ӧ��ʱ���״̬��Ϊ0
		updateCoachSchedule(teach_time, coach_id, "0");
		int user_id = coachDao.findById(coach_id).getUser_id();
		// ��ȡ�������ֻ���
		String phone = userDao.findById(user_id).getPhone();
		// ��ȡѧԱ������
		int student_id = record.getStudent_id();
		Student student = studentDao.findById(student_id);
		String name = student.getStudent_name();
		// ����������ȡ������
		String templateCode = "SMS_71330512";
		Message.sendNotifyMsg(phone, name, templateCode);
		return true;
	}

	public List<Map<String, String>> findTeachEvaluations(int coach_id, int evaltype, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<TeachRecord> list;
		if (evaltype == 0) {
			// �鿴ȫ������
			list = teachRecordDao.findTeachEvaluation(coach_id, offset, pageSize);
		} else {
			// �鿴��Ӧ���͵�����
			list = teachRecordDao.findTeachEvaluations(coach_id, evaltype, offset, pageSize);
		}
		System.out.println("list:" + list);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (TeachRecord record : list) {
			Map<String, String> map = new HashMap<String, String>();
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
			map.put("evalstar", "" + evalstar);
			map.put("evaluation", evaluation);
			map.put("evaltime", evaltime);
			result.add(map);
		}
		return result;
	}

	public List<Map<String, Object>> listAllTeachRecord(String teach_subject, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// ������һ��map�����������¼����
		Map<String, Object> map1 = new HashMap<String, Object>();
		int recordNum = teachRecordDao.getTeachRecordNum(teach_subject);
		map1.put("recordNum", recordNum);
		result.add(map1);
		List<TeachRecord> records = teachRecordDao.listAllTeachRecord(teach_subject, offset, pageSize);
		for (TeachRecord record : records) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			try {
				int student_id = record.getStudent_id();
				String student_name = studentDao.findById(student_id).getStudent_name();
				map2.put("student_name", student_name);
				int coach_id = record.getCoach_id();
				String coach_name = coachDao.findById(coach_id).getCoach_name();
				map2.put("coach_name", coach_name);
			} catch (Exception e) {
				throw new DataBaseException("��ѯʧ��");
			}
			map2.put("teachRecord", record);
			result.add(map2);
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByCoachName(String coach_name) {
		// ģ����ѯ(�ַ�������)
		coach_name = "%" + coach_name + "%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByCoachName(coach_name);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByStudentName(String student_name) {
		// ģ����ѯ(�ַ�������)
		student_name = "%" + student_name + "%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByStudentName(student_name);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordByTeachTime(String teach_time) {
		// ģ����ѯ(�ַ�������)
		teach_time = "%" + teach_time + "%";
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordByTeachTime(teach_time);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		return result;
	}

	public List<Map<String, Object>> findRecordBySubject(String teach_subject) {
		List<Map<String, Object>> result;
		try {
			result = teachRecordDao.findRecordBySubject(teach_subject);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		return result;
	}

	public Map<String, Object> listSchoolEval(String school_name, int evaltype, int page, int pageSize) {
		List<Map<String, String>> evals = new ArrayList<Map<String, String>>();
		List<Map<String, Object>> list = coachService.listRecomdCoach(school_name);
		// ȫ����������
		int all = 0;
		// ��������
		int good = 0;
		// ��������
		int medium = 0;
		// ��������
		int worse = 0;
		try {
			for (Map<String, Object> coach : list) {
				int coach_id = (Integer) coach.get("coach_id");
				// ��ÿ��������õ����ۣ���ӵ���У��������
				evals.addAll(findTeachEvaluations(coach_id, evaltype, page, pageSize));
				all += teachRecordDao.findTeachEvaluationNumber(coach_id);
				good += teachRecordDao.findTeachEvaluationNumByType(coach_id, 1);
				medium += teachRecordDao.findTeachEvaluationNumByType(coach_id, 2);
				worse += teachRecordDao.findTeachEvaluationNumByType(coach_id, 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("all", all);
		map.put("good", good);
		map.put("medium", medium);
		map.put("worse", worse);
		map.put("evalList", evals);
		return map;
	}

	/**
	 * ���ȶ������Լ��ʱ��(eg:2017-11-9 14:00-15:00)ת���ɶ�Ӧ�����ں�ʱ���, ���½����ճ̱��Ӧ��ʱ��״̬Ϊ1���ѱ�ԤԼ��
	 * 
	 * @param teach_time �����Լ��ʱ��
	 * @param coach_id ����id
	 * @param status ��Ӧ��״̬(ѧԱԤԼΪ1�������ܵ���ѧԱȡ������Ϊ0)
	 * @return
	 */
	public boolean updateCoachSchedule(String teach_time, Integer coach_id, String status) {
		// ��Լ��ʱ�䰴�տո�ֿ�,ȡ�ö�Ӧ���ں�ʱ���
		String[] date = teach_time.split(" ");
		String appoint_time = date[0].trim();
		String time = date[1].trim();
		// ͨ��coach_id��appoint_time�ҵ���Ӧ���ճ̱�
		CoachSchedule coachSchedule = coachScheduleMapper.findSchedule(appoint_time, coach_id);
		switch (time) {
		case "07:00-08:00":// ʱ���1
			coachSchedule.setTime1(status);
			break;
		case "08:20-09:20":// ʱ���2
			coachSchedule.setTime2(status);
			break;
		case "09:40-10:40":// ʱ���3
			coachSchedule.setTime3(status);
			break;
		case "11:00-12:00":
			coachSchedule.setTime4(status);
			break;
		case "12:40-13:40":
			coachSchedule.setTime5(status);
			break;
		case "14:00-15:00":
			coachSchedule.setTime6(status);
			break;
		case "15:20-16:20":
			coachSchedule.setTime7(status);
			break;
		case "16:40-17:40":
			coachSchedule.setTime8(status);
			break;
		case "18:00-19:00":
			coachSchedule.setTime9(status);
			break;
		case "19:20-20:20":
			coachSchedule.setTime10(status);
			break;
		default:
			break;
		}
		try {
			coachScheduleMapper.updateByPrimaryKeySelective(coachSchedule);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return true;
	}

}
