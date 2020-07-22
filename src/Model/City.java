package Model;

import java.sql.Timestamp;

public class City {

    private int cityId;
    private String city;
    private int countryId;
    private Country country;
    private Timestamp createDate;
    private String createdBy;
    private String lastUpdateBy;

    public City(
            String city,
            int countryId,
            Timestamp createDate,
            String createdBy,
            String lastUpdateBy) {
        this.city = city;
        this.countryId = countryId;
        this.country = getCountry(countryId);
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
    }

    public City(
            int cityId,
            String city,
            int countryId,
            Timestamp createDate,
            String createdBy,
            String lastUpdateBy) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.country = getCountry(countryId);
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
    }

    public Country getCountry(int countryId) {
        return country;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public int getCountryId() {
        return countryId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
}