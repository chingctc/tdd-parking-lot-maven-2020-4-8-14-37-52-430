package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SmartParkingBoyTest {

    @Test
    public void should_smart_parking_boy_park_cars_to_parking_lot_with_more_space() throws NotEnoughPositionException, NoTicketProvidedException, UnrecognizedParkingTicketException {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

}