package cn.jyb.service;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jyb.dao.WxOpenidMapper;
import cn.jyb.entity.WxOpenid;
import cn.jyb.exception.WxpublicException;
import cn.jyb.util.WxpayUtil;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.WxpublicUtil;
@Service("wxpublicService")
public class WxpublicServiceImpl implements WxpublicService {

	@Resource
	private WxOpenidMapper wxOpenidMapper;
	
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
		if(wxOpenid == null){
			request.getRequestDispatcher("/log.html").forward(request, response);
			return;
		}else{   
			session.setAttribute("userId", wxOpenid.getUserId());
		}
		if(state == null || state.trim().isEmpty()){
			throw new WxpublicException("错误的请求");
		}else if(state.equals("首页")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("货拉宝")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("一键挪车")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("违章查询&处理")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("模拟练习")){
			request.getRequestDispatcher("/src/pages/exercises/exer_simulate.html").forward(request, response);
		}else if(state.equals("订单查询")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
		}else if(state.equals("个人信息")){
			request.getRequestDispatcher("/src/pages/index.html").forward(request, response);
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

	
}
