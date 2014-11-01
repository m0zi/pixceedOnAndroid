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

import com.pixceed.util.Memory;

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
	/* activity */
	public static final String URL_ACTIVITY = URL_API + "/activity";

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
		if (!checkConnection(context, Memory.isMobileDataAllowed))
		{
			Log.w(LOG_TAG, "No conntection to the internet.");
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

	/**
	 * Check whether or not Internet connection is available.
	 * 
	 * @param context
	 *            required to retrieve network and connectivity information.
	 * @param isMobileDataAllowed
	 *            flags whether or not mobile data is allowed to be used. Please note, that this will only make sure
	 *            {@link ConnectivityManager#TYPE_MOBILE} will not be used. {@link ConnectivityManager#TYPE_MOBILE_DUN TYPE_MOBILE_...} are still
	 *            possible since I don't have no idea what these other connections might be.
	 * @return <code>true</code> if data connection to Internet is allowed and possible , <code>false</code> otherwise.
	 */
	public static boolean checkConnection(Context context, boolean isMobileDataAllowed)
	{
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
		if (!isMobileDataAllowed && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
			return false;
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

			return null;
		}
		finally
		{
			try
			{
				if (is != null)
					is.close();
			}
			catch (IOException e1)
			{
				Log.e(LOG_TAG, "Error while closing input stream (probably already closed).", e1);
				return null;
			}
		}
	}

	protected abstract InputStream createResponseInputStream(URL url) throws IOException;

	protected abstract R readIt(InputStream stream) throws IOException;

}
