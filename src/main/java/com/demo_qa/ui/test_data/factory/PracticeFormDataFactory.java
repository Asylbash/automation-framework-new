package com.demo_qa.ui.test_data.factory;

import com.github.javafaker.Faker;
import com.demo_qa.ui.test_data.models.PracticeFormData;
import com.demo_qa.ui.utils.RandomUtils;

import java.util.List;

import static com.demo_qa.ui.utils.RandomUtils.randomPhoneNumber;

public class PracticeFormDataFactory {

    private final Faker faker = new Faker();

    private static final String[] STATES = {
            "NCR",
            "Uttar Pradesh",
            "Haryana",
            "Rajasthan"
    };

    private String randomCity(String state) {

        return switch (state) {

            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaisalmer");
            default -> throw new IllegalArgumentException("Unknown state: " + state);

        };

    }

    private String randomState() {

        return faker.options().option(STATES);

    }

    private List<String> getRandomSubjects() {
        String[] SUBJECTS = {"Maths", "Physics", "Chemistry", "Biology", "English"};
        return RandomUtils.randomMultipleSelection(SUBJECTS);
    }

    private List<String> getRandomHobbies() {
        String[] HOBBIES = {"Sports", "Reading", "Music"};
        return RandomUtils.randomMultipleSelection(HOBBIES);
    }

    private String getMonth() {
        String[] MONTHS = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return RandomUtils.randomElement(MONTHS);
    }

    private String getGender() {
        String[] GENDERS = {"Male", "Female", "Other"};
        return RandomUtils.randomElement(GENDERS);
    }

    public PracticeFormData createRandomData() {

        String state = randomState();

        return new PracticeFormData(

                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                getGender(),
                randomPhoneNumber(10),
                String.valueOf(faker.number().numberBetween(1, 28)),
                getMonth(),
                String.valueOf(faker.number().numberBetween(1980, 2005)),
                getRandomSubjects(),
                getRandomHobbies(),
                faker.address().streetAddress(),
                state,
                randomCity(state),
                "images/photo.png"

        );

    }
}
