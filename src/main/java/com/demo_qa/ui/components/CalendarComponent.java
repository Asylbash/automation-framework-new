package com.demo_qa.ui.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public CalendarComponent selectDate(String dayOfBirth, String monthOfBirth, String yearOfBirth) {

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(dayOfBirth)).click();

        return this;
    }
}
