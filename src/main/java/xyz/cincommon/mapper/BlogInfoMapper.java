package xyz.cincommon.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import xyz.cincommon.model.BlogInfo;

public interface BlogInfoMapper {

    public List<BlogInfo> findTop10BlogInfo(@Param("start")int start, @Param("offset")int offset);

    public List<BlogInfo> findByIdWithPrePost(@Param("id") String id);

    public BlogInfo findById(String id);

    public Integer updateBlog(BlogInfo blogInfo);

    public List<BlogInfo> findBlog(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                   @Param("uid") String uid);

    public List<Map<String, Object>> findOneYearBlogCount(@Param("year") int year);

    public List<BlogInfo> findBlogByKeywordTagForum(@Param("keyword") String keyword,
                                                    @Param("tagIdArray") String[] tagIdArray, 
                                                    @Param("forumId") String forumId,
                                                    @Param("start") Integer start,
                                                    @Param("offset") Integer offset);

    public void insertBlog(BlogInfo blogInfo);

    public Integer countBlog();

    int deleteByPrimaryKey(Integer id);

    int insert(BlogInfo record);

    int insertSelective(BlogInfo record);

    BlogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogInfo record);

    int updateByPrimaryKeyWithBLOBs(BlogInfo record);

    int updateByPrimaryKey(BlogInfo record);
}