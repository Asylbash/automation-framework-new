package ui_tests;

import com.codeborne.selenide.Configuration;
import com.demo_qa.common.ConfigReader;
import com.demo_qa.ui.components.SubmissionModal;
import com.demo_qa.ui.helper.JavaScriptHelper;
import com.demo_qa.ui.pages.PracticeFormPage;
import com.demo_qa.ui.pages.TextBoxPage;
import com.demo_qa.ui.test_data.factory.PracticeFormDataFactory;
import com.demo_qa.ui.test_data.factory.TextBoxDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected final PracticeFormPage practiceFormPage = new PracticeFormPage();
    protected final JavaScriptHelper javaScriptHelper = new JavaScriptHelper();
    protected final TextBoxPage textBoxPage = new TextBoxPage();
    protected final SubmissionModal submissionModal = new SubmissionModal();
    protected final PracticeFormDataFactory practiceFormRandomData = new PracticeFormDataFactory();
    protected final TextBoxDataFactory textBoxRandomData = new TextBoxDataFactory();

    @BeforeAll
    static void setup() {

        String target = ConfigReader.getProperty("target");

        if ("local".equalsIgnoreCase(target)) {
            Configuration.baseUrl =
                    Paths.get("src/test/resources/pages")
                            .toUri()
                            .toString();
        } else if ("web".equalsIgnoreCase(target)) {
            Configuration.baseUrl =
                    ConfigReader.getProperty("demoUrl");
        }

        Configuration.browser = "chrome";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
