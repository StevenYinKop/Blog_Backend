package xyz.cincommon.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.TagService;
import xyz.cincommon.vo.ReturnResult;

import java.util.Collection;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/get/tag")
    public ReturnResult<Collection<TagInfo>> getTag() {
        return tagService.getTag();
    }

    @GetMapping("/get/tag/{id}")
    public ReturnResult<TagInfo> getTagById(@PathVariable("id") String id) throws Exception {
        return tagService.getTagById(id);
    }
}
