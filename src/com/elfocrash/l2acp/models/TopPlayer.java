/*
 * Copyright (C) 2017  Nick Chapsas
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 * 
 * L2ACP is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
