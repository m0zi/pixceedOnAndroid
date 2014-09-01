package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import com.pixceed.data.PixceedPicture;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;
import com.pixceed.util.Memory;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class PictureTask extends TokenRequestTask<Void, PixceedPicture>
{

	private static final String PICTURE_TAG = "PICTURE";

	public PictureTask(Context context, OnPostExecuteInterface<PixceedPicture> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		if (params == null || params.length < 1)
			throw new IllegalArgumentException("Not enougth parameters.");
		return new URL(URL_IMAGE + params[0]);
	}

	@Override
	protected PixceedPicture readIt(InputStream stream) throws IOException
	{
		try
		{
			Log.d(PICTURE_TAG, "Start of JSON parsing");
			final PixceedPicture readValue = PixceedObjectsNamingStrategy.getMapper(PixceedPicture.class).readValue(stream, PixceedPicture.class);
			Log.d(PICTURE_TAG, "End of JSON parsing");
			Memory.addPixceedPictureToMemoryCache(readValue);
			return readValue;
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e(PICTURE_TAG, "Error during parsing JSON", e);
			return null;
		}
	}
}
