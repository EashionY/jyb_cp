package cn.jyb.websocket;


import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * websocket������
 * @author Eashion
 *
 */
public class WebSocketHandler extends TextWebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	
	/** 
     * ����ǰ�˷��͵��ı���Ϣ 
     * js����websocket.sendʱ�򣬻���ø÷��� 
     * 
     * @param session 
     * @param message 
     * @throws Exception 
     */  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        // ��ȡ�ύ��������Ϣ����  
        logger.info("�յ��û� " + username + "����Ϣ:" + message.toString());  
        // �ظ�һ����Ϣ 
        session.sendMessage(new TextMessage("reply msg:" + message.getPayload()));  
    }
    /** 
     * �������ӽ�����ʱ�򣬱����� 
     * ���ӳɹ�ʱ�򣬻ᴥ��ҳ����onOpen���� 
     * 
     * @param session 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        users.add(session);  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        logger.info("�û� " + username + " Connection Established");  
        session.sendMessage(new TextMessage(username + " connect"));  
        session.sendMessage(new TextMessage("hello wellcome"));  
    }
    
    /** 
     * �����ӹر�ʱ������ 
     * 
     * @param session 
     * @param status 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        logger.info("�û� " + username + " Connection closed. Status: " + status);  
        users.remove(session);  
    }
    
    /** 
     * �������ʱ���� 
     * 
     * @param session 
     * @param exception 
     * @throws Exception 
     */  
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");  
        if (session.isOpen()) {  
            session.close();  
        }  
        logger.debug("�û�: " + username + " websocket connection closed......");  
        users.remove(session);  
    }
    
    /** 
     * �����������û�������Ϣ 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * ��ĳ���û�������Ϣ 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String userName, TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {  
                try {  
                    if (user.isOpen()) {  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    } 
}
