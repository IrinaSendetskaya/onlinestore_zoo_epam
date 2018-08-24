package by.htp.onlinestore.entity;

public class Image extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3583899817792581954L;

	private int id;
	private String imageUrl;
	
	private Image() {

	}

	public int getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	
	
	public static Builder newBuilder() {
		return new Image().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
		}
		
		public Builder setId(int id) {
			Image.this.id = id;
			return this;
		}

		public Builder setImageUrl(String imageUrl) {
			Image.this.imageUrl = imageUrl;
			return this;
		}
		
		public Image build() {
			return Image.this;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		return result;
	}

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

	@Override
	public String toString() {
		return "Image {" + "id=" + id + ", linkImage=" + imageUrl
				+'}';
	}
}
