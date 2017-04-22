/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.elfocrash.l2acp;

import com.elfocrash.l2acp.requests.*;

import com.google.gson.JsonObject;

import java.util.function.Supplier;

/**
 * @author Elfocrash
 *
 */
public enum L2ACPRequests
{
	LOGIN(1, LoginRequest::new),
	REGISTER(2, RegisterRequest::new),
	GETACCOUNTINFO(3, GetAccountInfoRequest::new),
	GETINVENTORY(4, GetPlayerInventoryRequest::new),
	GETPLAYERINFO(5, GetPlayerInfoRequest::new),
	ENCHANTITEM(6, EnchantItemRequest::new),
	DONATEREQUEST(7, DonateRequest::new),
	CHANGEPASS(8, ChangePassRequest::new),
	BUYITEM(9, BuyItemRequest::new),
	GETBUYLIST(10, GetBuyListRequest::new),
	GETTOPLIST(11, GetStatsRequest::new),
	GETDONATESERVICES(12, GetDonateServicesRequest::new),
	RENAMEPLAYER(13, RenamePlayerRequest::new),
	CHANGESEX(14, ChangeSexRequest::new),
	RESETPK(15, ResetPkRequest::new),
	SETNOBLESS(16, SetNoblessRequest::new),
	GETALLPLAYERS(17, GetAllPlayerNamesRequest::new),
	GIVEITEM(18, GiveItemRequest::new),
	ANNOUNCE(19, AnnounceRequest::new),
	PUNISH(20, PunishRequest::new),
	GETMAPDATA(21, GetAllOnlinePlayersForMapRequest::new),
	SPAWNNPC(22, SpawnNpcRequest::new),
	SETDONATELIST(23, SetDonateListRequest::new),
	RESTARTSERVER(24, RestartServerRequest::new),
	LUCKYWHEELSPIN(25, LuckyWheelSpinRequest::new),
	LUCKYWHEELLIST(26, LuckyWheelListRequest::new),
	GETANALYTICSPLAYERS(27, GetAnalyticsPlayersRequest::new),
	GETBOSSMAPDATA(28, GetLiveRbsForMapRequest::new),
	GIVEDONATEPOINTS(29, GiveDonatePointsRequest::new),
	SETPLAYERLEVEL(30, SetPlayerLevelRequest::new);
	
	public static final L2ACPRequests[] REQUESTS;
	
	static
	{
		REQUESTS = new L2ACPRequests[values().length + 1];
		for (L2ACPRequests request : values())
		{
			REQUESTS[request._requestId] =  request;
		}
	}
	
	private int _requestId;
	private Supplier<L2ACPRequest> _request;
	
	L2ACPRequests(int requestId, Supplier<L2ACPRequest> request){
		_requestId = requestId;
		_request = request != null ? request : () -> null;
	}
	
	public L2ACPRequest newRequest(JsonObject content){
		L2ACPRequest request = _request.get();
		request.setContent(content);
		return request;
	}
}
