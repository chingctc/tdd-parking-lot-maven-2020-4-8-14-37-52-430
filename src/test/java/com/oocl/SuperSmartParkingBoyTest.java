package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_super_smart_parking_boy_park_cars_to_parking_lot_with_higher_space_ratio() throws NotEnoughPositionException, NoTicketProvidedException, UnrecognizedParkingTicketException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        ParkingTicket parkingTicket3 = parkingBoy.park(car3);
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);
        Car fetchedCar3 = parkingBoy.fetch(parkingTicket3);

        Assert.assertEquals(parkingLot1, parkingTicket1.getParkingLot());
        Assert.assertEquals(parkingLot2, parkingTicket2.getParkingLot());
        Assert.assertEquals(parkingLot1, parkingTicket3.getParkingLot());
        Assert.assertEquals(car1, fetchedCar1);
        Assert.assertEquals(car2, fetchedCar2);
        Assert.assertEquals(car3, fetchedCar3);
    }

}