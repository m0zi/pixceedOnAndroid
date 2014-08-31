package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.pixceed.data.Login;
import com.pixceed.download.HttpPostRequestTask;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.util.PixceedObjectsNamingStrategy;

public class LoginTask extends HttpPostRequestTask<Void, Login>
{

	public LoginTask(OnPostExecuteInterface<Login> opei, String username, String password)
	{
		super(opei, new BasicNameValuePair("grant_type", "password"), new BasicNameValuePair("username", username), new BasicNameValuePair("password", password));
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException
	{
		return new URL(URL_LOGIN);
	}

	@Override
	protected Login readIt(InputStream stream) throws IOException
	{
		try
		{
			return PixceedObjectsNamingStrategy.getMapper(Login.class).readValue(stream, Login.class);
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Log.e("LOGIN", "Error during parsing JSON", e);
			return null;
		}
	}
}
