package xyz.cincommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.cincommon.mapper.ForumInfoMapper;
import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.vo.ReturnResult;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
@Service
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumInfoMapper forumInfoMapper;
	@Override
	public ReturnResult<List<ForumInfo>> getForum() {
		return ReturnResult.success(forumInfoMapper.selectAll());
	}

	@Override
	public ReturnResult<ForumInfo> getForumById(String id) {
		ForumInfo forum = forumInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
		return ReturnResult.success(forum);
	}

}
