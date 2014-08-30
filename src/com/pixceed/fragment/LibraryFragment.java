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
import com.pixceed.adapter.LibraryAdapter;

public class LibraryFragment extends Fragment implements OnItemClickListener
{
	private View rootView;
	private GridView gridViewLibrary;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_library, container, false);

		gridViewLibrary = (GridView) rootView.findViewById(R.id.gridViewLibrary);
		LibraryAdapter albumAdapter = new LibraryAdapter(rootView.getContext());
		gridViewLibrary.setAdapter(albumAdapter);
		gridViewLibrary.setOnItemClickListener(this);
		
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
