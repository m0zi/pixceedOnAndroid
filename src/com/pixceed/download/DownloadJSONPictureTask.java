package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.pixceed.MainActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadJSONPictureTask extends AsyncTask<String, Void, Bitmap> {

	private OnPostExecuteInterface<Bitmap> onPostExecuteInterface;
	private int scalingWidth;
	private int scalingHeight;

	public DownloadJSONPictureTask(OnPostExecuteInterface<Bitmap> onPostExecuteInterface, int width, int height) {
		this.onPostExecuteInterface = onPostExecuteInterface;
		scalingWidth = width;
		scalingHeight = height;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		try {
			return downloadUrl(params[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		onPostExecuteInterface.onPostExecute(result);
	}

	// Given a URL, establishes an HttpUrlConnection and retrieves
	// the web page content as a InputStream, which it returns as
	// a string.
	private Bitmap downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		// Only display the first 500 characters of the retrieved
		// web page content.
		int len = 500;

		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000); // milliseconds
			conn.setConnectTimeout(15000); // milliseconds
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.addRequestProperty("Authorization", "Bearer "+MainActivity.token);
			Log.d("SEND_PICTURE", "Sending to adress: "+myurl);
			// Starts the query
			conn.connect();
			int response = conn.getResponseCode();
			Log.d("RECEIVE_PICTURE", "The server response is: " + response);
			is = conn.getInputStream();

			// Convert the InputStream into a bitmap
			Bitmap contentAsBitmap = readIt(is, len);
			return contentAsBitmap;

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	// Reads an InputStream and converts it to a String.
	public Bitmap readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		BitmapFactory.Options options = new BitmapFactory.Options();

		if (stream.markSupported()) {
			stream.mark(Integer.MAX_VALUE); // set mark to 10MB picture
			options.inJustDecodeBounds = true;

			BitmapFactory.decodeStream(stream, null, options);

			options.inSampleSize = calculateInSampleSize(options, scalingWidth, scalingHeight);
			stream.reset();
		}
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(stream, null, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
}
