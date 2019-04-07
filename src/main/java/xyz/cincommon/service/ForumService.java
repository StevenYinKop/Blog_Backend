package xyz.cincommon.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.vo.ReturnResult;

public interface ForumService {

	ReturnResult<List<ForumInfo>> getForum();

	ReturnResult<ForumInfo> getForumById(String id) throws IllegalAccessException, InvocationTargetException;
}
