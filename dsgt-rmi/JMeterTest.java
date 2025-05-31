import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;

import hotel.BookingInterface;
import hotel.BookingDetail;

public class JMeterTest implements JavaSamplerClient {

    private BookingInterface bookingManager;

    @Override
    public void setupTest(JavaSamplerContext context) {
        try {
            String host = context.getParameter("host", "172.191.97.184");
            int port = context.getIntParameter("port", 1099);

            System.out.println("JMeterTest: Connecting to " + host + ":" + port);

            // Connect to RMI registry
            Registry registry = LocateRegistry.getRegistry(host, port);

            System.out.println("JMeterTest: Connected to registry, listing services...");

            // Just get the list of services first to verify connection
            String[] services = registry.list();
            System.out.println("JMeterTest: Available services: ");
            for (String service : services) {
                System.out.println("JMeterTest:  - " + service);
            }

            System.out.println("JMeterTest: Looking up BookingManager...");

            // Now try to look up the BookingManager
            bookingManager = (BookingInterface) registry.lookup("BookingManager");
            System.out.println("JMeterTest: Successfully connected to BookingManager");

        } catch (Exception e) {
            System.err.println("JMeterTest: Setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.setSampleLabel("RMI Test");

        try {
            result.sampleStart();

            if (bookingManager == null) {
                throw new Exception("BookingManager not initialized");
            }

            String operation = context.getParameter("operation", "getAllRooms");
            StringBuilder responseData = new StringBuilder();

            switch (operation) {
                case "getAllRooms":
                    Set<Integer> allRooms = bookingManager.getAllRooms();
                    responseData.append("All rooms: ").append(allRooms);
                    break;

                case "getAvailableRooms":
                    String dateStr = context.getParameter("date", LocalDate.now().toString());
                    LocalDate date = LocalDate.parse(dateStr);
                    Set<Integer> availableRooms = bookingManager.getAvailableRooms(date);
                    responseData.append("Available rooms for ").append(dateStr).append(": ").append(availableRooms);
                    break;

                case "isRoomAvailable":
                    int roomNumber = context.getIntParameter("roomNumber", 101);
                    dateStr = context.getParameter("date", LocalDate.now().toString());
                    date = LocalDate.parse(dateStr);
                    boolean available = bookingManager.isRoomAvailable(roomNumber, date);
                    responseData.append("Room ").append(roomNumber).append(" available on ").append(dateStr).append(": ").append(available);
                    break;

                case "addBooking":
                    roomNumber = context.getIntParameter("roomNumber", 101);
                    dateStr = context.getParameter("date", LocalDate.now().toString());
                    date = LocalDate.parse(dateStr);
                    String guestName = context.getParameter("guestName", "Test Guest");

                    BookingDetail booking = new BookingDetail(guestName, roomNumber, date);
                    bookingManager.addBooking(booking);
                    responseData.append("Booking added for ").append(guestName).append(" in room ").append(roomNumber).append(" on ").append(dateStr);
                    break;

                default:
                    responseData.append("Unknown operation: ").append(operation);
            }

            result.setResponseData(responseData.toString(), "UTF-8");
            result.setSuccessful(true);

        } catch (Exception e) {
            result.setSuccessful(false);
            result.setResponseMessage("Exception: " + e.getMessage());
            result.setResponseData(e.toString(), "UTF-8");
        } finally {
            result.sampleEnd();
        }

        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        // Nothing to clean up
        bookingManager = null;
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument("host", "172.191.97.184");
        args.addArgument("port", "1099");
        args.addArgument("operation", "getAllRooms");
        args.addArgument("roomNumber", "101");
        args.addArgument("date", LocalDate.now().toString());
        args.addArgument("guestName", "Test Guest");
        return args;
    }
}