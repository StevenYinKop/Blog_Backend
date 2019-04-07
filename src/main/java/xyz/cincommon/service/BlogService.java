package xyz.cincommon.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.vo.ReturnResult;

public interface BlogService {
	ReturnResult<List<BlogInfo>> initMain() throws Exception;
	
	ReturnResult<BlogInfo> findBlogById(String id);

	ReturnResult<Map<String, List<BlogInfo>>> findBlog(String uid, Date startDate, Date endDate);

	ReturnResult<List<BlogInfo>> getOneDayBlog(Date date);

	ReturnResult<Map<String, Long>> getOneYearCount(Integer year);
}
