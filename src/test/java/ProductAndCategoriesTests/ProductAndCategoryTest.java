package ProductAndCategoriesTests;

import Models.MyShop.NavMenu;
import Pages.Category.CategoryPage;
import Pages.Category.FilterPage;
import Pages.Home.HeaderNavigationPage;
import Pages.Product.ProductGridPage;
import Pages.Product.SingleProductFromGrid;
import TestBase.Pages;
import TestBase.ScreenShotWatcher;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Epic("Products and Category Tests")
@Feature("Correct scenario features")
@ExtendWith(ScreenShotWatcher.class)
public class ProductAndCategoryTest extends Pages {

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    @Story("User checks for correct category names")
    @Severity(SeverityLevel.MINOR)
    @Description("Verification of categories and sub-categories")
    void checkIfCategoryHaveCorrectNameWhenOpened(int depthLevel) {
        HeaderNavigationPage headerNavigationPage = new HeaderNavigationPage(driver);
        ProductGridPage productGridPage = new ProductGridPage(driver);

        // depth level (0 = categories, 1 = subcategories)
        List<NavMenu> categories = at(HeaderNavigationPage.class)
                .getCategories(depthLevel);

        categories.forEach(c -> {
            CategoryPage cp = headerNavigationPage.getCategoryPage(c);

            assertThat(cp.getCategoryName()).isEqualTo(c.getCategoryName());
            assertThat(cp.isFilterMenuVisible()).isTrue();
            assertThat(cp.getTotalProductItems()).isEqualTo(productGridPage.productItems());
        });
    }

    @ParameterizedTest
    @MethodSource("intsListProvider")
    @Story("The user is looking for an item in a given price range")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The filter (slider) is checked to see if it correctly filters the items on the page")
    void checkIfFilterIsWorkingCorrectly(int minPrice, int maxPrice) {
        HeaderNavigationPage headerNavigationPage = new HeaderNavigationPage(driver);
        ProductGridPage productGridPage = new ProductGridPage(driver);
        FilterPage filterPage = new FilterPage(driver);

        headerNavigationPage.goToCategory("ACCESSORIES");
        filterPage.moveSliderToPrice(minPrice, maxPrice);
        List<SingleProductFromGrid> products = productGridPage.getListOfProducts();
        for (SingleProductFromGrid product : products) {
            assertThat(product.getProductPrice()).isGreaterThan(minPrice);
            assertThat(product.getProductPrice()).isLessThan(maxPrice);
        }
        filterPage.resetFilter();
        List<SingleProductFromGrid> productsWithOutFiltration = productGridPage.getListOfProducts();

        assertThat(products.size()).isLessThanOrEqualTo(productsWithOutFiltration.size());

    }

    static Stream<Arguments> intsListProvider() {
        return Stream.of(
                arguments(13, 16),
                arguments(15, 19),
                arguments(11, 13)
        );
    }
}