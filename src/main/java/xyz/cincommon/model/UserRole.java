package xyz.cincommon.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1887449526743418914L;

	private Integer uid;

    private Integer rid;

}