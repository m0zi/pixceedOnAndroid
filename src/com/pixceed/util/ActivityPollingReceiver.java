package com.pixceed.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActivityPollingReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent service = new Intent(context, PollActivityService.class);
		PendingIntent pendingService = PendingIntent.getService(context, 0, service, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		long intervalHour = AlarmManager.INTERVAL_HOUR;
		long start = System.currentTimeMillis() + intervalHour;
		alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, start, intervalHour, pendingService);
	}

}
