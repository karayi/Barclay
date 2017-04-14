package com.theatremgmt.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.theatremgmt.model.Booking;
import com.theatremgmt.model.Row;
import com.theatremgmt.model.Section;
import com.theatremgmt.model.Theater;

/**
 * 
 * @author Shyju Karayi
 * This is a mail class for Theatre Seating allocation
 * 
 *
 */
public class TheatreManager {
	
	public static void main(String[] args) {
		
		//Constructing the theater structure
		Theater theatre = buildTheater();
		int totalCapacity = calculateTotalCapacity(theatre);
		int availableCapacity = totalCapacity;
		
		//Reading the ticket request
		List<Booking> bookingList = readBooking();
		String exceedLimitMsg = " Sorry, we can't handle your party.";
		String callToSplit = " Call to split party.";
		Boolean bookingDone = Boolean.FALSE;
		for(Booking booking : bookingList){
			
			if(booking.getTicketCount() <= availableCapacity){
				for(int i=0; i<theatre.getRows().size();i++){
					if(bookingDone){
						bookingDone = Boolean.FALSE;
						break;
					}
						
					for(int j=0;j< theatre.getRows().get(i).getSection().size();j++){
						if(bookingDone){
							break;
						}
						if(theatre.getRows().get(i).getSection().get(j).getIsAvailable()){
							//Check whether the first booking can accommodate in first row
							if(j==0 && booking.getTicketCount() 
									<= getAvailableSeats(theatre.getRows().get(i).getSection().get(j))){
								assignSeat(theatre.getRows().get(i).getSection().get(j), 
										booking.getTicketCount());
								System.out.println(booking.getName()+" Row "+(i+1)+" Section "+(j+1));
								availableCapacity = availableCapacity - booking.getTicketCount();
								bookingDone = Boolean.TRUE;
							}
							if(booking.getTicketCount() 
									== getAvailableSeats(theatre.getRows().get(i).getSection().get(j))){
								assignSeat(theatre.getRows().get(i).getSection().get(j), 
										booking.getTicketCount());
								System.out.println(booking.getName()+" Row "+(i+1)+" Section "+(j+1));
								availableCapacity = availableCapacity - booking.getTicketCount();
								bookingDone = Boolean.TRUE;
							}
							//Making seat availability of a section false, if there is no seat available 
							if(getAvailableSeats(theatre.getRows().get(i).getSection().get(j)) == 0){
								theatre.getRows().get(i).getSection().get(j).setIsAvailable(Boolean.FALSE);
							}
						}
						
					}
					//Cannot find the section for the party
					if((i == theatre.getRows().size()-1) && bookingDone.equals(Boolean.FALSE)){
						System.out.println(booking.getName()+ callToSplit);
					}
				}
				
				
			}else{
				System.out.println(booking.getName()+ exceedLimitMsg);
			}
			
		};
		
		
	}
	
	/**
	 * Method used to build theater 
	 * read the theater configuration file from D:/Theatre.txt
	 * user can modify the theater seating at time by editing 
	 * the Theatre.txt file.
	 */
	private static Theater buildTheater(){
		
		Theater theater = new Theater();
		List<Row> rowList = new ArrayList<Row>(1);
		try(BufferedReader reader = new BufferedReader(new FileReader("D:/Theatre.txt"))){
		String line = "";
		int rowId = 1;
		while((line = reader.readLine())!= null){
			Row row = new Row();
			row.setRowId(rowId);
			//Creating a list of section based on the configuration file
			List<Section> sectionList = new ArrayList<Section>(line.split("\\s").length);
			//Find the number of section in the file
			int sectionCount[] = Stream.of(line.split("\\s")).mapToInt(Integer::parseInt).toArray();
			//Creating the sections in row
			for(int count : sectionCount){
				Section section = new Section();
				int seats[] = new int[count];
				section.setSeats(seats);
				sectionList.add(section);
			}
			
			//Add section to the row
			row.setSection(sectionList);
			rowList.add(row);
			rowId++;
		}
		theater.setRows(rowList);
		
		
		}catch(IOException ex){
			System.out.println("Exception occurred while processing the file" + ex);
		}
		
		
		return theater;
	}

	/**
	 * Method used to calculate the total capacity of the theater
	 * @param theater
	 * @return capacity
	 */
	private static int calculateTotalCapacity(Theater theater){
		int capacity = 0;
		for(Row row : theater.getRows()){
			for(Section section : row.getSection()){
				capacity = capacity + section.getSeats().length;
			}
		}
		return capacity;
	}
	
	/**
	 * Method read the booking list from D:/TicketRequest.txt
	 * @return bookingList
	 */
	private static List<Booking> readBooking(){
		
		List<Booking> bookingList = new ArrayList<Booking>(1);
		try(BufferedReader br = new BufferedReader(new FileReader("D:/TicketRequest.txt"))){
			String line = "";
			while((line = br.readLine()) != null){	
				Booking booking = new Booking();
				String splitLine[] = line.split("\\s");
				booking.setName(splitLine[0]);
				booking.setTicketCount(Integer.valueOf(splitLine[1]));
				bookingList.add(booking);
			}
			
		}catch(IOException ex){
			System.out.println("Exception occurred while processing the ticket request file" + ex);
		}
		
		return bookingList;
	}
	
	/**
	 * Methos used to check the available seats in a section
	 * @param section
	 * @return
	 */
	private static int getAvailableSeats(Section section){
		int availableSeats = 0 ;
		for(int i=0;i<section.getSeats().length;i++){
			int seats[] = section.getSeats();
			if(seats[i] == 0){
				availableSeats++;
			}
		}
		return availableSeats;
	}

	/**
	 * Method used to assign seat
	 */
	private static void assignSeat(Section section, int seatCount) {

		for (int i = 0; i < section.getSeats().length; i++) {
			if (seatCount != 0) {
				if (section.getSeats()[i] == 0) {
					int seats[] = section.getSeats();
					seats[i] = 1;
					seatCount--;
				}
			}
			else{
				break;
			}
		}
	}
}
