package com.pixceed.util;

import java.util.Collection;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.pixceed.R;
import com.pixceed.data.Album;
import com.pixceed.data.Group;
import com.pixceed.data.GroupDescription;
import com.pixceed.data.LibraryMonth;
import com.pixceed.data.PixceedPicture;
import com.pixceed.util.BitmapWorkerTask.AsyncDrawable;

public class Memory
{
	public static final String PIXCEED_TAG = "com.pixceed";

	private static final String TOKEN_KEY = "token";
	private static final String LOGIN_NAME_KEY = "loginName";
	private static final String IS_REMEMBER_EMAIL_CHECKED_KEY = "rememberEmail";

	public static String token = null;
	public static String loginName;
	public static boolean isRememberEmailChecked = false;

	static Bitmap icLauncher;

	private static LruCache<Integer, Bitmap> pictureCache;
	private static LruCache<Long, PixceedPicture> pixceedCache;
	private static LruCache<Integer, Album> albumCache;
	private static LruCache<Long, Group> groupCache;
	private static Collection<LibraryMonth> userLibraryCache;
	private static Collection<GroupDescription> groupDescriptionsCache;

	private Memory()
	{}

	public static SharedPreferences.Editor save(SharedPreferences.Editor nvmDataStore)
	{
		nvmDataStore.putBoolean(IS_REMEMBER_EMAIL_CHECKED_KEY, isRememberEmailChecked);
		nvmDataStore.putString(LOGIN_NAME_KEY, loginName);
		nvmDataStore.putString(TOKEN_KEY, token);
		return nvmDataStore;
	}

	public static void init(Context context, SharedPreferences nvmData)
	{
		icLauncher = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		isRememberEmailChecked = nvmData.getBoolean(IS_REMEMBER_EMAIL_CHECKED_KEY, false);
		loginName = nvmData.getString(LOGIN_NAME_KEY, "");
		token = nvmData.getString(TOKEN_KEY, null);
		initCaches();
	}

	public static void initCaches()
	{
		// clear cache if already initialized
		userLibraryCache = null;
		groupDescriptionsCache = null;
		initPixceedCache();
		initAlbumCache();
		initGroupCache();
		initPictureCache();
	}

	private static void initPixceedCache()
	{
		if (pixceedCache != null) pixceedCache.evictAll();
		else pixceedCache = new LruCache<Long, PixceedPicture>(50);
	}

	private static void initAlbumCache()
	{
		if (albumCache != null) albumCache.evictAll();
		else albumCache = new LruCache<Integer, Album>(10);
	}

	private static void initGroupCache()
	{
		if (groupCache != null) groupCache.evictAll();
		else groupCache = new LruCache<Long, Group>(10);
	}

	private static void initPictureCache()
	{
		// Get max available VM memory, exceeding this amount will throw an
		// OutOfMemory exception. Stored in kilobytes as LruCache takes an
		// int in its constructor.
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		// Use 1/8th of the available memory for this memory cache.
		final int cacheSize = maxMemory / 8;

		if (pictureCache != null) pictureCache.evictAll();
		else pictureCache = new LruCache<Integer, Bitmap>(cacheSize)
		{
			@Override
			protected int sizeOf(Integer key, Bitmap bitmap)
			{
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
			}
		};
	}

	public static void addBitmapToMemoryCache(String key, Bitmap bitmap)
	{
		if (getBitmapFromMemCache(key) == null)
		{
			// no need initialize cache here because method in if-statement has already done it
			pictureCache.put(key.hashCode(), bitmap);
		}
	}

	private static Bitmap getBitmapFromMemCache(String key)
	{
		if (key == null) return null;
		if (pictureCache == null) initPictureCache();
		return pictureCache.get(key.hashCode());
	}

	public static void loadAndSetBitmap(String data, ImageView imageView, String defaultImageData)
	{
		if (imageView == null)
		{
			Log.e("BITMAP_MEMORY", "imageView is null.", new NullPointerException());
			return;
		}
		if (data == null || data.isEmpty())
		{
			imageView.setImageResource(R.drawable.ic_launcher);
			return;
		}
		// try to retrieve image from cache
		final Bitmap bitmap = getBitmapFromMemCache(data);
		if (bitmap != null)
		{
			imageView.setImageBitmap(bitmap);
		}
		else
			// image not in cache
			if (BitmapWorkerTask.cancelPotentialWork(data, imageView))
			{
				final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
				Bitmap defaultBitmap = getBitmapFromMemCache(defaultImageData);
				final Resources resources = imageView.getContext().getResources();
				final AsyncDrawable asyncDrawable;
				//@formatter:off
				if (defaultBitmap == null) asyncDrawable = new AsyncDrawable(resources, task);
				else 					   asyncDrawable = new AsyncDrawable(resources, task, defaultBitmap);
				//@formatter:on
				imageView.setImageDrawable(asyncDrawable);
				task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data);
			}
	}

	public static void loadAndSetBitmap(String data, ImageView imageView)
	{
		loadAndSetBitmap(data, imageView, null);
	}

	public static void addPixceedPictureToMemoryCache(PixceedPicture picture)
	{
		if (pixceedCache == null) initPixceedCache();
		pixceedCache.put(picture.getImageInformation().getId(), picture);
	}

	public static PixceedPicture getPixceedPictureFromMemoryCache(long id)
	{
		if (pixceedCache == null) initPixceedCache();
		return pixceedCache.get(id);
	}

	public static void addAlbumToMemoryCache(Album album)
	{
		if (albumCache == null) initAlbumCache();
		albumCache.put(album.getId(), album);
	}

	public static Album getAlbumFromMemoryCache(int id)
	{
		if (albumCache == null) initAlbumCache();
		return albumCache.get(id);
	}

	public static void setLibraryToMemoryCache(Collection<LibraryMonth> library)
	{
		userLibraryCache = library;
	}

	public static Collection<LibraryMonth> getUserLibraryFromMemoryCache()
	{
		return userLibraryCache;
	}

	public static void setGroupDescriptionsToMemoryCache(Collection<GroupDescription> groupDescriptions)
	{
		groupDescriptionsCache = groupDescriptions;
	}

	public static Collection<GroupDescription> getGroupDescriptionsFromMemoryCache()
	{
		return groupDescriptionsCache;
	}

	public static void addGroupToMemoryCache(Group group)
	{
		if (groupCache == null) initGroupCache();
		groupCache.put(group.getGroupId(), group);
	}

	public static Group getGroupFromMemoryCache(long groupId)
	{
		if (groupCache == null) initGroupCache();
		return groupCache.get(groupId);
	}
}
