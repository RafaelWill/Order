package be.willekens.template.api.dto.customer;

public class CreateCustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String phoneNumber;

    public CreateCustomerDto() {
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
}
