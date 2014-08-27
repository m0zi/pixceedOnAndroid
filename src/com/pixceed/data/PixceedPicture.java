package com.pixceed.data;

import java.util.List;

/**
 * A {@link PixceedPicture} is an image or picture from pixceed with several additional information.
 * 
 * @author Michael
 * 
 */
public class PixceedPicture
{
	public static final String FOLDER_ICON_TAG = "FolderIconBase64";
	public static final String IMAGE_INFORMATION_TAG = "Image";
	public static final String IMAGE_TAG = "ImageBase64";

	private String folderIcon;
	private ImageInformation imageInformation;
	private String image;

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
	 * @return the imageInformation
	 */
	public ImageInformation getImageInformation()
	{
		return imageInformation;
	}

	/**
	 * @param imageInformation
	 *            the imageInformation to set
	 */
	public void setImageInformation(ImageInformation imageInformation)
	{
		this.imageInformation = imageInformation;
	}

	/**
	 * @return the image
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	public static class ImageInformation
	{
		public static final String VIEW_MODE_TAG = "ViewMode";
		public static final String ADDED_ON_TAG = "Added";
		public static final String COMMENTS_TAG = "Comments";
		public static final String DESCRIPTION_TAG = "Description";
		public static final String EXPOSURE_TIME_TAG = "EXIFExposureTime";
		public static final String F_NUMBER_TAG = "EXIFFNumber";
		public static final String FOCAL_LENGTH_TAG = "EXIFFocalLength";
		public static final String ISO_TAG = "EXIFISO";
		public static final String LENS_TAG = "EXIFLens";
		public static final String MAKE_TAG = "EXIFMake";
		public static final String MODEL_TAG = "EXIFModel";
		public static final String FILE_SIZE_TAG = "FileSize";
		public static final String FILENAME_TAG = "Filename";
		public static final String FOLDER_ID_TAG = "FolderId";
		public static final String FOLDER_NAME_TAG = "FolderName";
		public static final String GPS_LATITUDE_TAG = "GPSLatitude";
		public static final String GPS_LONGITUDE_TAG = "GPSLongitude";
		public static final String GROUPS_TAG = "Groups";
		public static final String ID_TAG = "Id";
		public static final String IMAGE_COUNT_TAG = "ImageCount";
		public static final String IMAGE_WIDTH_TAG = "ImageWidth";
		public static final String IMAGE_HEIGHT_TAG = "ImageHeight";
		public static final String IMAGE_INDEX_TAG = "ImageIndex";
		public static final String LICENCE_TAG = "Licence";
		public static final String NAME_TAG = "Name";
		public static final String NEXT_IMAGE_ID_TAG = "NextImageId";
		public static final String OWNER_TAG = "Owner";
		public static final String PREVIOUS_IMAGE_ID_TAG = "PreviousImageId";
		public static final String PUBLIC_TAG = "Public";
		public static final String SHARED_TO_TAG = "SharedTo";
		public static final String SOURCE_NAME_TAG = "SourceName";
		public static final String SUGGESTED_PUBLIC_TAGS_TAG = "SuggestedPublicTags";
		public static final String SUGGESTED_TAGS_TAG = "SuggestedTags";
		public static final String TAGS_TAG = "Tags";
		public static final String TAKEN_ON_TAG = "Taken";
		public static final String VIEW_COUNT_TAG = "ViewCount";

		private int viewMode;
		private String addedOn;
		private List<Comment> comments;
		private String description;
		private String sEXIFExposureTime;
		private String sEXIFFNumber;
		private String sEXIFFocalLength;
		private String sEXIFISO;
		private String sEXIFLens;
		private String sEXIFMake;
		private String sEXIFModel;
		private float fileSize;
		private String fileName;
		private int folderId;
		private String folderName;
		private String sGPSLatitude;
		private String sGPSLongitude;
		private List<Group> groups;
		private int id;
		private int imageWidth;
		private int imageHeight;
		private int imageIndex;
		private String licence;
		private String name;
		private int nextImageId;
		private int previousImageId;
		private boolean public_;
		private Owner owner;
		private List<SharedTo> sharedTos;
		private String sourceName;

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
		 * @return the comments
		 */
		public List<Comment> getComments()
		{
			return comments;
		}

