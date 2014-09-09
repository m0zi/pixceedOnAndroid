package com.pixceed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pixceed.R;
import com.pixceed.util.Memory;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment
{
	public LoginFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
	{
		final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

		// show sample text
		// final Context context = rootView.getContext();
		final TextView textViewLoginName = (TextView) rootView.findViewById(R.id.editTextLoginName);
		final TextView textViewPassword = (TextView) rootView.findViewById(R.id.editTextPassword);
		final CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checkBoxSaveLoginName);

		textViewLoginName.setText(Memory.loginName);
		checkBox.setChecked(Memory.isRememberEmailChecked);
		if (Memory.loginName != null && !Memory.loginName.isEmpty())
			textViewPassword.requestFocus();

		// final OnPostExecuteInterface<Collection<Article>> showLoginText = new OnPostExecuteInterface<Collection<Article>>()
		// {
		// @Override
		// public void onPostExecute(Collection<Article> result)
		// {
		// if (result != null)
		// ((TextView) rootView.findViewById(R.id.textViewArticle)).setText(result.iterator().next().getHtml());
		// else
		// ((TextView) rootView.findViewById(R.id.textViewArticle)).setText("");
		// }
		// };
		// new ArticlesTask(context, showLoginText).execute();

		// show sample image
		// final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewRndPicture);
		// final OnPostExecuteInterface<Bitmap> showLoginPicture = new OnPostExecuteInterface<Bitmap>()
		// {
		// @Override
		// public void onPostExecute(Bitmap result)
		// {
		// imageView.setScaleType(ScaleType.CENTER_CROP);
		// imageView.setImageBitmap(result);
		// }
		// };
		// final OnPostExecuteInterface<int[]> getRandomPublicPicture = new OnPostExecuteInterface<int[]>()
		// {
		// @Override
		// public void onPostExecute(int[] result)
		// {
		// if (result == null || result.length < 1)
		// return;
		// new PublicPictureTask(context, showLoginPicture, imageView.getWidth(), imageView.getHeight()).execute("" + result[0]);
		// }
		// };
		// new PublicPictureListTask(context, getRandomPublicPicture).execute();

		return rootView;
	}

	@Override
	public void onPause()
	{
		super.onPause();
		((EditText) getActivity().findViewById(R.id.editTextLoginName)).setText(Memory.loginName);
		((EditText) getActivity().findViewById(R.id.editTextPassword)).setText(null);
	}
}
