package cn.jyb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.QuestionsMapper;
import cn.jyb.entity.Questions;
import cn.jyb.exception.DataBaseException;
import cn.jyb.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService {
	public static final String APPKEY = "2485934808878226";// 你的appkey
	public static final String URL = "http://api.jisuapi.com/driverexam/query";
	public static final String type = "C1";// 驾照类型 A1,A3,B1,A2,B2,C1,C2,C3,D,E,F
	public static final String subject = "4";// 1：科目一 4：科目四
	public static final String pagesize = "10";
//	public static final String pagenum = "1";
	public static final String sort = "normal";// normal：顺序查询 rand：随机查询
	
	@Resource
	private QuestionsMapper qMapper;
	
	public void addQuestsion(Integer pagenum) {
		String result = null;
		String url = URL + "?appkey=" + APPKEY + "&type=" + type + "&subject=" + subject + "&pagesize=" + pagesize
				+ "&pagenum=" + pagenum + "&sort=" + sort;
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
						q.setSubject(Integer.parseInt(subject));
						System.out.println(q);
						qMapper.saveQuestion(q);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据异常");
		}
	}

}
