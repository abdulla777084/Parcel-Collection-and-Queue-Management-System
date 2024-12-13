public class ManagerMain {
    public static void main(String[] args) {
        Parcel parcel = new Parcel("X345", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10);
        Parcel parcel2 = new Parcel("X567", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10);
        Parcel parcel3 = new Parcel("X238", ParcelStatus.FOR_COLLECTION, 20, 30.4, 5.0, 9.8, 10);
        Customer customer = new Customer(1,"Ivan Ivanov", parcel);
        Customer customer2 = new Customer(2,"Petr Petrov", parcel2);
        Customer customer3 = new Customer(3,"Vasily Vasilev", parcel3);


        //Associate parcels with their customers
        parcel.setCustomer(customer);
        parcel2.setCustomer(customer2);
        parcel3.setCustomer(customer3);

        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(parcel);
        parcelMap.addParcel(parcel2);
        parcelMap.addParcel(parcel3);
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        queueOfCustomers.addCustomer(customer);
        queueOfCustomers.addCustomer(customer2);
        queueOfCustomers.addCustomer(customer3);

        System.out.println(queueOfCustomers.printAllCustomers());
        System.out.println(parcelMap.printParcels());
        System.out.println(queueOfCustomers.searchByName("Ivan"));
        System.out.println(queueOfCustomers.searchParcelsByCustomer("Ivan"));

    }
}