package edu.aitu.oop3.components.reservation;

public class NoFreeSpotsException extends RuntimeException {
    public NoFreeSpotsException(String message) {
        super(message);
    }
}
