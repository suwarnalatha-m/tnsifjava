package onlineshopping.services;

import onlineshopping.entities.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private ProductService productService;

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public void addOrder(Order order) {
        if (order != null) orders.add(order);
    }

    public Order getOrderById(int id) {
        for (Order o : orders) {
            if (o.getOrderId() == id) return o;
        }
        return null;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getUserId() == customerId) result.add(o);
        }
        return result;
    }

    /**
     * Update status. If setting to Cancelled, restock items back to productService.
     */
    public boolean updateOrderStatus(int orderId, String newStatus) {
        Order o = getOrderById(orderId);
        if (o == null) return false;
        String old = o.getStatus();
        o.setStatus(newStatus);
        // If cancelled and previously not cancelled, restock
        if (!old.equalsIgnoreCase("Cancelled") && newStatus.equalsIgnoreCase("Cancelled")) {
            for (ProductQuantityPair pqp : o.getProducts()) {
                productService.increaseStock(pqp.getProduct().getProductId(), pqp.getQuantity());
            }
        }
        return true;
    }
}
