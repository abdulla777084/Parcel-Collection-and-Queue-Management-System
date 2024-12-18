public class Parcel implements Comparable<Parcel> {
    private String parcelID;
    private ParcelStatus status;
    private int daysInDepot;
    private double weight, length, width, height;
    private Customer customer;
    private static final long serialVersionUID = 1L;

    public Parcel(String parcelID, ParcelStatus status, int daysInDepot, double weight, double length, double width, double height, Customer customer) {
        this.parcelID = parcelID;
        this.status = status;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.customer = customer;
    }

    public String getParcelID() {return parcelID;}

    public void setParcelID(String parcelID) {this.parcelID = parcelID;}

    public ParcelStatus getStatus() {return status;}

    public void setStatus(ParcelStatus status) {this.status = status;}

    public int getDaysInDepot() {return daysInDepot;}

    public void setDaysInDepot(int daysInDepot) {this.daysInDepot = daysInDepot;}

    public double getWeight() {return weight;}

    public void setWeight(double weight) {this.weight = weight;}

    public double getLength() {return length;}

    public void setLength(double length) {this.length = length;}

    public double getWidth() {return width;}

    public void setWidth(double width) {this.width = width;}

    public double getHeight() {return height;}

    public void setHeight(double height) {this.height = height;}

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public int compareTo(Parcel parcel) {return getCustomer().getCustomerSurname().compareTo(parcel.getCustomer().getCustomerSurname());}

    // Override equals() to compare parcels based on parcelID
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Parcel parcel = (Parcel) obj;

        return getParcelID().equals(parcel.getParcelID());
    }

    @Override
    public String toString() {
        return parcelID + " " + status + " " + daysInDepot + " " + weight + " " + length + " " + width + " " + height + " " + customer;
    }

}
