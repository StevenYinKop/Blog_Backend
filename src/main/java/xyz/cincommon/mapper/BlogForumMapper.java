package xyz.cincommon.mapper;

import xyz.cincommon.model.BlogForum;

public interface BlogForumMapper {
    int insert(BlogForum record);

    int insertSelective(BlogForum record);
}