package com.demo_qa.ui.test_data.models;

import lombok.Builder;

@Builder(toBuilder = true)
public record TextBoxUser(
        String userName,
        String userEmail,
        String currentAddress,
        String permanentAddress
) {
}
