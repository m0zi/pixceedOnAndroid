package com.pixceed;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.pixceed.fragment.SettingsFragment;

public class SettingsActivity extends ActionBarActivity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment())
				.commit();
	}
}
