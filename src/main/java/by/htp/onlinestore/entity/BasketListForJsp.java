package by.htp.onlinestore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Class describes BasketList only for forms
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class BasketListForJsp implements Serializable {

	/**
	* An unique serial version identifier
	*/
	private static final long serialVersionUID = 726070217588791265L; // `Baskets`.`id`, `Images`.`imageUrl`,"
			                                                     //" `SpecificationGoods`.`name`, `quantity`, `sum`, `dateOrder`, 
	                                                             //`status`, `fk_buyers`, `fk_goods`
	private int id;	 
	private String imageUrl; 
	private String name;
	private int quantity;
	private BigDecimal sum;
	private Date dateOrders;
	private String statusOrders;
	private int buyerId;
	private int goodId;
	/**
	 * constructor without parameter
	 */
	private BasketListForJsp() {

	}
	/**
	 * getters and setters
	 * @return fields
	 */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getSum() {
		return sum;
	}
	
	public Date getDateOrders() {
		return dateOrders;
	}

	public String getStatusOrders() {
		return statusOrders;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public int getGoodId() {
		return goodId;
	}
	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new BasketListForJsp().new Builder();
	}
	/**
	 * Inner class for build BasketListForJsp class object
	 * @author irina
	 *
	 */
	public class Builder {
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
			BasketListForJsp.this.id = id;
			return this;
		}

		public Builder setName(String name) {
			BasketListForJsp.this.name = name;
			return this;
		}

		public Builder setImageUrl(String imageUrl) {
			BasketListForJsp.this.imageUrl = imageUrl;
			return this;
		}


		public Builder setQuantity(int quantity) {
			BasketListForJsp.this.quantity = quantity;
			return this;
		}


		public Builder setSum(BigDecimal sum) {
			BasketListForJsp.this.sum = sum;
			return this;
		}

		public Builder setDateOrders(Date dateOrders) {
			BasketListForJsp.this.dateOrders = dateOrders;
			return this;
		}

		public Builder setStatusOrders(String statusOrders) {
			BasketListForJsp.this.statusOrders = statusOrders;
			return this;
		}

		public Builder setBuyerId(int buyerId) {
			BasketListForJsp.this.buyerId = buyerId;
			return this;
		}

		public Builder setGoodId(int goodId) {
			BasketListForJsp.this.goodId = goodId;
			return this;
		}
		/**
		 * @return ready BasketListForJsp class object
		 */
		public BasketListForJsp build() {
			return BasketListForJsp.this;
		}
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buyerId;
		result = prime * result + ((dateOrders == null) ? 0 : dateOrders.hashCode());
		result = prime * result + goodId;
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((statusOrders == null) ? 0 : statusOrders.hashCode());
		result = prime * result + ((sum == null) ? 0 : sum.hashCode());
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
		BasketListForJsp other = (BasketListForJsp) obj;
		if (buyerId != other.buyerId)
			return false;
		if (dateOrders == null) {
			if (other.dateOrders != null)
				return false;
		} else if (!dateOrders.equals(other.dateOrders))
			return false;
		if (goodId != other.goodId)
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
			return false;
		if (statusOrders == null) {
			if (other.statusOrders != null)
				return false;
		} else if (!statusOrders.equals(other.statusOrders))
			return false;
		if (sum == null) {
			if (other.sum != null)
				return false;
		} else if (!sum.equals(other.sum))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoodListForJsp {" + "id=" + id + "name=" + name +"imageUrl=" 
	+ imageUrl +", quantity=" + quantity + "sum=" + sum +", dateOrders=" + dateOrders + ", "
				+ "statusOrdersd=" + statusOrders + ", buyerId=" + buyerId +", goodId=" + goodId +'}';
	}
}
