package xyz.cincommon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.vo.ReturnResult;

public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public ReturnResult<List<ForumInfo>> getForum() {
        return forumService.getForum();
    }

    @GetMapping("/forum/{id}")
    public ReturnResult<ForumInfo> getForumById(@PathVariable("id") String id) throws Exception {
        return forumService.getForumById(id);
    }
}
