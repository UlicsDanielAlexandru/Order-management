package model;

/**
 * Clasa Client este utilizata pentru a reprezenta un obiect din tabelul cu clienti.
 */

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    public Client()
    {

    }

    public Client(int id, String firstName, String lastName, String address, String email, String phoneNumber)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public Client(String firstName, String lastName, String address, String email, String phoneNumber)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return "Client id:"+id+" firstName:"+firstName+" lastName:"+lastName+" address:"+address+" email:"+email+" phoneNumber:"+phoneNumber;
    }
}
