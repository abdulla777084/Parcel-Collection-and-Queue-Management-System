import java.io.*;
import java.util.*;
public class QueueOfCustomers {
    private final HashSet<Customer> customers;
    private int queueNumber;

    public QueueOfCustomers() {customers = new HashSet<>();queueNumber = 1;}
    public void addCustomer(Customer customer) {customers.add(customer);}
    public void removeCustomer(Customer customer) {customers.removeIf(temp -> temp.equals(customer));}

    public int getNumberOfParcels() {return customers.size();}

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

    public Customer searchCustomerByParcelId(String parcelId) {
        for (Customer customer : customers) {
            if (customer.getParcelId().equals(parcelId)) {
                return customer;
            }
        }
        return null;
    }


    public String printAllCustomers() {

        StringBuilder allCustomers = new StringBuilder();

        if (!customers.isEmpty()) {

            for (Customer customer : customers) {allCustomers.append(customer.toString()).append("\n");}

        } else {allCustomers.append("\n").append("There are no customers in the queue");}

        return allCustomers.toString();
    }


    public HashSet<Customer> getCustomers() {return customers;}

    public void readFromFile(File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String inputLine = reader.readLine();

            while(inputLine != null && !inputLine.isEmpty()) {

                processLine(inputLine);

                inputLine = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {e.printStackTrace();}

    }

    private void processLine(String inputLine) {

        String[] data = inputLine.split(",");

        String[] nameParts = data[0].split(" ");

        String name = nameParts[0].trim();
        String surname = nameParts[1].trim();

        String parcelId = data[1].trim();

        Customer customer = new Customer(queueNumber,name,surname, parcelId);

        addCustomer(customer);
        queueNumber++;
    }


}



