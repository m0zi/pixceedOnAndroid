package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixceed.download.HttpGetRequestTask;
import com.pixceed.download.OnPostExecuteInterface;

public class PublicPictureListTask extends HttpGetRequestTask<Void, int[]>
{

	public PublicPictureListTask(OnPostExecuteInterface<int[]> opei)
	{
		super(opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException {
		return new URL(URL_RND_PICTURE);
	}

	@Override
	protected int[] readIt(InputStream stream) throws IOException {
		try
		{
			return new ObjectMapper().readValue(stream, int[].class);
		}
		catch (Exception e)
		{
			Log.e("PUBLIC_PICTURE", "Error during parsing JSON", e);
			return null;
		}
	}
}
