package cn.jyb.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.alibaba.fastjson.JSONObject;

import cn.jyb.entity.HLBOrder;
import cn.jyb.service.HLBService;
/**
 * websocket服务
 * 
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 * 
 * @author Eashion
 *
 */
/**
@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class)
public class WebSocket {

	@Autowired
	private HLBService hlbService;
	
//	public Session session;
	
	@OnOpen
	public void onOpen(){
		System.out.println("websocket已连接");
	}
	@OnMessage
	public void onMessage(String message,Session session) throws IOException{
		System.out.println("接收到的消息："+message);
		String hlbOrderNo = message;
		/*
		 * 太消耗性能，放弃该方法
		HLBOrder hlbOrder = hlbService.getOrderInfo(hlbOrderNo);
		session.getBasicRemote().sendText("First:" + JSONObject.toJSONString(hlbOrder));
		Integer orderStatus = hlbOrder.getOrderStatus();
		do {
			hlbOrder = hlbService.getOrderInfo(hlbOrderNo);
//			System.out.println("订单查询中...");
		} while (orderStatus == hlbOrder.getOrderStatus());
		session.getBasicRemote().sendText("Second:" + JSONObject.toJSONString(hlbOrder));
		
		if(HLBService.changed.get(hlbOrderNo) != null && HLBService.changed.get(hlbOrderNo) == true){
		    HLBOrder hlbOrder = hlbService.getOrderInfo(hlbOrderNo);
		    session.getBasicRemote().sendText(JSONObject.toJSONString(hlbOrder));
		    HLBService.changed.remove(hlbOrderNo);
		}
	}
	@OnError
	public void onError(Throwable t){
		t.printStackTrace();
	}
	@OnClose
	public void onClose(){
		System.out.println("websocket连接关闭");  
	}
	
}
*/
