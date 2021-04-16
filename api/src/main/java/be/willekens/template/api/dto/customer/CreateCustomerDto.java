package be.willekens.template.api.dto.customer;

public class CreateCustomerDto {

    private String username;
    private String password;

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

    public CreateCustomerDto(String username, String password, String firstName, String lastName, String email, String streetName, String houseNumber, String postalCode, String city, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
