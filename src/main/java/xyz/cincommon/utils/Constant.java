package xyz.cincommon.utils;

public class Constant {

	
	public static final String DEFAULT_ENCRYPT_KEY = "YNWAForLiverpool";
	public static final String CONTENT_URL_WILDCARD = "{{@@IMAGE_URL@@}}";
	public static final String CURRENT_USER = "CUR_USER";
    public static final String SEPARATOR = "/";
    public static final String LIV_CN = "利物浦";
	
	public class Blog {
		public static final int SUMMARY_LENGTH = 200;
	}
	
	public enum Role {
		ADMIN(1, "admin"),
		BLOGGER(2, "blogger"),
		USER(3, "user");
		private int id;
		private String name;
		private Role(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
}
