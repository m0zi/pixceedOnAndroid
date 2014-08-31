package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pixceed.data.Article;
import com.pixceed.download.HttpGetRequestTask;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class ArticlesTask extends HttpGetRequestTask<Void, Collection<Article>>
{

	public ArticlesTask(Context context, OnPostExecuteInterface<Collection<Article>> opei)
	{
		super(context, opei);
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		return new URL(URL_ARTICLES);
	}

	@Override
	protected Collection<Article> readIt(InputStream stream) throws IOException
	{
		try
		{
			return PixceedObjectsNamingStrategy.getMapper(Article.class).readValue(stream, new TypeReference<Collection<Article>>()
			{});
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e("PUBLIC_ARTICLE", "Error during parsing JSON", e);
			return null;
		}
	}
}
