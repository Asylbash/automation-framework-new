package com.demo_qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.demo_qa.ui.components.CalendarComponent;

import java.nio.file.Paths;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final CalendarComponent calendarComponent = new CalendarComponent();

    private final SelenideElement form = $("#userForm");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderRadio = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesCheckbox = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement stateCityInput = $("#stateCity-wrapper");
    private final SelenideElement cityDropdown = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private static final String WAS_VALIDATED = "was-validated";
    private final SelenideElement modal = $(".modal-content");

    public PracticeFormPage openPracticeFormPage() {

        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage insertFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage insertLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage insertUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectGender(String value) {
        genderRadio.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage insertUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectDateOfBirth(String day, String month, String year) {
        calendarComponent.selectDate(day, month, year);
        return this;
    }

    public PracticeFormPage insertSubjects(List<String> values) {
        for (String value : values) {
            subjectsInput
                    .setValue(value)
                    .pressEnter();
        }
        return this;
    }

    public PracticeFormPage selectHobby(List<String> values) {
        for (String value : values) {
            hobbiesCheckbox
                    .$(byText(value))
                    .shouldBe(visible, enabled)
                    .scrollIntoView(true)
                    .click();
        }
        return this;
    }

    public PracticeFormPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public PracticeFormPage insertCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectState(String value) {
        stateDropdown
                .scrollIntoView(true)
                .click();
        stateCityInput
                .scrollTo()
                .$(byText(value))
                .shouldBe(visible, enabled)
                .scrollIntoView(true)
                .click();
        return this;
    }

    public PracticeFormPage selectCity(String value) {
        cityDropdown
                .scrollIntoView(true)
                .click();

        $(byText(value))
                .shouldBe(visible)
                .click();
        return this;
    }

    public PracticeFormPage clickSubmitForm() {
        submitButton
                .scrollTo()
                .click();
        return this;
    }

    public String getFileName(String filePath) {

        return Paths.get(filePath).getFileName().toString();

    }

    public PracticeFormPage shouldShowValidation() {

        form.shouldHave(cssClass(WAS_VALIDATED));

        return this;

    }

    private SelenideElement field(String id) {
        return $("#" + id);
    }

    public PracticeFormPage shouldBeInvalid(String fieldId) {

        Boolean invalid = executeJavaScript(
                "return document.getElementById(arguments[0]).matches(':invalid')",
                fieldId
        );

        if (!Boolean.TRUE.equals(invalid)) {
            throw new AssertionError(fieldId + " should be invalid");
        }
        return this;

    }

    public PracticeFormPage shouldNotOpenModal() {

        modal.shouldNot(appear);
        return this;

    }
}
