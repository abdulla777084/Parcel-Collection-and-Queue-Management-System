package Controller;

import Model.*;
import Log.*;

import java.io.*;

/*
   Worker class is the Controller class,
       that contains the business logic of a worker for:
           processing a customer,
           calculating the fee,
           and releasing the parcel
*/
public class Worker {
    private final QueueOfCustomers queueOfCustomers;

    private final ParcelMap parcelMap;

    private double totalFees = 0.0; //keeping track of total fees

    private final Log log = Log.getInstance();

    public Worker(QueueOfCustomers queueOfCustomers, ParcelMap parcelMap) {
        this.queueOfCustomers = queueOfCustomers;
        this.parcelMap = parcelMap;
    }

    public QueueOfCustomers getQueueOfCustomers() {return queueOfCustomers;}
    public ParcelMap getParcelMap() {return parcelMap;}

    public void addNewCustomer(Customer customer) {
        queueOfCustomers.addCustomer(customer);
        log.addEvent("New customer added to the queue: " + customer.getFullName() +
                     ", Parcel Id: " + customer.getParcelId());
    }

    public void removeCustomer(Customer customer) {queueOfCustomers.removeCustomer(customer);}

    public void addNewParcel(Parcel parcel) {
        parcelMap.addParcel(parcel);
        log.addEvent("New parcel added to the depot: " + parcel.getParcelId());
    }

    public void removeParcel(Parcel parcel) {parcelMap.removeParcel(parcel); }

    /*
       Process a customer:
        if the queue is not empty and customer is valid
            get customer from queue
        if the list of parcels is not empty:
            get parcel associated with the customer  by id
        if the parcel is valid and not collected:
            calculate fee
            update the total fees
            change status to collected
            remove customer from queue once the parcel is collected
    */
    public void processCustomer() {

        if (!queueOfCustomers.getCustomers().isEmpty()) {

            Customer customer = queueOfCustomers.getCustomers().peek();

            if (customer != null && !parcelMap.getParcels().isEmpty()) {

                Parcel parcel = parcelMap.searchParcelByParcelId(customer.getParcelId());

                if (parcel != null && parcel.getStatus() == ParcelStatus.FOR_COLLECTION) {

                    double fee = calculateFee(parcel);

                    totalFees += fee;

                    collectParcel(parcel);

                    removeCustomer(customer);

                    log.addEvent("Customer processed: " + customer.getFullName() +
                                 ", Parcel with id: " + customer.getParcelId() +
                                 " was collected" +
                                 ", Fee: " + String.format("%.2f", fee) + " £"
                    );
                }
            }
        }
    }
    public Parcel getCurrentParcel() {

        if (!queueOfCustomers.getCustomers().isEmpty()) {

            Customer customer = queueOfCustomers.getCustomers().peek();

            if (customer != null) {

                if (!parcelMap.getParcels().isEmpty()) {

                    return parcelMap.searchParcelByParcelId(customer.getParcelId());

                }
            }
        }
        return null;
    }

    public void collectParcel(Parcel parcel) {parcel.setStatus(ParcelStatus.COLLECTED);}

    public double calculateFee(Parcel parcel) {

        double baseFee = 4.80;

        double weightFee = calculateWeightFee(parcel.getWeight());
        double dimensionFee = calculateDimensionFee(parcel.getLength(), parcel.getWidth(), parcel.getHeight());
        double daysFee = calculateDaysFee(parcel.getDaysInDepot());

        double totalFee = baseFee + weightFee + dimensionFee + daysFee;
        double finalFee = applyDiscount(totalFee, parcel);

        return Double.parseDouble(String.format("%.2f", finalFee));
    }


    /*
      Generate a report
        list of collected parcels including the fee of each parcel that was collected
        list of uncollected parcels
        count (how many parcels were in the depot for the longest amount of days)
        total (how much fees was collected in total for each iteration)
        save the report to a file
    */
    public void generateReport(String filename) {

        String output =

                "Collected Parcels and Fees:\n" + getCollectedParcelsWithFee() + "\n" +

                "Parcels For Collection:\n" + parcelMap.printAllParcelsForCollection() + "\n" +

                "Count of Parcels in Depot with the longest amount of days: " + getCountOfParcelsWithMaxDays() + "\n" +

                "Total Fees Collected: " + String.format("%.2f", totalFees) + " £\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(output);
            log.addEvent("Report generated: " + filename);
        } catch (IOException e) {e.printStackTrace();}

    }


    /*
       Apply discount based on ParcelID range:
        if the parcel ID is between X100 to X300, apply a 15% discount
     */
    private double applyDiscount(double totalFee, Parcel parcel) {
        String parcelId = parcel.getParcelId();
        if (parcelId != null && (parcelId.equals("X100") || parcelId.equals("X300"))) {
            return totalFee * 0.85;
        }
        return totalFee;
    }

    /*
       Calculate the fee based on the weight of the parcel
       (less than 1 kg, less than 20 kg, greater than 20 kg)
     */
    private double calculateWeightFee(double weight) {
        if (weight <= 1) {return 0.50;}

        else if (weight <= 20) {return 1.00;}

        else {return 2.50;}
    }

    /**
       Calculate the fee based on the dimensions (length, width, height) of the parcel
       (more than 61 cm, more than 46 cm, more than 16 cm)
     */
    private double calculateDimensionFee(double length, double width, double height) {
        double dimensionFee = 2.00;

        if (length > 61) {dimensionFee += 1.50;}

        if (width > 46) {dimensionFee += 1.20;}

        if (height > 16) {dimensionFee += 1.00;}

        return dimensionFee;
    }

    /*
       Calculate the fee based on the days in the depot
       (0.3 for each day)
     */
    private double calculateDaysFee(int daysInDepot) {return 0.3 * daysInDepot;}

    private StringBuilder getCollectedParcelsWithFee() {

        StringBuilder collectedParcels = new StringBuilder();

        StringBuilder allCollectedParcels = parcelMap.printAllCollectedParcels();

        if (allCollectedParcels.length() == 0) {
            collectedParcels.append("There are no collected parcels.");
        }

        for (Parcel parcel : parcelMap.getParcels()) {

            if (parcel.getStatus() == ParcelStatus.COLLECTED) {

                double fee = calculateFee(parcel);

                collectedParcels.append(parcel)
                                .append(", Fee: ")
                                .append(String.format("%.2f", fee))
                                .append(" £\n");
            }
        }
        return collectedParcels;
    }

    private int getCountOfParcelsWithMaxDays() {

        int maxDays = 0;
        int count = 0;

        for (Parcel parcel : parcelMap.getParcels()) {

            int daysInDepot = parcel.getDaysInDepot();

            if (daysInDepot > maxDays) {maxDays = daysInDepot; count = 1;}

            else if (daysInDepot == maxDays) {count++;}
        }
        return count;
    }
}
