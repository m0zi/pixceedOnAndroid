package com.pixceed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.pixceed.download.DownloadJSONTask;
import com.pixceed.download.DownloadPictureTask;
import com.pixceed.download.OnPostExecuteInterface;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

	public static final String TAG = "LoginFragment";

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

		if (MainActivity.checkConnection(rootView.getContext().getApplicationContext())) {
			// show sample text
			OnPostExecuteInterface<JSONArray> showDemoText = new OnPostExecuteInterface<JSONArray>() {
				@Override
				public void onPostExecute(JSONArray result) {
					try {
						JSONObject json = result.getJSONObject(0);
						String string = json.getString("Html");
						((TextView) rootView.findViewById(R.id.textViewAlbumName)).setText(string);
					} catch (JSONException e) {
						((TextView) rootView.findViewById(R.id.textViewAlbumName)).setText("");
					}
					Toast.makeText(rootView.getContext().getApplicationContext(), "Download complete", Toast.LENGTH_SHORT).show();
				}
			};
			new DownloadJSONTask(showDemoText).execute(MainActivity.URL_ARTICLES);
			
			// show sample image
			final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewAlbum);
			OnPostExecuteInterface<Bitmap> showDemoPicture = new OnPostExecuteInterface<Bitmap>() {
				@Override
				public void onPostExecute(Bitmap result) {
					imageView.setScaleType(ScaleType.CENTER_CROP);
					imageView.setImageBitmap(result);
					Toast.makeText(rootView.getContext().getApplicationContext(), "Download picture complete", Toast.LENGTH_SHORT).show();
				}
			};
			new DownloadPictureTask(showDemoPicture, imageView.getWidth(), imageView.getHeight()).execute(MainActivity.URL_PUBLIC_PICTURE+"/53");
		}

		return rootView;
	}
}