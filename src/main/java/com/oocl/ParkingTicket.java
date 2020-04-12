package com.oocl;

public class ParkingTicket {
    public ParkingLot parkingLot;

    public ParkingTicket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

}
