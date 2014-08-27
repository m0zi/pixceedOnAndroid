package com.pixceed.download;

import org.apache.http.message.BasicNameValuePair;

import com.pixceed.LoginActivity;

public abstract class TokenRequestTask<S, R> extends HttpGetRequestTask<S, R>
{
	protected TokenRequestTask(OnPostExecuteInterface<R> opei)
	{
		super(opei, new BasicNameValuePair("Authorization", "Bearer " + LoginActivity.token));
	}
}
