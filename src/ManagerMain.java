import java.io.*;

public class ManagerMain {

    private final QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
    private final ParcelMap parcelMap = new ParcelMap();

    public ManagerMain() {
        read();
        new Worker(queueOfCustomers, parcelMap);
    }

    //Manager class - this is your driver class where you instantiate QueueofCustomers, ParcelMap and your GUI.
    //This is where you ideally read in your data files using suitable methods.- put methods of read files here ?
    //where do I put methods for write ? in the worker ?
    //write methods are only for parcel map not queue of customers

    /*

        Manager uses Worker, QueueOfCustomers, and ParcelMap.
	    Manager interacts with the Log Singleton for event tracking.
    */

    private void read() {
        readCustomersFromFile("Custs.csv");
        readParcelsFromFile("Parcels.csv");
    }

    private void readCustomersFromFile(String file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String inputLine = reader.readLine();

            int queueNumber = 1;

            while(inputLine != null && !inputLine.isEmpty()) {

                String[] data = inputLine.split(",");

                String[] nameParts = data[0].split(" ");

                String name = nameParts[0].trim();
                String surname = nameParts[1].trim();

                String parcelId = data[1].trim();

                Customer customer = new Customer(queueNumber,name,surname, parcelId);

                queueOfCustomers.addCustomer(customer);
                queueNumber++;

                inputLine = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {e.printStackTrace();}
    }

    private void readParcelsFromFile(String file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String inputLine = reader.readLine().trim();


            while (inputLine != null && !inputLine.isEmpty()) {

                String[] data = inputLine.split(",");

                String parcelId = data[0].trim();

                double weight = Double.parseDouble(data[1].trim());
                double length = Double.parseDouble(data[2].trim());
                double width = Double.parseDouble(data[3].trim());
                double height = Double.parseDouble(data[4].trim());
                int daysInDepot = Integer.parseInt(data[5].trim());

                Customer customer = queueOfCustomers.searchForCustomerByParcelId(parcelId);

                if (customer == null) {
                    System.out.println("No customer found with parcel ID: " + parcelId);
                    return;
                }

                Parcel parcel = new Parcel(parcelId, ParcelStatus.FOR_COLLECTION, weight, length, width, height, daysInDepot, customer);

                parcelMap.addParcel(parcel);

                inputLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}

    }



    public static void main(String[] args) {
        new ManagerMain();
    }


    //save file happens every time the program is closed or button "save" is clicked in the gui -> th same method will be triggered
    //output of iteration -> console + text file + gui

}

    /*
    //OLD TEST DATA for Main class for Console tests before GUI

    //read & write data from files
    queueOfCustomers.readFromFile(new File("Custs.csv"));
    parcelMap.readFromFile(new File("Parcels.csv"), queueOfCustomers);

    //place somewhere else
    Customer customer1 = new Customer(1,"Ivan","Ivanov","X345");
    Customer customer2 = new Customer(2,"Petr","Petrov","X567");
    Customer customer3 = new Customer(3,"Vasily","Vasilev","X238");

    Parcel parcel = new Parcel("X345", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer1);
    Parcel parcel2 = new Parcel("X567", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer2);
    Parcel parcel3 = new Parcel("X238", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer3);

    ParcelMap parcelMap = new ParcelMap();
    parcelMap.addParcel(parcel);
    parcelMap.addParcel(parcel2);
    parcelMap.addParcel(parcel3);

    QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
    queueOfCustomers.addCustomer(customer1);
    queueOfCustomers.addCustomer(customer2);
    queueOfCustomers.addCustomer(customer3);

    System.out.println(queueOfCustomers.printAllCustomers());
    System.out.println(parcelMap.printAllParcels());
    System.out.println(queueOfCustomers.searchByName("Ivan"));
    System.out.println(parcelMap.searchParcelByParcelId("X064"));

    System.out.println(parcelMap.getParcels());

    while (!queueOfCustomers.getCustomers().isEmpty()) {
    System.out.println("Processing next customer in queue...");
    worker.processCustomer();
    System.out.println(queueOfCustomers.getCustomers());
    }

    worker.generateReport("Report.txt");

    */