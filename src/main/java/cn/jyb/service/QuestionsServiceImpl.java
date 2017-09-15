package cn.jyb.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.QuestionsMapper;
import cn.jyb.entity.Questions;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.QuestionsException;
import cn.jyb.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService {
	//���������ݡ��ݿ�����appkey
	public static final String APPKEY = "2485934808878226";// ���appkey
	//���������ݡ��ݿ����Ľӿ������ַ
	public static final String URL = "http://api.jisuapi.com/driverexam/query";
	
	@Resource
	private QuestionsMapper qMapper;
	
	public void addQuestsion(Integer pagenum) {
		String result = null;
		String url = URL + "?appkey=" + APPKEY + "&type=" + "C1" + "&subject=" + "4" + "&pagesize=" + "10"
				+ "&pagenum=" + pagenum + "&sort=" + "normal";
		try {
			result = HttpUtil.sendGet(url, "utf-8");
			JSONObject json = JSONObject.fromObject(result);
			if (json.getInt("status") != 0) {
				System.out.println(json.getString("msg"));
			} else {
				JSONObject resultarr = json.optJSONObject("result");
				if (resultarr.opt("list") != null) {
					JSONArray list = resultarr.optJSONArray("list");
					for (int i = 0; i < list.size(); i++) {
						JSONObject obj = (JSONObject) list.opt(i);
						String question = obj.getString("question");
						String option1 = obj.getString("option1");
						String option2 = obj.getString("option2");
						String option3 = obj.getString("option3");
						String option4 = obj.getString("option4");
						String answer = obj.getString("answer");
						String explain = obj.getString("explain");
						String pic = obj.getString("pic");
						String type1 = obj.getString("type");
						String chapter = obj.getString("chapter");
						System.out.println(question + " " + option1 + " " + option2 + " " + option3 + " " + option4
								+ " " + answer + " " + explain + " " + pic + " " + type1 + " " + chapter);
						Questions q = new Questions();
						q.setQuestion(question);
						q.setOption1(option1);
						q.setOption2(option2);
						q.setOption3(option3);
						q.setOption4(option4);
						q.setAnswer(answer);
						q.setExplain(explain);
						q.setPic(pic);
						q.setType(type1);
						q.setChapter(chapter);
						q.setSubject(4);
						System.out.println(q);
						qMapper.saveQuestion(q);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("�����쳣");
		}
	}

	public List<Questions> getQuestions(Integer subject, Integer page, Integer pageSize, String sort) {
		Integer offset = 0;
		if(page != null && page > 1){
			 offset = (page-1)*pageSize;
		}
		if(subject != 1 && subject != 4){
			throw new QuestionsException("��Ŀ����ȷ");
		}
		List<Questions> result = new ArrayList<Questions>();
		//˳�����
		if("normal".equals(sort.toLowerCase())){
			result = qMapper.getNormalQuestions(subject, offset, pageSize);
		}
		//�������
		if("rand".equals(sort.toLowerCase())){
			if(subject==1){//��Ŀ1����(100���⣬�޶�ѡ)
				result = qMapper.getRandQuestions(subject,pageSize);
			}else if(subject==4){//��Ŀ4����(50���⣬45��ѡ��5��ѡ)
				result = qMapper.getRandQuestions(subject, pageSize-5);
				//5����ѡ��
				List<Questions> mult = qMapper.getMultSelection(pageSize-45);
				result.addAll(mult);
			}
		}
		return result;
	}

}
