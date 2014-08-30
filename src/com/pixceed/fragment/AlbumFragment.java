package com.pixceed.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.pixceed.R;
import com.pixceed.adapter.AlbumAdapter;
import com.pixceed.data.Album.ImageDay.ImagePreviewInformation;
import com.pixceed.data.PixceedPicture;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.PictureTask;
import com.pixceed.download.data.PublicPictureTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumFragment extends Fragment implements OnItemClickListener, OnPostExecuteInterface<PixceedPicture>
{

	private boolean isMaximizedPicture;

	/**
	 * Hold a reference to the current animator, so that it can be canceled mid-way.
	 */
	private Animator currentAnimator;

	public AlbumFragment()
	{}

	/**
	 * The system "short" animation time duration, in milliseconds. This /* duration is ideal for subtle animations or animations that occur very
	 * frequently.
	 */
	private int mShortAnimationDuration;

	private View rootView;

	private GridView gridViewAlbum;

	private AsyncTask<String, Void, PixceedPicture> currentTask;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_album, container, false);

		gridViewAlbum = (GridView) rootView.findViewById(R.id.gridViewAlbum);
		AlbumAdapter albumAdapter = new AlbumAdapter(rootView.getContext(), getArguments().getInt("id"));
		gridViewAlbum.setAdapter(albumAdapter);
		gridViewAlbum.setOnItemClickListener(this);

		// Retrieve and cache the system's default "short" animation time.
		mShortAnimationDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		return rootView;
	}

	public void onItemClick(AdapterView<? extends Adapter> parent, View v, int position, long id)
	{
		Toast.makeText(rootView.getContext(), "" + position, Toast.LENGTH_SHORT).show();

		if (parent.getAdapter() instanceof AlbumAdapter)
		{
			ImagePreviewInformation ipi = (ImagePreviewInformation) parent.getAdapter().getItem(position);
			currentTask = new PictureTask(this).execute("/" + ipi.getId());
			// start the animation
			final ImageView expandedIconView = (ImageView) rootView.findViewById(R.id.expandedView);
			final ImageView iconThumb = (ImageView) v.findViewById(R.id.picture);
			zoomIcon(iconThumb, expandedIconView, ipi.getImageIcon().getBytes());
		}
	}

	private void zoomIcon(final ImageView thumbView, final ImageView expandedView, byte[] imageBase64Encoded)
	{
		// If there's an animation in progress, cancel it
		// immediately and proceed with this one.
		if (currentAnimator != null)
			currentAnimator.cancel();

		// Load the low-resolution "zoomed-in" image/icon.
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(imageBase64Encoded, Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, expandedView.getWidth(), expandedView.getHeight());
		options.inJustDecodeBounds = false;
		expandedView.setScaleType(ScaleType.FIT_CENTER);
		expandedView.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));

		// Calculate the starting and ending bounds for the zoomed-in image.
		// This step involves lots of math. Yay, math.
		final Rect startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();

		// The start bounds are the global visible rectangle of the thumbnail,
		// and the final bounds are the global visible rectangle of the container
		// view. Also set the container view's offset as the origin for the
		// bounds, since that's the origin for the positioning animation
		// properties (X, Y).
		thumbView.getGlobalVisibleRect(startBounds);
		getActivity().findViewById(R.id.albumContainer).getGlobalVisibleRect(finalBounds, globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

		// Adjust the start bounds to be the same aspect ratio as the final
		// bounds using the "center crop" technique. This prevents undesirable
		// stretching during the animation. Also calculate the start scaling
		// factor (the end scaling factor is always 1.0).
		float startScale;
		if ((float) finalBounds.width() / finalBounds.height()
		> (float) startBounds.width() / startBounds.height())
		{
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		}
		else
		{
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}

		// Hide the thumbnail and show the zoomed-in view. When the animation
		// begins, it will position the zoomed-in view in the place of the
		// thumbnail.
		thumbView.setAlpha(0f);
		expandedView.setVisibility(View.VISIBLE);

		// Set the pivot point for SCALE_X and SCALE_Y transformations
		// to the top-left corner of the zoomed-in view (the default
		// is the center of the view).
		expandedView.setPivotX(0f);
		expandedView.setPivotY(0f);

		// Construct and run the parallel animation of the four translation and
		// scale properties (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		//@formatter:off
		set.play(ObjectAnimator.ofFloat(expandedView, "translationX", startBounds.left, finalBounds.left))
		   .with(ObjectAnimator.ofFloat(expandedView, "translationY", startBounds.top, finalBounds.top))
		   .with(ObjectAnimator.ofFloat(expandedView, "scaleX", startScale, 1f))
		   .with(ObjectAnimator.ofFloat(expandedView, "scaleY", startScale, 1f));
		//@formatter:on
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation)
			{
				currentAnimator = null;
				isMaximizedPicture = true;
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{
				currentAnimator = null;
				isMaximizedPicture = true;
			}
		});

		set.start();
		currentAnimator = set;

		// Upon clicking the zoomed-in image, it should zoom back down
		// to the return bounds and show the returnView instead of
		// the expanded icon.
		final float startScaleFinal = startScale;
		expandedView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (currentAnimator != null) currentAnimator.cancel();
				if (currentTask != null) currentTask.cancel(true);

				// Animate the four positioning/sizing properties in parallel,
				// back to their original values.
				AnimatorSet set = new AnimatorSet();
				//@formatter:off
				set.play(ObjectAnimator.ofFloat(expandedView, "translationX", startBounds.left))
				   .with(ObjectAnimator.ofFloat(expandedView, "translationY", startBounds.top))
				   .with(ObjectAnimator.ofFloat(expandedView, "scaleX", startScaleFinal))
				   .with(ObjectAnimator.ofFloat(expandedView, "scaleY", startScaleFinal));
				//@formatter:on
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new DecelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter()
				{
					@Override
					public void onAnimationEnd(Animator animation)
					{
						thumbView.setAlpha(1f);
						expandedView.setVisibility(View.GONE);
						isMaximizedPicture = false;
						currentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation)
					{
						thumbView.setAlpha(1f);
						expandedView.setVisibility(View.GONE);
						isMaximizedPicture = false;
						currentAnimator = null;
					}
				});
				set.start();
				currentAnimator = set;
			}
		});
	}

	@Override
	public void onPostExecute(PixceedPicture result)
	{
		if (result == null)
		{
			Log.e("PICTURE", "No picture received.");
			return;
		}
		// task has been done completely
		currentTask = null;
		// Load the high-resolution "zoomed-in" image.
		final ImageView expandedView = (ImageView) rootView.findViewById(R.id.expandedView);
		// Load the low-resolution "zoomed-in" image/icon.
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(result.getImage().getBytes(), Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, expandedView.getWidth(), expandedView.getHeight());
		options.inJustDecodeBounds = false;
		expandedView.setScaleType(ScaleType.FIT_CENTER);
		expandedView.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
	}

	public boolean isMaximizedPicture()
	{
		return isMaximizedPicture;
	}

	/**
	 * Minimizes the picture currently shown. If picture is not maximized currently, nothing is done.
	 */
	public void minimizePicture()
	{
		if (isMaximizedPicture)
		{
			final ImageView expandedView = (ImageView) rootView.findViewById(R.id.expandedView);
			expandedView.performClick();
		}
	}
}