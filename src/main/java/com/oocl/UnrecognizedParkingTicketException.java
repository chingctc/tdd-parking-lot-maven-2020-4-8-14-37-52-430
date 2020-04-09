package com.oocl;

public class UnrecognizedParkingTicketException extends Throwable {
    public UnrecognizedParkingTicketException(){
        super("Unrecognized parking ticket.");
    }
}
