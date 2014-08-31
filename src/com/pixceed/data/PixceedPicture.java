package com.pixceed.data;

import java.util.Collection;
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
		public static final String FILE_NAME_TAG = "Filename";
		public static final String FOLDER_ID_TAG = "FolderId";
		public static final String FOLDER_NAME_TAG = "Foldername";
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
		public static final String SHARED_TOS_TAG = "SharedTo";
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
		private String exposureTime;
		private String fNumber;
		private String focalLength;
		private String iso;
		private String lens;
		private String make;
		private String model;
		private float fileSize;
		private String fileName;
		private int folderId;
		private String folderName;
		private String gpsLatitude;
		private String gpsLongitude;
		private List<Group> groups;
		private long id;
		private int imageIndex;
		private int imageCount;
		private int imageWidth;
		private int imageHeight;
		private String licence;
		private String name;
		private int nextImageId;
		private int previousImageId;
		private boolean _public;
		private Owner owner;
		private Collection<SharedTo> sharedTos;
		private String sourceName;
		private Collection<Tag> tags;
		private Collection<Tag> suggestedPublicTags;
		private Collection<Tag> suggestedTags;
		private String takenOn;
		private int viewCount;

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
		 * @return the exposureTime
		 */
		public String getExposureTime()
		{
			return exposureTime;
		}

		/**
		 * @param exposureTime
		 *            the exposureTime to set
		 */
		public void setExposureTime(String exposureTime)
		{
			this.exposureTime = exposureTime;
		}

		/**
		 * @return the fNumber
		 */
		public String getfNumber()
		{
			return fNumber;
		}

		/**
		 * @param fNumber
		 *            the fNumber to set
		 */
		public void setfNumber(String fNumber)
		{
			this.fNumber = fNumber;
		}

		/**
		 * @return the focalLength
		 */
		public String getFocalLength()
		{
			return focalLength;
		}

		/**
		 * @param focalLength
		 *            the focalLength to set
		 */
		public void setFocalLength(String focalLength)
		{
			this.focalLength = focalLength;
		}

		/**
		 * @return the iso
		 */
		public String getIso()
		{
			return iso;
		}

		/**
		 * @param iso
		 *            the iso to set
		 */
		public void setIso(String iso)
		{
			this.iso = iso;
		}

		/**
		 * @return the lens
		 */
		public String getLens()
		{
			return lens;
		}

		/**
		 * @param lens
		 *            the lens to set
		 */
		public void setLens(String lens)
		{
			this.lens = lens;
		}

		/**
		 * @return the make
		 */
		public String getMake()
		{
			return make;
		}

		/**
		 * @param make
		 *            the make to set
		 */
		public void setMake(String make)
		{
			this.make = make;
		}

		/**
		 * @return the model
		 */
		public String getModel()
		{
			return model;
		}

		/**
		 * @param model
		 *            the model to set
		 */
		public void setModel(String model)
		{
			this.model = model;
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
		 * @return the gpsLatitude
		 */
		public String getGpsLatitude()
		{
			return gpsLatitude;
		}

		/**
		 * @param gpsLatitude
		 *            the gpsLatitude to set
		 */
		public void setGpsLatitude(String gpsLatitude)
		{
			this.gpsLatitude = gpsLatitude;
		}

		/**
		 * @return the gpsLongitude
		 */
		public String getGpsLongitude()
		{
			return gpsLongitude;
		}

		/**
		 * @param gpsLongitude
		 *            the gpsLongitude to set
		 */
		public void setGpsLongitude(String gpsLongitude)
		{
			this.gpsLongitude = gpsLongitude;
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
		 * @return the imageCount
		 */
		public int getImageCount()
		{
			return imageCount;
		}

		/**
		 * @param imageCount
		 *            the imageCount to set
		 */
		public void setImageCount(int imageCount)
		{
			this.imageCount = imageCount;
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
		 * @return the _public
		 */
		public boolean isPublic()
		{
			return _public;
		}

		/**
		 * @param _public
		 *            the _public to set
		 */
		public void setPublic(boolean _public)
		{
			this._public = _public;
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

		/**
		 * @return the sharedTos
		 */
		public Collection<SharedTo> getSharedTos()
		{
			return sharedTos;
		}

		/**
		 * @param sharedTos
		 *            the sharedTos to set
		 */
		public void setSharedTos(Collection<SharedTo> sharedTos)
		{
			this.sharedTos = sharedTos;
		}

		/**
		 * @return the tags
		 */
		public Collection<Tag> getTags()
		{
			return tags;
		}

		/**
		 * @param tags
		 *            the tags to set
		 */
		public void setTags(Collection<Tag> tags)
		{
			this.tags = tags;
		}

		/**
		 * @return the suggestedPublicTags
		 */
		public Collection<Tag> getSuggestedPublicTags()
		{
			return suggestedPublicTags;
		}

		/**
		 * @param suggestedPublicTags
		 *            the suggestedPublicTags to set
		 */
		public void setSuggestedPublicTags(Collection<Tag> suggestedPublicTags)
		{
			this.suggestedPublicTags = suggestedPublicTags;
		}

		/**
		 * @return the suggestedTags
		 */
		public Collection<Tag> getSuggestedTags()
		{
			return suggestedTags;
		}

		/**
		 * @param suggestedTags
		 *            the suggestedTags to set
		 */
		public void setSuggestedTags(Collection<Tag> suggestedTags)
		{
			this.suggestedTags = suggestedTags;
		}

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
			private boolean _public;

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
			 * @return the public
			 */
			public boolean isPublic()
			{
				return _public;
			}

			/**
			 * @param _public
			 *            the public to set
			 */
			public void setPublic(boolean _public)
			{
				this._public = _public;
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
			private boolean _public;

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
			 * @return the public
			 */
			public boolean isPublic()
			{
				return _public;
			}

			/**
			 * @param public the public to set
			 */
			public void setPublic(boolean _public)
			{
				this._public = _public;
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
			private boolean _public;
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
			 * @return the public
			 */
			public boolean isPublic()
			{
				return _public;
			}

			/**
			 * @param public
			 *            the public to set
			 */
			public void setPublic(boolean _public)
			{
				this._public = _public;
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