package Main;

import Controller.WorkerController;
import Log.Log;
import Model.*;
import View.WorkerGUI;
import java.io.*;

/*
    Manager class instantiates the QueueOfCustomers, ParcelMap and Worker classes.
    Manager class also has suitable methods to read data from files.
    Manager class  interacts with the Log Singleton for event tracking.
*/
public class ManagerMain {
    private final QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
    private final ParcelMap parcelMap = new ParcelMap();
    private final Log log = Log.getInstance();

    public ManagerMain() {
        read();

        Worker worker = new Worker(queueOfCustomers, parcelMap);

        WorkerGUI workerGui = new WorkerGUI(worker);

        new WorkerController(worker, workerGui);

        log.addEvent("Simulation has started.");
    }

    private void read() {
        readCustomersFromFile();
        readParcelsFromFile();
    }

    private void readCustomersFromFile() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Custs.csv"));
            String inputLine = reader.readLine();

            while(inputLine != null && !inputLine.isEmpty()) {

                String[] data = inputLine.split(",");

                String[] nameParts = data[0].split(" ");

                String name = nameParts[0].trim();
                String surname = nameParts[1].trim();

                String parcelId = data[1].trim();

                Customer customer = new Customer(name,surname, parcelId);

                queueOfCustomers.addCustomer(customer);

                inputLine = reader.readLine();
            }
            reader.close();
            log.addEvent("Customers loaded from file.");
        } catch(IOException e) {e.printStackTrace();}
    }

    private void readParcelsFromFile() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Parcels.csv"));
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

                if (customer != null) {

                    Parcel parcel = new Parcel(parcelId, ParcelStatus.FOR_COLLECTION, weight, length, width, height, daysInDepot, customer);

                    parcelMap.addParcel(parcel);
                }

                else {
                    System.out.println("No customer found with parcel Id: " + parcelId);
                    return;
                }
                inputLine = reader.readLine();
            }
            reader.close();
            log.addEvent("Parcels loaded from file.");
        } catch (IOException e) {e.printStackTrace();}

    }

    public static void main(String[] args) {
        new ManagerMain();

    }

}

    /*
    //OLD TEST DATA for Main class for Console Output tests before View.WorkerGUI

    //Read & write data from files
    queueOfCustomers.readFromFile(new File("Custs.csv"));
    parcelMap.readFromFile(new File("Parcels.csv"), queueOfCustomers);

    //Place somewhere else
    Model.Customer customer1 = new Model.Customer(1,"Ivan","Ivanov","X345");
    Model.Customer customer2 = new Model.Customer(2,"Petr","Petrov","X567");
    Model.Customer customer3 = new Model.Customer(3,"Vasily","Vasilev","X238");

    Model.Parcel parcel = new Model.Parcel("X345", Model.ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer1);
    Model.Parcel parcel2 = new Model.Parcel("X567", Model.ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer2);
    Model.Parcel parcel3 = new Model.Parcel("X238", Model.ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer3);

    Model.ParcelMap parcelMap = new Model.ParcelMap();
    parcelMap.addParcel(parcel);
    parcelMap.addParcel(parcel2);
    parcelMap.addParcel(parcel3);

    Model.QueueOfCustomers queueOfCustomers = new Model.QueueOfCustomers();
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