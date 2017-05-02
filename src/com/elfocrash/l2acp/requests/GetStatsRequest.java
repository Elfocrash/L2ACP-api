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
package com.elfocrash.l2acp.requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elfocrash.l2acp.models.InventoryInfo;
import com.elfocrash.l2acp.models.TopPlayer;
import com.elfocrash.l2acp.responses.GetStatsResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.datatables.CharTemplateTable;
import net.sf.l2j.gameserver.model.actor.template.PcTemplate;
import net.sf.l2j.gameserver.model.base.ClassId;

public class GetStatsRequest extends L2ACPRequest
{
	@Override
	public L2ACPResponse getResponse()
	{
		String topPvpQuery = "SELECT char_name,level,pvpkills,pkkills,online,onlinetime,classid FROM characters order by pvpkills desc limit 20";
		String topPkQuery = "SELECT char_name,level,pvpkills,pkkills,online,onlinetime,classid FROM characters order by pkkills desc limit 20";
		String topTimeQuery = "SELECT char_name,level,pvpkills,pkkills,online,onlinetime,classid FROM characters order by onlinetime desc limit 20";
		
		ArrayList<TopPlayer> topPvp = new ArrayList<TopPlayer>();
		ArrayList<TopPlayer> topPk = new ArrayList<TopPlayer>();
		ArrayList<TopPlayer> topOnline = new ArrayList<TopPlayer>();
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement topPvpSt = con.prepareStatement(topPvpQuery);
				PreparedStatement topPkSt = con.prepareStatement(topPkQuery);
				PreparedStatement topOnlineSt = con.prepareStatement(topTimeQuery))
		{
			try (ResultSet rset = topPvpSt.executeQuery())
			{
				while (rset.next())
				{
					String charName = rset.getString("char_name");
					int level = rset.getInt("level");
					int pvpkills = rset.getInt("pvpkills");
					int pkkills = rset.getInt("pkkills");
					int online = rset.getInt("online");
					int onlinetime = rset.getInt("onlinetime");		
					int classid = rset.getInt("classid");		
					
					PcTemplate template = CharTemplateTable.getInstance().getTemplate(classid);
					String className = template.getClassName();
					TopPlayer player = new TopPlayer(charName,level,pvpkills,pkkills,online,onlinetime,className);
					topPvp.add(player);
				}
			}
			
			try (ResultSet rset = topPkSt.executeQuery())
			{
				while (rset.next())
				{
					String charName = rset.getString("char_name");
					int level = rset.getInt("level");
					int pvpkills = rset.getInt("pvpkills");
					int pkkills = rset.getInt("pkkills");
					int online = rset.getInt("online");
					int onlinetime = rset.getInt("onlinetime");		
					int classid = rset.getInt("classid");		
					
					PcTemplate template = CharTemplateTable.getInstance().getTemplate(classid);
					String className = template.getClassName();
					TopPlayer player = new TopPlayer(charName,level,pvpkills,pkkills,online,onlinetime,className);
					topPk.add(player);
				}
			}
			
			try (ResultSet rset = topOnlineSt.executeQuery())
			{
				while (rset.next())
				{
					String charName = rset.getString("char_name");
					int level = rset.getInt("level");
					int pvpkills = rset.getInt("pvpkills");
					int pkkills = rset.getInt("pkkills");
					int online = rset.getInt("online");
					int onlinetime = rset.getInt("onlinetime");		
					int classid = rset.getInt("classid");		
					
					PcTemplate template = CharTemplateTable.getInstance().getTemplate(classid);
					String className = template.getClassName();
					TopPlayer player = new TopPlayer(charName,level,pvpkills,pkkills,online,onlinetime,className);
					topOnline.add(player);
				}
			}
			
			return new GetStatsResponse(200, "Successful retrieval", topPvp.toArray(new TopPlayer[topPvp.size()])
					, topPk.toArray(new TopPlayer[topPk.size()]), topOnline.toArray(new TopPlayer[topOnline.size()]));
				
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new L2ACPResponse(500, "Unsuccessful update. Database error.");
		}
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
