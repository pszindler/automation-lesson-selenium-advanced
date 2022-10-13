package BasketAndCheckoutTests;

import Models.MyShop.Product;
import Models.MyShop.ShoppingCart;
import Models.User.User;
import Models.User.UserFactory;
import Pages.Account.OrderHistoryPage;
import Pages.Account.OrderHistoryRow;
import Pages.Cart.CartPage;
import Pages.Cart.CartProduct;
import Pages.Cart.SummaryPopupPage;
import Pages.Checkout.AddressesPage;
import Pages.Checkout.ConfirmationPage;
import Pages.Checkout.PaymentPage;
import Pages.Checkout.ShippingMethodPage;
import Pages.Home.HeaderNavigationPage;
import Pages.Home.HeaderTopPage;
import Pages.Home.SignInPage;
import Pages.Product.ProductDetailsPage;
import Pages.Product.ProductGridPage;
import TestBase.Pages;
import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class BasketTest extends Pages {

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 1, 8, 12})
    void popupCheckTest(int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart();
        at(HeaderNavigationPage.class).goToCategory("ART");
        at(ProductGridPage.class).clickOnProductByName("THE BEST IS YET POSTER");
        at(ProductDetailsPage.class).setQuantity(quantity);
        Product pro = at(ProductDetailsPage.class).addToCart(quantity);
        shoppingCart.addItem(pro, quantity);

        assertThat(pro.getPrice()).isEqualTo(at(SummaryPopupPage.class).getProductPrice());
        assertThat(shoppingCart.get(pro)).isEqualTo(at(SummaryPopupPage.class).getProductQuantity());
        assertThat(pro.getTitle()).isEqualTo(at(SummaryPopupPage.class).getProductName());
        assertThat(shoppingCart.getTotalOrderValue()).isEqualTo(at(SummaryPopupPage.class).getSubTotal());

        if (quantity == 1) {
            assertThat("There is " + quantity + " item in your cart.").isEqualTo(at(SummaryPopupPage.class).getTotalItemsInCartText());
        } else {
            assertThat("There are " + quantity + " items in your cart.").isEqualTo(at(SummaryPopupPage.class).getTotalItemsInCartText());
        }

        at(SummaryPopupPage.class).continueShopping();
        int count = at(HeaderTopPage.class).getCartCounter();

        assertThat(count).isEqualTo(quantity);
    }

    @Test
    void basketCheckTest() {
        // tworzenie koszyka
        ShoppingCart actualShoppingCart = new ShoppingCart();
        ShoppingCart expectedShoppingCart = new ShoppingCart();
        while (actualShoppingCart.size() != 5) {
            at(HeaderNavigationPage.class).goToRandomCategory();
            at(ProductGridPage.class).getRandomProduct();
            int quantity = at(ProductDetailsPage.class).setRandomQuantity();
            Product pro = at(ProductDetailsPage.class).addToCart(quantity);
            actualShoppingCart.addItem(pro, quantity);
            at(SummaryPopupPage.class).continueShopping();

        }

        // przejscie do koszyka
        at(CartPage.class).goToCart();
        List<CartProduct> cartProducts = at(CartPage.class).getListOfProductsInCart();
        for (CartProduct product : cartProducts) {
            Product prod = new Product(product);
            expectedShoppingCart.addItem(prod, product.getQuantity());
        }

        assertThat(actualShoppingCart).usingRecursiveComparison().isEqualTo(expectedShoppingCart);
        assertThat(actualShoppingCart.getTotalOrderValue()).isEqualTo(at(CartPage.class).totalItemsPrice());


        // usuwanie koszyka
        for (int i = 0; i < 5; i++) {
            cartProducts = at(CartPage.class).getListOfProductsInCart();
            String productNameToDelete = cartProducts.get(0).getProductTitle();
            cartProducts.get(0).deleteItem();
            Product productToDelete = null;
            for (Map.Entry<Product, Integer> item : actualShoppingCart.entrySet()) {
                if (item.getKey().getTitle().equals(productNameToDelete)) {
                    productToDelete = item.getKey();
                }
            }

            ShoppingCart expectedShoppingCartAfterDelete = new ShoppingCart();
            cartProducts = at(CartPage.class).getListOfProductsInCart();
            for (CartProduct product : cartProducts) {
                Product prod = new Product(product);
                expectedShoppingCartAfterDelete.addItem(prod, product.getQuantity());
            }
            actualShoppingCart.deleteItem(productToDelete);

            assertThat(actualShoppingCart).usingRecursiveComparison().isEqualTo(expectedShoppingCartAfterDelete);
            assertThat(actualShoppingCart.getTotalOrderValue()).isEqualTo(at(CartPage.class).totalItemsPrice());

            // weryfikacja pustego koszyka
            if (expectedShoppingCartAfterDelete.size() == 0) {
                assertThat(at(CartPage.class).getEmptyCartLabel()).isEqualTo("There are no more items in your cart");
            }
        }
    }

    @Test
    void checkOutTest() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateNow = formatter.format(date);
        ShoppingCart shoppingCart = new ShoppingCart();
        User john = UserFactory.generateJohn();
        at(HeaderTopPage.class).goToLogIn();
        at(SignInPage.class).signIn(john.getEmail(), john.getPassword());
        at(HeaderNavigationPage.class).goToCategory("ART");
        at(ProductGridPage.class).clickOnProductByName("THE BEST IS YET POSTER");
        Product pro = at(ProductDetailsPage.class).addToCart(1);
        shoppingCart.addItem(pro, 1);
        at(SummaryPopupPage.class).proceedToCheckoutOrder();
        at(CartPage.class).proceedToCheckout();
        at(AddressesPage.class).setAddInvoiceAddressForm();
        at(ShippingMethodPage.class).setShippingMethod();
        at(PaymentPage.class).setPaymentOptions();

        double totalPrice = at(ConfirmationPage.class).getTotalPrice();
        String orderReferenceNumber = at(ConfirmationPage.class).getOrderReferenceNumber();
        String paymentMethod = at(ConfirmationPage.class).getPaymentMethod();
        String shippingMethod = at(ConfirmationPage.class).getShippingMethod();
        double shippingPrice = 7;
        at(ConfirmationPage.class).goToOrderHistory();
        OrderHistoryRow order = at(OrderHistoryPage.class).getOrder(orderReferenceNumber);

        assertThat(totalPrice + shippingPrice).isEqualTo(order.getTotalPrice());
        assertThat(paymentMethod).isEqualTo(order.getPayment());
        assertThat(dateNow).isEqualTo(order.getOrderDate());
        assertThat("Awaiting check payment").isEqualTo(order.getStatus());
    }
}



