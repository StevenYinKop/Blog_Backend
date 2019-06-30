package xyz.cincommon.mapper;

import xyz.cincommon.model.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}