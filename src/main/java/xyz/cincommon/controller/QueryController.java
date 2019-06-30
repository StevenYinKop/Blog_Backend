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

	@GetMapping("/initMain")
	public ReturnResult<Map<String, Object>> initMain(
			@RequestParam(required = false, name = "pageSize", defaultValue="10") int pageSize,
			@RequestParam(required = false, name = "pageNum", defaultValue="1") int pageNum
	) throws Exception {
		return blogService.initMain(pageSize, pageNum);
	}
}
