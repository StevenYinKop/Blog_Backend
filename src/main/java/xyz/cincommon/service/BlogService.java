package xyz.cincommon.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.vo.ReturnResult;

public interface BlogService {
	ReturnResult<Map<String, Object>> initMain(int pageSize, int pageNum) throws Exception;
	
	ReturnResult<Map<String, Object>> findBlogById(String id);

	ReturnResult<Map<String, Object>> findBlogByIdWithPreAndPost(String id);

	ReturnResult<Map<String, List<BlogInfo>>> findBlog(String uid, Date startDate, Date endDate);

	ReturnResult<List<BlogInfo>> getOneDayBlog(Date date);

	ReturnResult<Map<String, Long>> getOneYearCount(Integer year);

	ReturnResult<Map<String, Object>> getBlogList(String keyword, String tagIdList, String forumId, int pageSize, int pageNum);

	ReturnResult<Map<String, Object>> saveBlogInfo(String blogId, String title, String content,
			String introduction, String tagIdList, String forumId) throws BlogException;

	ReturnResult<Map<String, Object>> initBlogView();
}
