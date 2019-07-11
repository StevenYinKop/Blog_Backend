package xyz.cincommon.model;

import java.util.Date;

import lombok.Data;
@Data
public class UserInfo {
    private Integer uid;

    private String userName;

    private String password;

    private String avatarUrl;

    private Date createDate;

    private String status;

    private Date updateDate;

    private Integer updateUser;

    private String profile;
}