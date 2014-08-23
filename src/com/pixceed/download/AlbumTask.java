package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import com.pixceed.data.LibraryMonth;
import com.pixceed.data.PixceedObjectsNamingStrategy;

public class AlbumTask extends TokenRequestTask<Void, LibraryMonth>
{

	public AlbumTask(OnPostExecuteInterface<LibraryMonth> opei)
	{
		super(opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException {
		return new URL(URL_FOLDERS);
	}

	@Override
	protected LibraryMonth readIt(InputStream stream) throws IOException {
		try
		{
			return PixceedObjectsNamingStrategy.getMapper(LibraryMonth.class).readValue(stream, LibraryMonth.class);
		}
		catch (Exception e)
		{
			Log.e("LIBRARY", "Error during parsing JSON", e);
			return null;
		}
	}

}
