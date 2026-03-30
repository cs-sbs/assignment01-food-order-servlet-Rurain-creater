package cs.sbs.web.model;

public class Order {
    private int orderId;
    private String customer;
    private String food;
    private int quantity;

    public Order(int orderId, String customer, String food, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }
}