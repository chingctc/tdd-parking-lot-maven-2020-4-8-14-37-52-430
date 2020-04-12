package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {
    @Rule
//    public ExpectedException expectedException = "";
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void should_return_parking_ticket_when_parked_a_car() throws NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_return_car_when_ticket_is_used() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());

        Car car = parkingLot.fetch(parkingTicket);
        Assert.assertNull(car);
    }

    /** Replaced by new test case should_return_exception_when_parking_lot_is_full() */
    @Test
    public void should_not_park_car_when_parking_lot_is_full() throws NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_park_car_to_parking_lot() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCarFromParkingLot);
    }

    @Test
    public void should_return_exception_when_fetch_with_incorrect_ticket() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_exception_when_fetch_without_ticket() throws PleaseProvideTickerException, UnrecognizedParkingTicketException, NotEnoughPositionException {
        expectedException.expect(PleaseProvideTickerException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(null);
    }

    @Test
    public void should_return_exception_when_parking_lot_is_full() throws NotEnoughPositionException {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
    }

    @Test
    public void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full() throws NotEnoughPositionException, PleaseProvideTickerException, UnrecognizedParkingTicketException {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_park_a_car_to_a_parking_lot_with_more_empty_position() throws NotEnoughPositionException, PleaseProvideTickerException, UnrecognizedParkingTicketException {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_park_a_car_to_a_parking_lot_with_higher_available_position_rate() throws NotEnoughPositionException, PleaseProvideTickerException, UnrecognizedParkingTicketException {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }
}