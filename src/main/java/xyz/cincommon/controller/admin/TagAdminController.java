package xyz.cincommon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.TagService;
import xyz.cincommon.vo.ReturnResult;

public class TagAdminController {
    @Autowired
    private TagService tagService;
    @GetMapping("/getTagList")
    @ResponseBody
    public ReturnResult<PageInfo<TagInfo>> getTagList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum) {
        return tagService.getTag(pageSize, pageNum);
    }
}
