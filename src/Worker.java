import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Worker {

    //Worker class - to contain the logic for a worker processing a customer, calculating the fee, and releasing the parcel == marking parcel as collected;
    private final QueueOfCustomers queueOfCustomers;
    private final ParcelMap parcelMap;

    //private final Log log = Log.getInstance();
    private double totalFees = 0.0;

    public Worker(QueueOfCustomers queueOfCustomers, ParcelMap parcelMap) {
        this.queueOfCustomers = queueOfCustomers;
        this.parcelMap = parcelMap;
    }
    //add new customer to list of existing ones - to use set method
    public void addNewCustomer(Customer customer) {queueOfCustomers.addCustomer(customer); }
    public void removeCustomer(Customer customer) {queueOfCustomers.removeCustomer(customer); }

    //add new parcel to list of existing ones - to use set method
    public void addNewParcel(Parcel parcel) {parcelMap.addParcel(parcel); }
    public void removeParcel(Parcel parcel) {parcelMap.removeParcel(parcel); }


    //Process each customer one by one(queue -> process customer -> complete parcel collection -> move to the next customer), updating the list of parcels in the warehouse in memory, and write a report to a text file (generate info and save it to text file after the iteration with counts).
    //processCustomer()
        //identify and retrieve the customer's parcel from the list of parcels by id
        //get customer from queue
        //get parcel associated with the customer
        //if the parcel is valid and not collected:
            //collectParcel() search by id ,calculate fee and then change status setStatus() to collected and log the collection event to the log file
            //remove customer from queue once the parcel is collected
    public void processCustomer() {

        if (!getQueueOfCustomers().getCustomers().isEmpty()) {

            //Get the customer
            Customer customer = getQueueOfCustomers().getCustomers().peek();

            if (customer != null) {

                if (!getParcelMap().getParcels().isEmpty()) {

                    //Get the parcel associated with the customer
                    Parcel parcel = getParcelMap().searchParcelByParcelId(customer.getParcelId());
                    //Check if the parcel is valid and not collected

                    if (parcel != null && parcel.getStatus() == ParcelStatus.FOR_COLLECTION) {

                        //Calculate the fee
                        double fee = calculateFee(parcel);

                        //Update the total fees
                        totalFees += fee;

                        //Release parcel
                        releaseParcel(parcel);

                        //Log the collection event
                        /**logCollectionEvent(customer, parcel, fee);*/

                        //Remove the customer from the queue
                        removeCustomer(customer);
                    }
                }
            }
        }
    }
    public Parcel getCurrentParcel() {

        if (!getQueueOfCustomers().getCustomers().isEmpty()) {

            Customer customer = getQueueOfCustomers().getCustomers().peek();

            if (customer != null) {

                if (!getParcelMap().getParcels().isEmpty()) {

                    return getParcelMap().searchParcelByParcelId(customer.getParcelId());

                }
            }
        }
        return null;
    }


    public void releaseParcel(Parcel parcel) {parcel.setStatus(ParcelStatus.COLLECTED);}

    public double calculateFee(Parcel parcel) {

        double baseFee = 4.80;

        double weightFee = calculateWeightFee(parcel.getWeight());
        double dimensionFee = calculateDimensionFee(parcel.getLength(), parcel.getWidth(), parcel.getHeight());
        double daysFee = calculateDaysFee(parcel.getDaysInDepot());

        double totalFee = baseFee + weightFee + dimensionFee + daysFee;
        double finalFee = applyDiscount(totalFee, parcel);


        return Double.parseDouble(String.format("%.2f", finalFee));
    }

        public QueueOfCustomers getQueueOfCustomers() {return queueOfCustomers;}

        public ParcelMap getParcelMap() {return parcelMap;}

    //write/save report to text file after the iteration with counts = generate report
        //list collected parcels + fee of each parcel that was collected (list of collected parcels including the fee)
        //list for collection parcels (list of uncollected parcels)

        //counts (how many parcels were in the depot > more than a certain number of days) if daysInDepot > certain number then -> count++
        //totals (how much was collected on fees (sumOfFees()) on a certain day(for each iteration) (fees += fees) ) more like that of your choice

        //Save the report to a file
    public void generateReport(String filename) {

        String output = "Collected Parcels and Fees:\n" + getCollectedParcelsWithFee() + "\n" +

                "Parcels For Collection:\n" + parcelMap.printAllParcelsForCollection() + "\n" +

                "Count of Parcels in Depot with the longest amount of days: " + getCountOfParcelsWithMaxDays() + "\n" +

                "Total Fees Collected: " + String.format("%.2f", totalFees) + " £\n";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            writer.write(output);
        } catch (IOException e) {e.printStackTrace();}


    }



    /**
     *  Apply discount based on ParcelID range
     *  if the parcel ID is X100 or X300, apply a 15% discount
     */
    private double applyDiscount(double totalFee, Parcel parcel) {
        String parcelId = parcel.getParcelId();
        if (parcelId != null && (parcelId.equals("X100") || parcelId.equals("X300"))) {
            return totalFee * 0.85;
        }
        return totalFee;
    }

    /**
     *  Calculate the fee based on the weight of the parcel
     *  (less than 1 kg, less than 20 kg, greater than 20 kg)
     */
    private double calculateWeightFee(double weight) {
        if (weight <= 1) {return 0.50;}

        else if (weight <= 20) {return 1.00;}

        else {return 2.50;}
    }

    /**
     *  Calculate the fee based on the size (length, width, height) of the parcel
     */
    private double calculateDimensionFee(double length, double width, double height) {
        double sizeFee = 2.00;

        if (length > 61) {sizeFee += 1.50;}

        if (width > 46) {sizeFee += 1.20;}

        if (height > 16) {sizeFee += 1.00;}

        return sizeFee;
    }

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
                        .append(String.format("%.2f\n", fee));
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
