package com.pixceed;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pixceed.adapter.LibraryAdapter;
import com.pixceed.util.Memory;

public class LibraryActivity extends ActionBarActivity
{
	private ViewPager pager;
	private LibraryAdapter adapter;
	private boolean hasAskedForLogout = false;

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
			Memory.token = null;
			return logout();
		case R.id.action_refresh:
			adapter.update(true);
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
	public void onBackPressed()
	{
		if (logout())
			super.onBackPressed();
	}

	/**
	 * Performs the logout if and only if user has confirmed.
	 * 
	 * @return <code>false</code> if real logout has occurred, <code>true</code> if only confirmation has been shown.
	 */
	private boolean logout()
	{
		if (hasAskedForLogout)
		{
			Log.d("LIBRARY", "logout performed");
			Memory.token = null;
//			Memory.save(getPreferences(Context.MODE_PRIVATE).edit()).commit();
			hasAskedForLogout = false;
		}
		else
		{
			Toast.makeText(getBaseContext(), "Zum Ausloggen Zurück-Taste noch einmal drücken.", Toast.LENGTH_LONG).show();
			AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>()
			{
				@Override
				protected Void doInBackground(Void... params)
				{
					try
					{
						Thread.sleep(4 * getResources().getInteger(android.R.integer.config_longAnimTime));
						Log.d("LIBRARY", "logout interrupted.");
						hasAskedForLogout = false;
					}
					catch (InterruptedException e)
					{
						Log.e("LOGOUT_WAIT", "Wait for user to confirm logout interrupted.", e);
					}
					return null;
				}
			};
			task.execute(new Void[0]);
			hasAskedForLogout = true;
			Log.d("LIBRARY", "logout asked.");
		}
		return !hasAskedForLogout;
	}
}
