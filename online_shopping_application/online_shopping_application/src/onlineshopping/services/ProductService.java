package onlineshopping.services;

import onlineshopping.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    // productId -> Product
    private Map<Integer, Product> products = new HashMap<>();

    public boolean addProduct(Product product) {
        if (product == null) return false;
        if (products.containsKey(product.getProductId())) return false;
        products.put(product.getProductId(), product);
        return true;
    }

    public boolean removeProduct(int productId) {
        return products.remove(productId) != null;
    }

    public Product getProductById(int productId) {
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean updateStock(int productId, int newStock) {
        Product p = products.get(productId);
        if (p == null) return false;
        p.setStockQuantity(newStock);
        return true;
    }

    public boolean reduceStock(int productId, int qty) {
        Product p = products.get(productId);
        if (p == null) return false;
        if (p.getStockQuantity() < qty) return false;
        p.setStockQuantity(p.getStockQuantity() - qty);
        return true;
    }

    public void increaseStock(int productId, int qty) {
        Product p = products.get(productId);
        if (p != null) {
            p.setStockQuantity(p.getStockQuantity() + qty);
        }
    }
}
