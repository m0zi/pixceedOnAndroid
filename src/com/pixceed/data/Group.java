package com.pixceed.data;

import java.util.Collection;

public class Group
{

	public static final String GROUP_ICON_TAG = "IconBase64";
	public static final String FIRST_NAME_TAG = "FirstName";
	public static final String LAST_NAME_TAG = "LastName";
	public static final String GROUP_ID_TAG = "Id";
	public static final String NAME_TAG = "Name";
	public static final String OWNER_TAG = "Owner";
	public static final String PUBLIC_TAG = "Public";
	public static final String DESCRIPTION_TAG = "Description";
	public static final String FOLDERS_TAG = "Folders";
	public static final String GROUP_MEMBER_TYPE_TAG = "GroupMemberType";
	public static final String HAS_ICON_TAG = "HasIcon";
	public static final String IMAGES_TAG = "Images";
	public static final String INVITED_NEW_USER_TAG = "InvitedNewUser";
	public static final String INVITED_USER_TAG = "InvitedUser";
	public static final String USER_TAG = "User";

	private String groupIcon;
	private String firstName;
	private String lastName;
	private long groupId;
	private String name;
	private Owner owner;
	private boolean _public;
	private String description;
	private Collection<GroupFolder> folders;
	private int groupMemberType;
	private boolean hasIcon;
	private Collection<GroupImage> images;
	private String invitedNewUser;
	private String invitedUser;
	private String user;

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
	 * @return the groupId
	 */
	public long getGroupId()
	{
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId)
	{
		this.groupId = groupId;
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

	public boolean isPublic()
	{
		return _public;
	}

	public void setPublic(boolean _public)
	{
		this._public = _public;
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
	 * @return the folders
	 */
	public Collection<GroupFolder> getFolders()
	{
		return folders;
	}

	/**
	 * @param folders
	 *            the folders to set
	 */
	public void setFolders(Collection<GroupFolder> folders)
	{
		this.folders = folders;
	}

	/**
	 * @return the groupMemberType
	 */
	public int getGroupMemberType()
	{
		return groupMemberType;
	}

	/**
	 * @param groupMemberType
	 *            the groupMemberType to set
	 */
	public void setGroupMemberType(int groupMemberType)
	{
		this.groupMemberType = groupMemberType;
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
	 * @return the images
	 */
	public Collection<GroupImage> getImages()
	{
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(Collection<GroupImage> images)
	{
		this.images = images;
	}

	/**
	 * @return the invitedNewUser
	 */
	public String getInvitedNewUser()
	{
		return invitedNewUser;
	}

	/**
	 * @param invitedNewUser
	 *            the invitedNewUser to set
	 */
	public void setInvitedNewUser(String invitedNewUser)
	{
		this.invitedNewUser = invitedNewUser;
	}

	/**
	 * @return the invitedUser
	 */
	public String getInvitedUser()
	{
		return invitedUser;
	}

	/**
	 * @param invitedUser
	 *            the invitedUser to set
	 */
	public void setInvitedUser(String invitedUser)
	{
		this.invitedUser = invitedUser;
	}

	/**
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	public static class Owner
	{
		// TODO Inhalt füllen
	}

	public static class GroupFolder
	{
		public static final String FOLDER_ICON_TAG = "IconBase64";
		public static final String DATE_TAG = "Date";
		public static final String ID_TAG = "Id";
		public static final String NAME_TAG = "Name";

		private String folderIcon;
		private String date;
		private int id;
		private String name;

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

	public static class GroupImage
	{
		public static final String GROUP_IMAGE_ICON_TAG = "IconBase64";
		public static final String ADDED_ON_TAG = "Added";
		public static final String COMMENT_COUNT_TAG = "CommentCount";
		public static final String ID_TAG = "Id";
		public static final String NAME_TAG = "Name";
		public static final String SHARE_TYPE_TAG = "ShareType";
		public static final String TAKEN_ON_TAG = "Taken";

		private String groupImageIcon;
		private String addedOn;
		private int commentCount;
		private int id;
		private String name;
		private ShareType shareType;
		private String takenOn;

		/**
		 * @return the groupImageIcon
		 */
		public String getGroupImageIcon()
		{
			return groupImageIcon;
		}

		/**
		 * @param groupImageIcon
		 *            the groupImageIcon to set
		 */
		public void setGroupImageIcon(String groupImageIcon)
		{
			this.groupImageIcon = groupImageIcon;
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
		 * @return the shareType
		 */
		public ShareType getShareType()
		{
			return shareType;
		}

		/**
		 * @param shareType
		 *            the shareType to set
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
