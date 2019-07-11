package xyz.cincommon.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.TagService;
import xyz.cincommon.vo.ReturnResult;

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
