package by.htp.onlinestore.entity;

public class Measure extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3795298266196521970L;

	private int id;
	private String size;
	
	private Measure() {
	
	}

	public int getId() {
		return id;
	}

	public String getSize() {
		return size;
	}


	
	public static Builder newBuilder() {
		return new Measure().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
		}
		
		public Builder setId(int id) {
			Measure.this.id = id;
			return this;
		}

		public Builder setSize(String size) {
			Measure.this.size = size;
			return this;
		}
		
		public Measure biuld() {
			return Measure.this;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
	
	@Override
	public String toString() {
		return "Measure {" + "id=" + id + ", measure=" + size + 
				'}';
	}

}
