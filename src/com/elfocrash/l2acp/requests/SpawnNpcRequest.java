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

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.datatables.NpcTable;
import net.sf.l2j.gameserver.geoengine.GeoEngine;
import net.sf.l2j.gameserver.geoengine.geodata.ABlock;
import net.sf.l2j.gameserver.geoengine.geodata.GeoStructure;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.util.Broadcast;

public class SpawnNpcRequest extends L2ACPRequest {

    private int NpcId;
    private int X;
    private int Y;
	
	@Override
	public L2ACPResponse getResponse() {
		int geoX = GeoEngine.getInstance().getGeoX(X);
		int geoY = GeoEngine.getInstance().getGeoY(Y);
		int z = GeoEngine.getInstance().getHeightNearest(geoX, geoY, 0);
		Helpers.spawn(NpcId, X, Y, z, 0, false);
		NpcTemplate npc = NpcTable.getInstance().getTemplate(NpcId);
		return new L2ACPResponse(200,npc.getName());
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		NpcId = content.get("NpcId").getAsInt();
		X = content.get("X").getAsInt();
		Y = content.get("Y").getAsInt();
	}
}