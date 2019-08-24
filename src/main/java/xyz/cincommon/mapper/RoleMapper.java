package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<String> selectRoleNameByUserId(Integer uid);
}