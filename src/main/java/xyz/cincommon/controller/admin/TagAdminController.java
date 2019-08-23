package xyz.cincommon.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import xyz.cincommon.model.TagInfo;
import xyz.cincommon.service.TagService;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.ReturnResult;

@RequestMapping("/admin")
@RestController
public class TagAdminController {
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @GetMapping("/getTagList")
    @ResponseBody
    public ReturnResult<PageInfo<TagInfo>> getTagList(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            HttpServletRequest request) {
		userService.checkCurrentUserRole(Constant.Role.ADMIN, Constant.Role.BLOGGER);
        return tagService.getTag(pageSize, pageNum);
    }
}
