package com.pixceed.adapter;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixceed.R;
import com.pixceed.data.LibraryMonth;
import com.pixceed.data.LibraryMonth.AlbumPreview;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.UserLibrariesTask;
import com.pixceed.util.Memory;

public class UserLibraryAdapter extends ArrayAdapter<AlbumPreview> implements OnPostExecuteInterface<Collection<LibraryMonth>>
{

	private Context context;
	private ArrayList<AlbumPreview> userLibrary;

	public UserLibraryAdapter(Context context)
	{
		super(context, R.drawable.ic_launcher);
		this.context = context;
		this.userLibrary = new ArrayList<AlbumPreview>();
		update();
	}

	@Override
	public int getCount()
	{
		if (userLibrary.isEmpty())
			return super.getCount();
		return userLibrary.size();
	}

	@Override
	public AlbumPreview getItem(int position)
	{
		if (userLibrary.isEmpty())
			return super.getItem(position);
		return userLibrary.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		if (userLibrary.isEmpty())
			return super.getItemId(position);
		return getItem(position).getAlbumId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (userLibrary.isEmpty())
			return super.getView(position, convertView, parent);

		View v = convertView;
		ImageView picture;
		TextView name;

		if (v == null)
		{
			v = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false);
			v.setTag(R.id.picture, v.findViewById(R.id.picture));
			v.setTag(R.id.text, (TextView) v.findViewById(R.id.text));
		}
		picture = (ImageView) v.getTag(R.id.picture);
		name = (TextView) v.getTag(R.id.text);

		AlbumPreview item = getItem(position);

		name.setText(item.getAlbumName());

		Memory.loadBitmap(item.getAlbumIcon(), picture);
		return v;
	}

	public void update()
	{
		Collection<LibraryMonth> library = Memory.getUserLibraryFromMemoryCache();
		if (library == null) new UserLibrariesTask(context, this).execute();
		else onPostExecute(library);
	}

	@Override
	public void onPostExecute(Collection<LibraryMonth> library)
	{
		if (library == null)
		{
			Log.e("USER_LIBRARY", "User library not received.");
			return;
		}
		this.userLibrary.clear();
		// add up all picture icons once
		for (LibraryMonth libraryMonth : library)
			this.userLibrary.addAll(libraryMonth.getAlbumPreview());
		notifyDataSetChanged();
	}

}
