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
package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.InventoryInfo;
import com.elfocrash.l2acp.models.LuckyWheelItem;

/**
 * @author Elfocrash
 *
 */
public class LuckyWheelListResponse extends L2ACPResponse
{
	private LuckyWheelItem[] Items; 
	
	public LuckyWheelListResponse(int code, String message, LuckyWheelItem[] items)
	{
		super(code, message);
		Items = items;
		
	}
}