import java.io.*;
import java.nio.Buffer;

public class ManagerMain {
    public static void main(String[] args) {

        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();

        queueOfCustomers.readFromFile(new File("Custs.csv"));
        parcelMap.readFromFile(new File("Parcels.csv"), queueOfCustomers);



        /*
        Customer customer = new Customer(1,"Ivan","Ivanov","X345");
        Customer customer2 = new Customer(2,"Petr","Petrov","X567");
        Customer customer3 = new Customer(3,"Vasily","Vasilev","X238");

        Parcel parcel = new Parcel("X345", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer);
        Parcel parcel2 = new Parcel("X567", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer2);
        Parcel parcel3 = new Parcel("X238", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10,customer3);




        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(parcel);
        parcelMap.addParcel(parcel2);
        parcelMap.addParcel(parcel3);
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        queueOfCustomers.addCustomer(customer);
        queueOfCustomers.addCustomer(customer2);
        queueOfCustomers.addCustomer(customer3);
        */


        System.out.println(queueOfCustomers.printAllCustomers());
        System.out.println(parcelMap.printAllParcels());
        System.out.println(queueOfCustomers.searchByName("Ivan"));
        System.out.println(parcelMap.searchByParcelId("X064"));



        //try out bidirectional relationship with setMethods and constructor tha doesn't ask for object but just sets the object null at first
        //explain that its done so that customer also has access to parcel object so that parcel id that is used is accurate and is not just made up
    }


}