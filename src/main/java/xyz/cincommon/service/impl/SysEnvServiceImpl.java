package xyz.cincommon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import xyz.cincommon.mapper.SysEnvMapper;
import xyz.cincommon.model.SysEnv;
import xyz.cincommon.service.SysEnvService;
import xyz.cincommon.vo.ReturnResult;

@Service
public class SysEnvServiceImpl implements SysEnvService{
	@Autowired
	private SysEnvMapper sysEnvMapper;
	@Override
	public ReturnResult<Map<String, Object>> getSysEnvByKey(List<String> keyList) {
		List<SysEnv> envList = sysEnvMapper.selectByKeys(keyList);
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("envList", envList);
		return ReturnResult.success(resultMap);
	}

}
