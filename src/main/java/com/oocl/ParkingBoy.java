package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();

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
    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLot=getAvailableParkingLot();
        if(parkingLot==null){
            throw new NotEnoughPositionException();
        }else {
            ParkingTicket parkingTicket = parkingLot.park(car);
            if (parkingTicket == null) {
                throw new NotEnoughPositionException();
            }
            return parkingTicket;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) throws PleaseProvideTickerException, UnrecognizedParkingTicketException {
        if(parkingTicket==null){
            throw new PleaseProvideTickerException();
        }
        ParkingLot parkingLot=parkingTicket.getParkingLot();
        if(!parkingLots.contains(parkingLot)){;
            throw new UnrecognizedParkingTicketException();
        }
        Car fetchedCar=parkingLot.fetch(parkingTicket);
        if(fetchedCar==null) {
            return null;
        }
        return fetchedCar;
    }

}
