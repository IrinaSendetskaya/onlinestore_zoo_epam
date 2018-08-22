package by.htp.onlinestore.entity;

import java.math.BigDecimal;

public class Good extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 348329358459026590L;

	private int id;
	private BigDecimal price;
	private int specificationGoodId;
	private int measureId;

	public Good() {
		
	}

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
	
	@Override
	public String toString() {
		return "Good {" + "id=" + id + ", price=" + price + 
				", specificationGoodId=" + specificationGoodId + ", "
				+ "measureId=" + measureId + '}';
	}
}
