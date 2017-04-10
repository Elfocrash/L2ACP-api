package com.elfocrash.l2acp.responses;

public class GetAllPlayerNamesResponse extends L2ACPResponse
{
	private String[] AllPlayerNames; 
	
	public GetAllPlayerNamesResponse(int code, String message, String[] playerNames)
	{
		super(code, message);
		AllPlayerNames = playerNames;
	}
}