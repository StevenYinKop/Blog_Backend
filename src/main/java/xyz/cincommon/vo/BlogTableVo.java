package xyz.cincommon.vo;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import xyz.cincommon.model.ForumInfo;
import xyz.cincommon.model.TagInfo;

@Data
public class BlogTableVo {
	private Integer id;
	private Integer uid;
	private String title;
	private String introduction;
	private Integer clickRates;
	private Integer replyAmount;
	private Date createDate;
	private String status;

	private Set<TagInfo> tagInfoSet;
	private ForumInfo forumInfo;
	
	private String createDateStr;
	private String statusName;
	private String author;
}
