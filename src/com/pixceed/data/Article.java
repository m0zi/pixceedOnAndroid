package com.pixceed.data;

import java.util.Date;

public class Article
{
	public static final String ADDED_ON_TAG = "Added";
	public static final String HTML_TAG = "Html";
	public static final String ID_TAG = "Id";
	public static final String NAME_TAG = "Name";

	private Date addedOn;
	private String html;
	private int id;
	private String name;

	/**
	 * @return the added
	 */
	public Date getAddedOn() {
		return addedOn;
	}

	/**
	 * @param added
	 *            the added to set
	 */
	public void setAddedOn(String addedOn) {
		this.addedOn = new Date();
	}

	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * @param html
	 *            the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
