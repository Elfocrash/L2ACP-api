package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.collections.RandomCollection;
import com.elfocrash.l2acp.models.LuckyWheelItem;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.responses.LuckyWheelSpinResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.idfactory.IdFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

/**
 * @author Elfocrash
 *
 */
public class LuckyWheelSpinRequest extends L2ACPRequest
{
	private String PlayerName;

	@Override
	public L2ACPResponse getResponse()
	{
		int spinCost = 5;
		
		ArrayList<LuckyWheelItem> itemList = Helpers.getLuckyWheelList();
		RandomCollection<LuckyWheelItem> chanceList = new RandomCollection<>();
		for(LuckyWheelItem item : itemList){
			chanceList.add(item.Chance, item);
		}
		
		LuckyWheelItem winItem = chanceList.next();
		String accountName = Helpers.getAccountName(PlayerName);
		
		L2PcInstance player = World.getInstance().getPlayer(PlayerName);
		if(player == null){
			player = L2PcInstance.restore(Helpers.getPlayerIdByName(PlayerName));					
		}
		
		if(Helpers.getDonatePoints(accountName) > spinCost){
			if(winItem.Enchant > 0){
				ItemInstance item = new ItemInstance(IdFactory.getInstance().getNextId(), winItem.ItemId);
			
				item.setEnchantLevel(winItem.Enchant);
				player.addItem("Buy item", item, player, true);
			}else if(winItem.Count > 0){
				player.addItem("Buy item", winItem.ItemId, winItem.Count, player, true);
			}
			Helpers.removeDonatePoints(accountName, spinCost);
			return new LuckyWheelSpinResponse(200, "You won!", winItem);
		}else{
			return new L2ACPResponse(500, "Not enough donate points");
		}		
		//return new L2ACPResponse(500, "Not enough donate points");
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		PlayerName = content.get("PlayerName").getAsString();
	}
}