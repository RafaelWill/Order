package be.willekens.template.api.dto;

public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String phoneNumber;

    public CustomerDto() {
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerDto setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDto setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public CustomerDto setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public CustomerDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public CustomerDto setCity(String city) {
        this.city = city;
        return this;
    }

    public CustomerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
