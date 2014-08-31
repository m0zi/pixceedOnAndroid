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
import com.pixceed.data.LibraryMonth;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.Memory;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class UserLibrariesTask extends TokenRequestTask<Void, Collection<LibraryMonth>>
{

	public UserLibrariesTask(Context context, OnPostExecuteInterface<Collection<LibraryMonth>> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		return new URL(URL_FOLDERS);
	}

	@Override
	protected ArrayList<LibraryMonth> readIt(InputStream stream) throws IOException
	{
		try
		{
			final ArrayList<LibraryMonth> readValue = PixceedObjectsNamingStrategy.getMapper(LibraryMonth.class).readValue(stream, new TypeReference<ArrayList<LibraryMonth>>()
			{});
			Memory.addLibraryToMemoryCache(readValue);
			return readValue;
		}

		catch (Exception e)
		{
			Log.e("LIBRARY", "Error during parsing JSON", e);
			return null;
		}
	}
}
