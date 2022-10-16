package Pages.Cart;

import Models.MyShop.Product;
import Models.MyShop.ShoppingCart;
import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> cartList;
    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement totalItemsPrice;
    @FindBy(css = ".js-cart > .no-items")
    private WebElement emptyCartLabel;
    @FindBy(css = ".cart-detailed-actions .btn-primary")
    private WebElement proceedToCheckoutBtn;

    public double totalItemsPrice() {
        return getProductPrice(totalItemsPrice);
    }

    public List<CartProduct> getListOfProductsInCart() {
        List<CartProduct> products = new ArrayList<>();
        cartList.forEach(item -> {
            products.add(new CartProduct(driver, item));
        });
        return products;
    }

    public void goToCart() {
        String cartLink = "http://146.59.32.4/index.php?controller=cart&action=show";
        driver.get(cartLink);
    }

    public String deleteItem(int item) {
        List<CartProduct> cartProducts = getListOfProductsInCart();
        String productNameToDelete = cartProducts.get(item).getProductTitle();
        cartProducts.get(item).deleteItem();
        return productNameToDelete;
    }

    public CartProduct getProductFromCartByName(List<CartProduct> products, String productName) {
        return products.stream().filter(p -> p.getProductTitle().equals(productName)).findFirst().orElse(null);
    }

    public String getEmptyCartLabel() {
        return emptyCartLabel.getText();
    }

    public void proceedToCheckout() {
        proceedToCheckoutBtn.click();
    }

    public ShoppingCart toShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        for (CartProduct product : getListOfProductsInCart()) {
            shoppingCart.addItem(new Product(product), product.getQuantity());
        }
        return shoppingCart;
    }


}
