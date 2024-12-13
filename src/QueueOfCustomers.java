import java.util.*;
public class QueueOfCustomers {
    private final TreeSet<Customer> customers;

    public QueueOfCustomers() {customers = new TreeSet<>();}
    public void addCustomer(Customer customer) {customers.add(customer);}
    public void removeCustomer(Customer customer) {customers.removeIf(temp -> temp.equals(customer));}

    public int getNumberOfParcels() {
        return customers.size();
    }

    public StringBuilder searchByName(String name) {
        StringBuilder result = new StringBuilder();
        if(!customers.isEmpty()) {
            for (Customer customer : customers) {

                String customerName = name.toLowerCase();
                String fullName = customer.getCustomerName().toLowerCase() + " " + customer.getCustomerSurname().toLowerCase();

                if (fullName.contains(customerName)) {
                    result.append(customer).append("\n");
                }
            }
        }
        else { result.append("\n" + "There is no such customer in the queue"); }

        return result;

    }

    public StringBuilder searchParcelsByCustomer(String name) {
        StringBuilder result = new StringBuilder();
        if(!customers.isEmpty()) {
            for (Customer customer : customers) {

                String customerName = name.toLowerCase();
                String fullName = customer.getCustomerSurname().toLowerCase() + " " + customer.getCustomerName().toLowerCase();

                if (fullName.contains(customerName)) {
                    result.append(customer.getParcel()).append("\n");
                }
            }
        }
        else { result.append("\n" + "There is no such customer in the queue"); }

        return result;
    }

    public String printAllCustomers() {
        String allCustomers = "";

        if(!customers.isEmpty()) {

            for (Customer customer : customers) { allCustomers = allCustomers + customer.toString() + "\n"; } }

        else { allCustomers = allCustomers + "\n" + "There are no customers in the queue"; }

        return allCustomers;
    }

}



