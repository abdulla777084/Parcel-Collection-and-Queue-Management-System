public class Parcel {
    private String parcelID;
    private ParcelStatus status;
    private int daysInDepot;
    private double weight, length, width, height;
    private static final long serialVersionUID = 1L;

    public Parcel(String parcelID, ParcelStatus status, int daysInDepot, double weight, double length, double width, double height) {
        this.parcelID = parcelID;
        this.status = status;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
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

    // Override hashCode() based on parcelID
    @Override
    public int hashCode() {return getParcelID().hashCode();}

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
        return "Parcel{" +
                "parcelID='" + parcelID + '\'' +
                ", status=" + status +
                ", daysInDepot=" + daysInDepot +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
