package com.pixceed.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Base64;
import android.util.Log;

import com.pixceed.data.LibraryMonth;
import com.pixceed.data.LibraryMonth.AlbumPreview;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.LibrariesTask;
import com.pixceed.fragment.AlbumPreviewFragment;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in sequence.
 */
public class GalleryPagerAdapter extends FragmentStatePagerAdapter implements OnPostExecuteInterface<ArrayList<LibraryMonth>>
{
	private ArrayList<AlbumPreview> albums;

	public GalleryPagerAdapter(
			FragmentManager fm)
	{
		super(fm);
		albums = new ArrayList<AlbumPreview>();
		updateAlbums();
	}

	@Override
	public Fragment getItem(int position)
	{
		AlbumPreviewFragment albumFragment = new AlbumPreviewFragment();
		Bundle args = new Bundle();
		if (albums != null && position < albums.size())
		{
			AlbumPreview album = albums.get(position);
			args.putString("name", album.getAlbumName());
			args.putByteArray("image", Base64.decode(album.getAlbumIcon(), Base64.DEFAULT));
			args.putInt("id", album.getAlbumId());
		}
		albumFragment.setArguments(args);
		return albumFragment;
	}

	@Override
	public int getCount()
	{
		if (albums == null)
			return 0;
		return albums.size();
	}

	public void updateAlbums()
	{
		new LibrariesTask(this).execute();
	}

	@Override
	public void onPostExecute(ArrayList<LibraryMonth> result)
	{
		if (result == null)
		{
			Log.e("LIBRARY", "No albums received.");
			return;
		}
		albums.clear();
		for (LibraryMonth libraryMonth : result)
			albums.addAll(libraryMonth.getAlbumPreview());
		notifyDataSetChanged();
	}
}