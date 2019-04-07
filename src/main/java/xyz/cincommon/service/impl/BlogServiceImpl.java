package xyz.cincommon.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cincommon.mapper.BlogMapper;
import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogMapper blogMapper;

	@Override
	public ReturnResult<List<BlogInfo>> initMain() throws IllegalAccessException, InvocationTargetException {
		List<BlogInfo> blogList = blogMapper.findTop10BlogInfo();
		return ReturnResult.success(blogList);
	}

	@Override
	public ReturnResult<BlogInfo> findBlogById(String id) {
		BlogInfo blogInfo = blogMapper.findById(id);
		if (blogInfo != null) {
			blogInfo.setClickRates(blogInfo.getClickRates() + 1);
			blogMapper.updateBlog(blogInfo);			
		} else {
			return ReturnResult.error(CodeMsg.NOT_FIND_DATA);
		}
		return ReturnResult.success(blogInfo);
	}

	@Override
	public ReturnResult<Map<String, List<BlogInfo>>> findBlog(String uid, Date startDate, Date endDate) {
		List<BlogInfo> blogInfoList = blogMapper.findBlog(startDate, endDate, uid);
		SimpleDateFormat sdfbyDay = new SimpleDateFormat("yyyy/MM/dd");
		Map<String, List<BlogInfo>> result = new HashMap<>();
		for (BlogInfo blogInfo : blogInfoList) {
			String date = sdfbyDay.format(blogInfo.getCreateDate());
			if (result.get(date) != null) {
				result.get(date).add(blogInfo);
			} else {
				result.put(date, new ArrayList<>());
				result.get(date).add(blogInfo);
			}
		}
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<List<BlogInfo>> getOneDayBlog(Date date) {
		List<BlogInfo> blogInfoList = blogMapper.findBlog(date, null, null);
		return ReturnResult.success(blogInfoList);
	}

	@Override
	public ReturnResult<Map<String, Long>> getOneYearCount(Integer year) {
		Map<String, Long> result = new HashMap<>();
		List<Map<String, Object>> blogCount = blogMapper.findOneYearBlogCount(year);
		for (Map<String, Object> map : blogCount) {
			String month = (String) map.get("month");
			Long count = (Long) map.get("count");
			result.put(month, count);
		}
		return ReturnResult.success(result);
	}
}
