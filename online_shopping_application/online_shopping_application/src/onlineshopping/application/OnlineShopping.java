package onlineshopping.application;
import onlineshopping.entities.*;
import onlineshopping.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OnlineShopping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService();
        OrderService orderService = new OrderService(productService);
        AdminService adminService = new AdminService(productService, orderService);
        CustomerService customerService = new CustomerService(productService, orderService);

        while (true) {
            System.out.println();
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            int mainChoice = readInt(sc);

            if (mainChoice == 1) {
                adminLoop(sc, adminService, productService);
            } else if (mainChoice == 2) {
                customerLoop(sc, customerService, productService);
            } else if (mainChoice == 3) {
                System.out.println("\nExiting...");
                break;
            } else {
                System.out.println("\nInvalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void adminLoop(Scanner sc, AdminService adminService, ProductService productService) {
        while (true) {
            System.out.println();
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Retrn");
            System.out.print("\nChoose an option: ");
            int ch = readInt(sc);

            if (ch == 1) {
                System.out.print("\nEnter Product ID: ");
                int id = readInt(sc);
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Product Price: ");
                double price = readDouble(sc);
                System.out.print("Enter Stock Quantity: ");
                int qty = readInt(sc);

                Product p = new Product(id, name, price, qty);
                boolean ok = adminService.addProduct(p);
                if (ok) System.out.println("\nProduct added successfully!");
                else System.out.println("\nProduct ID already exists or error.");
            } else if (ch == 2) {
                System.out.print("\nEnter Product ID to remove: ");
                int id = readInt(sc);
                boolean ok = adminService.removeProduct(id);
                if (ok) System.out.println("\nProduct removed successfully!");
                else System.out.println("\nProduct not found.");
            } else if (ch == 3) {
                System.out.println("\nProducts:");
                List<Product> products = adminService.viewProducts();
                if (products.isEmpty()) {
                    System.out.println("No products available.");
                } else {
                    for (Product p : products) {
                        System.out.println(p.toString());
                    }
                }
            } else if (ch == 4) {
                System.out.print("\nEnter Admin ID: ");
                int id = readInt(sc);
                System.out.print("Enter Username: ");
                String username = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                Admin admin = new Admin(id, username, email);
                adminService.createAdmin(admin);
                System.out.println("\nAdmin created successfully!");
            } else if (ch == 5) {
                System.out.println("\nAdmins:");
                List<Admin> admins = adminService.viewAdmins();
                if (admins.isEmpty()) {
                    System.out.println("No admins present.");
                } else {
                    for (Admin a : admins) {
                        System.out.println(a.toString());
                    }
                }
            } else if (ch == 6) {
                System.out.print("\nEnter Order ID: ");
                int orderId = readInt(sc);
                System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                String status = sc.nextLine();
                boolean ok = adminService.updateOrderStatus(orderId, status);
                if (ok) System.out.println("\nOrder status updated!");
                else System.out.println("\nOrder not found.");
            } else if (ch == 7) {
                System.out.println("\nOrders:");
                List<onlineshopping.entities.Order> orders = adminService.viewOrders();
                if (orders.isEmpty()) {
                    System.out.println("No orders.");
                } else {
                    for (onlineshopping.entities.Order o : orders) {
                        System.out.print(o.toString());
                    }
                }
            } else if (ch == 8) {
                System.out.println("\nExiting Admin...");
                break;
            } else {
                System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void customerLoop(Scanner sc, CustomerService customerService, ProductService productService) {
        while (true) {
            System.out.println();
            System.out.println("Customer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("\nChoose an option: ");
            int ch = readInt(sc);

            if (ch == 1) {
                System.out.print("\nEnter User ID: ");
                int id = readInt(sc);
                System.out.print("Enter Username: ");
                String username = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Address: ");
                String addr = sc.nextLine();
                Customer c = new Customer(id, username, email, addr);
                customerService.createCustomer(c);
                System.out.println("\nCustomer created successfully!");
            } else if (ch == 2) {
                System.out.println("\nCustomers:");
                List<Customer> customers = customerService.viewCustomers();
                if (customers.isEmpty()) {
                    System.out.println("No customers.");
                } else {
                    for (Customer c : customers) {
                        System.out.println(c.toString());
                    }
                }
            } else if (ch == 3) {
                System.out.print("\nEnter Customer ID: ");
                int custId = readInt(sc);
                // Print products
                System.out.println();
                System.out.println("Products:");
                for (Product p : productService.getAllProducts()) {
                    System.out.println(p.toString());
                }
                Map<Integer, Integer> items = new HashMap<>();
                while (true) {
                    System.out.print("\nEnter Product ID to add to order (or -1 to complete): ");
                    int pid = readInt(sc);
                    if (pid == -1) break;
                    Product p = productService.getProductById(pid);
                    if (p == null) {
                        System.out.println("Product not found.");
                        continue;
                    }
                    System.out.print("Enter quantity: ");
                    int qty = readInt(sc);
                    if (qty <= 0) {
                        System.out.println("Enter positive quantity.");
                        continue;
                    }
                    items.put(pid, items.getOrDefault(pid, 0) + qty);
                }
                if (items.isEmpty()) {
                    System.out.println("\nNo items added. Order cancelled.");
                } else {
                    boolean ok = customerService.placeOrder(custId, items);
                    if (ok) System.out.println("\nOrder placed successfully!");
                    else System.out.println("\nOrder failed due to missing product or insufficient stock.");
                }
            } else if (ch == 4) {
                System.out.print("\nEnter Customer ID: ");
                int custId = readInt(sc);
                List<onlineshopping.entities.Order> orders = customerService.viewOrdersForCustomer(custId);
                System.out.println("\nOrders:");
                if (orders.isEmpty()) {
                    System.out.println("No orders for this customer.");
                } else {
                    for (onlineshopping.entities.Order o : orders) {
                        System.out.print(o.toString());
                    }
                }
            } else if (ch == 5) {
                System.out.println("\nProducts:");
                List<Product> products = productService.getAllProducts();
                if (products.isEmpty()) {
                    System.out.println("No products.");
                } else {
                    for (Product p : products) {
                        System.out.println(p.toString());
                    }
                }
            } else if (ch == 6) {
                System.out.println("\nExiting Customer Menu...");
                break;
            } else {
                System.out.println("\nInvalid choice.");
            }
        }
    }

    // helper methods to safely read ints/doubles and consume newline
    private static int readInt(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
