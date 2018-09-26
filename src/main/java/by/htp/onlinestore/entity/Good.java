package by.htp.onlinestore.entity;

import java.math.BigDecimal;

/**
 * Class describes Good entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Good extends EntityBase {

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = 348329358459026590L;

	private int id;
	private BigDecimal price;
	private int specificationGoodId;
	private int measureId;

	/**
	 * constructor without parameter
	 */
	public Good() {
		
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




	public BigDecimal getPrice() {
		return price;
	}




	public void setPrice(BigDecimal price) {
		this.price = price;
	}




	public int getSpecificationGoodId() {
		return specificationGoodId;
	}




	public void setSpecificationGoodId(int specificationGoodId) {
		this.specificationGoodId = specificationGoodId;
	}




	public int getMeasureId() {
		return measureId;
	}




	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}

	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new Good().new Builder();
	}

	/**
	 * Inner class for build Good class object
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
			Good.this.id = id;
			return this;
		}

		public Builder setPrice(BigDecimal price) {
			Good.this.price = price;
			return this;
		}

		public Builder setSpecificationGoodId(int specificationGoodId) {
			Good.this.specificationGoodId = specificationGoodId;
			return this;
		}

		public Builder setMeasureId(int measureId) {
			Good.this.measureId = measureId;
			return this;
		}
		/**
		 * @return ready Good class object
		 */
		public Good build() {
			return Good.this;
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
		result = prime * result + measureId;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + specificationGoodId;
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
		Good other = (Good) obj;
		if (id != other.id)
			return false;
		if (measureId != other.measureId)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (specificationGoodId != other.specificationGoodId)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Good {" + "id=" + id + ", price=" + price + 
				", specificationGoodId=" + specificationGoodId + ", "
				+ "measureId=" + measureId + '}';
	}
}
