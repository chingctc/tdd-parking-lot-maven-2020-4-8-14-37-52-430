package com.oocl;

import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
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

    @Override
    public ParkingLot getAvailableParkingLot(){
        ParkingLot availParkingLot = null;
        float availableSpaceRate = 0;
        for(ParkingLot parkingLot:parkingLots){
            if(parkingLot.getAvailableParkingPositionRate() > availableSpaceRate){
                availableSpaceRate = parkingLot.getAvailableParkingPositionRate();
                availParkingLot = parkingLot;
            }
        }
        return availParkingLot;
    }
}
