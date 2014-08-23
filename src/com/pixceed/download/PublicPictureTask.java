package com.pixceed.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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

			options.inSampleSize = calculateInSampleSize(options, onScreenWidth, onScreenHeight);
			stream.reset();
		}
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(stream, null, options);
	}

	/**
	 * Calculates the sample size for this picture and takes the required width and height into account. In order to reduce the used memory space in
	 * the device, pictures should not always be download in native resolution.
	 * 
	 * @param options
	 *            the previously used {@link BitmapFactory.Options} instance with which the actual size has been calculated.
	 * @param reqWidth
	 *            the actual width on which the picture shall be rendered.
	 * @param reqHeight
	 *            the actual height on which the picture shall be rendered.
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int actHeight = options.outHeight;
		final int actWidth = options.outWidth;
		int inSampleSize = 1;

		if (actHeight > reqHeight || actWidth > reqWidth)
		{
			final int halfHeight = actHeight / 2;
			final int halfWidth = actWidth / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth)
				inSampleSize *= 2;
		}

		return inSampleSize;
	}
}
