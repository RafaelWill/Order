package be.willekens.template.domain.models;

public class Address {

    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;

    public Address(String streetName, String houseNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return streetName + " " + houseNumber + ", " + postalCode + " " + city;
    }
}
