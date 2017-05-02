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

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.L2DatabaseFactory;

public class ChangePassRequest extends L2ACPRequest
{
	private String Username;
	
	private String CurrentPassword;
	
	private String NewPassword;

	@Override
	public L2ACPResponse getResponse()
	{
		String query = "SELECT login, password FROM accounts WHERE login=?";
		boolean validPass = false;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query))
		{
			ps.setString(1, Username);
			try (ResultSet rset = ps.executeQuery())
			{
				if (rset.next())
				{
					String pass = rset.getString("password");
					
					validPass = pass.equals(CurrentPassword);					
				}
			}
			
			if(validPass){
				try(PreparedStatement ps2 = con.prepareStatement("update accounts set password=? where login=?")){
					ps2.setString(1, NewPassword);
					ps2.setString(2, Username);
					ps2.executeUpdate();
					ps2.clearParameters();
				}
				return new L2ACPResponse(200, "Successful update");
			}
			return new L2ACPResponse(500, "Unsuccessful update. Invalid password.");			
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
		
		Username = content.get("Username").getAsString();
		CurrentPassword = content.get("CurrentPassword").getAsString();
		NewPassword = content.get("NewPassword").getAsString();
	}
}
