package com.pixceed.download.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.pixceed.download.HttpGetRequestTask;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.util.BitmapWorkerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PublicPictureTask extends HttpGetRequestTask<Void, Bitmap>
{
	private int onScreenWidth;
	private int onScreenHeight;

	/* public pictures */
	public static final String URL_PUBLIC_PICTURE = URL_BASE + "/pixceed/Image/GetImage/";

	public PublicPictureTask(OnPostExecuteInterface<Bitmap> opei, int onScreenWidth, int onScreenHeight)
	{
		super(opei);
		this.onScreenWidth = onScreenWidth;
		this.onScreenHeight = onScreenHeight;
	}

	@Override
	protected URL getURL(String... params) throws MalformedURLException, IllegalArgumentException {
		if (params != null && params.length < 1)
			return null;
		return new URL(URL_PUBLIC_PICTURE + params[0]);
	}

	/**
	 * Reads an {@link InputStream} and converts it to a Bitmap.
	 * 
	 * @param stream
	 *            the input stream where to read the picture from.
	 * @return the picture (with adjusted size, only if input stream supports mark {@link InputStream#markSupported()}).
	 * @throws IOException
	 *             if this stream is closed or another IOException occurs.
	 */
	public Bitmap readIt(InputStream stream) throws IOException {
		BitmapFactory.Options options = new BitmapFactory.Options();

		if (stream.markSupported())
		{
			stream.mark(Integer.MAX_VALUE); // set mark to 10MB picture

			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(stream, null, options);

			options.inSampleSize = BitmapWorkerTask.calculateInSampleSize(options, onScreenWidth, onScreenHeight);
			stream.reset();
		}
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(stream, null, options);
	}
}
