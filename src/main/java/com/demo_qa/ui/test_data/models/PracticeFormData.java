package com.demo_qa.ui.test_data.models;

import java.util.List;

public record PracticeFormData(
        String firstName,
        String lastName,
        String email,
        String gender,
        String mobile,
        String day,
        String month,
        String year,
        List<String> subjects,
        List<String> hobbies,
        String address,
        String state,
        String city,
        String selectPic
) {
}
