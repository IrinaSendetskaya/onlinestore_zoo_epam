package by.htp.onlinestore.entity;

public class SpecificationGood extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7176852371436669229L;
	
	private int id;
	private String name;
	private String description;
	private int sectionId;
	private int imageId;

	public SpecificationGood() {

	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getSectionId() {
		return sectionId;
	}



	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}



	public int getImageId() {
		return imageId;
	}



	public void setImageId(int imageId) {
		this.imageId = imageId;
	}



	public static Builder newBuilder() {
		return new SpecificationGood().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
		}
		
		public Builder setId(int id) {
			SpecificationGood.this.id = id;
			return this;
		}

		public Builder setName(String name) {
			SpecificationGood.this.name = name;
			return this;
		}

		public Builder setDescription(String description) {
			SpecificationGood.this.description = description;
			return this;
		}

		public Builder setSectionId(int sectionId) {
			SpecificationGood.this.sectionId = sectionId;
			return this;
		}

		public Builder setImageId(int imageId) {
			SpecificationGood.this.imageId = imageId;
			return this;
		}
		
		public SpecificationGood build() {
			return SpecificationGood.this;
		}

	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + imageId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sectionId;
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
		SpecificationGood other = (SpecificationGood) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (imageId != other.imageId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sectionId != other.sectionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpecificationGood {" + "id=" + id + ", name=" + name + ", description=" + description + ", "
				+ "sectionId=" + sectionId + ", imageId=" + imageId + '}';
	}

}
