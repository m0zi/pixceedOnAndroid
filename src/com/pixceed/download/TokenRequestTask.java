package com.pixceed.download;

import org.apache.http.message.BasicNameValuePair;

import com.pixceed.util.Memory;

public abstract class TokenRequestTask<S, R> extends HttpGetRequestTask<S, R>
{
	protected TokenRequestTask(OnPostExecuteInterface<R> opei)
	{
		super(opei, new BasicNameValuePair("Authorization", "Bearer " + Memory.token));
	}
}
