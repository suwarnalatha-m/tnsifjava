package onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status; // Pending, Completed, Delivered, Cancelled

    public Order(Customer customer) {
        this.orderId = ID_GENERATOR.getAndIncrement();
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<ProductQuantityPair> getProducts() { return products; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void addProduct(Product product, int quantity) {
        this.products.add(new ProductQuantityPair(product, quantity));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId)
          .append(", Customer: ").append(customer.getUsername())
          .append(", Status: ").append(status).append("\n");
        for (ProductQuantityPair pqp : products) {
            sb.append(pqp.toString()).append("\n");
        }
        return sb.toString();
    }
}
