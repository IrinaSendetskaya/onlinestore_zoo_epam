package by.htp.onlinestore.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Basket extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8162664389157484912L;
	
	private int id;
	private int quantity;
	private BigDecimal sum;          
	private Date dateOrders;
	private String statusOrders;
	private int buyerId;
	private int goodId;



	public Basket() {
		super();

	}
	
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




	@Override
	public String toString() {
		return "Basket {" + "id=" + id + ", quantity=" + quantity + ", summa=" + sum + ", "
				+ "date=" + dateOrders + ", statusOrders=" + statusOrders +  
				", buyerId=" + buyerId + ", goodId=" + goodId + '}';
	}
	
	
	public enum StatusOrder {
		IN_PROGRESS("в обработке"), COMPLETE("выполнен");
		
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


