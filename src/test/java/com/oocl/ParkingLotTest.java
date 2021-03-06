package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class ParkingLotTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_return_parking_ticket_when_parked_a_car() throws NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket() throws UnrecognizedParkingTicketException, NoTicketProvidedException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_park_car_when_parking_lot_is_full() throws NotEnoughPositionException {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_return_exception_when_fetch_with_incorrect_ticket() throws UnrecognizedParkingTicketException, NoTicketProvidedException, NotEnoughPositionException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(new ParkingTicket(parkingLot));
    }

    @Test
    public void should_return_exception_when_fetch_without_ticket() throws NoTicketProvidedException, UnrecognizedParkingTicketException, NotEnoughPositionException {
        expectedException.expect(NoTicketProvidedException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(null);
    }

    @Test
    public void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full() throws NotEnoughPositionException, NoTicketProvidedException, UnrecognizedParkingTicketException {
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
}