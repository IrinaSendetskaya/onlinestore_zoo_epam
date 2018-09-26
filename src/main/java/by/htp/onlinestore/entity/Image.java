package by.htp.onlinestore.entity;

/**
 * Class describes Image entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Image extends EntityBase {

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = 3583899817792581954L;

	private int id;
	private String imageUrl;
	
	/**
	 * constructor without parameter
	 */
	public Image() {

	}

	/**
	 * getters and setters
	 * @return fields
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new Image().new Builder();
	}
	/**
	 * Inner class for build Image class object
	 * @author irina
	 *
	 */
	public class Builder{
		/**
		 * constructor without parameter
		 */
		private Builder() {
		}
		/**
		 * it sets fields
		 * @param fields
		 * @return Builder class object
		 */
		public Builder setId(int id) {
			Image.this.id = id;
			return this;
		}

		public Builder setImageUrl(String imageUrl) {
			Image.this.imageUrl = imageUrl;
			return this;
		}
		
		/**
		 *  @return ready Image class object
		 */
		public Image build() {
			return Image.this;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image {" + "id=" + id + ", linkImage=" + imageUrl
				+'}';
	}
}
