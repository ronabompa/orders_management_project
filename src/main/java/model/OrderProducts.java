package model;

/**
 * Clasa care contine datele OrderProduct cu care se vor lucra
 * Created by d.rona on 01.05.2019.
 */
public class OrderProducts {

    private int id;
    private int clientOrderId;
    private int productId;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(int clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
