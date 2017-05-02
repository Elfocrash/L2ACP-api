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

public class DonateRequest extends L2ACPRequest {

	private String AccountName;
	private int	Amount;
	private String TransactionId;
	private String VerificationSign;
	
	@Override
	public L2ACPResponse getResponse() {

		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT count(*) as Count FROM l2acp_donations WHERE accountName=? and transactionid=? and verificationSign=?"))
		{
			ps.setString(1, AccountName);
			ps.setString(2, TransactionId);
			ps.setString(3, VerificationSign);
			try (ResultSet rset = ps.executeQuery())
			{
				if (rset.next())
				{
					int count = rset.getInt("Count");
					if(count > 0)
						return new L2ACPResponse(500, "Unsuccessful attempt");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new L2ACPResponse(500, "Unsuccessful attempt");
		}
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("insert into l2acp_donations (accountName,amount,transactionid,verificationSign,timestamp) values (?,?,?,?,?)"))
		{
			ps.setString(1, AccountName);
			ps.setInt(2, Amount);
			ps.setString(3, TransactionId);
			ps.setString(4, VerificationSign);
			ps.setLong(5, System.currentTimeMillis());
			ps.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new L2ACPResponse(500, "Unsuccessful attempt");
		}
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("update accounts set donatepoints=(donatepoints + ?) WHERE login=?"))
		{
			ps.setInt(1, Amount);
			ps.setString(2, AccountName);
			ps.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new L2ACPResponse(500, "Unsuccessful retrieval");
		}

		return new L2ACPResponse(200,"Success");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		AccountName = content.get("AccountName").getAsString();
		Amount = content.get("Amount").getAsInt();
		TransactionId = content.get("TransactionId").getAsString();
		VerificationSign = content.get("VerificationSign").getAsString();
	}
}