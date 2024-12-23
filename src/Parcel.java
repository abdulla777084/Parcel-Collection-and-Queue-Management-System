public class Parcel implements Comparable<Parcel> {
    private String parcelID;
    private ParcelStatus status;
    private double weight, length, width, height;
    private int daysInDepot;
    private Customer customer;

    public Parcel(String parcelID, ParcelStatus status, double weight, double length, double width, double height,
                  int daysInDepot, Customer customer)
    {
        this.parcelID = parcelID;
        this.status = status;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.daysInDepot = daysInDepot;
        this.customer = customer;
    }

    public String getParcelID() {return parcelID;}

    public void setParcelID(String parcelID) {this.parcelID = parcelID;}

    public ParcelStatus getStatus() {return status;}

    public void setStatus(ParcelStatus status) {this.status = status;}

    public double getWeight() {return weight;}

    public void setWeight(double weight) {this.weight = weight;}

    public double getLength() {return length;}

    public void setLength(double length) {this.length = length;}

    public double getWidth() {return width;}

    public void setWidth(double width) {this.width = width;}

    public double getHeight() {return height;}

    public void setHeight(double height) {this.height = height;}

    public int getDaysInDepot() {return daysInDepot;}

    public void setDaysInDepot(int daysInDepot) {this.daysInDepot = daysInDepot;}

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public int compareTo(Parcel parcel) {

        //Combining customer surname and parcel id into a single string for comparison
        String thisCompareString = this.getCustomer().getCustomerSurname() + this.getParcelID();
        String otherCompareString = parcel.getCustomer().getCustomerSurname() + parcel.getParcelID();

        return thisCompareString.compareTo(otherCompareString);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Parcel parcel = (Parcel) obj;

        return getParcelID().equals(parcel.getParcelID());
    }

    @Override
    public String toString() {
        return parcelID + ", " + status + ", "
                + weight + ", " + length + ", "
                + width + ", " + height + ", "
                + daysInDepot + ", " + customer.getFullName();
    }

}
