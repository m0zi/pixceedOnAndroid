package com.pixceed;

import java.util.Collection;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.pixceed.data.Article;
import com.pixceed.download.ArticlesTask;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.PublicPictureListTask;
import com.pixceed.download.PublicPictureTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment
{
	public LoginFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

		if (MainActivity.checkConnection(rootView.getContext().getApplicationContext()))
		{
			// show sample text
			final OnPostExecuteInterface<Collection<Article>> showDemoText = new OnPostExecuteInterface<Collection<Article>>()
			{
				@Override
				public void onPostExecute(Collection<Article> result) {
					if (result != null)
					((TextView) rootView.findViewById(R.id.textViewAlbumName)).setText(result.iterator().next().getHtml());
					else
					((TextView) rootView.findViewById(R.id.textViewAlbumName)).setText("");
					Toast.makeText(rootView.getContext().getApplicationContext(), "Download complete", Toast.LENGTH_SHORT).show();
				}
			};
			new ArticlesTask(showDemoText).execute();

			// show sample image
			final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewAlbum);
			final OnPostExecuteInterface<Bitmap> showDemoPicture = new OnPostExecuteInterface<Bitmap>()
			{
				@Override
				public void onPostExecute(Bitmap result) {
					imageView.setScaleType(ScaleType.CENTER_CROP);
					imageView.setImageBitmap(result);
					Toast.makeText(rootView.getContext().getApplicationContext(), "Download picture complete", Toast.LENGTH_SHORT).show();
				}
			};
			final OnPostExecuteInterface<int[]> getRandomPublicPicture = new OnPostExecuteInterface<int[]>()
			{
				@Override
				public void onPostExecute(int[] result) {
					if (result == null || result.length < 1)
						return;
					new PublicPictureTask(showDemoPicture, imageView.getWidth(), imageView.getHeight()).execute("" + result[0]);
				}
			};
			new PublicPictureListTask(getRandomPublicPicture).execute();
		}

		return rootView;
	}
}