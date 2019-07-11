package xyz.cincommon.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.cincommon.interceptor.CustomAuthenticationFilter;

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
	public ShiroFilterFactoryBean shiroFilter(
			@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
//		 Map<String, Filter> filters = new HashMap<>();
//		 filters.put("authc", customAuthenticationFilter());
//		 bean.setFilters(filters);
//		 HashMap<String, String> filterChainDefinitionMap = new HashMap<>();
//		// org.apache.shiro.web.filter.mgt.DefaultFilter
//		 filterChainDefinitionMap.put("/**", "anon");
//		 filterChainDefinitionMap.put("/admin/**", "authc");
//		// filterChainDefinitionMap.put("/loginUser", "anon");
//		// filterChainDefinitionMap.put("/delete", "perms[delete]");
//		// filterChainDefinitionMap.put("/admin", "roles[admin]");
//		// filterChainDefinitionMap.put("/query", "perms[query]");
//		// filterChainDefinitionMap.put("/**", "authc");
//		 bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	 @Bean
	 public CustomAuthenticationFilter customAuthenticationFilter() {
	 return new CustomAuthenticationFilter();
	 }

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
}
