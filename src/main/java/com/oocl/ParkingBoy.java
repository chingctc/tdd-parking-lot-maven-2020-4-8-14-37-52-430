package com.oocl;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException{
        return this.parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        return this.parkingLot.fetch(parkingTicket);
    }

}
