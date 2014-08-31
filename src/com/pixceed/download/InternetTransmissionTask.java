package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public abstract class InternetTransmissionTask<S, R> extends AsyncTask<String, S, R>
{
	/* base pixceed URL */
	public static final String URL_BASE = "https://www.pixceed.com";
	/* api */
	public static final String URL_API = URL_BASE + "/api";
	/* login */
	public static final String URL_LOGIN = URL_API + "/token";
	/* homepage */
	public static final String URL_HP = URL_API + "/homepage";
	public static final String URL_ARTICLES = URL_HP + "/articles";
	public static final String URL_RND_PICTURE = URL_HP + "/images";
	/* private pictures */
	public static final String URL_IMAGE = URL_API + "/image";
	/* alben */
	public static final String URL_FOLDERS = URL_API + "/folder";
	/* groups */
	public static final String URL_GROUPS = URL_API + "/group";

	private static final String LOG_TAG = "DOWNLOAD";

	protected OnPostExecuteInterface<R> opei;

	protected Context context;

	protected InternetTransmissionTask(Context context, OnPostExecuteInterface<R> opei)
	{
		this.context = context;
		this.opei = opei;
	}

	@Override
	protected void onPostExecute(R result)
	{
		opei.onPostExecute(result);
	}

	@Override
	protected R doInBackground(String... params)
	{
		if (!checkConnection(context))
		{
			Log.w(LOG_TAG, "No conntection to the internet.");
			Toast.makeText(context, "No connection.", Toast.LENGTH_LONG).show();
			return null;
		}
		try
		{
			return download(getURL(params));
		}
		catch (MalformedURLException e)
		{
			Log.e(LOG_TAG, "Given URL string could not be parsed as valid URL", e);
			return null;
		}
		catch (IllegalArgumentException e)
		{
			Log.e(LOG_TAG, "Task cannot be executed with the given set of parameters.", e);
			return null;
		}
	}

	public static boolean checkConnection(Context context)
	{
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

	protected abstract URL getURL(String... params) throws MalformedURLException, IllegalArgumentException;

	protected R download(URL url)
	{
		InputStream is = null;
		try
		{
			is = createResponseInputStream(url);
		}
		catch (IOException e)
		{
			Log.e(LOG_TAG, "Communication error occured.", e);
			return null;
		}
		try
		{
			return readIt(is);
		}
		catch (IOException e)
		{
			Log.e(LOG_TAG, "Error while reading input stream data.", e);
			try
			{
				if (is != null)
					is.close();
			}
			catch (IOException e1)
			{
				Log.e(LOG_TAG, "Error while closing input stream.", e1);
			}
			return null;
		}
	}

	protected abstract InputStream createResponseInputStream(URL url) throws IOException;

	protected abstract R readIt(InputStream stream) throws IOException;

}
