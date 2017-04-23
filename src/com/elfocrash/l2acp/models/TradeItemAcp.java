package com.elfocrash.l2acp.models;

import net.sf.l2j.gameserver.model.item.kind.Item;

public class TradeItemAcp {
	private int ObjectId;
	private int ItemId;
	private int Enchant;
	private int Count;
	private int Price;
	private String PlayerName;
	private int PlayerId;
	
	public TradeItemAcp(int objId, int itemId, int enchant, int count, int price, String playerName, int playerId){
		ObjectId = objId;
		ItemId = itemId;
		Enchant = enchant;
		Count = count;
		Price = price;
		PlayerName = playerName;
		PlayerId = playerId;
	}
}
