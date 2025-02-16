package belajarjava.application;

import belajarjava.data.CreateUserRequest;
import belajarjava.util.ValidationUtil;

public class ReflectionApp {
  public static void main(String[] args) {

    CreateUserRequest request = new CreateUserRequest();
    request.setUsername("Eko");
    request.setPassword("rahasia");

    ValidationUtil.validationReflection(request);
  }
}
