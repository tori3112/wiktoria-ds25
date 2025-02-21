package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;

public class BookingManager implements BookingInterface {

	private Room[] rooms;

	public BookingManager() throws RemoteException {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		//implement this method
		for (Room room : rooms) {
			if (Objects.equals(room.getRoomNumber(), roomNumber)) {
				List<BookingDetail> bookings = room.getBookings();
				for (BookingDetail detail : bookings) {
					if (detail.getDate().isEqual(date)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void addBooking(BookingDetail bookingDetail) throws RemoteException {
		//implement this method
		if (!isRoomAvailable(bookingDetail.getRoomNumber(), bookingDetail.getDate())) {
			throw new RemoteException("Room not available");
		}

		for (Room room : rooms) {
			List<BookingDetail> after_bookings = room.getBookings();
			if (Objects.equals(bookingDetail.getRoomNumber(), room.getRoomNumber())) {
				after_bookings.add(bookingDetail);
				room.setBookings(after_bookings);
				break;
			}
		}

	}

	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//implement this method
		Set<Integer> availableRooms = new HashSet<Integer>();
		for (Room room : rooms) {
			System.out.print("\nis room available?\t"+room.getRoomNumber()+"\t"+date);
			if (isRoomAvailable(room.getRoomNumber(), date)) {
				System.out.print(" -> true");
				availableRooms.add(room.getRoomNumber());
			} else {
				System.out.print(" -> false");
			}
		}
		return availableRooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
