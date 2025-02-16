package belajarjava.error;

import belajarjava.record.LoginRequest;

public class BlankException extends RuntimeException {

    public BlankException(String message) {
        super(message);
    }
}
