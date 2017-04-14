package com.theatremgmt.model;

import java.util.List;
/**
 * 
 * @author Shyju
 * Theatre Object
 *
 */
public class Theater {
	
	private List<Row> rows;

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
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
		Theater other = (Theater) obj;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}
	
	/**
	 * to String method for Theater object
	 */
	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		this.getRows().forEach(line ->{
			sb.append(line);
		});
		return sb.toString();
	}

}
