package onlineshopping.services;

import onlineshopping.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    private ProductService productService;
    private OrderService orderService;

    public CustomerService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public boolean createCustomer(Customer customer) {
        if (customer == null) return false;
        customers.add(customer);
        return true;
    }

    public List<Customer> viewCustomers() {
        return new ArrayList<>(customers);
    }

    public boolean placeOrder(int customerId, Map<Integer, Integer> productIdToQty) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) return false;

        // Validate stock first
        for (Map.Entry<Integer, Integer> e : productIdToQty.entrySet()) {
            Product p = productService.getProductById(e.getKey());
            if (p == null || p.getStockQuantity() < e.getValue()) {
                return false; // insufficient or product missing
            }
        }

        // Create order and reduce stock
        Order order = new Order(customer);
        for (Map.Entry<Integer, Integer> e : productIdToQty.entrySet()) {
            Product p = productService.getProductById(e.getKey());
            int qty = e.getValue();
            productService.reduceStock(p.getProductId(), qty);
            order.addProduct(p, qty);
        }
        orderService.addOrder(order);
        customer.addOrder(order);
        return true;
    }

    public List<Order> viewOrdersForCustomer(int customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    public Customer getCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }
}
