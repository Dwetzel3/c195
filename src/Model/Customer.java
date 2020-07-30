package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;

public class Customer {

    private static Customer selectedCustomer;

    private int addressId;
    private int customerID;
    private String customerName;
    private int addressID;
    private Boolean active;
    private Date createdDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Customer(Integer customerId, String customerName, Integer addressID, boolean active, java.sql.Date createdDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.customerID = customerId;
        this.customerName = customerName;
        this.addressID = addressID;
        this.active = active;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;

    }

    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static void setAllCustomers(ObservableList<Customer> allCustomers) {
        Customer.allCustomers = allCustomers;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressId) {
        this.addressID = addressId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        Customer.selectedCustomer = selectedCustomer;
    }
}
