package com.pixceed.fragment;

import java.util.Locale;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.pixceed.AlbumActivity;
import com.pixceed.R;
import com.pixceed.download.data.PublicPictureTask;

public class AlbumPreviewFragment extends Fragment implements OnClickListener
{
	private ImageView imageViewAlbumIcon;
	private TextView textViewAlbumName;
	private int albumId;

	public AlbumPreviewFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_album_preview, container, false);
		imageViewAlbumIcon = (ImageView) rootView.findViewById(R.id.imageViewAlbumIcon);
		textViewAlbumName = (TextView) rootView.findViewById(R.id.textViewAlbumName);
		Bundle args = getArguments();
		if (args == null)
		{
			Log.e("ALBUM", "no album contained");
		}
		if (args.containsKey("image"))
		{
			imageViewAlbumIcon.setScaleType(ScaleType.CENTER_CROP);
			byte[] imageByteArray = args.getByteArray("image");
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
			options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, imageViewAlbumIcon.getWidth(), imageViewAlbumIcon.getHeight());
			options.inJustDecodeBounds = false;
			imageViewAlbumIcon.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
			imageViewAlbumIcon.setOnClickListener(this);
			textViewAlbumName.setOnClickListener(this);
		}
		else
		{
			imageViewAlbumIcon.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		}
		textViewAlbumName.setText(args.getString("name"));
		albumId = args.getInt("id");
		Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
		Debug.getMemoryInfo(memoryInfo);

		String memMessage = String.format(Locale.GERMAN,
				"Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB",
				memoryInfo.getTotalPss() / 1024.0,
				memoryInfo.getTotalPrivateDirty() / 1024.0,
				memoryInfo.getTotalSharedDirty() / 1024.0);
		Log.d("ALBUM", "Remaining memory:" + memMessage);
		return rootView;
	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent(v.getContext(), AlbumActivity.class);
		Toast.makeText(v.getContext(), "Item clicked: " + textViewAlbumName.getText(), Toast.LENGTH_SHORT).show();
		intent.putExtra("com.pixceed.AlbumId", albumId);
		startActivity(intent);
	}

}
