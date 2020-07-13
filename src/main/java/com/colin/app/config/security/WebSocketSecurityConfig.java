package com.colin.app.config.security;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

//@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

  @Override
  protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
    messages.simpSubscribeDestMatchers("/update").permitAll()
    		.simpDestMatchers("/app/**", "/topic/**").permitAll();
  }

  @Override
  protected boolean sameOriginDisabled() {
    return true;
  }
}