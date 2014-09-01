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

public class LibraryActivity extends ActionBarActivity
{
	private ViewPager pager;
	private LibraryAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// final ActionBar actionBar = getActionBar();
		setContentView(R.layout.activity_library);
		adapter = new LibraryAdapter(getApplicationContext(), getSupportFragmentManager());
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
		case android.R.id.home:
			// this is a willingly logout
			Memory.token = null;
			logout();
			return false;
		case R.id.action_refresh:
			Memory.initCaches();
			adapter.update();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d("LIBRARY", "save data");
		Memory.save(getPreferences(Context.MODE_PRIVATE).edit()).commit();
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		logout();
	}

	private void logout()
	{
		Log.d("LIBRARY", "logout and save data");
		Memory.token = null;
		Memory.save(getPreferences(Context.MODE_PRIVATE).edit()).commit();
	}
}
