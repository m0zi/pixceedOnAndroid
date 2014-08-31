package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;

public abstract class HttpGetRequestTask<S, R> extends InternetTransmissionTask<S, R>
{
	private NameValuePair[] requestProperties;

	protected HttpGetRequestTask(Context context, OnPostExecuteInterface<R> opei, NameValuePair... requestProperties)
	{
		super(context, opei);
		this.requestProperties = requestProperties;
	}

	@Override
	protected InputStream createResponseInputStream(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		if (requestProperties != null)
			for (NameValuePair requestProperty : requestProperties)
			{
				Log.d("HTTP_GET_SEND", String.format("add request property:%s=%s", requestProperty.getName(), requestProperty.getValue()));
				conn.addRequestProperty(requestProperty.getName(), requestProperty.getValue());
			}
		// Starts the query
		Log.d("HTTP_GET_SEND", url.toString());
		conn.connect();
		int response = conn.getResponseCode();
		Log.d("HTTP_GET_RECEIVE", "The response is: " + response);
		return conn.getInputStream();
	}
}
