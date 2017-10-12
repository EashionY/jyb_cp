package cn.jyb.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 从网上扒考题
 * @author Eashion
 *
 */
public class Query {
	public static final String APPKEY = "2485934808878226";// 你的appkey
	public static final String URL = "http://api.jisuapi.com/driverexam/query";
	public static final String type = "C1";// 驾照类型 A1,A3,B1,A2,B2,C1,C2,C3,D,E,F
	public static final String subject = "4";// 1：科目一 4：科目四
	public static final String pagesize = "10";
//	public static final String pagenum = "1";
	public static final String sort = "normal";// normal：顺序查询 rand：随机查询
	
	public void Get(String pagenum) {
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
				String total = resultarr.getString("total");
				pagenum = resultarr.getString("pagenum");
				String pagesize = resultarr.getString("pagesize");
				String subject = resultarr.getString("subject");
				String type = resultarr.getString("type");
				String sort = resultarr.getString("sort");
				System.out.println(total + " " + pagenum + " " + pagesize + " " + subject + " " + type + " " + sort);
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
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Integer pagenum = 1;
		Query query = new Query();
		query.Get(""+pagenum);
	}
}
