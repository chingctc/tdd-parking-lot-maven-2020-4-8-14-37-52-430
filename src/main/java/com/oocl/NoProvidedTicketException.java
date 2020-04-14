package com.oocl;

public class NoProvidedTicketException extends Exception {
    public NoProvidedTicketException(){
        super("Please provide your parking ticket.");
    }
}
