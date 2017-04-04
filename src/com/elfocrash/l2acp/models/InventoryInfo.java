package com.elfocrash.l2acp.models;


public class InventoryInfo{
	private int ItemId;
	private int ItemCount;
	private boolean Equipped;
	private int Enchant;
	
	public InventoryInfo(int itemId, int itemCount, boolean equipped, int enchant){
		ItemId = itemId;
		ItemCount = itemCount;
		Equipped = equipped;
		Enchant = enchant;
	}
}
