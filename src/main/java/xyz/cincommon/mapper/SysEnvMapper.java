package xyz.cincommon.mapper;

import java.util.List;

import xyz.cincommon.model.SysEnv;

public interface SysEnvMapper {
    int deleteByPrimaryKey(String envName);

    int insert(SysEnv record);

    int insertSelective(SysEnv record);

    SysEnv selectByPrimaryKey(String envName);

    int updateByPrimaryKeySelective(SysEnv record);

    int updateByPrimaryKey(SysEnv record);

	List<SysEnv> selectByKeys(List<String> list);
}