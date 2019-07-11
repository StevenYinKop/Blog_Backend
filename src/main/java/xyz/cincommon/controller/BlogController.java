package xyz.cincommon.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import xyz.cincommon.model.BlogInfo;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.vo.ReturnResult;

@RestController
public class BlogController{
    @Autowired
    private BlogService blogService;
    @GetMapping("/get/blog/{id}")
    public ReturnResult<Map<String, Object>> getBlog(@PathVariable("id") String id) {
        return blogService.findBlogById(id);
    }


    @GetMapping("/get/queryByDate")
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

    @GetMapping("/get/getOneYearCount/{year}")
    public ReturnResult<Map<String, Long>> getOneYearCount(@PathVariable("year") Integer year) {
        return blogService.getOneYearCount(year);
    }

    @GetMapping("/get/getOneDayBlog/{date}")
    public ReturnResult<List<BlogInfo>> getOneDayBlog(@PathVariable("date") String date) {
        return blogService.getOneDayBlog(new Date(Long.parseLong(date)));
    }
}	
