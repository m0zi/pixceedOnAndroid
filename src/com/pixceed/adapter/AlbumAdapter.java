package com.pixceed.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixceed.R;
import com.pixceed.data.Album;
import com.pixceed.data.Album.ImageDay;
import com.pixceed.data.Album.ImageDay.ImagePreviewInformation;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.AlbumTask;
import com.pixceed.util.Memory;

public class AlbumAdapter extends ArrayAdapter<ImagePreviewInformation> implements OnPostExecuteInterface<Album>
{
	private int albumId;
	private Context context;
	private ArrayList<ImagePreviewInformation> albumImages;

	public AlbumAdapter(Context context, int albumId)
	{
		super(context, R.drawable.ic_launcher);
		this.context = context;
		this.albumImages = new ArrayList<ImagePreviewInformation>();
		this.albumId = albumId;
		update();
	}

	@Override
	public int getCount()
	{
		if (albumImages.isEmpty())
			return super.getCount();
		return albumImages.size();
	}

	@Override
	public ImagePreviewInformation getItem(int position)
	{
		if (albumImages.isEmpty())
			return super.getItem(position);
		return albumImages.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		if (albumImages.isEmpty())
			return super.getItemId(position);
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (albumImages.isEmpty())
			return super.getView(position, convertView, parent);
		View v = convertView;
		ImageView picture;
		TextView name;

		if (v == null)
		{
			v = LayoutInflater.from(context).inflate(R.layout.gridview_squared_image_item, parent, false);
			v.setTag(R.id.squaredImage, v.findViewById(R.id.squaredImage));
			v.setTag(R.id.textViewSquarePicture, (TextView) v.findViewById(R.id.textViewSquarePicture));
		}
		picture = (ImageView) v.getTag(R.id.squaredImage);
		name = (TextView) v.getTag(R.id.textViewSquarePicture);

		ImagePreviewInformation item = getItem(position);

		name.setText(item.getName());

		Memory.loadAndSetBitmap(item.getImageIcon(), picture);
		return v;
	}

	public void update()
	{
		Album album = Memory.getAlbumFromMemoryCache(albumId);
		if (album == null) new AlbumTask(context, this).execute("/" + albumId);
		else onPostExecute(album);
	}

	@Override
	public void onPostExecute(Album album)
	{
		if (album == null)
		{
			Log.e("ALBUM", String.format("Album with number %s not received.", albumId));
			return;
		}
		if (album.getId() != albumId)
			Log.w("ALBUM", String.format("Received album id (%s) does not match expected (%s).", album.getId(), albumId));
		albumImages.clear();
		// add up all picture icons once
		for (ImageDay imageDay : album.getImagesOrderedByDay())
			albumImages.addAll(imageDay.getImagePreviewInformations());
		notifyDataSetChanged();
	}

}