package com.pixceed;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.SendLoginJSONTask;

public class MainActivity extends ActionBarActivity {

	public final static String URL_STRING = "https://www.pixceed.com/api/";
	public final static String URL_LOGIN = "token";
	public final static String URL_DEBUG_STRING = "homepage/articles";
	public final static String URL_PICTURE = "https://www.pixceed.com/pixceed/Image/GetImage/";
	public static final String URL_HP_IMAGES = "homepage/images";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void login(View view) {

		if (checkConnection(getApplicationContext())) {
			String username = ((EditText) findViewById(R.id.editTextLoginName)).getText().toString();
			String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
			if(((CheckBox) findViewById(R.id.checkBoxSaveLoginName)).isChecked())
				{
				username = "m.remmos@googlemail.com";
				password = "Th1s1sthelast4pixceed";
				}
			OnPostExecuteInterface<JSONObject> loginExecuter = new OnPostExecuteInterface<JSONObject>() {
				@Override
				public void onPostExecute(JSONObject result) {
					try {
						if (result != null) {
							// login successfully
							String token = result.getString("access_token");
							Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
							intent.putExtra("com.pixceed.token", token);
							Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
							startActivity(intent);
						} else {
							Log.e("LOGIN", "login failed");
						}
					} catch (JSONException e) {
						// login failed
						try {
							Log.e("LOGIN", result.getString("error"));
						} catch (JSONException e1) {
							Log.e("LOGIN", "login failed", e1);
						}
					}
				}
			};
			new SendLoginJSONTask(loginExecuter, username, password).execute(URL_STRING + URL_LOGIN);

		} else {
			Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
		}
	}

	public static boolean checkConnection(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}
}
