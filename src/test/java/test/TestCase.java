package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jyb.util.DateUtil;
import cn.jyb.util.IDCardUtil;

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
		boolean tf = IDCardUtil.isIDCard(certNo);
		System.out.println(tf);
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

}
