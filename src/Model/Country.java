package Model;

import java.sql.Timestamp;

public class Country {

    private int countryId;
    private String country;
    private Timestamp createDate;
    private String createdBy;
    private String lastUpdateBy;

    public Country(
            String country,
            Timestamp createDate,
            String createdBy,
            String lastUpdateBy
    ) {
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
    }

    public Country(
            int countryId,
            String country,
            Timestamp createDate,
            String createdBy,
            String lastUpdateBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
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
