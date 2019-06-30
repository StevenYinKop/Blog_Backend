package xyz.cincommon.mapper;

import xyz.cincommon.model.BlogTag;

public interface BlogTagMapper {
    int insert(BlogTag record);

    int insertSelective(BlogTag record);
}