package xyz.cincommon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogTag implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4125447349125853244L;

	private Integer blogId;

    private Integer tagId;
}