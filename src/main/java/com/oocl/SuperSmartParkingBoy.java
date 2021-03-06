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
            if (parkingLot.getAvailableParkingSpaceRate() > availableSpaceRate) {
                availParkingLot = parkingLot;
                availableSpaceRate = parkingLot.getAvailableParkingSpaceRate();
            }
        }
        return availParkingLot;
    }
}
