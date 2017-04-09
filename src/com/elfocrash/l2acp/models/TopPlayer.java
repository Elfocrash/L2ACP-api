package com.elfocrash.l2acp.models;

public class TopPlayer {
	private String CharName;
	private int Level;
	private int PvpKills;
	private int PkKills;
	private int Online;
	private int OnlineTime;
	private String ClassName;
	
	public TopPlayer(String charName, int level, int pvpkills, int pkkills, int online, int onlineTime,String className){
		CharName = charName;
		Level = level;
		PvpKills = pvpkills;
		PkKills = pkkills;
		Online = online;
		OnlineTime = onlineTime;
		ClassName = className;
	}
}
