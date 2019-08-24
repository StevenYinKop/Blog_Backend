package xyz.cincommon.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.mapper.BlogInfoMapper;
import xyz.cincommon.mapper.RoleMapper;
import xyz.cincommon.mapper.UserInfoMapper;
import xyz.cincommon.model.Role;
import xyz.cincommon.model.User;
import xyz.cincommon.model.UserInfo;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.EncryptedUtil;
import xyz.cincommon.utils.UserUtil;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.LoginUserInfo;
import xyz.cincommon.vo.ReturnResult;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private BlogInfoMapper blogInfoMapper;

	public User findByUsername(String username) {
		return userInfoMapper.findByUsername(username);
	}

	@Override
	public ReturnResult<String> logout() {
		return ReturnResult.success("Logout success");
	}

	@Override
	public ReturnResult<User> login(LoginUserInfo loginUserInfo) {
		String username = loginUserInfo.getUsername();
		if (StringUtils.isBlank(username)) {
			return ReturnResult.error(CodeMsg.PARAMETER_ISNULL);
		}
		User user = userInfoMapper.findByUsername(username);
		if (user == null) {
			return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
		}
		String dbPassword = user.getPassword();
		String inputPassword = loginUserInfo.getPassword();
		if (StringUtils.equals(EncryptedUtil.decrypt(dbPassword, Constant.DEFAULT_ENCRYPT_KEY), inputPassword)) {
			user.setPassword(null);
			return ReturnResult.success(user);
		}
		return ReturnResult.error(CodeMsg.BAD_CERTIFICATE);
	}

	@Override
	public void checkCurrentUserRole(Constant.Role ... roles) {
		User user = UserUtil.getUser();
		Collection<Role> dbRole = queryRoleByUser(user);
		boolean b = false;
		for (Role role : dbRole) {
			if (b) {
				break;
			}
			for (Constant.Role roleEnum : roles) {
				if (roleEnum.getId() == role.getRid()) {
					b = true;
					break;
				}
			}
		}
		if (!b) {
			throw new BlogException(CodeMsg.NOT_PERMISSION);
		}
	}

	@Override
	public Collection<Role> queryRoleByUser(User user) {
		User userRole = userInfoMapper.findByUsername(user.getUsername());
		Set<Role> roles = userRole.getRoles();
		return roles;
	}

	@Override
	public ReturnResult<String> verifyLogin() {
		User user = UserUtil.getUser();
		if (null == user) {
			throw new BlogException(CodeMsg.LOGIN_EXPIRED);
		}
		return ReturnResult.success();
	}

	@Override
	public ReturnResult<Map<String, Object>> initDashboard(User user) throws Exception {
		Map<String, Object> result = Maps.newHashMap();
		// 查询当前用户的所有角色名
		List<String> roles = roleMapper.selectRoleNameByUserId(user.getUid());
		// Blogger, Admin
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < roles.size(); i ++) {
			String role = roles.get(i);
			sb.append(role);
			if(i != roles.size() - 1) {
				sb.append(",");
			}
		}
		String roleName = sb.toString();
//		 计算入站时间
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(user.getUid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date startDate = userInfo.getCreateDate();
        Date endDate = sdf.parse(sdf.format(new Date()));
//      写了多少篇博客, 收到多少评论, 博客总点击量
        List<Map<String, Integer>> blogInfo = blogInfoMapper.selectBlogInfoByUserId(user.getUid());
        if(blogInfo != null && blogInfo.size() != 0) {
        	Map<String, Integer> map = blogInfo.get(0);
            result.put("blogNum", map.get("blog_amount"));
            result.put("clickNum", map.get("click_amount"));
            result.put("commentsNum", map.get("reply_amount"));
        }
        long betweenDate = (endDate.getTime() - startDate.getTime())/(1000*60*60*24);
        result.put("roleName", roleName);
        result.put("days", betweenDate);
		return ReturnResult.success(result);
	}
}
