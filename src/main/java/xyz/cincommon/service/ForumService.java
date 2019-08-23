package xyz.cincommon.service;

import java.lang.reflect.InvocationTargetException;

import com.github.pagehelper.PageInfo;

import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.vo.ReturnResult;

public interface ForumService {

	ReturnResult<PageInfo<ForumInfo>> getForum(Integer pageNum, Integer pageSize);

	ReturnResult<ForumInfo> getForumById(String id) throws IllegalAccessException, InvocationTargetException;
}
