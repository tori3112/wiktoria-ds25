package hotel;

import hotel.BookingManager;

import java.awt.print.Book;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class BookingServer {
    public static void main(String[] args) {
        try {

            BookingManager manager = new BookingManager();
            BookingInterface stub = (BookingInterface) UnicastRemoteObject.exportObject(manager, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("BookingManager", stub);

            System.out.println("Server binding complete!");
            System.out.println("Bound names: " + Arrays.toString(registry.list()));

        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}