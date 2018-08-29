package by.htp.onlinestore.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodListForJsp implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 726070217588791265L; // `Goods`.`id`, `name`, `Images`.`imageUrl`,
																		// `price`, `size`,`fk_measures`,
																		// `fk_specificationGoods`

	private int id;
	private String name; 
	private String imageUrl; 
	private BigDecimal price;
	private String size; 
	private int specificationGoodId;
	private int measureId;

	private GoodListForJsp() {

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public String getSize() {
		return size;
	}


	public int getSpecificationGoodId() {
		return specificationGoodId;
	}


	public int getMeasureId() {
		return measureId;
	}


	public static Builder newBuilder() {
		return new GoodListForJsp().new Builder();
	}

	public class Builder {

		private Builder() {

		}

		public Builder setId(int id) {
			GoodListForJsp.this.id = id;
			return this;
		}

		public Builder setName(String name) {
			GoodListForJsp.this.name = name;
			return this;
		}



		public Builder setImageUrl(String imageUrl) {
			GoodListForJsp.this.imageUrl = imageUrl;
			return this;
		}


		public Builder setPrice(BigDecimal price) {
			GoodListForJsp.this.price = price;
			return this;
		}


		public Builder setSize(String size) {
			GoodListForJsp.this.size = size;
			return this;
		}


		public Builder setSpecificationGoodId(int specificationGoodId) {
			GoodListForJsp.this.specificationGoodId = specificationGoodId;
			return this;
		}


		public Builder setMeasureId(int measureId) {
			GoodListForJsp.this.measureId = measureId;
			return this;
		}


		public GoodListForJsp build() {
			return GoodListForJsp.this;
		}

	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + measureId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		GoodListForJsp other = (GoodListForJsp) obj;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (measureId != other.measureId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (specificationGoodId != other.specificationGoodId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GoodListForJsp {" + "id=" + id + "name=" + name +"imageUrl=" + imageUrl +", price=" + price + "measure=" + size +", specificationGoodId=" + specificationGoodId + ", "
				+ "measureId=" + measureId + '}';
	}
}
