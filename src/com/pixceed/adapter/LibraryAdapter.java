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
import com.pixceed.data.LibraryMonth;
import com.pixceed.data.LibraryMonth.AlbumPreview;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.LibrariesTask;
import com.pixceed.download.data.PublicPictureTask;

public class LibraryAdapter extends ArrayAdapter<AlbumPreview> implements OnPostExecuteInterface<ArrayList<LibraryMonth>>
{

	private LayoutInflater inflater;
	private ArrayList<AlbumPreview> library;

	public LibraryAdapter(Context context)
	{
		super(context, R.drawable.ic_launcher);
		inflater = LayoutInflater.from(context);
		this.library = new ArrayList<AlbumPreview>();
		update();
	}

	@Override
	public int getCount()
	{
		if (library.isEmpty())
			return super.getCount();
		return library.size();
	}

	@Override
	public AlbumPreview getItem(int position)
	{
		if (library.isEmpty())
			return super.getItem(position);
		return library.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		if(library.isEmpty())
			return super.getItemId(position);
		return getItem(position).getAlbumId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (library.isEmpty())
			return super.getView(position, convertView, parent);

		View v = convertView;
		ImageView picture;
		TextView name;

		if (v == null)
		{
			v = inflater.inflate(R.layout.gridview_item, parent, false);
			v.setTag(R.id.picture, v.findViewById(R.id.picture));
			v.setTag(R.id.text, (TextView) v.findViewById(R.id.text));
		}
		picture = (ImageView) v.getTag(R.id.picture);
		name = (TextView) v.getTag(R.id.text);

		AlbumPreview item = getItem(position);

		name.setText(item.getAlbumName());

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		byte[] imageByteArray = Base64.decode(item.getAlbumIcon().getBytes(), Base64.DEFAULT);
		BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		options.inSampleSize = PublicPictureTask.calculateInSampleSize(options, picture.getWidth(), picture.getHeight());
		options.inJustDecodeBounds = false;
		picture.setScaleType(ScaleType.CENTER_CROP);
		picture.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
		return v;
	}

	public void update()
	{
		new LibrariesTask(this).execute();
	}

	@Override
	public void onPostExecute(ArrayList<LibraryMonth> library)
	{
		if (library == null)
		{
			Log.e("LIBRARY", "Library not received.");
			return;
		}
		this.library.clear();
		// add up all picture icons once
		for (LibraryMonth libraryMonth : library)
			this.library.addAll(libraryMonth.getAlbumPreview());
		notifyDataSetChanged();
	}

}
