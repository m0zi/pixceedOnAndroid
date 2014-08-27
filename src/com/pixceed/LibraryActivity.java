package com.pixceed;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.pixceed.adapter.GalleryPagerAdapter;
import com.pixceed.view.ZoomOutPageTransformer;

public class LibraryActivity extends ActionBarActivity
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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library);

		// Instantiate a ViewPager and a PagerAdapter.
		pager = (ViewPager) findViewById(R.id.gallery);
		pagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
		pager.setPageTransformer(true, new ZoomOutPageTransformer());
		pager.setAdapter(pagerAdapter);
	}

	@Override
	public void onBackPressed()
	{
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
}
