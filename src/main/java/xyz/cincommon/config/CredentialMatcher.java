//package xyz.cincommon.config;
//
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
//
//public class CredentialMatcher extends SimpleCredentialsMatcher{
//
//	@Override
//	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//		String tokePassword = new String(usernamePasswordToken.getPassword());
//		String dbPassword = (String) info.getCredentials();
//		return dbPassword.equals(tokePassword);
//	}
//	
//}
