package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class ParkingManagerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_manager_specify_parking_boy_to_park_and_fetch_a_car() throws NoProvidedTicketException, UnrecognizedParkingTicketException, NotEnoughPositionException {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(new ArrayList<ParkingLot>(),parkingBoys);

        ParkingTicket parkingTicket = parkingManager.parkCarByParkingBoy(parkingBoy,car);
        Car fetchedCar = parkingManager.fetchCarByParkingBoy(parkingBoy,parkingTicket);

        Assert.assertEquals(car,fetchedCar);
    }

    @Test
    public void should_manager_park_a_car_to_a_parking_lot_with_enough_space() throws NotEnoughPositionException, UnrecognizedParkingTicketException, NoProvidedTicketException {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingManager parkingManager = new ParkingManager(parkingLots,new ArrayList<ParkingBoy>());

        ParkingTicket parkingTicket = parkingManager.park(car);
        Car fetchedCar = parkingManager.fetch(parkingTicket);

        Assert.assertEquals(car,fetchedCar);
    }

    @Test
    public void should_manager_query_message_once_the_ticket_is_wrong_when_specify_parking_boy_to_park_car() throws NoProvidedTicketException, UnrecognizedParkingTicketException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(new ArrayList<ParkingLot>(),parkingBoys);

        parkingManager.fetchCarByParkingBoy(parkingBoy,parkingTicket);
    }

    @Test
    public void should_manager_query_error_message_for_used_ticket_when_specify_parking_boy_to_park_car() throws NotEnoughPositionException, UnrecognizedParkingTicketException, NoProvidedTicketException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);
    }

    @Test
    public void should_manager_query_message_once_ticket_is_not_provided_when_specify_parking_boy_to_park_car() throws UnrecognizedParkingTicketException, NoProvidedTicketException {
        expectedException.expect(NoProvidedTicketException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingBoy.fetch(null);
    }

    @Test
    public void should_manager_get_message_if_there_is_not_enough_position_when_specify_parking_boy_to_park_car() throws NotEnoughPositionException {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position");
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
    }
}