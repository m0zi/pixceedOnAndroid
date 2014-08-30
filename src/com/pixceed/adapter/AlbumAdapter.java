package com.pixceed.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.pixceed.R;
import com.pixceed.data.Album;
import com.pixceed.data.Album.ImageDay;
import com.pixceed.data.Album.ImageDay.ImagePreviewInformation;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.AlbumTask;
import com.pixceed.download.data.PublicPictureTask;

public class AlbumAdapter extends ArrayAdapter<ImagePreviewInformation> implements OnPostExecuteInterface<Album>
{
	private int albumId;
	private LayoutInflater inflater;
	private ArrayList<ImagePreviewInformation> albumImages;

	public AlbumAdapter(Context context, int albumId)
	{
		super(context, R.drawable.ic_launcher);
		inflater = LayoutInflater.from(context);
		this.albumImages = new ArrayList<ImagePreviewInformation>();
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
		View v = convertView;
		ImageView picture;
		TextView name;

		if (v == null)
		{
			v = inflater.inflate(R.layout.gridview_item, parent, false);
			v.setTag(R.id.picture, v.findViewById(R.id.picture));
			TextView textView = (TextView) v.findViewById(R.id.text);
//			textView.setMovementMethod(new ScrollingMovementMethod());
			v.setTag(R.id.text, textView);
		}
		picture = (ImageView) v.getTag(R.id.picture);
		name = (TextView) v.getTag(R.id.text);

		ImagePreviewInformation item = getItem(position);

		name.setText(item.getName());

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(item.getImageIcon().getBytes(), Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, picture.getWidth(), picture.getHeight());
		options.inJustDecodeBounds = false;
		picture.setScaleType(ScaleType.CENTER_CROP);
		picture.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
		return v;
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