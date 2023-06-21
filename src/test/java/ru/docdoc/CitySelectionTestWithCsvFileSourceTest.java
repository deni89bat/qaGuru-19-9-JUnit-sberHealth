package ru.docdoc;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CitySelectionTestWithCsvFileSourceTest extends TestBase {
    @CsvFileSource(resources = "/cities.csv")
    @ParameterizedTest(name = "Для города {0} осуществляется переход на страницу https://{1}.docdoc.ru/")
    @DisplayName("Тест на соотвествие абревиатуры в url - городу")
    @Tags({
            @Tag("CRITICAL"),
            @Tag("CITY")
    })
    void selectionCityTest(String city, String abbreviation) {
        $("[data-test-id=city-select-button]").click();
        $(".TheInputNext__input_2v1P").setValue(city);
        $(".CitySelectModal__highlight_2YRu").click();
        webdriver().shouldHave(url("https://" + abbreviation + ".docdoc.ru/"));
    }
}
