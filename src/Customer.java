

public class Customer {
    private int queueNumber;
    private String CustomerName;
    private String CustomerSurname;
    private String parcelId;
    public Customer(int queueNumber,String name,String parcelId)
    {
        this.queueNumber = queueNumber;

        String[] temp = name.split(" ");
        this.CustomerName = temp[0];
        this.CustomerSurname = temp[1];

        this.parcelId = parcelId;
    }

    public int getQueueNumber() {return queueNumber;}

    public void setQueueNumber(int queueNumber) {this.queueNumber = queueNumber;}

    public String getCustomerName() {return CustomerName;}

    public void setCustomerName(String customerName) {CustomerName = customerName;}

    public String getCustomerSurname() {return CustomerSurname;}

    public void setCustomerSurname(String customerSurname) {CustomerSurname = customerSurname;}

    public String getFullName() {return CustomerName + " " + CustomerSurname;}
    public String getParcelId() {return parcelId;}

    public void setParcelId(String parcelId) {this.parcelId = parcelId;}

    //without queueNumber and parcelId

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;

        return getCustomerName().equals(customer.getCustomerName())
               && getCustomerSurname().equals(customer.getCustomerSurname());
    }

    @Override
    public int hashCode() {return getCustomerName().hashCode() + getCustomerSurname().hashCode();}

    @Override
    public String toString() {return getFullName() + " : " + getParcelId();}

    /*
    @Override
    public String toString() {
        return "Customer{" +
                "queueNumber=" + queueNumber +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerSurname='" + CustomerSurname + '\'' +
                ", parcel=" + parcelId +
                '}'; }
    */



}
