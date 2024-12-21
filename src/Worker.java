public class Worker {

    //private final Log log = Log.getInstance();

    //Worker class - to contain the logic for a worker processing a customer, calculating the fee,
    //and releasing the parcel == mark as collected;

    //Process each customer one by one(queue -> process customer -> complete parcel collection -> move to the next customer), updating the list of parcels in the warehouse in memory, and write a report to a text file (generate info and save it to text file after the iteration with counts).

    //view list of collected parcel and view list of uncollected parcels - use printAllParcels()
    //processCustomer()
        //get customer from queue
        //get parcel associated with the customer
        //if the parcel is valid and not collected:
        //collectParcel() search by id ,calculate fee and then change status updateStatus() and log the collection event to the log file
        //remove customer from queue once the parcel is collected and add it to the list of collected parcels
        //movetoNextCustomer() == skip to next customer()

    //processCustomer()
        //identify and retrieve the customer's parcel from the list of parcels by id
        //calculate and display the collection fee
        //update the status of the parcel to "COLLECTED"
        //log the collection event to the log file

    //do_counts()

    //Show calculation fee and make able to apply discount
    private float calculateFee() {
        //The collection fee for the parcel should be based on some of:dimension;weight; number of days that the parcel has been in the depot for; discounts for parcels with a particular type of ID.
        //if id == "X345" - apply discount()
        return 0; } //based on attributes of parcel or make research about how it's calculated
    //if particular type of ID - apply discount to fee


    public String releaseParcel() { String msg =""; return msg; }

    private void updateQueueOfCustomers() { }



    //write/save report to text file after the iteration with counts = generate report
        //list collected parcels + fees (list of collected parcels including the fee)
        //list for collection parcels (list of uncollected parcels)
        //counts
            //some counts (how many parcels were in the depot > more than a certain number of days) if daysInDepot > certain number then -> count++
        //totals
            //and totals (how much was collected on fees (sumOfFees()) on a certain day(for each iteration) (fees += fees) ) more like that of your choice


    //saveLog to text file = write the event log to file

    //add new parcel to list of existing ones - to use set method
    //add new customer to queue

    //print list of collected parcels
    //print list of uncollected parcels


    //exit after processing all customers and writing outputs to reports - action listener





}
