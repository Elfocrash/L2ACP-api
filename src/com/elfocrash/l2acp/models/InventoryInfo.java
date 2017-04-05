package com.elfocrash.l2acp.models;


public class InventoryInfo{
	private int ObjectId;
	private int ItemId;
	private int ItemCount;
	private boolean Equipped;
	private int Enchant;
	
	public InventoryInfo(int objectId, int itemId, int itemCount, boolean equipped, int enchant){
		ObjectId = objectId;
		ItemId = itemId;
		ItemCount = itemCount;
		Equipped = equipped;
		Enchant = enchant;
	}
}
