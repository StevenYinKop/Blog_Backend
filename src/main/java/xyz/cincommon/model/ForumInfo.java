package xyz.cincommon.model;

import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;

@Data
public class ForumInfo {
	private Integer forumId;
	private String forumName;
	private String forumPicUrl;
	private Timestamp createDate;
	private Integer createUser;
	private Timestamp updateDate;
	private String updateUser;
	private Set<BlogInfo> blogInfoSet;
}
