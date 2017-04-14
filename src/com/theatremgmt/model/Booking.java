package com.theatremgmt.model;
/**
 * Class represents the ticket booking
 * @author Shyju
 *
 */
public class Booking {

	private String name;
	private int ticketCount;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ticketCount
	 */
	public int getTicketCount() {
		return ticketCount;
	}
	/**
	 * @param ticketCount the ticketCount to set
	 */
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	
}
