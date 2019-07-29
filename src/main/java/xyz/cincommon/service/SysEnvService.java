package xyz.cincommon.service;

import java.util.List;
import java.util.Map;

import xyz.cincommon.vo.ReturnResult;

public interface SysEnvService {

	ReturnResult<Map<String, Object>> getSysEnvByKey(List<String> keyList);
	
}
