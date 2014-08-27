package com.pixceed.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
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
import com.pixceed.download.data.PublicPictureTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumFragment extends Fragment implements OnItemClickListener
{

	/**
	 * Hold a reference to the current animator, so that it can be canceled mid-way.
	 */
	private Animator mCurrentAnimator;

	public AlbumFragment()
	{}

	/**
	 * The system "short" animation time duration, in milliseconds. This /* duration is ideal for subtle animations or animations that occur very
	 * frequently.
	 */
	private int mShortAnimationDuration;

	private View rootView;

	private GridView gridViewAlbum;

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
		Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

		if (parent.getAdapter() instanceof AlbumAdapter)
		{
			zoomPictureFromIcon((ImageView) v, (ImagePreviewInformation) parent.getAdapter().getItem(position));
		}
	}

	private void zoomPictureFromIcon(final ImageView v, ImagePreviewInformation ipi)
	{
		// If there's an animation in progress, cancel it
		// immediately and proceed with this one.
		if (mCurrentAnimator != null)
			mCurrentAnimator.cancel();

		// Load the high-resolution "zoomed-in" image.
		final ImageView expandedImageView = (ImageView) getActivity().findViewById(R.id.expanded_picture);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(ipi.getImageIcon().getBytes(), Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, expandedImageView.getWidth(), expandedImageView.getHeight());
		options.inJustDecodeBounds = false;
		expandedImageView.setScaleType(ScaleType.FIT_CENTER);
		expandedImageView.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));

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
		v.getGlobalVisibleRect(startBounds);
		View findViewById = getActivity().findViewById(R.id.albumContainer);
		findViewById.getGlobalVisibleRect(finalBounds, globalOffset);
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
		v.setAlpha(0f);
		expandedImageView.setVisibility(View.VISIBLE);

		// Set the pivot point for SCALE_X and SCALE_Y transformations
		// to the top-left corner of the zoomed-in view (the default
		// is the center of the view).
		expandedImageView.setPivotX(0f);
		expandedImageView.setPivotY(0f);

		// Construct and run the parallel animation of the four translation and
		// scale properties (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		set
				.play(ObjectAnimator.ofFloat(expandedImageView, "translationX",
						startBounds.left, finalBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, "translationY",
						startBounds.top, finalBounds.top))
				.with(ObjectAnimator.ofFloat(expandedImageView, "scaleX",
						startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
						"scaleY", startScale, 1f));
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation)
			{
				mCurrentAnimator = null;
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{
				mCurrentAnimator = null;
			}
		});

		set.start();
		mCurrentAnimator = set;

		// Upon clicking the zoomed-in image, it should zoom back down
		// to the original bounds and show the thumbnail instead of
		// the expanded image.
		final float startScaleFinal = startScale;
		expandedImageView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (mCurrentAnimator != null)
				{
					mCurrentAnimator.cancel();
				}

				// Animate the four positioning/sizing properties in parallel,
				// back to their original values.
				AnimatorSet set = new AnimatorSet();
				set.play(ObjectAnimator
						.ofFloat(expandedImageView, "translationX", startBounds.left))
						.with(ObjectAnimator
								.ofFloat(expandedImageView,
										"translationY", startBounds.top))
						.with(ObjectAnimator
								.ofFloat(expandedImageView,
										"scaleX", startScaleFinal))
						.with(ObjectAnimator
								.ofFloat(expandedImageView,
										"scaleY", startScaleFinal));
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new DecelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter()
				{
					@Override
					public void onAnimationEnd(Animator animation)
					{
						v.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation)
					{
						v.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}
				});
				set.start();
				mCurrentAnimator = set;
			}
		});
	}
}