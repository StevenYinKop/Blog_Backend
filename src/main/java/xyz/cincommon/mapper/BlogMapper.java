package xyz.cincommon.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import xyz.cincommon.model.BlogInfo;

public interface BlogMapper {

	public List<BlogInfo> findTop10BlogInfo();
	
	public List<BlogInfo> findById(@Param("id")String id);
	
	public Integer updateBlog(BlogInfo blogInfo);
	
	public List<BlogInfo> findBlog(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("uid")String uid);

	public List<Map<String, Object>> findOneYearBlogCount(@Param("year") int year);
}
