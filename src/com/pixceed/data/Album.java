package com.pixceed.data;

import java.util.Collection;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;

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
	public static final String IMAGES_MONTH_TAG = "Images";

	private int viewMode;
	private String description;
	private boolean hasIcon;
	private int id;
	private Collection<ImageMonth> imagesOrderedbyMonth;

	/**
	 * @return the viewMode
	 */
	public int getViewMode() {
		return viewMode;
	}

	/**
	 * @param viewMode
	 *            the viewMode to set
	 */
	public void setViewMode(int viewMode) {
		this.viewMode = viewMode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the hasIcon
	 */
	public boolean isHasIcon() {
		return hasIcon;
	}

	/**
	 * @param hasIcon
	 *            the hasIcon to set
	 */
	public void setHasIcon(boolean hasIcon) {
		this.hasIcon = hasIcon;
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
	 * @return the imagesOrderedbyMonth
	 */
	public Collection<ImageMonth> getImagesOrderedbyMonth() {
		return imagesOrderedbyMonth;
	}

	/**
	 * @param imagesOrderedbyMonth
	 *            the imagesOrderedbyMonth to set
	 */
	public void setImagesOrderedbyMonth(Collection<ImageMonth> imagesOrderedbyMonth) {
		this.imagesOrderedbyMonth = imagesOrderedbyMonth;
	}

	public class ImageMonth
	{
		public static final String MONTH_TAG = "Key";
		public static final String IMAGE_INFORMATION_TAG = "Value";

		private String month;
		private Collection<ImagePreviewInformation> images;

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
		 * @return the images
		 */
		public Collection<ImagePreviewInformation> getImages() {
			return images;
		}

		/**
		 * @param images
		 *            the images to set
		 */
		public void setImages(Collection<ImagePreviewInformation> images) {
			this.images = images;
		}

		public class ImagePreviewInformation
		{
			public static final String IMAGE_PREVIEW_TAG = "IconBase64";
			public static final String ADDED_ON_TAG = "Added";
			public static final String COMMENT_COUNT_TAG = "CommentCount";
			public static final String ID_TAG = "Id";
			public static final String NAME_TAG = "Name";
			public static final String SHARED_TYPE_TAG = "SharedType";
			public static final String TAKEN_ON_TAG = "Taken";

			private Bitmap imageIcon;
			private GregorianCalendar addedOn;
			private int commentCount;
			private int id;
			private String name;
			private SharedType sharedType;
			private GregorianCalendar takenOn;

			/**
			 * @return the imageIcon
			 */
			public Bitmap getImageIcon() {
				return imageIcon;
			}

			/**
			 * @param imageIcon
			 *            the imageIcon to set
			 */
			public void setImageIcon(Bitmap imageIcon) {
				this.imageIcon = imageIcon;
			}

			/**
			 * @return the addedOn
			 */
			public GregorianCalendar getAddedOn() {
				return addedOn;
			}

			/**
			 * @param addedOn
			 *            the addedOn to set
			 */
			public void setAddedOn(GregorianCalendar addedOn) {
				this.addedOn = addedOn;
			}

			/**
			 * @return the commentCount
			 */
			public int getCommentCount() {
				return commentCount;
			}

			/**
			 * @param commentCount
			 *            the commentCount to set
			 */
			public void setCommentCount(int commentCount) {
				this.commentCount = commentCount;
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

			/**
			 * @return the sharedType
			 */
			public SharedType getSharedType() {
				return sharedType;
			}

			/**
			 * @param sharedType
			 *            the sharedType to set
			 */
			public void setSharedType(SharedType sharedType) {
				this.sharedType = sharedType;
			}

			/**
			 * @return the takenOn
			 */
			public GregorianCalendar getTakenOn() {
				return takenOn;
			}

			/**
			 * @param takenOn
			 *            the takenOn to set
			 */
			public void setTakenOn(GregorianCalendar takenOn) {
				this.takenOn = takenOn;
			}
		}
	}
}
