package xyz.cincommon.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.cincommon.model.User;
import xyz.cincommon.model.UserInfo;

public interface UserInfoMapper {


    User findByUsername(@Param("username") String username);

    int deleteByPrimaryKey(Integer uid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}