package com.pixceed;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.pixceed.data.Login;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.LoginTask;
import com.pixceed.fragment.LoginFragment;
import com.pixceed.util.Memory;

public class LoginActivity extends ActionBarActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		SharedPreferences nvmData = getSharedPreferences(Memory.PIXCEED_TAG, Context.MODE_PRIVATE);
		Memory.init(getApplicationContext(), nvmData);

		getSupportFragmentManager().beginTransaction().add(R.id.login, new LoginFragment()).commit();
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
		if (id == R.id.action_settings) { return true; }
		if (id == R.id.action_refresh)
		{
			Memory.initCaches();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void login(View view)
	{
		if (Memory.token != null)
		{
			// we already have a token, so use this one
			Intent intent = new Intent(LoginActivity.this, LibraryActivity.class);
			Toast.makeText(getApplicationContext(), "Login resumed.", Toast.LENGTH_SHORT).show();
			startActivity(intent);
		}
		else
		{
			// no token available, so send a login request
			final String username = ((EditText) findViewById(R.id.editTextLoginName)).getText().toString();
			final String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
			final Button loginBtn = (Button) findViewById(R.id.buttonLogin);
			loginBtn.setEnabled(false);
			OnPostExecuteInterface<Login> loginExecuter = new OnPostExecuteInterface<Login>()
			{
				@Override
				public void onPostExecute(Login result)
				{
					final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxSaveLoginName);
					if (checkBox != null)
						Memory.isRememberEmailChecked = checkBox.isChecked();
					if (Memory.isRememberEmailChecked)
						Memory.loginName = username;
					if (result != null && result.getToken() != null)
					{
						// login successfully
						Memory.token = result.getToken().trim();
						Intent intent = new Intent(LoginActivity.this, LibraryActivity.class);
						Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_SHORT).show();
						startActivity(intent);
					}
					else
					{
						Memory.token = null;
						final String loginFailedMessage;
						if (result == null || result.getErrorDescription().isEmpty()) loginFailedMessage = "Login failed. Make sure that internet connection is available and try again.";
						else loginFailedMessage = result.getErrorDescription();
						Toast.makeText(getApplicationContext(), loginFailedMessage, Toast.LENGTH_LONG).show();
						Log.e("LOGIN", loginFailedMessage);
					}
					loginBtn.setEnabled(true);
				}
			};
			new LoginTask(getApplicationContext(), loginExecuter, username, password).execute();
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		final Button loginBtn = (Button) findViewById(R.id.buttonLogin);
		if (Memory.token != null)
		{
			Log.d("LOGIN", "resume login with token");
			loginBtn.setText(R.string.relogin);
		}
		else
		{
			Log.d("LOGIN", "resume login without token");
			loginBtn.setText(R.string.login);
		}
	}

	protected void onPause()
	{
		super.onPause();
		Log.d("LOGIN", "save data");
		Memory.save(getPreferences(Context.MODE_PRIVATE).edit()).commit();
	}
}
