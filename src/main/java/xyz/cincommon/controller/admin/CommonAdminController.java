package xyz.cincommon.controller.admin;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import xyz.cincommon.model.User;
import xyz.cincommon.service.SysEnvService;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.UserUtil;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@RestController
@RequestMapping("/admin")
public class CommonAdminController {
	@Autowired
	private SysEnvService sysEnvService;
	@Autowired
	private UserService userService;

	@GetMapping("/sysEnv")
	public ReturnResult<Map<String, Object>> sysEnv(
				@RequestParam(required = false, name = "keys") String keys
			) {
		userService.checkCurrentUserRole(Constant.Role.ADMIN, Constant.Role.BLOGGER);
		if (StringUtils.isEmpty(keys)) {
			return ReturnResult.error(CodeMsg.PARAMETER_ISNULL);
		}
		List<String> keyList = Lists.newArrayList(StringUtils.split(keys, ","));
		return sysEnvService.getSysEnvByKey(keyList);
	}

	@GetMapping("/initDashboard")
	public ReturnResult<Map<String, Object>> initDashboard() throws Exception {
		userService.verifyLogin();
		User user = UserUtil.getUser();
		return userService.initDashboard(user);
	}

}
