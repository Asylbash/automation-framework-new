package com.demo_qa.ui.test_data.factory;

import com.demo_qa.ui.test_data.models.TextBoxData;
import com.github.javafaker.Faker;

public class TextBoxDataFactory {

    private final Faker faker = new Faker();

    public TextBoxData createRandomTextBoxData() {
        return new TextBoxData(
                faker.name().fullName(),
                faker.internet().safeEmailAddress(),
                faker.address().streetAddress(),
                faker.address().fullAddress()
        );
    }

    public TextBoxData createInvalidEmailTextBoxData() {
        return new TextBoxData(
                faker.name().fullName(),
                "invalid-email-format",
                faker.address().streetAddress(),
                faker.address().fullAddress()
        );
    }
}
