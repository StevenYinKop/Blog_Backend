package xyz.cincommon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.cincommon.exception.BlogException;
import xyz.cincommon.mapper.BlogInfoMapper;
import xyz.cincommon.mapper.ForumInfoMapper;
import xyz.cincommon.mapper.TagInfoMapper;
import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.model.User;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.vo.CodeMsg;
import xyz.cincommon.vo.ReturnResult;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogInfoMapper blogInfoMapper;
	@Autowired
	private TagInfoMapper tagInfoMapper;
	@Autowired
	private ForumInfoMapper forumInfoMapper;

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
		List<BlogInfo> blogInfoList = blogInfoMapper.findByIdWithPrePost(id);
		BlogInfo currentBlog = null, prevBlog = null, postBlog = null;
		Integer currentId = Integer.valueOf(id);
		for (BlogInfo blogInfo : blogInfoList) {
			if (blogInfo.getId() == currentId) {
				currentBlog = blogInfo;
			} else if (blogInfo.getId() > currentId) {
				postBlog = blogInfo;
			} else if (blogInfo.getId() < currentId) {
				prevBlog = blogInfo;
			}
		}
		if (currentBlog != null) {
			currentBlog.setClickRates(currentBlog.getClickRates() + 1);
			blogInfoMapper.updateBlog(currentBlog);
		} else {
			return ReturnResult.error(CodeMsg.NOT_FIND_DATA);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("currentBlog", currentBlog);
		result.put("prevBlog", prevBlog);
		result.put("postBlog", postBlog);
		return ReturnResult.success(result);
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
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<BlogInfo> pageInfo = new PageInfo<>(
				blogInfoMapper.findBlogByKeywordTagForum(keyword, tagIdArray, forumId));
		map.put("pageInfo", pageInfo);
		return ReturnResult.success(map);
	}

	@Override
	public ReturnResult<Map<String, Object>> saveBlogInfo(String blogId, String title, String content,
			String introduction) throws BlogException {
		User principal = (User) SecurityUtils.getSubject().getPrincipal();
		Integer uid = principal.getUid();
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
