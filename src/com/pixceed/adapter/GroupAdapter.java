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
import com.pixceed.data.Group;
import com.pixceed.data.Group.GroupFolder;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.GroupTask;
import com.pixceed.util.Memory;

public class GroupAdapter extends ArrayAdapter<GroupFolder> implements OnPostExecuteInterface<Group>
{
	private ArrayList<GroupFolder> groupLibrary;
	private Context context;
	private long groupId;

	public GroupAdapter(Context context, long groupId)
	{
		super(context, R.drawable.ic_launcher);
		this.context = context;
		groupLibrary = new ArrayList<GroupFolder>();
		this.groupId = groupId;
		update();
	}

	@Override
	public int getCount()
	{
		if (groupLibrary.isEmpty())
			return super.getCount();
		return groupLibrary.size();
	}

	@Override
	public GroupFolder getItem(int position)
	{
		if (groupLibrary.isEmpty())
			return super.getItem(position);
		return groupLibrary.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		if (groupLibrary.isEmpty())
			return super.getItemId(position);
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (groupLibrary.isEmpty())
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

		GroupFolder item = getItem(position);

		name.setText(item.getName());

		Memory.loadBitmap(item.getFolderIcon(), picture);
		return v;
	}

	private void update()
	{
		Group group = Memory.getGroupFromMemoryCache(groupId);
		if (group == null) new GroupTask(context, this).execute("/" + groupId);
		else onPostExecute(group);
	}

	@Override
	public void onPostExecute(Group group)
	{
		if (group == null)
		{
			Log.e("GROUP", String.format("Group with number %s not received.", groupId));
			return;
		}
		if (group.getGroupId() != groupId)
			Log.w("GROUP", String.format("Received group id (%s) does not match expected (%s).", group.getGroupId(), groupId));
		groupLibrary.clear();
		// add up all folders
		groupLibrary.addAll(group.getFolders());
		notifyDataSetChanged();
	}
}
