package com.pixceed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixceed.R;
import com.pixceed.data.PixceedPicture;
import com.pixceed.download.OnPostExecuteInterface;
import com.pixceed.download.data.PictureTask;
import com.pixceed.util.Memory;

public class ImageFragment extends Fragment implements OnPostExecuteInterface<PixceedPicture>
{
	public static final String ID_KEY = "id";
	public static final String IMAGE_ICON_KEY = "imageIcon";
	private View rootView;
	private PixceedPicture picture;
	private String currentIcon;

	public ImageFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_image, container, false);
		final long id = getArguments().getLong(ID_KEY);
		picture = Memory.getPixceedPictureFromMemoryCache(id);
		ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewImagePicture);
		if (picture == null)
		{
			currentIcon = getArguments().getString(IMAGE_ICON_KEY);
			Memory.loadAndSetBitmap(currentIcon, imageView);
			new PictureTask(rootView.getContext(), this).execute("/" + id);
		}
		else onPostExecute(picture);
		return rootView;
	}

	@Override
	public void onPostExecute(PixceedPicture result)
	{
		if (result == null) return;
		Memory.loadAndSetBitmap(result.getImage(), ((ImageView) rootView.findViewById(R.id.imageViewImagePicture)), currentIcon);
		final TextView textView = (TextView) rootView.findViewById(R.id.textViewImageText);
		if (textView != null)
			textView.setText(result.getImageInformation().getName());
	}
}
