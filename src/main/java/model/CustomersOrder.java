package model;

/**
 * Clasa CustomersOrder este utilizata pentru a reprezenta un obiect din tabela de comenzi.
 */

public class CustomersOrder {
    private int id;
    private int quantity;
    private int clientId;
    private int productId;

    public CustomersOrder()
    {

    }

    public CustomersOrder(int id, int quantity, int clientId, int productId)
    {
        this.id = id;
        this.quantity = quantity;
        this.clientId = clientId;
        this.productId = productId;
    }

    public CustomersOrder(int quantity, int clientId, int productId)
    {
        this.quantity = quantity;
        this.clientId = clientId;
        this.productId = productId;
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

    public String toString()
    {
        return "CustomersOrder id:"+id+" quantity:"+quantity+" clientId:"+clientId+" productId:"+productId;
    }
}
