package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.ForumInfo;

public interface ForumMapper {

	public List<ForumInfo> findAllForumInfo();
	
	public ForumInfo findForumById(String id);
 	
}
