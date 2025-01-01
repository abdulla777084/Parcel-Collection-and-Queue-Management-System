package Model;

public class Parcel implements Comparable<Parcel> {
    private String parcelId;
    private ParcelStatus status;
    private double weight, length, width, height;
    private int daysInDepot;
    private Customer customer;

    public Parcel(String parcelId, ParcelStatus status, double weight, double length, double width, double height,
                  int daysInDepot, Customer customer)
    {
        this.parcelId = parcelId;
        this.status = status;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.daysInDepot = daysInDepot;
        this.customer = customer;
    }

    public String getParcelId() {return parcelId;}

    public void setParcelId(String parcelId) {this.parcelId = parcelId;}

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
        String thisCompareString = this.getCustomer().getCustomerSurname() + this.getParcelId();
        String otherCompareString = parcel.getCustomer().getCustomerSurname() + parcel.getParcelId();

        return thisCompareString.compareTo(otherCompareString);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Parcel parcel = (Parcel) obj;

        return getParcelId().equals(parcel.getParcelId());
    }

    @Override
    public String toString() {
        return  parcelId + ", " + status + ", "
                + weight + " kg(s), " + length + " x "
                + width + " x " + height + " cm(s), "
                + daysInDepot + " days, ordered by " + customer.getFullName();
    }

}
