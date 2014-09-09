package com.pixceed.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import com.pixceed.R;
import com.pixceed.adapter.AlbumAdapter;
import com.pixceed.adapter.AlbumExpandedPagerAdapter;
import com.pixceed.data.PixceedPicture;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.util.Memory;
import com.pixceed.util.Updateable;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumFragment extends Fragment implements OnItemClickListener, OnPostExecuteInterface<PixceedPicture>, Updateable
{
	public static final String IS_PICTURE_EXTENDED_KEY = "isPictureExtended";

	public static final String TAG = "com.pixceed.fragment.AlbumFragment";

	private boolean isPictureExtended = false;

	/**
	 * Hold a reference to the current animator, so that it can be canceled mid-way.
	 */
	private Animator currentAnimator;

	public AlbumFragment()
	{}

	/**
	 * The system "short" animation time duration, in milliseconds. This duration is ideal for subtle animations or animations that occur very
	 * frequently.
	 */
	private int mShortAnimationDuration;

	private View rootView;

	private AsyncTask<String, Void, PixceedPicture> currentTask;

	private AlbumAdapter albumAdapter;
	private AlbumExpandedPagerAdapter albumExpanedPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_album, container, false);

		GridView gridViewAlbum = (GridView) rootView.findViewById(R.id.gridViewAlbum);
		albumAdapter = new AlbumAdapter(rootView.getContext(), getArguments().getInt("id"));
		gridViewAlbum.setAdapter(albumAdapter);
		gridViewAlbum.setOnItemClickListener(this);

		ViewPager pager = (ViewPager) rootView.findViewById(R.id.viewPagerAlbumExpanded);
		albumExpanedPagerAdapter = new AlbumExpandedPagerAdapter(getChildFragmentManager(), albumAdapter);
		pager.setAdapter(albumExpanedPagerAdapter);
		pager.setOffscreenPageLimit(3);

		if (savedInstanceState != null)
			isPictureExtended = savedInstanceState.getBoolean(IS_PICTURE_EXTENDED_KEY);
		if (isPictureExtended) setMaximizedPicture();

		// Retrieve and cache the system's default "short" animation time.
		mShortAnimationDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		setRetainInstance(true);

		return rootView;
	}

	private void setMaximizedPicture()
	{
		final View expandedView = rootView.findViewById(R.id.viewPagerAlbumExpanded);
		// final PixceedPicture pixceedPictureFromMemoryCache = Memory.getPixceedPictureFromMemoryCache(recentId);
		// if (pixceedPictureFromMemoryCache == null)
		// return;
		// Memory.loadAndSetBitmap(pixceedPictureFromMemoryCache.getImage(), expandedView);
		expandedView.setVisibility(View.VISIBLE);
		expandedView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				// Animate the four positioning/sizing properties in parallel,
				// back to their original values.
				AnimatorSet set = new AnimatorSet();
				//@formatter:off
				set.play(ObjectAnimator.ofFloat(expandedView, "scaleX", 0f))
				   .with(ObjectAnimator.ofFloat(expandedView, "scaleY", 0f));
				//@formatter:on
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new DecelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter()
				{
					@Override
					public void onAnimationEnd(Animator animation)
					{
						expandedView.setVisibility(View.GONE);
						isPictureExtended = false;
						currentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation)
					{
						expandedView.setVisibility(View.GONE);
						isPictureExtended = false;
						currentAnimator = null;
					}
				});
				set.start();
				currentAnimator = set;
			}
		});
	}

	public void onItemClick(AdapterView<? extends Adapter> parent, View itemView, int position, long id)
	{
		if (parent.getAdapter() instanceof AlbumAdapter)
		{
			final ViewPager expandedView = (ViewPager) rootView.findViewById(R.id.viewPagerAlbumExpanded);
			final ImageView iconThumb = (ImageView) itemView.findViewById(R.id.squaredImage);

			expandedView.setCurrentItem(position);
			// recentId = id;
			// start the animation
			zoomIcon(iconThumb, expandedView);
		}
		else Log.e("ALBUM", String.format("Unexpected call to onItemClick. Given %s.getAdapter() does not return instance of %s.", parent.getClass(), AlbumAdapter.class.getName()));
	}

	private void zoomIcon(final ImageView thumbView, final View expandedView)
	{
		// If there's an animation in progress, cancel it
		// immediately and proceed with this one.
		if (currentAnimator != null)
			currentAnimator.cancel();

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
				isPictureExtended = true;
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{
				currentAnimator = null;
				isPictureExtended = true;
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
						isPictureExtended = false;
						currentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation)
					{
						thumbView.setAlpha(1f);
						expandedView.setVisibility(View.GONE);
						isPictureExtended = false;
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
		final ImageView expandedView = (ImageView) rootView.findViewById(R.id.imageViewImagePicture);
		// Load the low-resolution "zoomed-in" image/icon.
		Memory.loadAndSetBitmap(result.getImage(), expandedView);
	}

	public boolean isPictureExtended()
	{
		return isPictureExtended;
	}

	public void setData(boolean isPictureExtended, long recentId)
	{
		this.isPictureExtended = isPictureExtended;
		// this.recentId = recentId;
	}

	/**
	 * Minimizes the picture currently shown. If picture is not maximized currently, nothing is done.
	 */
	public void minimizePicture()
	{
		if (isPictureExtended)
		{
			final ViewPager expandedView = (ViewPager) rootView.findViewById(R.id.viewPagerAlbumExpanded);
			expandedView.performClick();
		}
	}

	public void update(boolean forceDownload)
	{
		if (isPictureExtended) albumExpanedPagerAdapter.update(forceDownload);
		else albumAdapter.update(forceDownload);
	}

	// public long getRecentId()
	// {
	// return recentId;
	// }
}