package com.demo_qa.ui.components;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SubmissionModal {
    public String getValue(String label) {

        return $(By.xpath("//td[normalize-space()='" + label +
                "']/following-sibling::td"))
                .getText();
    }

    public String getTitle() {
        Selenide.Wait().until(webDriver -> $("#example-modal-sizes-title-lg").isDisplayed());

        return $("#example-modal-sizes-title-lg").getText();
    }
}
