package com.pixceed.data;

import java.util.Collection;

/**
 * An album contains several pictures which are collected under a certain user defined topic.
 * 
 * @author Michael
 */
public class Album
{
	public static final String VIEW_MODE_TAG = "ViewMode";
	public static final String DESCRIPTION_TAG = "Description";
	public static final String HAS_ICON_TAG = "HasIcon";
	public static final String ID_TAG = "Id";
	public static final String IMAGES_ORDERED_BY_DAY_TAG = "Images";
	public static final String ADMIN_TAG = "IsAdmin";
	public static final String NAME_TAG = "Name";
	public static final String ORDER_TYPE_TAG = "OrderType";
	public static final String OWNER_TAG = "Owner";
	public static final String TRACKS_TAG = "Tracks";

	private int viewMode;
	private String description;
	private boolean hasIcon;
	private int id;
	private Collection<ImageDay> imagesOrderedByDay;
	private boolean admin;
	private String name;
	private int orderType;
	private Owner owner;
	private Collection<Track> tracks;

	/**
	 * @return the viewMode
	 */
	public int getViewMode()
	{
		return viewMode;
	}

	/**
	 * @param viewMode
	 *            the viewMode to set
	 */
	public void setViewMode(int viewMode)
	{
		this.viewMode = viewMode;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the hasIcon
	 */
	public boolean isHasIcon()
	{
		return hasIcon;
	}

	/**
	 * @param hasIcon
	 *            the hasIcon to set
	 */
	public void setHasIcon(boolean hasIcon)
	{
		this.hasIcon = hasIcon;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the imagesOrderedbyMonth
	 */
	public Collection<ImageDay> getImagesOrderedByDay()
	{
		return imagesOrderedByDay;
	}

	/**
	 * @param imagesOrderedByDay
	 *            the imagesOrderedbyMonth to set
	 */
	public void setImagesOrderedByDay(Collection<ImageDay> imagesOrderedByDay)
	{
		this.imagesOrderedByDay = imagesOrderedByDay;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin)
	{
		this.admin = isAdmin;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the orderType
	 */
	public int getOrderType()
	{
		return orderType;
	}

	/**
	 * @param orderType
	 *            the orderType to set
	 */
	public void setOrderType(int orderType)
	{
		this.orderType = orderType;
	}

	/**
	 * @return the owner
	 */
	public Owner getOwner()
	{
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Owner owner)
	{
		this.owner = owner;
	}

	/**
	 * @return the tracks
	 */
	public Collection<Track> getTracks()
	{
		return tracks;
	}

	/**
	 * @param tracks
	 *            the tracks to set
	 */
	public void setTracks(Collection<Track> tracks)
	{
		this.tracks = tracks;
	}

	public static class ImageDay
	{
		public static final String MONTH_TAG = "Key";
		public static final String IMAGE_PREVIEW_INFORMATIONS_TAG = "Value";

		private String month;
		private Collection<ImagePreviewInformation> imagePreviewInformations;

		/**
		 * @return the month
		 */
		public String getMonth()
		{
			return month;
		}

		/**
		 * @param month
		 *            the month to set
		 */
		public void setMonth(String month)
		{
			this.month = month;
		}

		/**
		 * @return the images
		 */
		public Collection<ImagePreviewInformation> getImagePreviewInformations()
		{
			return imagePreviewInformations;
		}

		/**
		 * @param imagePreviewInformations
		 *            the images to set
		 */
		public void setImagePreviewInformations(Collection<ImagePreviewInformation> imagePreviewInformations)
		{
			this.imagePreviewInformations = imagePreviewInformations;
		}

		public static class ImagePreviewInformation
		{
			public static final String IMAGE_ICON_TAG = "IconBase64";
			public static final String ADDED_ON_TAG = "Added";
			public static final String COMMENT_COUNT_TAG = "CommentCount";
			public static final String ID_TAG = "Id";
			public static final String NAME_TAG = "Name";
			public static final String SHARE_TYPE_TAG = "ShareType";
			public static final String TAKEN_ON_TAG = "Taken";

			private String imageIcon;
			private String addedOn;
			private int commentCount;
			private int id;
			private String name;
			private ShareType shareType;
			private String takenOn;

			/**
			 * @return the imageIcon
			 */
			public String getImageIcon()
			{
				return imageIcon;
			}

			/**
			 * @param imageIcon
			 *            the imageIcon to set
			 */
			public void setImageIcon(String imageIcon)
			{
				this.imageIcon = imageIcon;
			}

			/**
			 * @return the addedOn
			 */
			public String getAddedOn()
			{
				return addedOn;
			}

			/**
			 * @param addedOn
			 *            the addedOn to set
			 */
			public void setAddedOn(String addedOn)
			{
				this.addedOn = addedOn;
			}

			/**
			 * @return the commentCount
			 */
			public int getCommentCount()
			{
				return commentCount;
			}

			/**
			 * @param commentCount
			 *            the commentCount to set
			 */
			public void setCommentCount(int commentCount)
			{
				this.commentCount = commentCount;
			}

			/**
			 * @return the id
			 */
			public int getId()
			{
				return id;
			}

			/**
			 * @param id
			 *            the id to set
			 */
			public void setId(int id)
			{
				this.id = id;
			}

			/**
			 * @return the name
			 */
			public String getName()
			{
				return name;
			}

			/**
			 * @param name
			 *            the name to set
			 */
			public void setName(String name)
			{
				this.name = name;
			}

			/**
			 * @return the sharedType
			 */
			public ShareType getShareType()
			{
				return shareType;
			}

			/**
			 * @param shareType
			 *            the sharedType to set
			 */
			public void setShareType(ShareType shareType)
			{
				this.shareType = shareType;
			}

			/**
			 * @return the takenOn
			 */
			public String getTakenOn()
			{
				return takenOn;
			}

			/**
			 * @param takenOn
			 *            the takenOn to set
			 */
			public void setTakenOn(String takenOn)
			{
				this.takenOn = takenOn;
			}
		}
	}

	public static class Owner
	{
		public static final String OWNER_ICON_TAG = "IconBase64";
		public static final String EMAIL_TAG = "Email";
		public static final String ID_TAG = "Id";
		public static final String FIRST_NAME_TAG = "FirstName";
		public static final String LAST_NAME_TAG = "LastName";

		private String ownerIcon;
		private String email;
		private int id;
		private String firstName;
		private String lastName;

		/**
		 * @return the ownerIcon
		 */
		public String getOwnerIcon()
		{
			return ownerIcon;
		}

		/**
		 * @param ownerIcon
		 *            the ownerIcon to set
		 */
		public void setOwnerIcon(String ownerIcon)
		{
			this.ownerIcon = ownerIcon;
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
		 * @return the id
		 */
		public int getId()
		{
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id)
		{
			this.id = id;
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

	public static class Track
	{
		public static final String ID_TAG = "Id";
		public static final String NAME_TAG = "Name";

		private int id;
		private String name;

		/**
		 * @return the id
		 */
		public int getId()
		{
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id)
		{
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name)
		{
			this.name = name;
		}
	}
}
