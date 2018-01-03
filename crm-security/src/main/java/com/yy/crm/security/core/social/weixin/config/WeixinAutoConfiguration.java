/**
 * 
 */
package com.yy.crm.security.core.social.weixin.config;


import com.yy.crm.security.core.properties.SecurityProperties;
import com.yy.crm.security.core.properties.WeixinProperties;
import com.yy.crm.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * 
 * Created by luyuanyuan on 2017/10/31.
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "yy.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	
	//@Bean({"connect/weixinConnect", "connect/weixinConnected"})
	//@ConditionalOnMissingBean(name = "weixinConnectedView")
	//public View weixinConnectedView() {
	//	return new ImoocConnectView();
	//}
	
}