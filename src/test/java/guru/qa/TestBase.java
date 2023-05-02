package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;


import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeEach
    void openDocDoc() {
        Configuration.browserSize = "1920x1080";
        open("https://docdoc.ru/");
    }
}
