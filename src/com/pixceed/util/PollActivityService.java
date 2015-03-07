package com.pixceed.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PollActivityService extends Service
{

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d("POLLING_SERVICE", "poll");
		return START_STICKY;
	}
	
}
