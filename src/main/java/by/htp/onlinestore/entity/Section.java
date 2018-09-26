package by.htp.onlinestore.entity;

/**
 * Class describes Section entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Section extends EntityBase {

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = 8760156294505728346L;
	
	private int id;
	private String sectionTitle;

	/**
	 * constructor without parameter
	 */
	private Section() {
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



	public String getSectionTitle() {
		return sectionTitle;
	}



	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */

	public static Builder newBuilder(){
		return new Section().new Builder();
	}
	/**
	 * Inner class for build Section class object
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
			Section.this.id = id;
			return this;
		}

		public Builder setSectionTitle(String sectionTitle) {
			Section.this.sectionTitle = sectionTitle;
			return this;
		}
		/**
		 * @return ready Section class object
		 */
		public Section build() {
			return Section.this;
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
		result = prime * result + ((sectionTitle == null) ? 0 : sectionTitle.hashCode());
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
		Section other = (Section) obj;
		if (id != other.id)
			return false;
		if (sectionTitle == null) {
			if (other.sectionTitle != null)
				return false;
		} else if (!sectionTitle.equals(other.sectionTitle))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Section {" + "id=" + id + ", sectionTitle=" + sectionTitle
				+ '}';
	}
	

}
