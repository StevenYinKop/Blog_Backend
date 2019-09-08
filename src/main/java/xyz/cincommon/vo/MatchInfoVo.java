package xyz.cincommon.vo;

import lombok.Data;

@Data
public class MatchInfoVo {
	private String awayTeam;
	private String awayTeamLogo;
	private String homeTeam;
	private String homeTeamLogo;
	private String matchDate;
	private String result;
	private String type;
}
