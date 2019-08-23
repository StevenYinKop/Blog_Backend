package xyz.cincommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.cincommon.mapper.ForumInfoMapper;
import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.vo.ReturnResult;
@Service
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumInfoMapper forumInfoMapper;
	@Override
	public ReturnResult<PageInfo<ForumInfo>> getForum(Integer pageNum, Integer pageSize) {
		if (null != pageNum && null != pageSize) {
			PageHelper.startPage(pageNum, pageSize);
		}
		PageInfo<ForumInfo> result = PageInfo.of(forumInfoMapper.selectAll());
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<ForumInfo> getForumById(String id) {
		ForumInfo forum = forumInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
		return ReturnResult.success(forum);
	}

}
