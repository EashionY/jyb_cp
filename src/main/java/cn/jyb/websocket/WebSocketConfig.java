package cn.jyb.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * Spring-websocket≈‰÷√
 * @author Eashion
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Bean  
    public TextWebSocketHandler webSocketHandler() {  
        return new WebSocketHandler();  
    }
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//◊¢≤·WebSocket,…Ë÷√websocketµƒµÿ÷∑  
        String websocket_url = "/websocket"; 
        //◊¢≤·Handler,◊¢≤·Interceptor
        registry.addHandler(webSocketHandler(), websocket_url).addInterceptors(new WebSocketHandshakeInterceptor());    
	}
	
	

}
