package xyz.cincommon.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.cincommon.mapper.TagInfoMapper;
import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.TagService;
import xyz.cincommon.vo.ReturnResult;

@Component
public class TagServiceImpl implements TagService {
	@Autowired
	private TagInfoMapper tagInfoMapper;

	@Override
	public ReturnResult<Collection<TagInfo>> getTag() {
		List<TagInfo> allTagInfo = tagInfoMapper.findAllTagInfo();
		Collections.sort(allTagInfo, (t1, t2) -> t2.getBlogInfoSet().size() - t1.getBlogInfoSet().size());
		return ReturnResult.success(allTagInfo);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ReturnResult<PageInfo<TagInfo>> getTag(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo info = new PageInfo(tagInfoMapper.findAllTagInfo());
		return ReturnResult.success(info);
	}

	@Override
	public ReturnResult<TagInfo> getTagById(String id) throws Exception {
		TagInfo tagInfo = tagInfoMapper.findTagById(id);
		return ReturnResult.success(tagInfo);
	}
}
