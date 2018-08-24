package by.htp.onlinestore.entity;

public class Buyer extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1864618509160110298L;

	private int id;
	private String email;
	private String nickname;
	private String password;
	private String mobile;
	private String address;
	private int roleId;

	private Buyer() {

	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public int getRoleId() {
		return roleId;
	}


	
	public static Builder newBuilder() {
        return new Buyer().new Builder();
    }

    public class Builder {
    	
        private Builder() {
        }
        
        public Builder setId(int id) {
			Buyer.this.id = id;	
			return this;
		}
		
		public Builder setEmail(String email) {
			Buyer.this.email = email;
			return this;
		}

		public Builder setNickname(String nickname) {
			Buyer.this.nickname = nickname;
			return this;
		}

		public Builder setPassword(String password) {
			Buyer.this.password = password;
			return this;
		}

		public Builder setMobile(String mobile) {
			Buyer.this.mobile = mobile;
			return this;
		}

		public Builder setAddress(String address) {
			Buyer.this.address = address;
			return this;
		}

		public Builder setRoleId(int roleId) {
			Buyer.this.roleId = roleId;
			return this;
		}
		
		public Buyer build() {
			return Buyer.this;
		}
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + roleId;
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
		Buyer other = (Buyer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Buyer {" + "id=" + id + ", email=" + email + ", nickname=" + nickname + ", " + "password=" + password
				+ ", mobile=" + mobile + ", address=" + address + ", roleId=" + roleId + '}';
	}
	
	

	enum Discount {
		ONE(0), THREE(3), FIVE(5), SEVEN(7), TEN(10);

		int percent;

		private Discount(int percent) {
			this.percent = percent;
		}

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

	}
}
