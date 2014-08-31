package com.pixceed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.pixceed.AlbumActivity;
import com.pixceed.R;
import com.pixceed.adapter.GroupAdapter;

public class GroupFragment extends Fragment implements OnItemClickListener
{

	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_group, container, false);
		
		GridView gridViewGroup = (GridView) rootView.findViewById(R.id.gridViewGroup);
		GroupAdapter albumAdapter = new GroupAdapter(rootView.getContext(), getArguments().getLong("id"));
		gridViewGroup.setAdapter(albumAdapter);
		gridViewGroup.setOnItemClickListener(this);
		
		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View itemView, int position, long id)
	{
		Intent intent = new Intent(rootView.getContext(), AlbumActivity.class);
		intent.putExtra("com.pixceed.AlbumId", (int)id);
		startActivity(intent);		
	}

}
