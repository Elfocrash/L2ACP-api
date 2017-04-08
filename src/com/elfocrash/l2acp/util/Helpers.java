package com.elfocrash.l2acp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.responses.L2ACPResponse;

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
	
	public int getDonatePoints(String accountName){
		String query = "SELECT donatepoints FROM accounts WHERE login=?";
		try (Connection con = L2DatabaseFactory.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query))
		{
			ps.setString(1, accountName);
			try (ResultSet rset = ps.executeQuery())
			{
				if (rset.next())
				{
					int donatepoints = rset.getInt("donatepoints");
					return donatepoints;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<BuyListItem> getDonateItemList(){
		
		ArrayList<BuyListItem> invInfo = new ArrayList<BuyListItem>(); 
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			PreparedStatement statement = con.prepareStatement("SELECT itemId,itemCount,enchant,price FROM donateitems");
			ResultSet itemList = statement.executeQuery();
			
			while (itemList.next())// fills the package
			{
				int itemId = itemList.getInt("itemId");
				int itemCount = itemList.getInt("itemCount");
				int enchant = itemList.getInt("enchant");
				int price = itemList.getInt("price");
				invInfo.add(new BuyListItem(itemId,itemCount,enchant,price));
			}
			
			itemList.close();
			statement.close();			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invInfo;
	}
}
