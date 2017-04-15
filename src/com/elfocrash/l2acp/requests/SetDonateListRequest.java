package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.AdminDonateListViewmodel;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.util.Broadcast;

public class SetDonateListRequest extends L2ACPRequest {
	AdminDonateListViewmodel[] list;    
	private JsonArray Items;
	
	@Override
	public L2ACPResponse getResponse() {
		Helpers.deleteAllDonateItems();
		Helpers.addDonateItems(list);
		
		return new L2ACPResponse(200,"Successfully changed the item list!");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Items = content.get("Items").getAsJsonArray();
		Gson gson = new Gson();
		list = gson.fromJson(Items, AdminDonateListViewmodel[].class);
	}
}