package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pixceed.data.LibraryMonth;
import com.pixceed.data.PixceedObjectsNamingStrategy;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.TokenRequestTask;

public class LibrariesTask extends TokenRequestTask<Void, ArrayList<LibraryMonth>>
{

	public LibrariesTask(OnPostExecuteInterface<ArrayList<LibraryMonth>> opei)
	{
		super(opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException {
		return new URL(URL_FOLDERS);
	}

	@Override
	protected ArrayList<LibraryMonth> readIt(InputStream stream) throws IOException {
		try
		{
			return PixceedObjectsNamingStrategy.getMapper(LibraryMonth.class).readValue(stream, new TypeReference<ArrayList<LibraryMonth>>()
			{});
		}
		catch (Exception e)
		{
			Log.e("LIBRARY", "Error during parsing JSON", e);
			return null;
		}
	}
}
