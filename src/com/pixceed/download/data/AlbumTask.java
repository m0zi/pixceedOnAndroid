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

	private static final String ALBUM_TAG = "ALBUM";

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
			Log.d(ALBUM_TAG, "Start of JSON parsing");
			Album readValue = PixceedObjectsNamingStrategy.getMapper(Album.class).readValue(stream, Album.class);
			Log.d(ALBUM_TAG, "End of JSON parsing");
			Memory.addAlbumToMemoryCache(readValue);
			return readValue;
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e(ALBUM_TAG, "Error during parsing JSON", e);
			return null;
		}
	}

}
