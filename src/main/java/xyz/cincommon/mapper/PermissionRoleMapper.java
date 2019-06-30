package xyz.cincommon.mapper;

import xyz.cincommon.model.PermissionRole;

public interface PermissionRoleMapper {
    int insert(PermissionRole record);

    int insertSelective(PermissionRole record);
}