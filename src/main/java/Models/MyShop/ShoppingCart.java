package Models.MyShop;

import org.decimal4j.util.DoubleRounder;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCart extends HashMap<Product, Integer> {

    public void addItem(Product product, int quantity) {
        put(product, getOrDefault(product, 0) + quantity);
    }

    public ShoppingCart deleteProductFromCart(String productName) {
        for (Map.Entry<Product, Integer> item : entrySet()) {
            if (item.getKey().getTitle().equals(productName)) {
                remove(item.getKey());
                return this;
            }
        }
        return null;
    }


    public double getTotalOrderValue() {
        return DoubleRounder.round(entrySet().stream().mapToDouble(p -> p.getKey().getPrice() * p.getValue()).sum(), 2);
    }
}
