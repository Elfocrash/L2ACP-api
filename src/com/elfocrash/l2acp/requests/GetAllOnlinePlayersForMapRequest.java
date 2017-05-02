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

import java.util.ArrayList;

import com.elfocrash.l2acp.models.MapPlayer;
import com.elfocrash.l2acp.responses.GetAllOnlinePlayersForMapResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public class GetAllOnlinePlayersForMapRequest extends L2ACPRequest {

	@Override
	public L2ACPResponse getResponse() {
		ArrayList<MapPlayer> mapPlayers = new ArrayList<>();
		
		for(L2PcInstance player : World.getInstance().getPlayers()){
			mapPlayers.add(new MapPlayer(player.getName(), player.getTitle(), player.getLevel(), player.getX(), player.getY()));
		}
		
		return new GetAllOnlinePlayersForMapResponse(200,"Success", mapPlayers.toArray(new MapPlayer[mapPlayers.size()]));
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
