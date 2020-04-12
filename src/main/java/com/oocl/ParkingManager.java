package com.oocl;

import java.util.ArrayList;

public class ParkingManager extends ParkingBoy {

    private final ArrayList<ParkingBoy> parkingBoys;

    public ParkingManager(ArrayList<ParkingLot> parkingLots, ArrayList<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket parkCarByParkingBoy(ParkingBoy parkingBoy, Car car) throws NotEnoughPositionException {
        return parkingBoy.park(car);
    }

    public Car fetchCarByParkingBoy(ParkingBoy parkingBoy, ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        return parkingBoy.fetch(parkingTicket);
    }
}