		/**
		 * @param comments
		 *            the comments to set
		 */
		public void setComments(List<Comment> comments)
		{
			this.comments = comments;
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
		 * @return the sEXIFExposureTime
		 */
		public String getsEXIFExposureTime()
		{
			return sEXIFExposureTime;
		}

		/**
		 * @param sEXIFExposureTime
		 *            the sEXIFExposureTime to set
		 */
		public void setsEXIFExposureTime(String sEXIFExposureTime)
		{
			this.sEXIFExposureTime = sEXIFExposureTime;
		}

		/**
		 * @return the sEXIFFNumber
		 */
		public String getsEXIFFNumber()
		{
			return sEXIFFNumber;
		}

		/**
		 * @param sEXIFFNumber
		 *            the sEXIFFNumber to set
		 */
		public void setsEXIFFNumber(String sEXIFFNumber)
		{
			this.sEXIFFNumber = sEXIFFNumber;
		}

		/**
		 * @return the sEXIFFocalLength
		 */
		public String getsEXIFFocalLength()
		{
			return sEXIFFocalLength;
		}

		/**
		 * @param sEXIFFocalLength
		 *            the sEXIFFocalLength to set
		 */
		public void setsEXIFFocalLength(String sEXIFFocalLength)
		{
			this.sEXIFFocalLength = sEXIFFocalLength;
		}

		/**
		 * @return the sEXIFISO
		 */
		public String getsEXIFISO()
		{
			return sEXIFISO;
		}

		/**
		 * @param sEXIFISO
		 *            the sEXIFISO to set
		 */
		public void setsEXIFISO(String sEXIFISO)
		{
			this.sEXIFISO = sEXIFISO;
		}

		/**
		 * @return the sEXIFLens
		 */
		public String getsEXIFLens()
		{
			return sEXIFLens;
		}

		/**
		 * @param sEXIFLens
		 *            the sEXIFLens to set
		 */
		public void setsEXIFLens(String sEXIFLens)
		{
			this.sEXIFLens = sEXIFLens;
		}

		/**
		 * @return the sEXIFMake
		 */
		public String getsEXIFMake()
		{
			return sEXIFMake;
		}

		/**
		 * @param sEXIFMake
		 *            the sEXIFMake to set
		 */
		public void setsEXIFMake(String sEXIFMake)
		{
			this.sEXIFMake = sEXIFMake;
		}

		/**
		 * @return the sEXIFModel
		 */
		public String getsEXIFModel()
		{
			return sEXIFModel;
		}

		/**
		 * @param sEXIFModel
		 *            the sEXIFModel to set
		 */
		public void setsEXIFModel(String sEXIFModel)
		{
			this.sEXIFModel = sEXIFModel;
		}

		/**
		 * @return the fileSize
		 */
		public float getFileSize()
		{
			return fileSize;
		}

		/**
		 * @param fileSize
		 *            the fileSize to set
		 */
		public void setFileSize(float fileSize)
		{
			this.fileSize = fileSize;
		}

		/**
		 * @return the fileName
		 */
		public String getFileName()
		{
			return fileName;
		}

		/**
		 * @param fileName
		 *            the fileName to set
		 */
		public void setFileName(String fileName)
		{
			this.fileName = fileName;
		}

		/**
		 * @return the folderId
		 */
		public int getFolderId()
		{
			return folderId;
		}

		/**
		 * @param folderId
		 *            the folderId to set
		 */
		public void setFolderId(int folderId)
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

		/**
		 * @return the sGPSLatitude
		 */
		public String getsGPSLatitude()
		{
			return sGPSLatitude;
		}

		/**
		 * @param sGPSLatitude
		 *            the sGPSLatitude to set
		 */
		public void setsGPSLatitude(String sGPSLatitude)
		{
			this.sGPSLatitude = sGPSLatitude;
		}

		/**
		 * @return the sGPSLongitude
		 */
		public String getsGPSLongitude()
		{
			return sGPSLongitude;
		}

		/**
		 * @param sGPSLongitude
		 *            the sGPSLongitude to set
		 */
		public void setsGPSLongitude(String sGPSLongitude)
		{
			this.sGPSLongitude = sGPSLongitude;
		}

		/**
		 * @return the groups
		 */
		public List<Group> getGroups()
		{
			return groups;
		}

		/**
		 * @param groups
		 *            the groups to set
		 */
		public void setGroups(List<Group> groups)
		{
			this.groups = groups;
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
		 * @return the imageWidth
		 */
		public int getImageWidth()
		{
			return imageWidth;
		}

		/**
		 * @param imageWidth
		 *            the imageWidth to set
		 */
		public void setImageWidth(int imageWidth)
		{
			this.imageWidth = imageWidth;
		}

		/**
		 * @return the imageHeight
		 */
		public int getImageHeight()
		{
			return imageHeight;
		}

		/**
		 * @param imageHeight
		 *            the imageHeight to set
		 */
		public void setImageHeight(int imageHeight)
		{
			this.imageHeight = imageHeight;
		}

		/**
		 * @return the imageIndex
		 */
		public int getImageIndex()
		{
			return imageIndex;
		}

		/**
		 * @param imageIndex
		 *            the imageIndex to set
		 */
		public void setImageIndex(int imageIndex)
		{
			this.imageIndex = imageIndex;
		}

		/**
		 * @return the licence
		 */
		public String getLicence()
		{
			return licence;
		}

		/**
		 * @param licence
		 *            the licence to set
		 */
		public void setLicence(String licence)
		{
			this.licence = licence;
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
		 * @return the nextImageId
		 */
		public int getNextImageId()
		{
			return nextImageId;
		}

		/**
		 * @param nextImageId
		 *            the nextImageId to set
		 */
		public void setNextImageId(int nextImageId)
		{
			this.nextImageId = nextImageId;
		}

		/**
		 * @return the previousImageId
		 */
		public int getPreviousImageId()
		{
			return previousImageId;
		}

		/**
		 * @param previousImageId
		 *            the previousImageId to set
		 */
		public void setPreviousImageId(int previousImageId)
		{
			this.previousImageId = previousImageId;
		}

		/**
		 * @return the public_
		 */
		public boolean isPublic_()
		{
			return public_;
		}

		/**
		 * @param public_
		 *            the public_ to set
		 */
		public void setPublic_(boolean public_)
		{
			this.public_ = public_;
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
		 * @return the sharedTos
		 */
		public List<SharedTo> getSharedTos()
		{
			return sharedTos;
		}

		/**
		 * @param sharedTos
		 *            the sharedTos to set
		 */
		public void setSharedTos(List<SharedTo> sharedTos)
		{
			this.sharedTos = sharedTos;
		}

		/**
		 * @return the sourceName
		 */
		public String getSourceName()
		{
			return sourceName;
		}

		/**
		 * @param sourceName
		 *            the sourceName to set
		 */
		public void setSourceName(String sourceName)
		{
			this.sourceName = sourceName;
		}

		/**
		 * @return the tags
		 */
		public List<Tag> getTags()
		{
			return tags;
		}

		/**
		 * @param tags
		 *            the tags to set
		 */
		public void setTags(List<Tag> tags)
		{
			this.tags = tags;
		}

		/**
		 * @return the suggestedTags
		 */
		public List<Tag> getSuggestedTags()
		{
			return suggestedTags;
		}

		/**
		 * @param suggestedTags
		 *            the suggestedTags to set
		 */
		public void setSuggestedTags(List<Tag> suggestedTags)
		{
			this.suggestedTags = suggestedTags;
		}

		/**
		 * @return the suggestedPublicTags
		 */
		public List<Tag> getSuggestedPublicTags()
		{
			return suggestedPublicTags;
		}

		/**
		 * @param suggestedPublicTags
		 *            the suggestedPublicTags to set
		 */
		public void setSuggestedPublicTags(List<Tag> suggestedPublicTags)
		{
			this.suggestedPublicTags = suggestedPublicTags;
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

		/**
		 * @return the viewCount
		 */
		public int getViewCount()
		{
			return viewCount;
		}

		/**
		 * @param viewCount
		 *            the viewCount to set
		 */
		public void setViewCount(int viewCount)
		{
			this.viewCount = viewCount;
		}

		private List<Tag> tags;
		private List<Tag> suggestedTags;
		private List<Tag> suggestedPublicTags;
		private String takenOn;
		private int viewCount;

		public static class Comment
		{
			// TODO Inhalt füllen
		}

		public static class Group
		{
			public static final String GROUP_ICON_TAG = "IconBase64";
			public static final String FIRST_NAME_TAG = "FirstName";
			public static final String LAST_NAME_TAG = "LastName";
			public static final String NAME_TAG = "Name";
			public static final String ID_TAG = "Id";
			public static final String OWNER_TAG = "Owner";
			public static final String PUBLIC_TAG = "Public";

			private String groupIcon;
			private String firstName;
			private String lastName;
			private String name;
			private int id;
			private String owner;
			private boolean public_;

			/**
			 * @return the groupIcon
			 */
			public String getGroupIcon()
			{
				return groupIcon;
			}

			/**
			 * @param groupIcon
			 *            the groupIcon to set
			 */
			public void setGroupIcon(String groupIcon)
			{
				this.groupIcon = groupIcon;
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
			 * @return the owner
			 */
			public String getOwner()
			{
				return owner;
			}

			/**
			 * @param owner
			 *            the owner to set
			 */
			public void setOwner(String owner)
			{
				this.owner = owner;
			}

			/**
			 * @return the public_
			 */
			public boolean isPublic_()
			{
				return public_;
			}

			/**
			 * @param public_
			 *            the public_ to set
			 */
			public void setPublic_(boolean public_)
			{
				this.public_ = public_;
			}
		}

		public static class Owner
		{
			public static final String OWNER_ICON_TAG = "IconBase64";
			public static final String EMAIL_TAG = "Email";
			public static final String FIRST_NAME_TAG = "FirstName";
			public static final String LAST_NAME_TAG = "LastName";
			public static final String ID_TAG = "Id";

			private String ownerIcon;
			private String email;
			private String firstName;
			private String lastName;
			private int id;

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
		}

		public static class SharedTo
		{
			public static final String SHARED_TO_ICON_TAG = "IconBase64";
			public static final String FIRST_NAME_TAG = "FirstName";
			public static final String LAST_NAME_TAG = "LastName";
			public static final String NAME_TAG = "Name";
			public static final String ID_TAG = "Id";
			public static final String OWNER_TAG = "Owner";
			public static final String PUBLIC_TAG = "Public";

			private String sharedToIcon;
			private String firstName;
			private String lastName;
			private String name;
			private int id;
			private String owner;
			private boolean public_;

			/**
			 * @return the sharedToIcon
			 */
			public String getSharedToIcon()
			{
				return sharedToIcon;
			}

			/**
			 * @param sharedToIcon
			 *            the sharedToIcon to set
			 */
			public void setSharedToIcon(String sharedToIcon)
			{
				this.sharedToIcon = sharedToIcon;
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
			 * @return the owner
			 */
			public String getOwner()
			{
				return owner;
			}

			/**
			 * @param owner
			 *            the owner to set
			 */
			public void setOwner(String owner)
			{
				this.owner = owner;
			}

			/**
			 * @return the public_
			 */
			public boolean isPublic_()
			{
				return public_;
			}

			/**
			 * @param public_
			 *            the public_ to set
			 */
			public void setPublic_(boolean public_)
			{
				this.public_ = public_;
			}
		}

		public static class Tag
		{
			public static final String ID_TAG = "Id";
			public static final String NAME_TAG = "Name";
			public static final String PUBLIC_TAG = "Public";
			public static final String USER_ID_TAG = "UserId";
			public static final String WIDTH_TAG = "Width";
			public static final String HEIGHT_TAG = "Height";
			public static final String X_TAG = "X";
			public static final String Y_TAG = "Y";

			private int id;
			private String name;
			private boolean public_;
			private String userId;
			private int width;
			private int height;
			private int x;
			private int y;

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
			 * @return the public_
			 */
			public boolean isPublic_()
			{
				return public_;
			}

			/**
			 * @param public_
			 *            the public_ to set
			 */
			public void setPublic_(boolean public_)
			{
				this.public_ = public_;
			}

			/**
			 * @return the userId
			 */
			public String getUserId()
			{
				return userId;
			}

			/**
			 * @param userId
			 *            the userId to set
			 */
			public void setUserId(String userId)
			{
				this.userId = userId;
			}

			/**
			 * @return the width
			 */
			public int getWidth()
			{
				return width;
			}

			/**
			 * @param width
			 *            the width to set
			 */
			public void setWidth(int width)
			{
				this.width = width;
			}

			/**
			 * @return the height
			 */
			public int getHeight()
			{
				return height;
			}

			/**
			 * @param height
			 *            the height to set
			 */
			public void setHeight(int height)
			{
				this.height = height;
			}

			/**
			 * @return the x
			 */
			public int getX()
			{
				return x;
			}

			/**
			 * @param x
			 *            the x to set
			 */
			public void setX(int x)
			{
				this.x = x;
			}

			/**
			 * @return the y
			 */
			public int getY()
			{
				return y;
			}

			/**
			 * @param y
			 *            the y to set
			 */
			public void setY(int y)
			{
				this.y = y;
			}
		}
	}
}