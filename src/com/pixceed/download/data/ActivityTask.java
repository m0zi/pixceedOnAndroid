package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pixceed.data.Activity;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class ActivityTask extends TokenRequestTask<Void, Collection<Activity>>
{
	private static final String ACTIVITY_TAG = "ACTIVITY";

	protected ActivityTask(Context context, OnPostExecuteInterface<Collection<Activity>> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		return new URL(URL_ACTIVITY);
	}

	@Override
	protected Collection<Activity> readIt(InputStream stream) throws IOException
	{
		try
		{
			Log.d(ACTIVITY_TAG, "Start of JSON parsing");
			final Collection<Activity> readValue = PixceedObjectsNamingStrategy.getMapper(Activity.class).readValue(stream, new TypeReference<ArrayList<Activity>>()
			{});
			Log.d(ACTIVITY_TAG, "End of JSON parsing");
			return readValue;
		}

		catch (Exception e)
		{
			Log.e(ACTIVITY_TAG, "Error during parsing JSON", e);
			return null;
		}
	}
}
