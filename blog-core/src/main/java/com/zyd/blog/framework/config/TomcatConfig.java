package com.zyd.blog.framework.config;

import org.apache.catalina.valves.RemoteIpValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Tomcat相关配置</p>
 * @author zmingchun
 * @version 1.0 (2018/6/20 10:55)
 */
@Configuration
public class TomcatConfig {

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		RemoteIpValve remoteIpValve = new RemoteIpValve();
		// 下面这里的值要与nginx的header头对应
		remoteIpValve.setRemoteIpHeader("X-Forwarded-For");
		remoteIpValve.setProtocolHeader("X-Forwarded-Proto");
		remoteIpValve.setProtocolHeaderHttpsValue("https");
		tomcat.addEngineValves(remoteIpValve);
		return tomcat;
	}


}
