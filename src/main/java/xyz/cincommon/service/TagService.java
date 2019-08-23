package xyz.cincommon.service;

import java.util.Collection;

import com.github.pagehelper.PageInfo;

import xyz.cincommon.model.TagInfo;
import xyz.cincommon.vo.ReturnResult;

public interface TagService {

	public ReturnResult<TagInfo> getTagById(String id) throws Exception;	
	public ReturnResult<Collection<TagInfo>> getTag();	
	public ReturnResult<PageInfo<TagInfo>> getTag(Integer pageNum, Integer pageSize);
}
