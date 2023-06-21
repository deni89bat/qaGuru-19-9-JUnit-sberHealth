package ru.docdoc.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$;

public class SearchForm {
    private SelenideElement searchForm = $("[data-test-id=search_form]"),
            searchInput = $("[data-test-id=search_input]"),
            searchInputActive = $("input[placeholder='Врач, клиника, болезнь, услуга']"),
            searchSuggestions = $(".SearchSuggestion__text_2_9_"),
            searchGeoInput = $("[data-test-id=search_geo_input"),
            searchGeoInputActive = $("input[placeholder='Метро, район, округ, город МО']"),
            searchSuggestionsGeo = $(".SearchLocationSuggestion__text_2vgG"),
            searchButton = $("[data-test-id='search_button']");






    @Step("Отображается форма поиска")
    public SearchForm checkSearchForm() {
        searchForm.shouldBe(Condition.visible);
        return this;
    }

    @Step("Заполнить поле врач, клиника, болезнь, услуга. В результатах отображаются запрошенные данные. ")
    public SearchForm setValueSearchForm() {
        searchInput.click();
        searchInputActive.sendKeys("эпилептолог детский");
        searchSuggestions.shouldHave(Condition.text("Эпилептолог детский")).click();
        return this;
    }

    @Step("Заполнить поле метро,район,округ. В результатах отображаются запрошенные данные. ")
    public SearchForm setGeoValueSearchForm() {
        searchGeoInput.click();
        searchGeoInputActive.sendKeys("Электроу");
        searchSuggestionsGeo.shouldHave(Condition.text("Электроугли")).click();
        return this;
    }
    @Step("Нажать кнопку Найти.")
    public SearchForm clickSearchButton() {
        searchButton.click();
        Assertions.assertTrue(WebDriverRunner.url().contains("https://docdoc.ru/cookies"));
        return this;
    }
}


// v-autocomplete-input--active search_geo_input  clickSearchButton