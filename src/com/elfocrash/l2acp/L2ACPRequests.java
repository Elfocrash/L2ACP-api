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

import com.elfocrash.l2acp.requests.L2ACPRequest;
import com.elfocrash.l2acp.requests.LoginRequest;
import com.elfocrash.l2acp.requests.RegisterRequest;
import com.elfocrash.l2acp.requests.GetAccountCharsRequest;

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
	GETACCOUNTCHARS(3, GetAccountCharsRequest::new);
	
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
