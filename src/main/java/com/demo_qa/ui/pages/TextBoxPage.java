package com.demo_qa.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    private final SelenideElement userNameInput = $("[id = userName]");
    private final SelenideElement userEmailInput = $("[id = userEmail]");
    private final SelenideElement userCurrentAddressInput = $("[id = currentAddress]");
    private final SelenideElement userPermanentAddressInput = $("[id = permanentAddress]");
    private final SelenideElement submitButton = $("[id = submit]");
    private final SelenideElement outputResults = $("#output");
    private static final String FIELD_ERROR = "field-error";

    public TextBoxPage openTextBoxPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxPage typeCurrentAddress(String value) {
        userCurrentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage typePermanentAddress(String value) {
        userPermanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkField(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }

    public TextBoxPage shouldHighlightEmailAsInvalid() {
        userEmailInput.shouldHave(cssClass(FIELD_ERROR));
        return this;
    }

    public TextBoxPage shouldNotShowOutput() {
        outputResults.shouldNotBe(visible);
        return this;
    }
}
