package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private final int CAPACITY = 10;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        setCapacity(10);
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        boolean isFull = isFull();
        if(isFull){
            throw new NotEnoughPositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        if(parkingTicket == null) {
            throw new PleaseProvideTickerException();
        }
        if(!this.parkingTicketCarMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        Car car = this.parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

    public int getAvailableParkingPosition() {
        return capacity - parkingTicketCarMap.size();
    }

    public float getAvailableParkingPositionRate() {
        return (float)getAvailableParkingPosition()/capacity;
    }

    public boolean isFull() {
        return this.getCapacity() <= parkingTicketCarMap.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = CAPACITY;
    }

    private int getCapacity() {
        return capacity;
    }

}
