package model;

/**
 * Clasa CompleteOrder este utilizata pentru a reprezenta o comanda, alaturi de clientul
 * care a comanda-to si produsul pe care l-a comandat.
 */

public class CompleteOrder {
    private int id;
    private int quantity;
    private int clientId;
    private int productId;
    private String firstName;
    private String lastName;
    private String name;
    private String brand;
    private double price;

    public CompleteOrder()
    {

    }

    public CompleteOrder(CustomersOrder order, Client client, Product product) {
        this.id=order.getId();
        this.quantity=order.getQuantity();
        this.clientId=order.getClientId();
        this.productId=order.getProductId();
        this.firstName=client.getFirstName();
        this.lastName=client.getLastName();
        this.name=product.getName();
        this.brand=product.getBrand();
        this.price=product.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
