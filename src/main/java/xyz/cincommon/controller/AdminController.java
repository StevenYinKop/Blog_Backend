package xyz.cincommon.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import xyz.cincommon.exception.BlogException;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.vo.ReturnResult;

@RestController
@RequestMapping("/admin")
@RequiresAuthentication
@Api(authorizations = { @Authorization("已登录用户") })
public class AdminController {
	@Autowired
	private BlogService blogService;

	@GetMapping("/getBlogList")
	public ReturnResult<Map<String, Object>> getBlogList(
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "tagIdList", required = false, defaultValue = "") String tagIdList,
			@RequestParam(value = "forumId", required = false) String forumId,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		return blogService.getBlogList(keyword, tagIdList, forumId, pageSize, pageNum);
	}

	@GetMapping("/getTagList")
	public ReturnResult<Map<String, Object>> getTagList(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum) {
		return null;
	}

	@GetMapping("/getBlogById")
	public ReturnResult<Map<String, Object>> getBlogById(int id) {
		return null;
	}

	/**
	 * @param blogId
	 * @param user
	 * @param title
	 * @param content
	 * @param introduction
	 * @param tagIdList
	 * @param forumId
	 * @return
	 * @throws BlogException
	 */
	@PostMapping("/saveBlog")
	public ReturnResult<Map<String, Object>> saveBlogInfo(@RequestParam(required = false) String blogId,
			@RequestParam(required = false) String user, String title, String content, String introduction,
			String tagIdList, @RequestParam(required = false) String forumId) throws BlogException {
		return blogService.saveBlogInfo(blogId, title, content, introduction);
	}

	@PostMapping("/attachBlogAndTag")
	public ReturnResult<Map<String, Object>> attachBlogAndTag(int blogId, int tagId) {
		return null;
	}

}
