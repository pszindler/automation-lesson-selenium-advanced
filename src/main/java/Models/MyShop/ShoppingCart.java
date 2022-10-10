package Models.MyShop;

import org.decimal4j.util.DoubleRounder;

import java.util.HashMap;


public class ShoppingCart extends HashMap<Product, Integer> {

    public void addItem(Product product, int quantity) {
        put(product, getOrDefault(product, 0) + quantity);
    }

    public void deleteItem(Product product) {
        remove(product);
    }


    public double getTotalOrderValue() {
        return DoubleRounder.round(entrySet()
                .stream()
                .mapToDouble(p -> p.getKey().getPrice() * p.getValue()).sum(), 2);
    }
}
