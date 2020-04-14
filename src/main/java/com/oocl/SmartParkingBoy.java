package com.oocl;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        ParkingLot availParkingLot = null;
        int availableSpace = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableParkingSpace() > availableSpace) {
                availableSpace = parkingLot.getAvailableParkingSpace();
                availParkingLot = parkingLot;
            }
        }
        return availParkingLot;
    }
}
