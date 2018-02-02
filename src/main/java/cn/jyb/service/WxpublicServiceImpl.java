package cn.jyb.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jyb.dao.StudentDao;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.WxOpenidMapper;
import cn.jyb.entity.Student;
import cn.jyb.entity.User;
import cn.jyb.entity.WxOpenid;
import cn.jyb.exception.WxpublicException;
import cn.jyb.util.WxpayUtil;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.WxpublicUtil;
@Service("wxpublicService")
public class WxpublicServiceImpl implements WxpublicService {

	@Resource
	private WxOpenidMapper wxOpenidMapper;
	@Resource
	private StudentDao studentDao;
	@Resource
	private UserDao userDao;
	
	@Override
	public void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", WxpublicConfig.APPID).replace("SECRET", WxpublicConfig.APPSECRET).replace("CODE", code);
		JSONObject result = WxpayUtil.httpsRequest(url, "GET");
		System.out.println(result);
		//获得用户的openid
		String openid = result.getString("openid");
		System.out.println("openid:"+openid);
		HttpSession session = request.getSession();
		session.setAttribute("openid", openid);
		//检测用户是否已经绑定账号
		WxOpenid wxOpenid = wxOpenidMapper.findByOpenid(openid);
		if(wxOpenid == null || wxOpenid.getUserId() == null){
			request.getRequestDispatcher("/log.html").forward(request, response);
			return;
		}else{
			Integer userId = wxOpenid.getUserId();
			Map<String,Object> userInfo = new HashMap<String,Object>();
			//获取用户信息
			Student student = studentDao.findByUserId(userId);
			String student_idcard = student == null ? "" : student.getStudent_idcard();
			String student_id = student == null ? "" : String.valueOf(student.getStudent_id());
			userInfo.put("student_idcard", student_idcard);
			userInfo.put("student_id", student_id);
			User user = userDao.findById(userId);
			userInfo.put("address", user.getAddress());
			userInfo.put("birthday", user.getBirthday());
			userInfo.put("height", user.getHeight());
			userInfo.put("id", user.getUser_Id());
			// 如果头像路径为空，则替换为默认的logo
			String headImg = user.getImgpath() == null ? "http://39.108.73.207/img/default/head.png" : user.getImgpath();
			userInfo.put("imgpath", headImg);
			userInfo.put("interest", user.getInterest());
			userInfo.put("job", user.getJob());
			userInfo.put("nickname", user.getNickname());
			userInfo.put("password", user.getPassword());
			userInfo.put("phone", user.getPhone());
			userInfo.put("role", user.getRole());    
			userInfo.put("salary", user.getSalary());
			userInfo.put("sex", user.getSex());
			userInfo.put("signature", user.getSignature());
			userInfo.put("weight", user.getWeight());
			userInfo.put("xingzuo", user.getXingzuo());
			userInfo.put("region", user.getRegion());
			userInfo.put("QRImg", user.getQrImg());
			session.setAttribute("userInfo", userInfo);
		}
		if(state == null || state.trim().isEmpty()){
			throw new WxpublicException("错误的请求");
		}else if(state.equals("首页")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("货拉宝")){
			request.getRequestDispatcher("/src/pages/HuolaBao/hlbindex/hlbindex.html").forward(request, response);
		}else if(state.equals("一键挪车")){
			request.getRequestDispatcher("/src/pages/MoveCar/moveCar.html").forward(request, response);
//		}else if(state.equals("违章查询&处理")){
//			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("模拟练习")){
			request.getRequestDispatcher("/src/pages/exercises/exer_practice.html").forward(request, response);
//		}else if(state.equals("订单查询")){
//			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("个人信息")){
			request.getRequestDispatcher("/src/pages/PerCenter/personal.html").forward(request, response);
		}
	}

	@Override
	public String createMenu(String menu) {
		String accessToken = WxpublicUtil.getFromCache();
		if(accessToken == null){
			accessToken = WxpublicUtil.getNew();
		}
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
		String result = WxpayUtil.httpsRequest(url, "POST", menu);
		if(result == null){
			throw new WxpublicException("菜单创建失败");
		}
		return result;
	}

	@Override
	public String getOpenid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		return openid;
	}

	@Override
	public Map<String, Object> jsSign(String url) {
		String ticket = WxpublicUtil.getTicketFromCache();
		if(ticket == null){
			ticket = WxpublicUtil.getTicketNew();
		}
		String nonce_str = WxpayUtil.CreateNoncestr();
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		//注意这里参数名必须全部小写，且必须有序 
	    String string1 = "jsapi_ticket=" + ticket +
	         "&noncestr=" + nonce_str + 
	         "&timestamp=" + timestamp + 
	         "&url=" + url;
	    String signature = WxpublicUtil.SHA1(string1);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("url", url);
		result.put("nonceStr", nonce_str);
		result.put("timestamp", timestamp);
		result.put("signature", signature);
		result.put("jsapi_ticket", ticket);
		result.put("appid", WxpublicConfig.APPID);
		return result;
	}
	
}
