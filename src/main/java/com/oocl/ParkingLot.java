package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int CAPACITY = 10;
    public static final int NO_MORE_PARKING_SPACE = 0;
    private final int capacity;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingLot() {
        this(CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingSpace() {
        return capacity - parkingTicketCarMap.size();
    }

    public float getAvailableParkingSpaceRate() {
        return (float) getAvailableParkingSpace() / capacity;
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        if (getAvailableParkingSpace() == NO_MORE_PARKING_SPACE) {
            throw new NotEnoughPositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket(this);
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NoTicketProvidedException {
        if (parkingTicket == null) {
            throw new NoTicketProvidedException();
        }
        if (!this.parkingTicketCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car fetchedCar = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }
}
