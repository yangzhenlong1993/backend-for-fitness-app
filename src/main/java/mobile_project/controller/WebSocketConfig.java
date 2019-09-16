package mobile_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	@Autowired
	private  UserWebSocketHandler userWebSocketHandler;
	private static final String LINK_URI = "websocket";
	//add websocket handler, add handshake interceptor, interceptor first, after handler
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

		webSocketHandlerRegistry.addHandler(userWebSocketHandler, LINK_URI).addInterceptors(new UserHandShakeInterceptor()).setAllowedOrigins("*");
	}

}
