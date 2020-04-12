package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    protected final ArrayList<ParkingLot> parkingLots;
    private ParkingLot parkingLot;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingLot getAvailableParkingLot(){
        for(ParkingLot parkingLot:parkingLots){
            if(parkingLot.getAvailableParkingPosition()>0){
                return parkingLot;
            }
        }
        return null;
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException{
        ParkingLot selectedParkingLot = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
        return selectedParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        return this.parkingLots.get(0).fetch(parkingTicket);
    }

}
