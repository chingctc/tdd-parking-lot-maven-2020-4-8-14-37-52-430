package com.oocl;

import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        ParkingLot availParkingLot = null;
        float availableSpaceRate = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableParkingPositionRate() > availableSpaceRate) {
                availParkingLot = parkingLot;
                availableSpaceRate = parkingLot.getAvailableParkingPositionRate();
            }
        }
        return availParkingLot;
    }
}
