package xyz.cincommon.mapper;

import org.apache.ibatis.annotations.Param;

import xyz.cincommon.model.User;

public interface UserMapper {

	User findByUsername(@Param("username") String username);
}
