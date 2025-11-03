package onlineshopping.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Customer owner;
    // Map product -> quantity
    private Map<Product, Integer> items = new HashMap<>();

    public ShoppingCart(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() { return owner; }

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) return;
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
