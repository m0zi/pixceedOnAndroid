package com.pixceed;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;

import com.pixceed.data.LibraryMonth;
import com.pixceed.data.LibraryMonth.AlbumPreviewInformation;
import com.pixceed.download.LibrariesTask;
import com.pixceed.download.OnPostExecuteInterface;

public class GalleryActivity extends FragmentActivity
{
	/**
	 * The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
	 */
	private ViewPager pager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery_slider);

		// Instantiate a ViewPager and a PagerAdapter.
		pager = (ViewPager) findViewById(R.id.gallery);
		pagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
	}

	@Override
	public void onBackPressed() {
		if (pager.getCurrentItem() == 0)
		{
			// If the user is currently looking at the first step, allow the
			// system to handle the
			// Back button. This calls finish() on this activity and pops the
			// back stack.
			super.onBackPressed();
		}
		else
		{
			// Otherwise, select the previous step.
			pager.setCurrentItem(pager.getCurrentItem() - 1);
		}
	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in sequence.
	 */
	private class GalleryPagerAdapter extends FragmentStatePagerAdapter implements OnPostExecuteInterface<ArrayList<LibraryMonth>>
	{
		private ArrayList<AlbumPreviewInformation> albums;

		public GalleryPagerAdapter(
				FragmentManager fm)
		{
			super(fm);
			albums = new ArrayList<AlbumPreviewInformation>();
			updateAlbums();
		}

		@Override
		public Fragment getItem(int position) {
			AlbumFragment albumFragment = new AlbumFragment();
			Bundle args = new Bundle();
			if (albums != null && position < albums.size())
			{
				AlbumPreviewInformation album = albums.get(position);
				args.putString("name", album.getAlbumName());
				args.putByteArray("image", Base64.decode(album.getAlbumIcon(), Base64.DEFAULT));
			}

			// if (albums == null || position < library.getAlbumInformations().size()) try
			// {
			// JSONObject jsonAlbumObject = library.getAlbumInformations().iterator().ge.get(position);
			// String string = jsonAlbumObject.getString("Name");
			// String imageBase64 = jsonAlbumObject.getString("IconBase64");
			// Log.d("ALBUM", imageBase64);
			// int[] encoding = new int[] { Base64.URL_SAFE, Base64.DEFAULT, Base64.CRLF, Base64.NO_CLOSE, Base64.NO_PADDING, Base64.NO_WRAP };
			// byte[] decode = null;
			// for (int i : encoding)
			// try
			// {
			// decode = Base64.decode(imageBase64, i);
			// }
			// catch (IllegalArgumentException e)
			// {
			//
			// }
			// if (decode != null)
			// args.putByteArray("image", decode);
			// args.putString("name", string);
			// }
			// catch (JSONException e)
			// {
			// Log.e("ALBUM", "album data corrupt at position:" + position, e);
			// }
			albumFragment.setArguments(args);
			return albumFragment;
		}

		@Override
		public int getCount() {
			if (albums == null)
				return 0;
			return albums.size();
		}

		public void updateAlbums()
		{
			new LibrariesTask(this).execute();
		}

		@Override
		public void onPostExecute(ArrayList<LibraryMonth> result) {
			if (result == null)
			{
				Log.e("ALBUM", "No albums received.");
				return;
			}
			albums.clear();
			for (LibraryMonth libraryMonth : result)
				albums.addAll(libraryMonth.getAlbumInformations());
			notifyDataSetChanged();
		}
	}
}
