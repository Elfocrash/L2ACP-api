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

import com.elfocrash.l2acp.L2ACPServer;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.Shutdown;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
import net.sf.l2j.gameserver.util.Broadcast;

public class RestartServerRequest extends L2ACPRequest {

	private int Seconds;
	
	@Override
	public L2ACPResponse getResponse() {
		
		if (L2ACPServer.getInstance().serverShutdown == null || !L2ACPServer.getInstance().serverShutdown.isAlive())
		{
			L2ACPServer.getInstance().serverShutdown = new Shutdown(Seconds, true);
			L2ACPServer.getInstance().serverShutdown.start();
			return new L2ACPResponse(200, "Server is restarting...");
		}
		
		return new L2ACPResponse(500, "Server is already restarting");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Seconds = content.get("Seconds").getAsInt();
	}
}
