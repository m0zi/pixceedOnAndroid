package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pixceed.data.GroupDescription;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.Memory;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class GroupDescriptionsTask extends TokenRequestTask<Void, Collection<GroupDescription>>
{

	private static final String GROUP_DESCS_TAG = "GROUP_DESCS";

	public GroupDescriptionsTask(Context context, OnPostExecuteInterface<Collection<GroupDescription>> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		return new URL(URL_GROUPS);
	}

	@Override
	protected Collection<GroupDescription> readIt(InputStream stream) throws IOException
	{
		try
		{
			Log.d(GROUP_DESCS_TAG, "Start of JSON parsing");
			Collection<GroupDescription> readValue = PixceedObjectsNamingStrategy.getMapper(GroupDescription.class).readValue(stream, new TypeReference<Collection<GroupDescription>>()
			{});
			Log.d(GROUP_DESCS_TAG, "Start of JSON parsing");
			Memory.addGroupDescriptionsToMemoryCache(readValue);
			return readValue;
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e(GROUP_DESCS_TAG, "Error during parsing JSON", e);
			return null;
		}
	}
}
