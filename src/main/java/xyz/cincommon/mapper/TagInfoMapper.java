package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.TagInfo;

public interface TagInfoMapper {

    List<TagInfo> findAllTagInfo();

    TagInfo findTagById(String id);

    int deleteByPrimaryKey(Integer tagId);

    int insert(TagInfo record);

    int insertSelective(TagInfo record);

    TagInfo selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(TagInfo record);

    int updateByPrimaryKey(TagInfo record);
}