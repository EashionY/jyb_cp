package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jyb.util.DateUtil;
import cn.jyb.util.Distance;
import cn.jyb.util.WxpayUtil;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.WxpublicUtil;
import net.sf.json.JSONObject;

public class TestCase {

	@Test
	public void test1(){
		String str = null;
		System.out.println(str==null);
	}
	
	/**
	 * 指定字符集编码与解码
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void test2() throws UnsupportedEncodingException{
		String res = URLEncoder.encode("你好啊", "UTF-8");
		System.out.println(res);
		String str = URLDecoder.decode(res, "UTF-8");
		System.out.println(str);
	}
	
	@Test
	public void test3(){
		Object data = new Object();
		System.out.println(data);
	}
	
	@Test
	public void test4(){
		String num = DateUtil.getOrderNum();
		System.out.println(num);
	}
	
	@Test
	public void test5(){
		int i = (int) (15233245623L % 3288);
		System.out.println(i);
	}
	
	/**
	 * 测试生成随机数
	 */
	@Test
	public void test6(){
		for(int i=0;i<10;i++){
			int rand = (int)(Math.random()*10+1);
			System.out.println(rand);
		}
	}
	
	@Test
	public void test7(){
		int i = 7%2+1;
		System.out.println(i);
	}
	
	@Test
	public void test8(){
		String str = DateUtil.getThree();
		System.out.println(str);
	}
	
	@Test
	public void test9(){
		List<String> strs = new ArrayList<String>();
		strs.add("客户账户|凭证序号|凭证类型");
		strs.add("香锅|是|个|傻逼");
		for(String str : strs){
			String[] s = str.split("\\|");
			System.out.println(Arrays.toString(s));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test10() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String data = "{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}";
		System.out.println(data);
		Map<String,Object> params = objectMapper.readValue(data, Map.class);
		System.out.println(params);
		System.out.println(params.get("name"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test11() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String data = "[{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-11\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"},"
				+ "{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-12\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"},"
				+ "{\"coach_id\":\"21\",\"appoint_time\":\"2017-05-13\","
				+ "\"time1\":\"-1\",\"time2\":\"-1\",\"time3\":\"0\",\"time4\":\"0\",\"time5\":\"-1\","
				+ "\"time6\":\"0\",\"time7\":\"0\",\"time8\":\"-1\",\"time9\":\"-1\",\"time10\":\"-1\"}]";
		List<Map<String,String>> params = objectMapper.readValue(data, List.class);
		System.out.println(params);
		for(Map<String,String> map : params){
			System.out.println(map.get("appoint_time"));
		}
	}
	
	@Test
	public void test12(){
		System.out.println("".equals(null));
	}
	
	@Test
	public void testIDCard(){
		String certNo = "511025199006150202";
		String birthday = certNo.substring(6, 14);
		System.out.println(birthday);
	}
	
	@Test
	public void test13(){
		String curtime = ""+System.currentTimeMillis();
		System.out.println(curtime);
	}
	
	@Test
	public void test14() throws IOException{
		Map<String,String> params = new HashMap<String,String>();
		params.put("测试", "键值对1");
		params.put("test", "键值对2");
		params.put("exam", "键值对3");
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"), "UTF-8");
		osw.write(params.toString());
		osw.flush();
		osw.close();
		System.out.println("输出完毕");
	}

	@Test
	public void test15(){
		String str = WxpayUtil.CreateNoncestr(16);
		System.out.println(str);
	}
	
	@Test
	public void test16(){
		Integer i = 1;
		System.out.println(i == 1);
	}
	
	@Test
	public void test17(){
		String str = null;
		System.out.println(str==null);
	}
	
	@Test
	public void test18(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.getTime());
	}
	
	@Test
	public void test19(){
		String str = "你好";
		switch (str) {
		case "hello":
			System.out.println("Hello");
			break;
		case "你好":
			System.out.println("你好");
			break;
		default:
			System.out.println("default");
			break;
		}
	}
	
	@Test
	public void test20(){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		url = url.replace("APPID", WxpublicConfig.APPID).replace("SCOPE", "snsapi_base");
		System.out.println(url);
	}
	/**
	 * 测试map转换为JSONObject
	 */
	@Test
	public void test21(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("touser", "oUPCPxDxgyquGYS-vbL1cpXd1LiY");
		param.put("template_id", "0C5sq_PGYYrwVOwccWT9EFBiUr4szbeFUREPkbJHtBE");
		param.put("url", "");
//		param.put("topcolor", "#FF9212");
		Map<String,Object> data = new HashMap<String,Object>();
		Map<String,Object> first = new HashMap<String,Object>();
		first.put("value", "您好，你有一条挪车提醒");
		first.put("color", "#000000");
		data.put("first", first);
		Map<String,Object> keyword1 = new HashMap<String,Object>();
		keyword1.put("value", "驾易宝-一键挪车");
		keyword1.put("color", "#173177");
		data.put("keyword1", keyword1);
		Map<String,Object> keyword2 = new HashMap<String,Object>();
		keyword2.put("value", "您的爱车挡住路啦，麻烦您给挪一下呗");
		keyword2.put("color", "#173177");
		data.put("keyword2", keyword2);
		Map<String,Object> remark = new HashMap<String,Object>();
		remark.put("value", "如有疑问，请联系客服");
		remark.put("color", "#000000");
		data.put("remark", remark);
		param.put("data", data);
		JSONObject json = JSONObject.fromObject(param);
		System.out.println(json.toString());
		String accessToken = WxpublicUtil.getFromCache();
		if(accessToken == null){
			accessToken = WxpublicUtil.getNew();
		}
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		String result = WxpayUtil.httpsRequest(requestUrl, "POST", json.toString());
		System.out.println("result:"+result);
	}
	
	@Test
	public void test22(){
		String km = "22.66";
		BigDecimal bd = new BigDecimal(km);
		int licheng = bd.setScale(0, RoundingMode.CEILING).intValue();
		System.out.println(licheng);
	}
	
	@Test
	public void testDistance(){
		String d = Distance.getDistance(112.456123, 30.547841, 113.954138, 30.897412);
		System.out.println(d);
	}
	
	@Test
	public void testMap(){
		Map<String,Boolean> map = new HashMap<>();
		map.put("123456", true);
		map.put("123456", false);
		map.remove("123456");
		System.out.println(map.get("123456"));
	}
}
