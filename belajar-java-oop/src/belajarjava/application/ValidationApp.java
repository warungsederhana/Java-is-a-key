package belajarjava.application;

import belajarjava.error.ValidationException;
import belajarjava.record.LoginRequest;
import belajarjava.util.ValidationUtil;

public class ValidationApp {
    public static void main(String[] args) {

        LoginRequest loginRequest = new LoginRequest("eko", "rahasia");

        try {
            ValidationUtil.validate(loginRequest);
            System.out.println("Data valid");
        } catch (ValidationException | NullPointerException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } finally {
            System.out.println("Selalu di eksekusi");
        }

        LoginRequest loginRequest2 = new LoginRequest(null, null);
        ValidationUtil.validateRuntime(loginRequest2);
        System.out.println("Sukses");
    }
}
