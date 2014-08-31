package com.pixceed.data;

public class GroupDescription
{
	public static final String GROUP_ICON_TAG = "IconBase64";
	public static final String ID_TAG = "Id";
	public static final String FIRST_NAME_TAG = "FirstName";
	public static final String LAST_NAME_TAG = "LastName";
	public static final String NAME_TAG = "Name";
	public static final String EMAIL_TAG = "Owner";
	public static final String PUBLIC_TAG = "Public";

	private String groupIcon;
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String name;
	private boolean _public;

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
	 * @param _public
	 *            the public to set
	 */
	public void setPublic(boolean _public)
	{
		this._public = _public;
	}

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