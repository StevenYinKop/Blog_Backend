package xyz.cincommon.model;

import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "tagId" })
public class TagInfo {
	private Integer tagId;
	private String tagName;
	private Timestamp createDate;
	private Integer createUser;
	private Timestamp updateDate;
	private Integer updateUser;
	private Set<BlogInfo> blogInfoSet;

}
