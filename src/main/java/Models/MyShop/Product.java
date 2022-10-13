package Models.MyShop;

import Pages.Cart.CartPage;
import Pages.Cart.CartProduct;
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

    public Product(CartProduct cartProduct) {
        this.title = cartProduct.getProductTitle();
        this.price = cartProduct.getProductPrice();
    }
}
