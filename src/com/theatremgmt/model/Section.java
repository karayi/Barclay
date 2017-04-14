package com.theatremgmt.model;

/**
 * 
 * @author Shyju
 * Class represents the section of a row
 *
 */
public class Section {

	
	private int seats[];
	Boolean isAvailable = Boolean.TRUE;

	/**
	 * @return the seats
	 */
	public int[] getSeats() {
		return seats;
	}

	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int[] seats) {
		this.seats = seats;
	}

	/**
	 * @return the isAvailable
	 */
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	
	
	
}
