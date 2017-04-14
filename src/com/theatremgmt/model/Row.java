package com.theatremgmt.model;

import java.util.List;

/**
 * 
 * @author Shyju
 * Class represents the seating row of the theatre
 *
 */
public class Row {
	private int rowId;
	private List<Section> section;

	/**
	 * @return the rowId
	 */
	public int getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * 
	 * @return the section
	 */
	public List<Section> getSection() {
		return section;
	}

	/**
	 * 
	 * @param section the section to set
	 */
	public void setSection(List<Section> section) {
		this.section = section;
	}
	

}
