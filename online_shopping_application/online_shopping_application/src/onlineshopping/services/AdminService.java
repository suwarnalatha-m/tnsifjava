package onlineshopping.services;

import onlineshopping.entities.*;


import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Admin> admins = new ArrayList<>();
    private ProductService productService;
    private OrderService orderService;

    public AdminService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public boolean createAdmin(Admin admin) {
        if (admin == null) return false;
        admins.add(admin);
        return true;
    }

    public List<Admin> viewAdmins() {
        return new ArrayList<>(admins);
    }

    public boolean addProduct(Product product) {
        return productService.addProduct(product);
    }

    public boolean removeProduct(int productId) {
        return productService.removeProduct(productId);
    }

    public List<Product> viewProducts() {
        return productService.getAllProducts();
    }

    public boolean updateOrderStatus(int orderId, String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    public List<Order> viewOrders() {
        return orderService.getAllOrders();
    }
}
