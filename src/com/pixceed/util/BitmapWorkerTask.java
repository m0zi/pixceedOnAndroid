package com.pixceed.util;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap>
{
	private final WeakReference<ImageView> imageViewReference;

	String data;

	public BitmapWorkerTask(ImageView imageView)
	{
		// Use a WeakReference to ensure the ImageView can be garbage collected
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	// Decode image in background.
	@Override
	protected Bitmap doInBackground(String... params)
	{
		if (params == null || params.length < 1)
		{
			Log.e("BITMAP_WORKER", "Not enougth parameters.");
			return null;
		}
		data = params[0];
		final Bitmap decodeSampledBitmapFromResource = decodeSampledBitmapFromResource(data.getBytes(), imageViewReference.get().getWidth(), imageViewReference.get().getHeight());
		Memory.addBitmapToMemoryCache(data, decodeSampledBitmapFromResource);
		return decodeSampledBitmapFromResource;
	}

	private static Bitmap decodeSampledBitmapFromResource(byte[] encodedImage, int reqWidth, int reqHeight)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(encodedImage, Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
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
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
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

	// Once complete, see if ImageView is still around and set bitmap.
	@Override
	protected void onPostExecute(Bitmap bitmap)
	{
		if (isCancelled())
		{
			bitmap = null;
		}

		if (imageViewReference != null && bitmap != null)
		{
			final ImageView imageView = imageViewReference.get();
			final BitmapWorkerTask bitmapWorkerTask =
					getBitmapWorkerTask(imageView);
			if (this == bitmapWorkerTask && imageView != null)
			{
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	static boolean cancelPotentialWork(String data, ImageView imageView)
	{
		final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

		if (bitmapWorkerTask != null)
		{
			final String bitmapData = bitmapWorkerTask.data;
			// If bitmapData is not yet set or it differs from the new data
			if (bitmapData == null || bitmapData != data)
			{
				// Cancel previous task
				bitmapWorkerTask.cancel(true);
			}
			else
			{
				// The same work is already in progress
				return false;
			}
		}
		// No task associated with the ImageView, or an existing task was cancelled
		return true;
	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView)
	{
		if (imageView != null)
		{
			final Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable)
			{
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}

	static class AsyncDrawable extends BitmapDrawable
	{
		private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

		AsyncDrawable(BitmapWorkerTask bitmapWorkerTask)
		{
			super(Memory.appContext.getResources(), Memory.icLauncher);
			bitmapWorkerTaskReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
		}

		private BitmapWorkerTask getBitmapWorkerTask()
		{
			return bitmapWorkerTaskReference.get();
		}
	}
}
