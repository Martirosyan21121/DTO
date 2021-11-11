package com.company.validator;

import com.company.annotations.Adulthood;
import com.company.annotations.Email;
import com.company.annotations.Lenght;
import com.company.annotations.Min;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    public static void adulthoodValidator(Object adulthood) throws IllegalAccessException {
        Field[] fields = adulthood.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Adulthood.class)) {
                LocalDate localDate = (LocalDate) field.get(adulthood);
                if (LocalDate.now().getYear() - localDate.getYear() > 18) {
                    System.out.println("you are registered");
                } else {
                    System.out.println("you are not 18 years old you can't registered");
                }
            }
        }
    }

    private static void lenght(Object lenght) throws IllegalAccessException {
        Field[] fields = lenght.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Lenght.class)) {
                String name = (String) field.get(lenght);
                Lenght lenght1 = field.getAnnotation(Lenght.class);
                if (lenght1 == null) {
                    System.out.println("you cant read your name");
                } else if (name.length() < lenght1.min()) {
                    System.out.println("you can't registered: min 2 symbol ");
                } else if (name.length() > lenght1.max()) {
                    System.out.println("you can't registered: max 30 symbol");
                } else {
                    System.out.println("you are registered");
                }
            }
        }
    }

    private static void email(Object emile) throws IllegalAccessException {
        Field[] fields = emile.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Email.class)) {
                String email = (String) field.get(emile);
                Pattern pattern = Pattern.compile(Email.regex);
                if (pattern.matcher(email).matches()) {
                    System.out.println("You are registered");
                }
                if (!pattern.matcher(email).matches()) {
                    System.out.println("Wrong command please user '@' symbol");
                }

            }

        }

    }


    public static void min(Object min) throws IllegalAccessException {
        Field[] field = min.getClass().getFields();
        for (Field field1 : field) {
            field1.setAccessible(true);
            if (field1.isAnnotationPresent(Min.class)) {
                int min2 = (int) field1.get(min);
                Min minann = field1.getAnnotation(Min.class);
                if (min2 < minann.value()) {
                    System.out.println("you aren't registered");
                } else {
                    System.out.println("you are registered");
                }
            }
        }
    }

    public static void max(Object max) throws IllegalAccessException {
        Field[] field = max.getClass().getFields();
        for (Field field1 : field) {
            field1.setAccessible(true);
            if (field1.isAnnotationPresent(Min.class)) {
                int max2 = (int) field1.get(max);
                Min maxann = field1.getAnnotation(Min.class);
                if (max2 > maxann.value()) {
                    System.out.println("you aren't registered");
                } else {
                    System.out.println("you are registered");
                }
            }
        }
    }
}