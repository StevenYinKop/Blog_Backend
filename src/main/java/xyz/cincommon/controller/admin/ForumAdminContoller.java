package xyz.cincommon.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.service.ForumService;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.ReturnResult;

@RequestMapping("admin")
@RestController
public class ForumAdminContoller {
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/getForumList")
    @ResponseBody
    public ReturnResult<PageInfo<ForumInfo>> getTagList(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            HttpServletRequest request) {
		userService.checkCurrentUserRole(request.getSession(), Constant.Role.ADMIN, Constant.Role.BLOGGER);
        return forumService.getForum(pageSize, pageNum);
    }

}
