package by.htp.onlinestore.entity;

public class Section extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8760156294505728346L;
	
	private int id;
	private String sectionTitle;

	private Section() {
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getSectionTitle() {
		return sectionTitle;
	}



	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}



	public static Builder newBuilder(){
		return new Section().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
		}
		
		public Builder setId(int id) {
			Section.this.id = id;
			return this;
		}

		public Builder setSectionTitle(String sectionTitle) {
			Section.this.sectionTitle = sectionTitle;
			return this;
		}
		
		public Section build() {
			return Section.this;
		}
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((sectionTitle == null) ? 0 : sectionTitle.hashCode());
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
		Section other = (Section) obj;
		if (id != other.id)
			return false;
		if (sectionTitle == null) {
			if (other.sectionTitle != null)
				return false;
		} else if (!sectionTitle.equals(other.sectionTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section {" + "id=" + id + ", sectionTitle=" + sectionTitle
				+ '}';
	}
	

}
