package be.willekens.template.api.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDto)) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(streetName, that.streetName) && Objects.equals(houseNumber, that.houseNumber) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, streetName, houseNumber, postalCode, city, phoneNumber);
    }
}
