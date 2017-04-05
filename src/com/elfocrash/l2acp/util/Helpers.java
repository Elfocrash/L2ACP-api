package com.elfocrash.l2acp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.l2j.L2DatabaseFactory;

public class Helpers {
	public int getPlayerIdByName(String name){
		String query = "SELECT obj_Id FROM characters WHERE char_name=?";
		try (Connection con = L2DatabaseFactory.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query))
		{
			ps.setString(1, name);
			try (ResultSet rset = ps.executeQuery())
			{
				if (rset.next())
				{
					int objId = rset.getInt("obj_Id");
					return objId;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
