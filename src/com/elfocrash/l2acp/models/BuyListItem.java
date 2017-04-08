package com.elfocrash.l2acp.models;


public class BuyListItem{
	public int ItemId;
	public int ItemCount;
	public int Enchant;
	public int Price;
	
	public BuyListItem(int itemId, int itemCount,int enchant,int price){
		ItemId = itemId;
		ItemCount = itemCount;
		Enchant = enchant;
		Price = price;
	}
}
