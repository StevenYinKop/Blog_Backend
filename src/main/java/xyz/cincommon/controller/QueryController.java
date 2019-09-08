package xyz.cincommon.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import xyz.cincommon.service.BlogService;
import xyz.cincommon.service.LandingPageService;
import xyz.cincommon.service.SysEnvService;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@RestController
@RequestMapping("/get")
public class QueryController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private SysEnvService sysEnvService;
	@Autowired
	private LandingPageService landingPageSerivce;

	@GetMapping("/initMain")
	public ReturnResult<Map<String, Object>> initMain(
			@RequestParam(required = false, name = "pageSize", defaultValue="10") int pageSize,
			@RequestParam(required = false, name = "pageNum", defaultValue="1") int pageNum
	) throws Exception {
		return blogService.initMain(pageSize, pageNum);
	}
	
	@GetMapping("/sysEnv")
	public ReturnResult<Map<String, Object>> sysEnv(
				@RequestParam(required = false, name = "keys") String keys
			) {
		if (StringUtils.isEmpty(keys)) {
			return ReturnResult.error(CodeMsg.PARAMETER_ISNULL);
		}
		List<String> keyList = Lists.newArrayList(StringUtils.split(keys, ","));
		return sysEnvService.getSysEnvByKey(keyList);
	}
	
	@GetMapping("/initLandingPage")
	public ReturnResult<Map<String, Object>> initLandingPage() {
		return landingPageSerivce.initLandingPage();
	}
}
