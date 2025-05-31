package hotel;

import hotel.BookingManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class BookingServer {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "172.191.97.184");

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