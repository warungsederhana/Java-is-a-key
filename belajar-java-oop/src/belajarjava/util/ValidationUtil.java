package belajarjava.util;

import belajarjava.annotation.NotBlank;
import belajarjava.error.BlankException;
import belajarjava.error.ValidationException;
import belajarjava.record.LoginRequest;

import java.lang.reflect.Field;

public class ValidationUtil {

    public static void validate(LoginRequest loginRequest) throws ValidationException, NullPointerException {
        if (loginRequest.username() == null) {
            throw new NullPointerException("Username tidak boleh null");
        } else if (loginRequest.username().isBlank()) {
            throw new ValidationException("Username tidak boleh kosong");
        } else if (loginRequest.password() == null) {
            throw new NullPointerException("Password tidak boleh null");
        } else if (loginRequest.password().isBlank()) {
            throw new ValidationException("Password tidak boleh kosong");
        }
    }

    public static void validateRuntime(LoginRequest loginRequest) {
        if (loginRequest.username() == null) {
            throw new NullPointerException("Username tidak boleh null");
        } else if (loginRequest.username().isBlank()) {
            throw new BlankException("Username tidak boleh kosong");
        } else if (loginRequest.password() == null) {
            throw new NullPointerException("Password tidak boleh null");
        } else if (loginRequest.password().isBlank()) {
            throw new BlankException("Password tidak boleh kosong");
        }
    }

    public static void validationReflection(Object object) {
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for(var field: fields) {
            field.setAccessible(true);

            if(field.getAnnotation(NotBlank.class) != null) {
                // validate
                try {
                    String value = (String) field.get(object);

                    if (value == null || value.isBlank()) {
                        throw new BlankException("Field " + field.getName() + " tidak boleh kosong");
                    }
                } catch (IllegalAccessException exception) {
                    System.out.println("Tidak bisa mengakses field " + field.getName());
                }
            }
        }
    }
}
