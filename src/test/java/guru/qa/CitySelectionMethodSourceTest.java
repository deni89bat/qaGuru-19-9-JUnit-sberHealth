package guru.qa;

import com.codeborne.selenide.Condition;

import guru.qa.data.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;


public class CitySelectionMethodSourceTest extends TestBase {


    static Stream<Arguments> selectionCityTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        City.ALMETEVSK.getDesc(), List.of("Альметьевске", "alm")
                ),
                Arguments.of(
                        City.PENZA.getDesc(), List.of("Пензе", "pnz")
                ),
                Arguments.of(
                        City.TULA.getDesc(), List.of("Туле", "tula")
                )

        );
    }


    @DisplayName("A parameterized test with MethodSource")
    @ParameterizedTest(name = "Для города {0}: отображается 'Найдите проверенного врача в' {1} 'в URL'")
    @MethodSource("selectionCityTestDataProvider")
    @Tags({@Tag("CRITICAL"), @Tag("CITY")})
    void selectionCityTest(String city, List<String> inCity) {
        //Поиск и выбор нужного города
        $("[data-test-id=city-select-button]").click();
        $(".TheInputNext__input_2v1P").setValue(String.valueOf(city));
        $(".CitySelectModal__highlight_2YRu").click();
        //Проверка заголовка на странице города
        $("[data-test-id=main-page-search-form]").shouldHave(Condition.text("Найдите проверенного врача в " + inCity.get(0) + " и запишитесь на приём"));
        //Проверка URL на соотвествие с городом
        webdriver().shouldHave(url("https://" + inCity.get(1) + ".docdoc.ru/"));
    }
}
