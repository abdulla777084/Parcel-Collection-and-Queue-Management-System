

public class Customer {
    private int queueNumber;
    private String customerName;
    private String customerSurname;
    private String parcelId;


    public Customer(int queueNumber,String CustomerName,String CustomerSurname,String parcelId)
    {
        this.queueNumber = queueNumber;
        this.customerName = CustomerName;
        this.customerSurname = CustomerSurname;
        this.parcelId = parcelId;
    }

    public int getQueueNumber() {return queueNumber;}

    public void setQueueNumber(int queueNumber) {this.queueNumber = queueNumber;}

    public String getCustomerName() {return customerName;}

    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getCustomerSurname() {return customerSurname;}

    public void setCustomerSurname(String customerSurname) {this.customerSurname = customerSurname;}

    public String getFullName() {return customerName + " " + customerSurname;}

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
    public int hashCode() {return Integer.hashCode(getQueueNumber());}

    @Override
    public String toString() {return queueNumber + " " + customerName + " " + customerSurname + " " + parcelId;}




}
