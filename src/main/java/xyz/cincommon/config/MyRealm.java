//package xyz.cincommon.config;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import xyz.cincommon.model.Permission;
//import xyz.cincommon.model.Role;
//import xyz.cincommon.model.User;
//import xyz.cincommon.service.UserService;
//
//public class MyRealm extends AuthorizingRealm {
//
//	private static final Logger log = LoggerFactory.getLogger(MyRealm.class);
//
//	@Autowired
//	private UserService userService;
//
//	// 授权
//	/**
//	 * Retrieves the AuthorizationInfo for the given principals from the underlying
//	 * data store. When returning an instance from this method, you might want to
//	 * consider using an instance of
//	 * {@link org.apache.shiro.authz.SimpleAuthorizationInfo
//	 * SimpleAuthorizationInfo}, as it is suitable in most cases.
//	 *
//	 * @param principals
//	 *            the primary identifying principals of the AuthorizationInfo that
//	 *            should be retrieved.
//	 * @return the AuthorizationInfo associated with this principals.
//	 * @see org.apache.shiro.authz.SimpleAuthorizationInfo
//	 */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
//		Set<Role> roles = user.getRoles();
//		List<String> permissionList = new ArrayList<>();
//		List<String> roleNameList = new ArrayList<>();
//		for (Role role : roles) {
//			roleNameList.add(role.getName());
//			Set<Permission> permissions = role.getPermissions();
//			for (Permission permission : permissions) {
//				log.info(permission.getName());
//				permissionList.add(permission.getName());
//			}
//		}
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addStringPermissions(permissionList);
//		info.addRoles(roleNameList);
//		return info;
//	}
//
//	/**
//	 * Retrieves authentication data from an implementation-specific datasource
//	 * (RDBMS, LDAP, etc) for the given authentication token.
//	 * <p/>
//	 * For most datasources, this means just 'pulling' authentication data for an
//	 * associated subject/user and nothing more and letting Shiro do the rest. But
//	 * in some systems, this method could actually perform EIS specific log-in logic
//	 * in addition to just retrieving data - it is up to the Realm implementation.
//	 * <p/>
//	 * A {@code null} return value means that no account could be associated with
//	 * the specified token.
//	 *
//	 * @param token
//	 *            the authentication token containing the user's principal and
//	 *            credentials.
//	 * @return an {@link AuthenticationInfo} object containing account data
//	 *         resulting from the authentication ONLY if the lookup is successful
//	 *         (i.e. account exists and is valid, etc.)
//	 * @throws AuthenticationException
//	 *             if there is an error acquiring data or performing realm-specific
//	 *             authentication logic for the specified <tt>token</tt>
//	 */
//	// 认证登录
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//		String username = usernamePasswordToken.getUsername();
//		User user = userService.findByUsername(username);
//		if (user == null) {
//			return null;
//		}
//		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
//	}
//
//}
