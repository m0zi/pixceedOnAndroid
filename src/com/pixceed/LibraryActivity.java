package com.pixceed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.pixceed.adapter.LibraryAdapter;
import com.pixceed.util.Memory;
import com.pixceed.util.Updateable;

public class LibraryActivity extends ActionBarActivity
{
	private ViewPager pager;
	private Updateable updateDelegate;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// final ActionBar actionBar = getActionBar();
		setContentView(R.layout.activity_library);
		LibraryAdapter adapter = new LibraryAdapter(getApplicationContext(), getSupportFragmentManager());
		updateDelegate = adapter;
		pager = (ViewPager) findViewById(R.id.libraryPager);
		pager.setAdapter(adapter);

		getSupportActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id)
		{

		case R.id.action_settings:
			return true;
		case R.id.action_logout:
			Memory.token = null;
		case android.R.id.home:
			return logout();
		case R.id.action_refresh:
			updateDelegate.update(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPause()
	{
		Log.d("LIBRARY", "save data");
		Memory.save(getSharedPreferences(Memory.PIXCEED_TAG, Context.MODE_PRIVATE).edit()).commit();
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		Log.d("LIBRARY", "destroyed");
		super.onDestroy();
	}

	@Override
	public void onBackPressed()
	{
		if (logout())
			super.onBackPressed();
	}

	/**
	 * Performs the logout. This function always logs you out and reinitialize the caches.
	 * 
	 * @return <code>true</code> if real logout has occurred, <code>false</code> if only confirmation has been shown.
	 */
	private boolean logout()
	{
		Log.d("LIBRARY", "logout performed");
		Memory.initCaches();
		finish();
		return true;
	}
}
