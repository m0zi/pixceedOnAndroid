package com.pixceed.download;

import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.pixceed.util.Memory;

public abstract class TokenRequestTask<S, R> extends HttpGetRequestTask<S, R>
{
	protected TokenRequestTask(Context context, OnPostExecuteInterface<R> opei)
	{
		super(context, opei, new BasicNameValuePair("Authorization", "Bearer " + Memory.token));
	}
}
