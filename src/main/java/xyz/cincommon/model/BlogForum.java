package xyz.cincommon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogForum implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3088695190583091651L;

	private Integer blogId;

    private Integer forumId;
}