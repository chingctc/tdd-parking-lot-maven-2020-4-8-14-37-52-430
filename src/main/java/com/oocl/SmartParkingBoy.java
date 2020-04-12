package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLot = getAvailableParkingLot();
        if(parkingLot == null){
            return null;
        }else {
            ParkingTicket parkingTicket = parkingLot.park(car);
            if (parkingTicket == null) {
                return null;
            }
            return parkingTicket;
        }
    }

    public ParkingLot getAvailableParkingLot(){
        ParkingLot availParkingLot = null;
        int availableSpace = 0;
        for(ParkingLot parkingLot:parkingLots){
            if(parkingLot.getAvailableParkingPosition() > availableSpace){
                availableSpace = parkingLot.getAvailableParkingPosition();
                availParkingLot = parkingLot;
            }
        }
        return availParkingLot;
    }
}
