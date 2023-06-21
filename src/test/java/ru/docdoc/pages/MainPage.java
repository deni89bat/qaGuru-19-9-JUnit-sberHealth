package ru.docdoc.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class MainPage {

  @Step("Открыть главную страницу")

  public MainPage openPage() {
    open("");
    return this;

  }

  @Step("Проверить отображение баннера")
  public MainPage checkCookieBannerIsVisible() {
    cookieBanner.shouldBe(Condition.visible);
    cookieBanner.shouldHave(Condition.text(
        "Мы используем файлы cookies, чтобы делать сайт удобнее. Оставаясь на сайте, вы соглашаетесь с политикой их использования."));
    cookieBannerLink.shouldHave(Condition.attribute("href", "https://docdoc.ru/cookies"));
    return this;

  }

  @Step("Переход по ссылке с политикой ")
  public MainPage ClickLinkCookieBanner() {
    String currentWindowHandle = WebDriverRunner.getWebDriver()
        .getWindowHandle(); // сохраняем идентификатор текущего окна перед кликом по ссылке
    cookieBannerLink.click();
    switchTo().window(1);
    Assertions.assertTrue(WebDriverRunner.url().contains("https://docdoc.ru/cookies"));
    closeWindow();
    switchTo().window(currentWindowHandle);
    return this;
  }

  @Step("Закрыть баннер")
  public MainPage closeCookieBanner() {
    closeCookieButton.click();
    cookieBanner.shouldNotBe(Condition.visible); // Проверяем, что баннер больше не отображается
    return this;
  }

  @Step("Проверить отображение формы поиска")
  public MainPage checkSearchForm() {
    searchForm.should(Condition.exist);
    searchForm.shouldBe(Condition.visible);

    return this;
  }

  private SelenideElement cookieBanner = $("[data-test-id=cookies-banner]"),
      closeCookieButton = $("[data-test-id=cookies-banner-button]"),
      cookieBannerLink = cookieBanner.find("a"), // Находим ссылку внутри баннера;
      searchForm = $("[data-test-id=search_form]");



}

