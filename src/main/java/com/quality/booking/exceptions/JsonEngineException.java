package com.quality.booking.exceptions;

public class JsonEngineException extends Exception {
    public JsonEngineException() {
        super();
    }

    public JsonEngineException(String message) {
        super(message);
    }

    public JsonEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonEngineException(Throwable cause) {
        super(cause);
    }

    protected JsonEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
