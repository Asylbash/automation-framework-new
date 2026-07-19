package com.demo_qa.ui.utils;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    Random random = new Random();

    public static String generateRandomString(int length) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < length; i++) {
            randomString.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
        }
        return randomString.toString();
    }

    public static String getRandomEmail() {
        // return generateRandomString(10) + '@' + generateRandomString(5) + ".com";
        return format("%s@%s.com", generateRandomString(10), generateRandomString(5));
    }

    private <T> T getRandomItem(T[] array) {
        return array[random.nextInt(array.length)];
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public static String randomPhoneNumber(int length) {
        StringBuilder phone = new StringBuilder();

        for (int i = 0; i < length; i++) {
            phone.append(getRandomInt(0, 9));
        }

        return phone.toString();
    }

//    private static String getRandomItemFromStringArrayCustom(String[] array) {
//        int randomIndex = getRandomInt(0, array.length - 1);
//        return array[randomIndex];
//    }

    public static <T> T randomElement(T[] array) {
        int randomIndex = getRandomInt(0, array.length - 1);
        return array[randomIndex];
    }

    public static <T> List<T> randomMultipleSelection(T[] source) {
        int count = getRandomInt(1, source.length);

        List<T> selection = new ArrayList<>(Arrays.asList(source));
        Collections.shuffle(selection);

        return selection.subList(0, count);
    }
}
