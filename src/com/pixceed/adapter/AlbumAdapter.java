package com.pixceed.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.pixceed.R;
import com.pixceed.data.Album;
import com.pixceed.data.Album.ImageDay;
import com.pixceed.data.Album.ImageDay.ImagePreviewInformation;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.AlbumTask;
import com.pixceed.download.data.PublicPictureTask;

public class AlbumAdapter extends ArrayAdapter<ImagePreviewInformation> implements OnPostExecuteInterface<Album>
{
	private Context context;
	private int albumId;
	private ArrayList<ImagePreviewInformation> albumImages;

	public AlbumAdapter(Context context, int albumId)
	{
		super(context, R.drawable.ic_launcher);
		this.albumImages = new ArrayList<ImagePreviewInformation>();
		this.context = context;
		this.albumId = albumId;
		update();
	}

	@Override
	public int getCount()
	{
		return albumImages.size();
	}

	@Override
	public ImagePreviewInformation getItem(int position)
	{
		return albumImages.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageViewPictureIcon;
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			imageViewPictureIcon = new ImageView(context);
//			imageViewPictureIcon.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageViewPictureIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageViewPictureIcon.setPadding(8, 8, 8, 8);
		}
		else imageViewPictureIcon = (ImageView) convertView;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(albumImages.get(position).getImageIcon().getBytes(), Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, imageViewPictureIcon.getWidth(), imageViewPictureIcon.getHeight());
		options.inJustDecodeBounds = false;
		imageViewPictureIcon.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
		return imageViewPictureIcon;
	}

	private void update()
	{
		new AlbumTask(this).execute("/" + albumId);
	}

	@Override
	public void onPostExecute(Album album)
	{
		if (album == null)
		{
			Log.e("LIBRARY", String.format("Album with number %s not received.", albumId));
			return;
		}
		if (album.getId() != albumId)
			Log.w("LIBRARY", String.format("Received album id (%s) does not match expected (%s).", album.getId(), albumId));
		albumImages.clear();
		// add up all picture icons once
		for (ImageDay imageDay : album.getImagesOrderedByDay())
			albumImages.addAll(imageDay.getImagePreviewInformations());
		notifyDataSetChanged();
	}

}