package by.htp.onlinestore.entity;

/**
 * Class describes Measure entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Measure extends EntityBase{

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = -3795298266196521970L;

	private int id;
	private String size;
	/**
	 * constructor without parameter
	 */
	private Measure() {
	
	}
	/**
	 * getters and setters
	 * @return fields
	 */
	public int getId() {
		return id;
	}

	public String getSize() {
		return size;
	}


	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new Measure().new Builder();
	}
	/**
	 * Inner class for build Measure class object
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
			Measure.this.id = id;
			return this;
		}

		public Builder setSize(String size) {
			Measure.this.size = size;
			return this;
		}

		/**
		 * @return ready Measure class object
		 */
		public Measure biuld() {
			return Measure.this;
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
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		Measure other = (Measure) obj;
		if (id != other.id)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Measure {" + "id=" + id + ", measure=" + size + 
				'}';
	}

}
