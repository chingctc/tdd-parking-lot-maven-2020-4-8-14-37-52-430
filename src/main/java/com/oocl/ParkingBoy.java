package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    public static final int NO_MORE_PARKING_SPACE = 0;
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingLot getAvailableParkingLot() {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableParkingSpace() > NO_MORE_PARKING_SPACE) {
                return parkingLot;
            }
        }
        return null;
    }

    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLot = getAvailableParkingLot();
        if (parkingLot == null) {
            throw new NotEnoughPositionException();
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        if (parkingTicket == null) {
            throw new NotEnoughPositionException();
        }
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) throws NoTicketProvidedException, UnrecognizedParkingTicketException {
        if (parkingTicket == null) {
            throw new NoTicketProvidedException();
        }
        ParkingLot parkingLot = parkingTicket.getParkingLot();
        if (!parkingLots.contains(parkingLot)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        return fetchedCar;
    }

}
