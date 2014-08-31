package com.pixceed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

		Memory.init(getApplicationContext());

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.login, new LoginFragment()).commit();
		}
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
		return super.onOptionsItemSelected(item);
	}

	public void login(View view)
	{
		final String username = ((EditText) findViewById(R.id.editTextLoginName)).getText().toString();
		final String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
		OnPostExecuteInterface<Login> loginExecuter = new OnPostExecuteInterface<Login>()
		{
			@Override
			public void onPostExecute(Login result)
			{
				if (((CheckBox) findViewById(R.id.checkBoxSaveLoginName)).isChecked())
					Memory.loginName = username;
				if (result != null && result.getToken() != null)
				{
					// login successfully
					Memory.token = result.getToken();
					Intent intent = new Intent(LoginActivity.this, LibraryActivity.class);
					Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_LONG).show();
					startActivity(intent);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_LONG).show();
					Log.e("LOGIN", "login failed");
				}
			}
		};
		new LoginTask(getApplicationContext(), loginExecuter, username, password).execute();
	}
}
