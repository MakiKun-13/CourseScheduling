package com.example.geektrust.exceptions;

public class CancellationRejectedException extends Exception {
    private String registrationId;

    public CancellationRejectedException(String registrationId) {
        this.registrationId = registrationId;
    }

    public String toString() {
        return registrationId+" CANCEL_REJECTED";
    }
}
