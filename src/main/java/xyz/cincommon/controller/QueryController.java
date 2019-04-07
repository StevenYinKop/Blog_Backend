package xyz.cincommon.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.service.TagService;
import xyz.cincommon.vo.ReturnResult;

@RestController
@RequestMapping("/get")
public class QueryController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private TagService tagService;
	@Autowired
	private ForumService forumService;

	@GetMapping("/blog/{id}")
	public ReturnResult<BlogInfo> getBlog(@PathVariable("id") String id) {
		return blogService.findBlogById(id);
	}

	@RequestMapping("/initMain")
	public ReturnResult<List<BlogInfo>> initMain() throws Exception {
		return blogService.initMain();
	}

	@GetMapping("/queryByDate")
	public ReturnResult<Map<String, List<BlogInfo>>> findBlog(@RequestParam(required = false, name = "uid") String uid,
			@RequestParam(required = false, name = "startDate") String startStr,
			@RequestParam(required = false, name = "endDate") String endStr) throws Exception {
		Date startDate = null;
		Date endDate = null;
		if (!StringUtils.isEmpty(startStr)) {
			startDate = new Date(Long.parseLong(startStr));
		}
		if (endStr != null) {
			endDate = new Date(Long.parseLong(endStr));
		}
		return blogService.findBlog(uid, startDate, endDate);
	}

	@GetMapping("/getOneYearCount/{year}")
	public ReturnResult<Map<String, Long>> getOneYearCount(@PathVariable("year") Integer year) {
		return blogService.getOneYearCount(year);
	}

	@GetMapping("/getOneDayBlog/{date}")
	public ReturnResult<List<BlogInfo>> getOneDayBlog(@PathVariable("date") String date) {
		return blogService.getOneDayBlog(new Date(Long.parseLong(date)));
	}

	@GetMapping("/tag")
	public ReturnResult<Collection<TagInfo>> getTag() {
		return tagService.getTag();
	}

	@GetMapping("/tag/{id}")
	public ReturnResult<TagInfo> getTagById(@PathVariable("id") String id) throws Exception {
		return tagService.getTagById(id);
	}

	@GetMapping("/forum")
	public ReturnResult<List<ForumInfo>> getForum() {
		return forumService.getForum();
	}

	@GetMapping("/forum/{id}")
	public ReturnResult<ForumInfo> getForumById(@PathVariable("id") String id) throws Exception {
		return forumService.getForumById(id);
	}
}
