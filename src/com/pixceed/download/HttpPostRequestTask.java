package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

public abstract class HttpPostRequestTask<S, R> extends InternetTransmissionTask<S, R>
{

	private NameValuePair[] requestProperties;

	protected HttpPostRequestTask(Context context, OnPostExecuteInterface<R> opei, NameValuePair... requestProperties)
	{
		super(context, opei);
		this.requestProperties = requestProperties;
	}

	@Override
	protected InputStream createResponseInputStream(URL url) throws IOException
	{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (NameValuePair nameValuePair : requestProperties)
			pairs.add(nameValuePair);

		HttpPost post;
		try
		{
			post = new HttpPost(url.toURI());
		}
		catch (URISyntaxException e)
		{
			Log.w("HTTP_POST", "Given URL was not URI conform. Try to use String.", e);
			post = new HttpPost(url.toString());
		}
		post.setEntity(new UrlEncodedFormEntity(pairs));
		post.setHeader("Accept", "application/json");

		Log.d("HTTP_POST_SEND", "Send Login data");
		return new DefaultHttpClient().execute(post).getEntity().getContent();
	}
}
