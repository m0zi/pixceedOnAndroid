package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import com.pixceed.data.Group;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.Memory;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class GroupTask extends TokenRequestTask<Void, Group>
{

	public GroupTask(OnPostExecuteInterface<Group> opei)
	{
		super(opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		if (params == null || params.length < 1)
			throw new IllegalArgumentException("Not enougth parameters.");
		return new URL(URL_GROUPS + params[0]);
	}

	@Override
	protected Group readIt(InputStream stream) throws IOException
	{
		try
		{
			Group readValue = PixceedObjectsNamingStrategy.getMapper(Group.class).readValue(stream, Group.class);
			Memory.addGroupToMemoryCache(readValue);
			return readValue;
		}
		catch (Exception e)
		{
			Log.e("GROUP", "Error during parsing JSON", e);
			return null;
		}
	}
}
