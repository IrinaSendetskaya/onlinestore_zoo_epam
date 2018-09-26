package by.htp.onlinestore.entity;

/**
 * Class describes Role entity
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class Role extends EntityBase {

	/**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = -3054924966756985112L;

	private int id;
	private String roleName;
	
	

	/**
	 * constructor without parameter
	 */
	private Role() {
		
	}

	/**
	 * getters and setters
	 * @return fields
	 */
	public int getId() {
		return id;
	}

	public String getRole() {
		return roleName;
	}
	
	
	/**
	 * Static method for create inner Builder class object
	 * @return inner Builder class object
	 */
	public static Builder newBuilder() {
		return new Role().new Builder();
	}
	/**
	 * Inner class for build Role class object
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
			Role.this.id = id;
			return this;
		}

		public Builder setRole(String roleName) {
			Role.this.roleName = roleName;
			return this;
		}
		/**
		 * @return ready Role class object
		 */
		public Role build() {
			return Role.this;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role {" + "id=" + id + ", roleName=" + roleName + '}';
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	
	
}
