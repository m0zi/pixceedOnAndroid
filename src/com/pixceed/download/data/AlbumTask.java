package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import com.pixceed.data.Album;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.Memory;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class AlbumTask extends TokenRequestTask<Void, Album>
{

	public AlbumTask(Context context, OnPostExecuteInterface<Album> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		if (params == null || params.length < 1)
			throw new IllegalArgumentException("Not enougth parameters.");
		return new URL(URL_FOLDERS + params[0]);
	}

	@Override
	protected Album readIt(InputStream stream) throws IOException
	{
		try
		{
			Log.d("LIBRARY", "Start JSON parsing");
			Album readValue = PixceedObjectsNamingStrategy.getMapper(Album.class).readValue(stream, Album.class);
			Memory.addAlbumToMemoryCache(readValue);
			Log.d("LIBRARY", "End JSON parsing");
			return readValue;
		}
		catch (Exception e)
		{
			Log.e("LIBRARY", "Error during parsing JSON", e);
			return null;
		}
	}

}
