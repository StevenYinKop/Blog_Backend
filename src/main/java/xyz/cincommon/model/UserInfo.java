package xyz.cincommon.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class UserInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7739236975122803723L;

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