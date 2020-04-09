package com.oocl;

public class NotEnoughPositionException extends Exception {
    public NotEnoughPositionException(){
        super("Not enough position.");
    }
}
