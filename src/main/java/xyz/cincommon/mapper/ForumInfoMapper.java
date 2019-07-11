package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.ForumInfo;

public interface ForumInfoMapper {
    int deleteByPrimaryKey(Integer forumId);

    int insert(ForumInfo record);

    int insertSelective(ForumInfo record);

    ForumInfo selectByPrimaryKey(Integer forumId);

    int updateByPrimaryKeySelective(ForumInfo record);

    int updateByPrimaryKeyWithBLOBs(ForumInfo record);

    int updateByPrimaryKey(ForumInfo record);

    List<ForumInfo> selectAll();
}