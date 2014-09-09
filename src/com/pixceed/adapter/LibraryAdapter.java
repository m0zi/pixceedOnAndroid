package com.pixceed.adapter;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.pixceed.data.GroupDescription;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.GroupDescriptionsTask;
import com.pixceed.fragment.GroupFragment;
import com.pixceed.fragment.UserLibraryFragment;
import com.pixceed.util.Memory;

public class LibraryAdapter extends FragmentPagerAdapter implements OnPostExecuteInterface<Collection<GroupDescription>>
{
	private final ArrayList<GroupDescription> groups;
	private final Context context;

	public LibraryAdapter(Context context, FragmentManager fm)
	{
		super(fm);
		this.context = context;
		groups = new ArrayList<GroupDescription>();
		update(false);
	}

	@Override
	public Fragment getItem(int position)
	{
		Fragment fragment;
		if (position == 0) fragment = new UserLibraryFragment();
		else
		{
			fragment = new GroupFragment();
			Bundle bundle = new Bundle();
			bundle.putLong("id", getItemId(position));
			fragment.setArguments(bundle);
		}
		return fragment;
	}

	@Override
	public long getItemId(int position)
	{
		if (position == 0)
			return 0;
		if (groups.isEmpty())
			return super.getItemId(position);
		return groups.get(position - 1).getId();
	}

	@Override
	public int getCount()
	{
		return groups.size() + 1;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		if (position == 0)
			return "Deine Alben";
		final GroupDescription groupDescription = groups.get(position - 1);
		return groupDescription.getName() + " (" + groupDescription.getFirstName() + " " + groupDescription.getLastName() + ")";
	}

	public void update(boolean forceDownload)
	{
		
		Collection<GroupDescription> groups = Memory.getGroupDescriptionsFromMemoryCache();
		if (groups == null || forceDownload) new GroupDescriptionsTask(context, this).execute();
		else onPostExecute(groups);
	}

	@Override
	public void onPostExecute(Collection<GroupDescription> groups)
	{
		if (groups == null)
		{
			Log.e("LIBRARY", "Library not received.");
			return;
		}
		this.groups.clear();
		this.groups.addAll(groups);
		notifyDataSetChanged();
	}
}
