package SearchTests;

import Pages.Home.HeaderNavigationPage;
import Pages.Product.ProductGridPage;
import Pages.Product.SingleProductFromGrid;
import Pages.SearchPage.SearchResultPage;
import TestBase.Pages;
import TestBase.ScreenShotWatcher;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Search Tests")
@Feature("Correct scenario features")
@ExtendWith(ScreenShotWatcher.class)
public class SearchTest extends Pages {

    @Test
    @Story("Searching for an item")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("The user finds the item on the home page and then searches for the item through the search engine")
    void searchForRandomProduct() {
        List<SingleProductFromGrid> products = at(ProductGridPage.class)
                .getListOfProducts();
        SingleProductFromGrid product = at(ProductGridPage.class)
                .getRandomWebElement(products);
        String foundProduct = product.getProductTitle();
        at(HeaderNavigationPage.class)
                .setSearchInput(product.getProductTitle())
                .submitSearch();

        String productTitleDetails = at(SearchResultPage.class).getTheFirstItemName();

        assertThat(productTitleDetails).isEqualTo(foundProduct);
    }

    @ParameterizedTest
    @ValueSource(strings = {"HUMMINGBIRD", "MUG", "POSTER"})
    @Story("The user enters the initial letters of the item name")
    @Severity(SeverityLevel.MINOR)
    @Description("Test verifies that specific items from the search list are prompted")
    void dropDownTest(String productSearch) {
        at(HeaderNavigationPage.class)
                .setSearchInput(productSearch);
        List<String> autocompleteSuggest = at(HeaderNavigationPage.class).getAutocompleteSuggestItems();

        assertThat(autocompleteSuggest).anyMatch(c -> c.contains(productSearch));
    }
}
