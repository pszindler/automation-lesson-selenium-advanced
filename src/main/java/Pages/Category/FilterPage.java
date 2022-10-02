package Pages.Category;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FilterPage extends BasePage {
    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[aria-disabled] .ui-corner-all:nth-child(1)")
    private WebElement sliderWidth;
    @FindBy(css = "[aria-disabled] .ui-corner-all:nth-child(2)")
    private WebElement sliderLeft;
    @FindBy(css = "[aria-disabled] .ui-corner-all:nth-child(3)")
    private WebElement sliderRight;
    @FindBy(css = "[data-slider-min] p")
    private WebElement priceRange;
    @FindBy(css = ".collapse.faceted-slider")
    private WebElement sliderData;
    @FindBy(css = ".filter-block")
    private WebElement filterBlock;
    @FindBy(css = ".spinner")
    private WebElement spinner;
    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearAllFilters;

    private List<Integer> calculateDragAndDropByPixels(int newMinPrice, int newMaxPrice) {
        List<Integer> prices = new ArrayList<>();
        double divWidth = sliderWidth.getSize().getWidth();
        double minValue = Integer.parseInt(sliderData.getAttribute("data-slider-min"));
        double maxValue = Integer.parseInt(sliderData.getAttribute("data-slider-max"));
        double oneMoveByPercentage = 100 / (maxValue - minValue);
        double oneMoveByPixels = oneMoveByPercentage * divWidth / 100;
        double pixelsToMoveRightToLeft = (maxValue - newMaxPrice) * oneMoveByPixels;
        double pixelsToMoveLefToToRight = (newMinPrice - minValue) * oneMoveByPixels;
        prices.add((int) pixelsToMoveLefToToRight);
        prices.add((int) pixelsToMoveRightToLeft);
        return prices;
    }

    public void moveSliderToPrice(int newPriceLeft, int newPriceRight) {
        List<Integer> prices = calculateDragAndDropByPixels(newPriceLeft, newPriceRight);
        actions.dragAndDropBy(sliderLeft, prices.get(0), 0).perform();
        wait.until(ExpectedConditions.visibilityOf(filterBlock));
        actions.dragAndDropBy(sliderRight, -prices.get(1), 0).perform();
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }

    public FilterPage resetFilter() {
        clearAllFilters.click();
        wait.until(ExpectedConditions.invisibilityOf(spinner));
        return this;
    }


}
