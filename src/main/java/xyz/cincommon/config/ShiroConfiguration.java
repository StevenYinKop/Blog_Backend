package xyz.cincommon.config;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
	@Bean
	public CredentialMatcher credentialMatcher() {
		return new CredentialMatcher();
	}
	
	@Bean
	public MyRealm myRealm() {
		MyRealm myRealm = new MyRealm();
		myRealm.setCredentialsMatcher(credentialMatcher());
		return myRealm;
	}
	
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm());
		return securityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
//		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//		// org.apache.shiro.web.filter.mgt.DefaultFilter
//		filterChainDefinitionMap.put("/loginUser", "anon");
//		filterChainDefinitionMap.put("/delete", "perms[delete]");
//		filterChainDefinitionMap.put("/admin", "roles[admin]");		
//		filterChainDefinitionMap.put("/query", "perms[query]");		
//		filterChainDefinitionMap.put("/**", "authc");		
//		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	
}
