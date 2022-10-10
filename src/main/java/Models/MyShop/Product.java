package Models.MyShop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
    String title;
    double price;

    public Product(String title, String price) {
        this.title = title;
        this.price = Double.parseDouble(price.replace("$", ""));
    }
}
