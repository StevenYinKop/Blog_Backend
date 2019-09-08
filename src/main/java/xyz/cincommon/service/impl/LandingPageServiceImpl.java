package xyz.cincommon.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import xyz.cincommon.mapper.SysEnvMapper;
import xyz.cincommon.model.SysEnv;
import xyz.cincommon.service.LandingPageService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.JuheFootballApi;
import xyz.cincommon.vo.MatchInfoVo;
import xyz.cincommon.vo.ReturnResult;
@Service
public class LandingPageServiceImpl implements LandingPageService{
	@Autowired
	private SysEnvMapper sysEnvMapper;

	@Override
	public ReturnResult<Map<String, Object>> initLandingPage() {
		Map<String, Object> result = Maps.newHashMap();
		// TODO 1. 获取图片地址和个人简介
		
		// TODO 2. 获取个人详细信息, 个人网站url, 头像
		
		// TODO 3 获取最新三篇uid=1的博客
		
		// TODO 4 调用API接口, 获得利物浦最近一场赛事信息.
		fillMatchInfo(result);
		return ReturnResult.success(result);
	}

	private void fillMatchInfo(Map<String, Object> result) {
		// TODO 读取数据库中保存的赛事信息, 如果更新日期为今天, 则不去调用API(API调用有限制, 如果每次都直接调用API那很快就一滴都没有了.png)
		MatchInfoVo matchInfo = checkApiUpdateTime();
		if(null == matchInfo) {
			matchInfo = invokeLiverpoolScheduleApi();
		}
		result.put("matchInfo", matchInfo);
	}


	private MatchInfoVo invokeLiverpoolScheduleApi() {
		SysEnv sysEnv = sysEnvMapper.selectByPrimaryKey("football.api.key.juhe");
		List<MatchInfoVo> matchInfoList = new JuheFootballApi(sysEnv.getEnvValue()).queryTeamSchedule(Constant.LIV_CN);
		// list中包含了今天之前的三场比赛和之后的三场比赛, 为了防止接口内容变化, 遍历寻找最近一场没有开赛的比赛.
		// 查找的思路: 返回的参数中"c4R"代表比分, 对于已经结束的比赛, c4R: "2-1", 对于没有开赛的比赛, c4R: "VS", 所以找到最近的一场c4R字段为"VS"的比赛即可
		return queryUpcomingMatch(matchInfoList);
	}

	private MatchInfoVo queryUpcomingMatch(List<MatchInfoVo> matchInfoList) {
		for (int i = 0; i < matchInfoList.size(); i++) {
			MatchInfoVo matchInfoVo = matchInfoList.get(i);
			if (StringUtils.equalsIgnoreCase("vs", matchInfoVo.getType())) {
				return matchInfoVo;
			}
		}
		return null;
	}

	private MatchInfoVo checkApiUpdateTime() {
		return null;
	}

}
