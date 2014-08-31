package com.pixceed.util;

import java.util.Collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
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
	public static String token = "9QOAE_hGRZ3ikN82cXdl4PcRRqGjcbYoXPZQtRlWoXsxrXmhq2ubS5OCwjpReIUDAQMUrYzabpuo74IpKHnFsT1yqCbrGdfSOVUmL1BBCpy2IfuROluKFZKkY0lB7uFBWsFws8XT_shIZfM1ducghPUw2VePkoui2KpOWJYeBftmGG48rVzTQUN1KvqdG3ach7lix1Ja9Uag60FJjxhKUFcyc6ciMCumLZ60RYPCA9oCdEek2gbzERH5_eYOwbidnOKDDf08wHkFWcNyi8KLGIieJRhbeZ7e8bYlkxFuPfKt8CEtTSdSJvg2ji5IeT6LhZKiODtXqqf99PUeDflD0FeFC5ayTGKS82FMvFwffN7R5phsJWlxmZ0pwi-ss1uquNISqDH3UAqodI1JRkKD4pnLCp6xCEGA6ZaQLOsJL8v5rL22z8G23-vs5UZGIO_2bUUqO7TgxIy0YciGffPSCQ";
	public static String loginName;

	private static LruCache<Integer, Bitmap> pictureCache;
	private static LruCache<Long, PixceedPicture> pixceedCache;
	private static LruCache<Integer, Album> albumCache;
	private static LruCache<Long, Group> groupCache;
	private static Collection<LibraryMonth> userLibraryCache;
	private static Collection<GroupDescription> groupDescriptionsCache;

	static Bitmap icLauncher;

	static Context appContext;

	private Memory()
	{}

	public static void init(Context context)
	{
		appContext = context;
		icLauncher = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		initCaches();
	}

	public static void initCaches()
	{
		// Get max available VM memory, exceeding this amount will throw an
		// OutOfMemory exception. Stored in kilobytes as LruCache takes an
		// int in its constructor.
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		// Use 1/8th of the available memory for this memory cache.
		final int cacheSize = maxMemory / 8;

		// clear cache if already initialized
		if (pixceedCache != null) pixceedCache.evictAll();
		else pixceedCache = new LruCache<Long, PixceedPicture>(200);
		if (albumCache != null) albumCache.evictAll();
		else albumCache = new LruCache<Integer, Album>(10);
		if (groupCache != null) groupCache.evictAll();
		else groupCache = new LruCache<Long, Group>(10);
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
			pictureCache.put(key.hashCode(), bitmap);
		}
	}

	private static Bitmap getBitmapFromMemCache(String key)
	{
		return pictureCache.get(key.hashCode());
	}

	public static void loadBitmap(String data, ImageView imageView)
	{
		if (data == null)
			imageView.setImageResource(R.drawable.ic_launcher);
		final Bitmap bitmap = getBitmapFromMemCache(data);
		if (bitmap != null) imageView.setImageBitmap(bitmap);
		else
			if (BitmapWorkerTask.cancelPotentialWork(data, imageView))
			{
				final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
				final AsyncDrawable asyncDrawable = new AsyncDrawable(task);
				imageView.setImageDrawable(asyncDrawable);
				task.execute(data);
			}
	}

	public static void addPixceedPictureToMemoryCache(PixceedPicture picture)
	{
		pixceedCache.put(picture.getImageInformation().getId(), picture);
	}

	public static PixceedPicture getPixceedPictureFromMemoryCache(long id)
	{
		return pixceedCache.get(id);
	}

	public static void addAlbumToMemoryCache(Album album)
	{
		albumCache.put(album.getId(), album);
	}

	public static Album getAlbumFromMemoryCache(int id)
	{
		return albumCache.get(id);
	}

	public static void addLibraryToMemoryCache(Collection<LibraryMonth> library)
	{
		userLibraryCache = library;
	}

	public static Collection<LibraryMonth> getUserLibraryFromMemoryCache()
	{
		return userLibraryCache;
	}

	public static void addGroupDescriptionsToMemoryCache(Collection<GroupDescription> groupDescriptions)
	{
		groupDescriptionsCache = groupDescriptions;
	}

	public static Collection<GroupDescription> getGroupDescriptionsFromMemoryCache()
	{
		return groupDescriptionsCache;
	}

	public static void addGroupToMemoryCache(Group group)
	{
		groupCache.put(group.getGroupId(), group);
	}

	public static Group getGroupFromMemoryCache(long groupId)
	{
		return groupCache.get(groupId);
	}
}
