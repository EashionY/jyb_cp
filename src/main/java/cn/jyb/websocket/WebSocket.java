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
 * websocket����
 * 
 * @ServerEndpoint ע����һ�����ε�ע�⣬���Ĺ�����Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket��������,
 * ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ,�ͻ��˿���ͨ�����URL�����ӵ�WebSocket��������
 * configurator = SpringConfigurator.class��Ϊ��ʹ�������ͨ��Springע�롣
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
		System.out.println("websocket������");
	}
	@OnMessage
	public void onMessage(String message,Session session) throws IOException{
		System.out.println("���յ�����Ϣ��"+message);
		String hlbOrderNo = message;
		/*
		 * ̫�������ܣ������÷���
		HLBOrder hlbOrder = hlbService.getOrderInfo(hlbOrderNo);
		session.getBasicRemote().sendText("First:" + JSONObject.toJSONString(hlbOrder));
		Integer orderStatus = hlbOrder.getOrderStatus();
		do {
			hlbOrder = hlbService.getOrderInfo(hlbOrderNo);
//			System.out.println("������ѯ��...");
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
		System.out.println("websocket���ӹر�");  
	}
	
}
*/
