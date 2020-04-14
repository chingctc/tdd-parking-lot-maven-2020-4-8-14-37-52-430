package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SuperSmartParkingBoyTest {
    
    @Test
    public void should_super_smart_parking_boy_park_cars_to_parking_lot_with_higher_space_ratio() throws NotEnoughPositionException, PleaseProvideTickerException, UnrecognizedParkingTicketException {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(secondParkingLot);
        parkingLots.add(firstParkingLot);
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car1 = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car1);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        Assert.assertEquals(car1, fetchedCar);
    }

}