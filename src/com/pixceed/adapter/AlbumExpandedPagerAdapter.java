package com.pixceed.adapter;

import java.lang.ref.WeakReference;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pixceed.data.Album.ImageDay.ImagePreviewInformation;
import com.pixceed.fragment.ImageFragment;

public class AlbumExpandedPagerAdapter extends FragmentStatePagerAdapter
{
	private final AlbumAdapter albumAdapter;

	public AlbumExpandedPagerAdapter(FragmentManager fm, AlbumAdapter albumAdapter)
	{
		super(fm);
		this.albumAdapter = albumAdapter;
		albumAdapter.registerDataSetObserver(new DelegateDataSetObserver(new WeakReference<AlbumExpandedPagerAdapter>(this)));
	}

	@Override
	public Fragment getItem(int position)
	{
		final ImageFragment imageFragment = new ImageFragment();
		Bundle args = new Bundle();
		final ImagePreviewInformation item = albumAdapter.getItem(position);
		if (item != null)
		{
			args.putString(ImageFragment.IMAGE_ICON_KEY, item.getImageIcon());
			args.putLong(ImageFragment.ID_KEY, albumAdapter.getItemId(position));
		}
		imageFragment.setArguments(args);
		return imageFragment;
	}

	@Override
	public int getCount()
	{
		return albumAdapter.getCount();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return albumAdapter.getItem(position).getName();
	}

	static class DelegateDataSetObserver extends DataSetObserver
	{
		/**
		 * Someone says, it's Best Practice to use {@link WeakReference} here to avoid memory leak.
		 */
		private final WeakReference<AlbumExpandedPagerAdapter> reference;

		public DelegateDataSetObserver(WeakReference<AlbumExpandedPagerAdapter> reference)
		{
			this.reference = reference;
		}

		@Override
		public void onChanged()
		{
			final AlbumExpandedPagerAdapter albumExpandedPagerAdapter = (AlbumExpandedPagerAdapter) reference.get();
			// since this is a WeakReference, the reference could be gone
			if (albumExpandedPagerAdapter != null) albumExpandedPagerAdapter.notifyDataSetChanged();
		}
	}
}
