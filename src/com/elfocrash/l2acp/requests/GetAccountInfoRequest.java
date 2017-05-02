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

import com.elfocrash.l2acp.responses.GetAccountInfoResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.CharSelectInfoPackage;

/**
 * @author Elfocrash
 *
 */
public class GetAccountInfoRequest extends L2ACPRequest
{
	private String Username;
	
	
	@Override
	public L2ACPResponse getResponse()
	{
		ArrayList<String> chars = new ArrayList<>();
		int donatePoints = 0;
		int accessLevel = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			PreparedStatement statement = con.prepareStatement("SELECT account_name, obj_Id, char_name, level, maxHp, curHp, maxMp, curMp, face, hairStyle, hairColor, sex, heading, x, y, z, exp, sp, karma, pvpkills, pkkills, clanid, race, classid, deletetime, cancraft, title, accesslevel, online, char_slot, lastAccess, base_class FROM characters WHERE account_name=?");
			statement.setString(1, Username);
			ResultSet charList = statement.executeQuery();
			
			while (charList.next())// fills the package
			{
				String charName = charList.getString("char_name");
				chars.add(charName);
			}
			
			charList.close();
			statement.close();
		
			try (PreparedStatement ps = con.prepareStatement("SELECT access_level, donatepoints FROM accounts WHERE login=?"))
			{
				ps.setString(1, Username);
				try (ResultSet rset = ps.executeQuery())
				{
					if (rset.next())
					{
						donatePoints = rset.getInt("donatepoints");
						accessLevel = rset.getInt("access_level");
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return new L2ACPResponse(500, "Unsuccessful retrieval");
			}
			
			return new GetAccountInfoResponse(200,"Success", chars.toArray(new String[chars.size()]), donatePoints, accessLevel);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new L2ACPResponse(500, "Unsuccessful retrieval");
		}
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
	}
}
