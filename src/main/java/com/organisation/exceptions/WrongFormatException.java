package com.organisation.exceptions;

import java.io.IOException;

public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public WrongFormatException(String errorMessage) {
        super(errorMessage);
    }
}
