package com.oocl;

public class NoTicketProvidedException extends Exception {
    public NoTicketProvidedException(){
        super("Please provide your parking ticket.");
    }
}
