package com.pixceed;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.pixceed.download.DownloadJSONTask;
import com.pixceed.download.OnPostExecuteInterface;

public class GalleryActivity extends FragmentActivity
{
	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
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
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */
	private class GalleryPagerAdapter extends FragmentStatePagerAdapter implements OnPostExecuteInterface<JSONArray>
	{
		private int[] albums;

		public GalleryPagerAdapter(
				FragmentManager fm)
		{
			super(fm);
			updateAlbums();
		}

		@Override
		public Fragment getItem(int position) {
			AlbumFragment albumFragment = new AlbumFragment();
			Bundle args = new Bundle();
			if (albums == null || position < albums.length)
				args.putInt("imageNumber", albums[position]);
			albumFragment.setArguments(args);
			return albumFragment;
		}

		@Override
		public int getCount() {
			if (albums == null)
				return 0;
			return albums.length;
		}

		public void updateAlbums()
		{
			new DownloadJSONTask(this).execute(MainActivity.URL_STRING + MainActivity.URL_HP_IMAGES);
		}

		@Override
		public void onPostExecute(JSONArray result) {
			if (result == null)
			{
				Log.e("ALBUM", "No albums received.");
				return;
			}
			albums = new int[result.length()];
			for (int i = 0; i < albums.length; i++)
				try
				{
					albums[i] = result.getInt(i);
				}
				catch (JSONException e)
				{
					Log.e("ALBUM", "error retrieving data for albums", e);
				}
			notifyDataSetChanged();
		}
	}
}
