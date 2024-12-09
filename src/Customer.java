

public class Customer  implements Comparable<Customer>{
    private int queueNumber;
    private String CustomerName;
    private String CustomerSurname;
    private Parcel parcel;
    public Customer(int queueNumber,String name, Parcel parcel)
    {
        this.queueNumber = queueNumber;

        String[] temp = name.split(" ");
        this.CustomerName = temp[0];
        this.CustomerSurname = temp[1];

        this.parcel = parcel;
    }

    public int getQueueNumber() {return queueNumber;}

    public void setQueueNumber(int queueNumber) {this.queueNumber = queueNumber;}

    public String getCustomerName() {return CustomerName;}

    public void setCustomerName(String customerName) {CustomerName = customerName;}

    public String getCustomerSurname() {return CustomerSurname;}

    public void setCustomerSurname(String customerSurname) {CustomerSurname = customerSurname;}

    public Parcel getParcel() {return parcel;}

    public void setParcel(Parcel parcel) {this.parcel = parcel;}

    //without queueNumber
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;

        if (!getCustomerName().equals(customer.getCustomerName())) return false;
        if (!getCustomerSurname().equals(customer.getCustomerSurname())) return false;
        return getParcel().equals(customer.getParcel());
    }
    //without queueNumber
    @Override
    public int hashCode() {
        int result = getCustomerName().hashCode();
        result = 31 * result + getCustomerSurname().hashCode();
        result = 31 * result + getParcel().hashCode();
        return result;
    }

    /* //toString
        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return this.toString().equals(obj.toString());
        }
     */


    /*
    @Override
    public String toString() {
        return "Customer{" +
                "queueNumber=" + queueNumber +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerSurname='" + CustomerSurname + '\'' +
                ", parcel=" + parcel +
                '}';
    }
    */


    @Override
    public String toString()
    {
        return CustomerName + " " + CustomerSurname;
    }

    @Override
    public int compareTo(Customer customer)
    {
        return CustomerSurname.compareTo(customer.CustomerSurname);
    }
}
