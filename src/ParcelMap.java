import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ParcelMap {

    private final TreeSet<Parcel> parcels;



    public ParcelMap() {parcels = new TreeSet<>();}
    public void addParcel(Parcel parcel) {parcels.add(parcel);}
    public void removeParcel(Parcel parcel) {parcels.removeIf(p -> p.getParcelID().equals(parcel.getParcelID()));}

    public int getNumberOfParcels() {
        return parcels.size();
    }

    public Parcel searchByParcelId(String parcelId) {
        for (Parcel parcel : parcels) {
            if (parcel.getParcelID().equals(parcelId)) {
                return parcel;
            }
        }
        return null;
    }

    public String printAllParcels() {
        String allParcels = "";

        if(!parcels.isEmpty()) {

            for (Parcel parcel  : parcels) { allParcels = allParcels + parcel.toString() + "\n"; } }

        else { allParcels = allParcels + "\n" + "There are no customers in the queue"; }

        return allParcels;
    }

    // Method to load parcels from a file
    public void readFromFile(File file, QueueOfCustomers queueOfCustomers) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String inputLine = reader.readLine().trim();

            while (inputLine != null && !inputLine.isEmpty()) {

                processLine(inputLine, queueOfCustomers);

                inputLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}



    }

    private void processLine(String inputLine, QueueOfCustomers queueOfCustomers) {

        String[] data = inputLine.split(",");
        // Assuming you already have a Customer object





        String parcelID = data[0].trim();
        int daysInDepot = Integer.parseInt(data[1].trim());
        double weight = Double.parseDouble(data[2].trim());
        double length = Double.parseDouble(data[3].trim());
        double width = Double.parseDouble(data[4].trim());
        double height = Double.parseDouble(data[5].trim());

        Customer customer = queueOfCustomers.searchCustomerByParcelId(parcelID);

        if (customer == null) {
            System.out.println("No customer found with parcel ID: " + parcelID);
            return;
        }

        Parcel parcel = new Parcel(parcelID, ParcelStatus.FOR_COLLECTION, daysInDepot, weight, length, width, height, customer);
        addParcel(parcel);



    }

}

