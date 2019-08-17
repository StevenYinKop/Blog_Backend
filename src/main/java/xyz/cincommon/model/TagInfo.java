package xyz.cincommon.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "tagId" })
public class TagInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2793582800588077311L;
	private Integer tagId;
	private String tagName;
	private Timestamp createDate;
	private Integer createUser;
	private Timestamp updateDate;
	private Integer updateUser;
	private Set<BlogInfo> blogInfoSet;

}
