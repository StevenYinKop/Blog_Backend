package xyz.cincommon.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cincommon.mapper.ForumMapper;
import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.vo.ReturnResult;
@Service
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumMapper forumMapper;
	@Override
	public ReturnResult<List<ForumInfo>> getForum() {
		return ReturnResult.success(forumMapper.findAllForumInfo());
	}

	@Override
	public ReturnResult<ForumInfo> getForumById(String id) throws IllegalAccessException, InvocationTargetException {
		ForumInfo forum = forumMapper.findForumById(id);
		return ReturnResult.success(forum);
	}

}
