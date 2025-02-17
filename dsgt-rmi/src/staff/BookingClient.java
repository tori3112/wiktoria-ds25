package staff;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingInterface;
import hotel.BookingManager;

public class BookingClient extends AbstractScriptedSimpleTest {

	private BookingInterface bm = null;

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			// Get registry and lookup remote object
			Registry registry = LocateRegistry.getRegistry();
			bm = (BookingInterface) registry.lookup("BookingManager");
			System.out.println("Connected to BookingManager");
		} catch (Exception e) {
			System.err.println("Client initialization failed: " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//Implement this method
		return true;
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		//Implement this method
		bm.addBooking(bookingDetail);
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//Implement this method
		return bm.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return bm.getAllRooms();
	}

	public static void main(String[] args) throws Exception {
		String host = (args.length < 1) ? null : args[0];
		BookingClient client = new BookingClient();
		try {
			System.out.println("Looking up BookingManager...");
			Registry registry = LocateRegistry.getRegistry(host);
			System.out.println("Available services: " + Arrays.toString(registry.list()));
			BookingInterface stub = (BookingInterface) registry.lookup("BookingManager");
			client.run();
		} catch (Exception e) {
			System.err.println("-> Client exception: " + e.toString());
			e.printStackTrace();
		}
		//BookingClient client = new BookingClient();
		//client.run();
	}

}