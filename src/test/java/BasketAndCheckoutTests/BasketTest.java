package BasketAndCheckoutTests;

import Models.Address.AddressFactory;
import Models.MyShop.Product;
import Models.MyShop.ShoppingCart;
import Models.User.User;
import Models.User.UserFactory;
import Pages.Account.OrderHistoryPage;
import Pages.Account.OrderHistoryRow;
import Pages.Cart.CartPage;
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
import TestBase.ScreenShotWatcher;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Basket Tests")
@Feature("Correct scenario features")
@ExtendWith(ScreenShotWatcher.class)
public class BasketTest extends Pages {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, -2})
    @Issue("288")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Customer adds product x times to cart")
    @Description("The customer goes to the art category and adds the poster," +
            " the data that is displayed after adding to the cart is verified")
    @Step()
    void popupCheckTest(int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart();
        at(HeaderNavigationPage.class).goToCategory("ART");
        at(ProductGridPage.class)
                .getProductByName("THE BEST IS YET POSTER")
                .setQuantity(quantity);
        Product product = at(ProductDetailsPage.class).addToCart(quantity);
        shoppingCart.addItem(product, quantity);

        at(SummaryPopupPage.class).waitForPopUp();
        assertThat(at(SummaryPopupPage.class).getProductPrice()).isEqualTo(product.getPrice());
        assertThat(at(SummaryPopupPage.class).getProductQuantity()).isEqualTo(shoppingCart.get(product));
        assertThat(at(SummaryPopupPage.class).getProductName()).isEqualTo(product.getTitle());
        assertThat(at(SummaryPopupPage.class).getSubTotal()).isEqualTo(shoppingCart.getTotalOrderValue());
        assertThat(at(SummaryPopupPage.class).getTotalItemsInCartText()).isEqualTo(getExpectedCartItemMsg(quantity));

        at(SummaryPopupPage.class).continueShopping();
        int count = at(HeaderTopPage.class).getCartCounter();
        assertThat(count).isEqualTo(quantity);
    }

    private String getExpectedCartItemMsg(int quantity) {
        return quantity == 1 ? "There is " + quantity + " item in your cart." : "There are " + quantity + " items in your cart.";
    }

    @Test
    @Story("User adds and removes products from the cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Generic test, tests addition and deletion of products, checks correct data.")
    void basketCheckTest() {
        ShoppingCart expectedShoppingCart = new ShoppingCart();

        while (expectedShoppingCart.size() != 5) {
            at(HeaderNavigationPage.class).goToRandomCategory();
            at(ProductGridPage.class).openRandomProduct();
            int quantity = at(ProductDetailsPage.class).setRandomQuantity();

            Product pro = at(ProductDetailsPage.class).addToCart(quantity);
            expectedShoppingCart.addItem(pro, quantity);
            at(SummaryPopupPage.class).continueShopping();
        }

        at(CartPage.class).goToCart();

        assertThat(at(CartPage.class).toShoppingCart()).usingRecursiveComparison().isEqualTo(expectedShoppingCart);
        assertThat(at(CartPage.class).totalItemsPrice()).isEqualTo(expectedShoppingCart.getTotalOrderValue());

        for (int i = 0; i < 5; i++) {
            String productNameToDelete = at(CartPage.class).deleteItem(0);
            expectedShoppingCart.deleteProductFromCart(productNameToDelete);

            assertThat(at(CartPage.class).toShoppingCart()).usingRecursiveComparison().isEqualTo(expectedShoppingCart);
            assertThat(at(CartPage.class).totalItemsPrice()).isEqualTo(expectedShoppingCart.getTotalOrderValue());
        }
        assertThat(at(CartPage.class).getEmptyCartLabel()).isEqualTo("There are no more items in your cart");
    }

    @ParameterizedTest
    @MethodSource("generator")
    @Story("Test checks orders")
    @Severity(SeverityLevel.BLOCKER)
    @Description("User creates orders and then checks their status ")
    @Step("Type {user.email} / {user.password}.")
    void checkOutTest(User user) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateNow = formatter.format(date);

        at(HeaderTopPage.class).goToLogIn();
        at(SignInPage.class).signIn(user);
        at(HeaderNavigationPage.class).goToCategory("ART");
        at(ProductGridPage.class).getProductByName("THE BEST IS YET POSTER");

        at(ProductDetailsPage.class).addToCart(1);

        at(SummaryPopupPage.class)
                .proceedToCheckoutOrder()
                .proceedToCheckout();
        at(AddressesPage.class).setAddInvoiceAddressForm(AddressFactory.generateJohnAddress());
        at(ShippingMethodPage.class).setShippingMethod();
        at(PaymentPage.class).setPaymentOptions();

        double totalPrice = at(ConfirmationPage.class).getTotalPrice();
        String orderReferenceNumber = at(ConfirmationPage.class).getOrderReferenceNumber();
        String paymentMethod = at(ConfirmationPage.class).getPaymentMethod();
        double shippingPrice = 7;

        at(ConfirmationPage.class).goToOrderHistory();
        OrderHistoryRow order = at(OrderHistoryPage.class).getOrder(orderReferenceNumber);

        assertThat(totalPrice + shippingPrice).isEqualTo(order.getTotalPrice());
        assertThat(paymentMethod).isEqualTo(order.getPayment());
        assertThat(dateNow).isEqualTo(order.getOrderDate());
        assertThat("Awaiting check payment").isEqualTo(order.getStatus());
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(UserFactory.generateJohn()));
    }
}



