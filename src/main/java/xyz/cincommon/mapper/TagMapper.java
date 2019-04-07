package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.TagInfo;

public interface TagMapper {

	public List<TagInfo> findAllTagInfo();
	
	public TagInfo findTagById(String id);
 	
}
