package com.elfocrash.l2acp.models;

public class LuckyWheelItem{
	public int ItemId;
	public int Count;
	public int Enchant;
	public double Chance;
	
	public LuckyWheelItem(int itemId, int itemCount,int enchant,double chance){
		ItemId = itemId;
		Count = itemCount;
		Enchant = enchant;
		Chance = chance;
	}
}