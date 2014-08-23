package com.pixceed;

import java.util.Locale;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class AlbumFragment extends Fragment
{

	private ImageView imageViewAlbum;
	private TextView textViewAlbumName;

	public AlbumFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_album, container, false);
		imageViewAlbum = (ImageView) rootView.findViewById(R.id.imageViewAlbum);
		textViewAlbumName = (TextView) rootView.findViewById(R.id.textViewAlbumName);
		Bundle args = getArguments();
		if (args == null)
		{
			Log.e("ALBUM", "no album contained");
		}
		if (args.containsKey("image"))
		{
			imageViewAlbum.setScaleType(ScaleType.CENTER_CROP);
			byte[] imageByteArray = args.getByteArray("image");
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
//			options.inSampleSize = DownloadPictureTask.calculateInSampleSize(options, imageViewAlbum.getWidth(), imageViewAlbum.getHeight());
			options.inJustDecodeBounds = false;
			imageViewAlbum.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
		}
		else
		{
			imageViewAlbum.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		}
		textViewAlbumName.setText(args.getString("name"));
		Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
		Debug.getMemoryInfo(memoryInfo);

		String memMessage = String.format(Locale.GERMAN,
		    "Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB",
		    memoryInfo.getTotalPss() / 1024.0,
		    memoryInfo.getTotalPrivateDirty() / 1024.0,
		    memoryInfo.getTotalSharedDirty() / 1024.0);
		Log.d("ALBUM", "Remaining memory:"+memMessage);
		return rootView;
	}

}
