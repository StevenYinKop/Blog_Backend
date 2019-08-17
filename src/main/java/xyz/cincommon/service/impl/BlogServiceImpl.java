package xyz.cincommon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.mapper.BlogInfoMapper;
import xyz.cincommon.mapper.ForumInfoMapper;
import xyz.cincommon.mapper.TagInfoMapper;
import xyz.cincommon.mapper.UserInfoMapper;
import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.model.User;
import xyz.cincommon.model.UserInfo;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.utils.UserUtil;
import xyz.cincommon.vo.BlogTableVo;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogInfoMapper blogInfoMapper;
	@Autowired
	private TagInfoMapper tagInfoMapper;
	@Autowired
	private ForumInfoMapper forumInfoMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public ReturnResult<Map<String, Object>> initMain(int pageSize, int pageNum) {
		Map<String, Object> result = new HashMap<>();
		Integer total = blogInfoMapper.countBlog();
		result.put("total", total);
		result.put("list", blogInfoMapper.findTop10BlogInfo(pageSize * pageNum, pageSize));
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<Map<String, Object>> findBlogById(String id) {
		BlogInfo blogInfo = blogInfoMapper.findById(id);
		Map<String, Object> result = Maps.newHashMap();
		result.put("blogInfo", blogInfo);
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<Map<String, Object>> findBlogByIdWithPreAndPost(String id) {
		// 查找当前Blog, 同时查出相邻的两篇博客用于预览
		List<BlogInfo> blogInfoList = blogInfoMapper.findByIdWithPrePost(id);
		BlogInfo currentBlog = null, prevBlog = null, postBlog = null;
		Integer currentId = Integer.valueOf(id);
		for (BlogInfo blogInfo : blogInfoList) {
			if (blogInfo.getId() == currentId) {
				currentBlog = blogInfo;
			} else if (blogInfo.getId() > currentId) {
				postBlog = new BlogInfo();
				postBlog.setId(blogInfo.getId());
				postBlog.setTitle(blogInfo.getTitle());
				postBlog = blogInfo;
			} else if (blogInfo.getId() < currentId) {
				prevBlog = new BlogInfo();
				prevBlog.setId(blogInfo.getId());
				prevBlog.setTitle(blogInfo.getTitle());
				prevBlog = blogInfo;
			}
		}
		if (currentBlog != null) {
			currentBlog.setClickRates(currentBlog.getClickRates() + 1);
			blogInfoMapper.updateBlog(currentBlog);
			String content = currentBlog.getContent();
			String result = replaceWildcard(content);
			currentBlog.setContent(result);
		} else {
			return ReturnResult.error(CodeMsg.NOT_FIND_DATA);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("currentBlog", currentBlog);
		result.put("prevBlog", prevBlog);
		result.put("postBlog", postBlog);
		return ReturnResult.success(result);
	}

	private String replaceWildcard(String content) {
		return StringUtils.replace(content, Constant.CONTENT_URL_WILDCARD, "http://47.102.152.87");
	}

	@Override
	public ReturnResult<Map<String, List<BlogInfo>>> findBlog(String uid, Date startDate, Date endDate) {
		List<BlogInfo> blogInfoList = blogInfoMapper.findBlog(startDate, endDate, uid);
		SimpleDateFormat sdfbyDay = new SimpleDateFormat("yyyy/MM/dd");
		Map<String, List<BlogInfo>> result = new HashMap<>();
		for (BlogInfo blogInfo : blogInfoList) {
			String date = sdfbyDay.format(blogInfo.getCreateDate());
			if (result.get(date) != null) {
				result.get(date).add(blogInfo);
			} else {
				result.put(date, new ArrayList<>());
				result.get(date).add(blogInfo);
			}
		}
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<List<BlogInfo>> getOneDayBlog(Date date) {
		List<BlogInfo> blogInfoList = blogInfoMapper.findBlog(date, null, null);
		return ReturnResult.success(blogInfoList);
	}

	@Override
	public ReturnResult<Map<String, Long>> getOneYearCount(Integer year) {
		Map<String, Long> result = new HashMap<>();
		List<Map<String, Object>> blogCount = blogInfoMapper.findOneYearBlogCount(year);
		for (Map<String, Object> map : blogCount) {
			String month = (String) map.get("month");
			Long count = (Long) map.get("count");
			result.put(month, count);
		}
		return ReturnResult.success(result);
	}

	@Override
	public ReturnResult<Map<String, Object>> getBlogList(String keyword, String tagIdList, String forumId, int pageSize,
			int pageNum) {
		Map<String, Object> map = new HashMap<>();
		String[] tagIdArray = StringUtils.splitByWholeSeparator(tagIdList, ",");
		List<BlogInfo> blogInfoList = blogInfoMapper.findBlogByKeywordTagForum(keyword, tagIdArray, forumId, (pageNum - 1) * pageSize, pageSize);
		int total = blogInfoMapper.countBlog();
		List<BlogTableVo> blogTableVoList = Lists.newLinkedList();
		for (BlogInfo blogInfo : blogInfoList) {
			blogTableVoList.add(convertBlogInfoToBlogTableVo(blogInfo));
		}
		map.put("list", blogTableVoList);
		map.put("total", total);
		return ReturnResult.success(map);
	}

	public BlogTableVo convertBlogInfoToBlogTableVo(BlogInfo blogInfo) {
		BlogTableVo vo = new BlogTableVo();
		BeanUtils.copyProperties(blogInfo, vo);
		UserInfo authorInfo = userInfoMapper.selectByPrimaryKey(vo.getUid());
		vo.setAuthor(authorInfo.getUserName());
		vo.setCreateDateStr(sdf.format(vo.getCreateDate()));
		vo.setStatusName("已发布");
		return vo;
	}
	
	@Override
	public ReturnResult<Map<String, Object>> saveBlogInfo(String blogId, String title, String content,
			String introduction) throws BlogException {
		User user = UserUtil.getUser();
		Integer uid = user.getUid();
		BlogInfo blogInfo;
		if (StringUtils.isEmpty(blogId)) {
			blogInfo = blogInfoMapper.findById(blogId);
			if (ObjectUtils.isEmpty(blogInfo)) {
				throw new BlogException(CodeMsg.NOT_FIND_BLOG);
			}
			blogInfo.setContent(content);
			blogInfo.setIntroduction(introduction);
			blogInfo.setTitle(title);
			blogInfo.setUpdateUser(uid.toString());
			blogInfo.setUpdateDate(new Date());
			blogInfoMapper.updateBlog(blogInfo);
		} else {
			blogInfo = new BlogInfo();
			blogInfo.setUid(uid);
			blogInfo.setContent(content);
			blogInfo.setIntroduction(introduction);
			blogInfo.setTitle(title);
			blogInfo.setReplyAmount(0);
			blogInfo.setClickRates(0);
			blogInfo.setCreateUser(uid.toString());
			blogInfo.setCreateDate(new Date());
			blogInfo.setUpdateUser(uid.toString());
			blogInfo.setUpdateDate(new Date());
			blogInfoMapper.insertBlog(blogInfo);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("blogInfo", blogInfo);
		return ReturnResult.success(map);
	}

	@Override
	public ReturnResult<Map<String, Object>> initBlogView() {
		Map<String, Object> res = new HashMap<>();
		res.put("tagList", tagInfoMapper.findAllTagInfo());
		res.put("forumList", forumInfoMapper.selectAll());
		return ReturnResult.success(res);
	}
}
