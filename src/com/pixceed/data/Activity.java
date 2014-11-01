package com.pixceed.data;

public class Activity
{

	public static final String ACTIVITY_TYPE_TAG = "ActivityType";
	public static final String FOLDER_TAG = "Folder";
	public static final String GROUP_TAG = "Group";
	public static final String ID_TAG = "Id";
	public static final String IMAGE_TAG = "Image";
	public static final String TIME_AGO_TAG = "TimeAgo";
	public static final String TRACK_TAG = "Track";
	public static final String UNREAD_TAG = "Unread";
	public static final String USER_TAG = "User";

	private ActivityType activityType;
	private Folder folder;
	private Group group;
	private long id;
	private Image image;
	private String timeAgo;
	private Track track;
	private boolean unread;
	private User user;

	/**
	 * @return the activityType
	 */
	public ActivityType getActivityType()
	{
		return activityType;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(ActivityType activityType)
	{
		this.activityType = activityType;
	}

	/**
	 * @return the folder
	 */
	public Folder getFolder()
	{
		return folder;
	}

	/**
	 * @param folder
	 *            the folder to set
	 */
	public void setFolder(Folder folder)
	{
		this.folder = folder;
	}

	/**
	 * @return the group
	 */
	public Group getGroup()
	{
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(Group group)
	{
		this.group = group;
	}

	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the image
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}

	/**
	 * @return the timeAgo
	 */
	public String getTimeAgo()
	{
		return timeAgo;
	}

	/**
	 * @param timeAgo
	 *            the timeAgo to set
	 */
	public void setTimeAgo(String timeAgo)
	{
		this.timeAgo = timeAgo;
	}

	/**
	 * @return the track
	 */
	public Track getTrack()
	{
		return track;
	}

	/**
	 * @param track
	 *            the track to set
	 */
	public void setTrack(Track track)
	{
		this.track = track;
	}

	/**
	 * @return the unread
	 */
	public boolean isUnread()
	{
		return unread;
	}

	/**
	 * @param unread
	 *            the unread to set
	 */
	public void setUnread(boolean unread)
	{
		this.unread = unread;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	public static class Folder
	{
		public static final String FOLDER_ICON_TAG = "IconBase64";
		public static final String DATE_TAG = "Date";
		public static final String FOLDER_ID_TAG = "Id";
		public static final String FOLDER_NAME_TAG = "Name";

		private String folderIcon;
		private String date;
		private long folderId;
		private String folderName;

		/**
		 * @return the folderIcon
		 */
		public String getFolderIcon()
		{
			return folderIcon;
		}

		/**
		 * @param folderIcon
		 *            the folderIcon to set
		 */
		public void setFolderIcon(String folderIcon)
		{
			this.folderIcon = folderIcon;
		}

		/**
		 * @return the date
		 */
		public String getDate()
		{
			return date;
		}

		/**
		 * @param date
		 *            the date to set
		 */
		public void setDate(String date)
		{
			this.date = date;
		}

		/**
		 * @return the folderId
		 */
		public long getFolderId()
		{
			return folderId;
		}

		/**
		 * @param folderId
		 *            the folderId to set
		 */
		public void setFolderId(long folderId)
		{
			this.folderId = folderId;
		}

		/**
		 * @return the folderName
		 */
		public String getFolderName()
		{
			return folderName;
		}

		/**
		 * @param folderName
		 *            the folderName to set
		 */
		public void setFolderName(String folderName)
		{
			this.folderName = folderName;
		}
	}

	public static class Group
	{
		// TODO add implementation
	}

	public static class Image
	{
		// TODO add implementation
	}

	public static class Track
	{
		public static final String TRACK_ID_TAG = "Id";
		public static final String TRACK_NAME_TAG = "Name";

		private long trackId;
		private String trackName;

		/**
		 * @return the trackId
		 */
		public long getTrackId()
		{
			return trackId;
		}

		/**
		 * @param trackId
		 *            the trackId to set
		 */
		public void setTrackId(long trackId)
		{
			this.trackId = trackId;
		}

		/**
		 * @return the trackName
		 */
		public String getTrackName()
		{
			return trackName;
		}

		/**
		 * @param trackName
		 *            the trackName to set
		 */
		public void setTrackName(String trackName)
		{
			this.trackName = trackName;
		}
	}

	public static class User
	{
		public static final String USER_ICON_TAG = "IconBase64";
		public static final String USER_ID_TAG = "Id";
		public static final String EMAIL_TAG = "Email";
		public static final String FIRST_NAME_TAG = "FirstName";
		public static final String LAST_NAME_TAG = "LastName";

		private String userIcon;
		private long userId;
		private String email;
		private String firstName;
		private String lastName;

		/**
		 * @return the userIcon
		 */
		public String getUserIcon()
		{
			return userIcon;
		}

		/**
		 * @param userIcon
		 *            the userIcon to set
		 */
		public void setUserIcon(String userIcon)
		{
			this.userIcon = userIcon;
		}

		/**
		 * @return the userId
		 */
		public long getUserId()
		{
			return userId;
		}

		/**
		 * @param userId
		 *            the userId to set
		 */
		public void setUserId(long userId)
		{
			this.userId = userId;
		}

		/**
		 * @return the email
		 */
		public String getEmail()
		{
			return email;
		}

		/**
		 * @param email
		 *            the email to set
		 */
		public void setEmail(String email)
		{
			this.email = email;
		}

		/**
		 * @return the firstName
		 */
		public String getFirstName()
		{
			return firstName;
		}

		/**
		 * @param firstName
		 *            the firstName to set
		 */
		public void setFirstName(String firstName)
		{
			this.firstName = firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName()
		{
			return lastName;
		}

		/**
		 * @param lastName
		 *            the lastName to set
		 */
		public void setLastName(String lastName)
		{
			this.lastName = lastName;
		}
	}
}
