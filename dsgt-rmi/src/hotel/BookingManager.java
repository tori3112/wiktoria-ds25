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
					if (detail.getDate() == date) {
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
			if (Objects.equals(bookingDetail.getRoomNumber(), room.getRoomNumber())) {
				room.getBookings().add(bookingDetail);
			}
		}

	}

	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//implement this method
		Set<Integer> availableRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			if (isRoomAvailable(room.getRoomNumber(), date)) {
				availableRooms.add(room.getRoomNumber());
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
