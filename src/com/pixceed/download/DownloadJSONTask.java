package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadJSONTask extends AsyncTask<String, Void, JSONArray>
{

	private OnPostExecuteInterface<JSONArray> onPostExecuteInterface;

	public DownloadJSONTask(OnPostExecuteInterface<JSONArray> onPostExecuteInterface)
	{
		this.onPostExecuteInterface = onPostExecuteInterface;
	}

	@Override
	protected JSONArray doInBackground(String... params) {
		JSONArray result;
		try
		{
			result = new JSONArray(downloadUrl(params[0]));
			return result;
		}
		catch (JSONException | IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		onPostExecuteInterface.onPostExecute(result);
	}

	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	private String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		// Only display the first 500 characters of the retrieved
		// web page content.
		int len = 512;

		try
		{
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			Log.d("SEND", myurl);
			conn.connect();
			int response = conn.getResponseCode();
			Log.d("RECEIVE", "The response is: " + response);
			is = conn.getInputStream();

			// Convert the InputStream into a string
			String contentAsString = readIt(is, len);
			return contentAsString;

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		}
		finally
		{
			if (is != null)
			{
				is.close();
			}
		}
	}

	// Reads an InputStream and converts it to a String.
	public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		StringBuffer sb = new StringBuffer();
		char[] buffer = new char[len];
		do
		{
			sb.append(new String(buffer).trim());
		} while ((reader.read(buffer)) != -1);
		return sb.toString();
	}
}