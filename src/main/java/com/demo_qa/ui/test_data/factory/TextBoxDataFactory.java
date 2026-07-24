package com.demo_qa.ui.test_data.factory;

import com.demo_qa.ui.test_data.models.TextBoxUser;
import com.github.javafaker.Faker;

public class TextBoxDataFactory {

    private final Faker faker = new Faker();

    public TextBoxUser createRandomTextBoxData() {
        return TextBoxUser.builder()
                .userName(faker.name().fullName())
                .userEmail(faker.internet().emailAddress())
                .currentAddress(faker.address().streetAddress())
                .permanentAddress(faker.address().fullAddress())
                .build();
    }
}
