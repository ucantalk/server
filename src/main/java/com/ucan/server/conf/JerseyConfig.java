package com.ucan.server.conf;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ucan.server.controller.UserCoreController;
import com.ucan.server.controller.UserInfoController;

/**
 * 
 * Jersey配置，提供Rest Api接口
 * 
 * @author soso
 * @since 2015-01-06
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		this.packages("com.ucan.server");
		register(MultiPartFeature.class);
		register(JacksonFeature.class);
		register(UserCoreController.class);
		register(UserInfoController.class);
	}
}
