package by.htp.onlinestore.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Class describes Basket entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Basket extends EntityBase {

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = -8162664389157484912L;
	
	private int id;
	private int quantity;
	private BigDecimal sum;          
	private Date dateOrders;
	private String statusOrders;
	private int buyerId;
	private int goodId;

	/**
	 * constructor without parameter
	 */
	private Basket() {

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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getSum() {
		return sum;
	}


	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}


	public Date getDateOrders() {
		return dateOrders;
	}


	public void setDateOrders(Date dateOrders) {
		this.dateOrders = dateOrders;
	}


	public String getStatusOrders() {
		return statusOrders;
	}


	public void setStatusOrders(String statusOrders) {
		this.statusOrders = statusOrders;
	}


	public int getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}


	public int getGoodId() {
		return goodId;
	}


	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}


	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new Basket().new Builder();
	}
	
	/**
	 * Inner class for build Basket class object
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
			Basket.this.id = id;
			return this;
		}

		public Builder setQuantity(int quantity) {
			Basket.this.quantity = quantity;
			return this;
		}

		public Builder setSum(BigDecimal sum) {
			Basket.this.sum = sum;
			return this;
		}

		public Builder setDateOrders(Date dateOrders) {
			Basket.this.dateOrders = dateOrders;
			return this;
		}

		public Builder setStatusOrders(String statusOrders) {
			Basket.this.statusOrders = statusOrders;
			return this;
		}

		public Builder setBuyerId(int buyerId) {
			Basket.this.buyerId = buyerId;
			return this;
		}

		public Builder setGoodId(int goodId) {
			Basket.this.goodId = goodId;
			return this;
		}
		
		/**
		 * @return ready Basket class object
		 */
		public Basket build() {
			return Basket.this;
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
		Basket other = (Basket) obj;
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
		return "Basket {" + "id=" + id + ", quantity=" + quantity + ", summa=" + sum + ", "
				+ "date=" + dateOrders + ", statusOrders=" + statusOrders +  
				", buyerId=" + buyerId + ", goodId=" + goodId + '}';
	}
	
	
	/**
	 * inner ENUM 
	 *
	 */
	public enum StatusOrder {
		IN_PROGRESS("в корзине"), COMPLETE("выполнен");
		
		String status;

		private StatusOrder(String status) {
			this.status = status;
		}


		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
	}

}


