package com.pixceed.data;

import java.util.Collection;

/**
 * A library is a previewed list of albums.
 * 
 * @author Michael Sommer
 * 
 */
public class LibraryMonth
{
	public static final String MONTH_TAG = "Key";
	public static final String ALBUM_INFORMATIONS_TAG = "Value";

	private String month;
	private Collection<AlbumPreviewInformation> albumInformations;

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the albumInformations
	 */
	public Collection<AlbumPreviewInformation> getAlbumInformations() {
		return albumInformations;
	}

	/**
	 * @param albumInformations
	 *            the albumInformations to set
	 */
	public void setAlbumInformations(Collection<AlbumPreviewInformation> albumInformations) {
		this.albumInformations = albumInformations;
	}

	public static class AlbumPreviewInformation
	{
		public static final String ALBUM_ICON_TAG = "IconBase64";
		public static final String ALBUM_DATE_TAG = "Date";
		public static final String ALBUM_ID_TAG = "Id";
		public static final String ALBUM_NAME_TAG = "Name";

		private String albumIcon;
		private String date;
		private int albumId;
		private String name;

		/**
		 * @return the albumIcon
		 */
		public String getAlbumIcon() {
			return albumIcon;
		}

		/**
		 * @param albumIcon
		 *            the albumIcon to set
		 */
		public void setAlbumIcon(String albumIcon) {
			this.albumIcon = albumIcon;
		}

		/**
		 * @return the albumDate
		 */
		public String getAlbumDate() {
			return date;
		}

		/**
		 * @param albumDate
		 *            the albumDate to set
		 */
		public void setAlbumDate(String albumDate) {
			this.date = albumDate;
		}

		/**
		 * @return the albumId
		 */
		public int getAlbumId() {
			return albumId;
		}

		/**
		 * @param albumId
		 *            the albumId to set
		 */
		public void setAlbumId(int albumId) {
			this.albumId = albumId;
		}

		/**
		 * @return the albumName
		 */
		public String getAlbumName() {
			return name;
		}

		/**
		 * @param albumName
		 *            the albumName to set
		 */
		public void setAlbumName(String albumName) {
			this.name = albumName;
		}
	}
}
