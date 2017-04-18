package com.elfocrash.l2acp.tasks;

import com.elfocrash.l2acp.util.Helpers;

public class DatamineTask implements Runnable {

	@Override
	public void run() {
		Helpers.recordOnlinePlayersCount();
	}

}
