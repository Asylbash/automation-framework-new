package com.demo_qa.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SubmissionModal {

    private final SelenideElement title = $("#example-modal-sizes-title-lg");

    private SelenideElement value(String label) {

        return $(By.xpath(
                "//td[normalize-space()='" + label + "']/following-sibling::td"));
    }

    public SubmissionModal shouldHaveValue(

            String label,
            String expected) {
        value(label)
                .shouldHave(exactText(expected));
        return this;
    }

    public SubmissionModal shouldHaveTitle(String expected) {
        title.shouldHave(exactText(expected));
        return this;
    }

    public SubmissionModal shouldBeOpened() {

        title.shouldBe(visible);
        return this;
    }

    public SubmissionModal shouldNotBeOpened() {
        title.shouldNotBe(visible);
        return this;
    }

    //assertEquals(expected, actual);

//    public String getActualValue(String label) {
//
//        return $(By.xpath("//td[normalize-space()='" + label +
//                "']/following-sibling::td"))
//                .getText();
//    }
//
//    public String getActualTitle() {
//        Selenide.Wait().until(webDriver -> title.isDisplayed());
//        return title.getText();
//    }

}
