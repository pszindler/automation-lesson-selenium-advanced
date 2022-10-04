package BasketAndCheckoutTests;

import Pages.Home.HeaderNavigationPage;
import Pages.Product.ProductGridPage;
import TestBase.Pages;
import org.junit.jupiter.api.Test;

public class BasketTest extends Pages {

    @Test
    void sds() {
        at(HeaderNavigationPage.class).goToCategory("ART");
        at(ProductGridPage.class).clickOnProductByName("THE BEST IS YET POSTER");

    }

}
