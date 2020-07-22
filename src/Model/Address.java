package Model;

import java.sql.Timestamp;

public class Address {

    private int addressId;
    private int cityId;
    private City city;

    private String address;
    private String address2;
    private String postalCode;
    private String phone;
    private String createdBy;
    private String lastUpdatedBy;
    private Timestamp createDate;

    public Address(
            int cityId,
            String address,
            String address2,
            String postalCode,
            String phone,
            String createdBy,
            String lastUpdatedBy,
            Timestamp createDate
    ) {
        this.cityId = cityId;
        this.city = getCity(cityId);
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
    }

    Address(
            int addressId,
            int cityId,
            String address,
            String address2,
            String postalCode,
            String phone,
            String createdBy,
            String lastUpdateBy,
            Timestamp createDate
    ) {
        this.addressId = addressId;
        this.cityId = cityId;
        this.city = getCity(cityId);
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdateBy;
        this.createDate = createDate;
    }

    Address(
            int addressId,
            City city,
            String address,
            String address2,
            String postalCode,
            String phone,
            String createdBy,
            String lastUpdateBy,
            Timestamp createDate
    ) {
        this.addressId = addressId;
        this.city = city;
        this.cityId = city.getCityId();
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdateBy;
        this.createDate = createDate;
    }

    Address(
            int addressId,
            City city,
            Country country,
            String address,
            String address2,
            String postalCode,
            String phone,
            String createdBy,
            String lastUpdateBy,
            Timestamp createDate
    ) {
        this.addressId = addressId;
        this.city = city;
        this.cityId = city.getCityId();
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdateBy;
        this.createDate = createDate;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getCityId() {
        return cityId;
    }

    public City getCity(int cityId) {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
}