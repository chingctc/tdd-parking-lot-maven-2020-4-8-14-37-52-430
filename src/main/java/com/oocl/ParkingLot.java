package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int CAPACITY = 10;
    private final int capacity;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingLot() {
        this(CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return capacity - parkingTicketCarMap.size();
    }

    public float getAvailableParkingPositionRate() {
        return (float)getAvailableParkingPosition()/capacity;
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        if(getAvailableParkingPosition() == 0){
            throw new NotEnoughPositionException();
        }else {
            ParkingTicket parkingTicket= new ParkingTicket(this);
            parkingTicketCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        if(parkingTicket == null) {
            throw new PleaseProvideTickerException();
        }
        if(!this.parkingTicketCarMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        Car fetchedCar = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }
}
