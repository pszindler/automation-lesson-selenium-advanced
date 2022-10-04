package SearchTests;

import Pages.Home.HeaderNavigationPage;
import Pages.Product.ProductGridPage;
import Pages.Product.SingleProductFromGrid;
import Pages.SearchPage.SearchResultPage;
import TestBase.Pages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends Pages {
    @Test
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
    void dropDownTest(String productSearch) {
        at(HeaderNavigationPage.class)
                .setSearchInput(productSearch);
        List<String> autocompleteSuggest = at(HeaderNavigationPage.class).getAutocompleteSuggestItems();

        assertThat(autocompleteSuggest).anyMatch(c -> c.contains(productSearch));
    }
}
