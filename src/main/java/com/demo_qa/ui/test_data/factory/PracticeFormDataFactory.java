package com.demo_qa.ui.test_data.factory;

import com.github.javafaker.Faker;
import com.demo_qa.ui.test_data.models.PracticeFormUser;
import com.demo_qa.ui.utils.RandomUtils;

import java.util.List;

import static com.demo_qa.ui.utils.RandomUtils.randomPhoneNumber;

public class PracticeFormDataFactory {

    private final Faker faker = new Faker();

    private String randomCity(String state) {

        return switch (state) {

            case "NCR" -> RandomUtils.randomElement(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh" -> RandomUtils.randomElement(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana" -> RandomUtils.randomElement(new String[]{"Karnal", "Panipat"});
            case "Rajasthan" -> RandomUtils.randomElement(new String[]{"Jaipur", "Jaisalmer"});
            default -> throw new IllegalArgumentException("Unknown state: " + state);

        };

    }

    private String randomState() {
        String[] STATES = {
                "NCR",
                "Uttar Pradesh",
                "Haryana",
                "Rajasthan"
        };

        return RandomUtils.randomElement(STATES);

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

    public PracticeFormUser createNewUser() {
        String state = randomState();

        return PracticeFormUser.builder()

                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .gender(getGender())
                .mobile(randomPhoneNumber(10))
                .day(String.valueOf(faker.number().numberBetween(1, 28)))
                .month(getMonth())
                .year(String.valueOf(faker.number().numberBetween(1980, 2005)))
                .subjects(getRandomSubjects())
                .hobbies(getRandomHobbies())
                .address(faker.address().streetAddress())
                .state(state)
                .city(randomCity(state))
                .selectPic("images/photo.png")
                .build();
    }
}
