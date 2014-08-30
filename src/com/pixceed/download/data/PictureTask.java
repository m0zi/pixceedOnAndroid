package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import com.pixceed.data.PixceedObjectsNamingStrategy;
import com.pixceed.data.PixceedPicture;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;

public class PictureTask extends TokenRequestTask<Void, PixceedPicture>
{

	public PictureTask(OnPostExecuteInterface<PixceedPicture> opei)
	{
		super(opei);
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
			return PixceedObjectsNamingStrategy.getMapper(PixceedPicture.class).readValue(stream, PixceedPicture.class);
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e("PUBLIC_PICTURE", "Error during parsing JSON", e);
			return null;
		}
	}
}
