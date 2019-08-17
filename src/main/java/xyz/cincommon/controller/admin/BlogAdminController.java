package xyz.cincommon.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.cincommon.exception.BlogException;
import xyz.cincommon.service.BlogService;
import xyz.cincommon.service.UserService;
import xyz.cincommon.utils.Constant;
import xyz.cincommon.vo.ReturnResult;

@RestController
@RequestMapping("/admin")
public class BlogAdminController {

    @Autowired
    private BlogService blogService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/initBlogView")
    @ResponseBody
    public ReturnResult<Map<String, Object>> initBlogView(HttpServletRequest request) {
		userService.checkCurrentUserRole(request.getSession(), Constant.Role.ADMIN, Constant.Role.BLOGGER);
        return blogService.initBlogView();
    }

    @GetMapping("/getBlogList")
    @ResponseBody
    public ReturnResult<Map<String, Object>> getBlogList(
    		HttpSession session,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "tagIdList", required = false, defaultValue = "") String tagIdList,
            @RequestParam(value = "forumId", required = false) String forumId,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		userService.checkCurrentUserRole(session, Constant.Role.ADMIN, Constant.Role.BLOGGER);
        return blogService.getBlogList(keyword, tagIdList, forumId, pageSize, pageNum);
    }

    @GetMapping("/getBlogById")
    @ResponseBody
    public ReturnResult<Map<String, Object>> getBlogById(HttpSession session, String id) {
		userService.checkCurrentUserRole(session, Constant.Role.ADMIN, Constant.Role.BLOGGER);
        return blogService.findBlogById(id);
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
    @ResponseBody
    public ReturnResult<Map<String, Object>> saveBlogInfo(HttpSession session,
    													  @RequestParam(required = false) String blogId,
                                                          @RequestParam(required = false) String user, String title, String content, String introduction,
                                                          String tagIdList, @RequestParam(required = false) String forumId) throws BlogException {
    	userService.checkCurrentUserRole(session, Constant.Role.ADMIN, Constant.Role.BLOGGER);
        if (StringUtils.isBlank(tagIdList)) {
            throw new BlogException("没有选择Tag");
        }
        return blogService.saveBlogInfo(blogId, title, content, introduction);
    }
}
