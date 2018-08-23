package by.htp.onlinestore.entity;

public class Role extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3054924966756985112L;

	private int id;
	private String roleName;
	
	

	private Role() {
		
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return roleName;
	}
	
	
	
	public static Builder newBuilder() {
		return new Role().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
		}
		
		public Builder setId(int id) {
			Role.this.id = id;
			return this;
		}

		public Builder setRole(String roleName) {
			Role.this.roleName = roleName;
			return this;
		}
		
		public Role build() {
			return Role.this;
		}
	}

	@Override
	public String toString() {
		return "Role {" + "id=" + id + ", roleName=" + roleName + '}';
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
