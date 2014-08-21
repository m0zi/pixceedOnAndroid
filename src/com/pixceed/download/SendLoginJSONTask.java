package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class SendLoginJSONTask extends AsyncTask<String, Void, JSONObject> {

	private OnPostExecuteInterface<JSONObject> opeiExecuteInterface;
	private String username;
	private String password;

	public SendLoginJSONTask(OnPostExecuteInterface<JSONObject> opei, String username, String password) {
		opeiExecuteInterface = opei;
		this.username = username;
		this.password = password;
	}

	@Override
	protected JSONObject doInBackground(String... urls) {
		try {
			String string = downloadUrl(urls[0]);
			return new JSONObject(string);
		} catch (IOException | JSONException e) {
			Log.e("JSON", e.getMessage(), e);
			return null;
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		opeiExecuteInterface.onPostExecute(result);
	}

	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	private String downloadUrl(String myurl) throws IOException, JSONException {
		InputStream is = null;
		try {
//			JSONObject request = new JSONObject();
//			request.accumulate("grant_type", "password");
//			request.accumulate("username", username);
//			request.accumulate("password", password);
//			StringEntity se = new StringEntity(request.toString());
//			se.setContentType("application/json");
//			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

			List<NameValuePair> pairs = new ArrayList<NameValuePair>(3);
			pairs.add(new BasicNameValuePair("grant_type", "password"));
			pairs.add(new BasicNameValuePair("username", username));
			pairs.add(new BasicNameValuePair("password", password));
			
			HttpPost post = new HttpPost(myurl);
			post.setEntity(new UrlEncodedFormEntity(pairs));
//			post.setEntity(se);
			post.setHeader("Accept", "application/json");

			Log.i("SEND_JSON", pairs.toString());
			is = new DefaultHttpClient().execute(post).getEntity().getContent();
			String readIt = DownloadJSONTask.readIt(is, 500);
			Log.i("RECEIVE_JSON", readIt);
			return readIt;

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
	
}

