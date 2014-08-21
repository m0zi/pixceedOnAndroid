package com.pixceed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.pixceed.download.DownloadPictureTask;
import com.pixceed.download.OnPostExecuteInterface;

public class AlbumFragment extends Fragment implements OnPostExecuteInterface<Bitmap>
{

	private ImageView imageViewAlbum;
	private TextView textViewAlbumName;
	private int imageNumber;

	public AlbumFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_album, container, false);
		imageViewAlbum = (ImageView) rootView.findViewById(R.id.imageViewAlbum);
		textViewAlbumName = (TextView) rootView.findViewById(R.id.textViewAlbumName);
		if (getArguments() != null && getArguments().containsKey("imageNumber")) imageNumber = getArguments().getInt("imageNumber");
		else imageNumber = 0;
		if (imageNumber == 0)
			// TODO is there an image with number 0 in the database?
			Log.e("ALBUM", "Image with number 0 loaded. Possible error here!");
		// TODO implement download of album name
		// new DownloadJSONTask(new OPEI4AlbumName()).execute(params)
		// TODO replace this after "real" implementation
		textViewAlbumName.setText("ImageNumber:" + imageNumber);
		// TODO change to real image download
		new DownloadPictureTask(this, imageViewAlbum.getWidth(), imageViewAlbum.getHeight()).execute(MainActivity.URL_PICTURE + imageNumber);
		return rootView;
	}

	@Override
	public void onPostExecute(Bitmap result) {
		imageViewAlbum.setScaleType(ScaleType.FIT_CENTER);
		if (result != null) imageViewAlbum.setImageBitmap(result);
		else imageViewAlbum.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
	}
}
