package xyz.cincommon.service;

import java.util.Collection;

import xyz.cincommon.model.TagInfo;
import xyz.cincommon.vo.ReturnResult;

public interface TagService {

	public ReturnResult<TagInfo> getTagById(String id) throws Exception;	
	public ReturnResult<Collection<TagInfo>> getTag();	
}
