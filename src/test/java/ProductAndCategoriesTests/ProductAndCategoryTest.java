package ProductAndCategoriesTests;

import Models.MyShop.NavMenu;
import Pages.Category.CategoryPage;
import Pages.Category.FilterPage;
import Pages.Home.HeaderNavigationPage;
import Pages.Product.ProductGridPage;
import Pages.Product.ProductPage;
import TestBase.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ProductAndCategoryTest extends Pages {

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
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
    void checkIfFilterIsWorkingCorrectly(int minPrice, int maxPrice) {
        HeaderNavigationPage headerNavigationPage = new HeaderNavigationPage(driver);
        ProductGridPage productGridPage = new ProductGridPage(driver);
        FilterPage filterPage = new FilterPage(driver);

        headerNavigationPage.goToCategory("ACCESSORIES");
        filterPage.moveSliderToPrice(minPrice, maxPrice);
        List<ProductPage> products = productGridPage.getListOfProducts();
        for (ProductPage product: products) {
            assertThat(product.getProductPrice()).isGreaterThan(minPrice);
            assertThat(product.getProductPrice()).isLessThan(maxPrice);
        }
        filterPage.resetFilter();
        List<ProductPage> productsWithOutFiltration = productGridPage.getListOfProducts();

        assertThat(products.size()).isNotEqualTo(productsWithOutFiltration.size());

    }

    static Stream<Arguments> intsListProvider() {
        return Stream.of(
                arguments(13,16),
                arguments(15,19),
                arguments(11,13)
        );
    }
}