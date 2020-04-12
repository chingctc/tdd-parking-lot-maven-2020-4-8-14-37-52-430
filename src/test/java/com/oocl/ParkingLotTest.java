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
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_park_car_to_parking_lot() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCarFromParkingLot);
    }

    @Test
    public void should_not_park_car_when_parking_lot_is_full() throws NotEnoughPositionException {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");
        ParkingLot parkingLot = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_return_exception_when_fetch_with_incorrect_ticket() throws UnrecognizedParkingTicketException, PleaseProvideTickerException, NotEnoughPositionException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(new ParkingTicket(parkingLot));
    }

    @Test
    public void should_return_exception_when_fetch_without_ticket() throws PleaseProvideTickerException, UnrecognizedParkingTicketException, NotEnoughPositionException {
        expectedException.expect(PleaseProvideTickerException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(null);
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
    public void should_smart_parking_boy_park_cars_to_parking_lot_with_more_space() throws NotEnoughPositionException, PleaseProvideTickerException, UnrecognizedParkingTicketException {
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

    @Test
    public void should_manager_specify_parking_boy_to_park_and_fetch_a_car() throws PleaseProvideTickerException, UnrecognizedParkingTicketException, NotEnoughPositionException {
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
    public void should_manager_park_a_car_to_a_parking_lot_with_enough_space() throws NotEnoughPositionException, UnrecognizedParkingTicketException, PleaseProvideTickerException {
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
    public void should_manager_query_message_once_the_ticket_is_wrong_when_specify_parkingboy_to_park_car() throws PleaseProvideTickerException, UnrecognizedParkingTicketException {
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
//        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    public void should_manager_query_error_message_for_used_ticket_when_specify_parkingboy_to_park_car() throws NotEnoughPositionException, UnrecognizedParkingTicketException, PleaseProvideTickerException {
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
    public void should_manager_query_message_once_ticket_is_not_provided_when_specify_parkingboy_to_park_car() throws UnrecognizedParkingTicketException, PleaseProvideTickerException {
        expectedException.expect(PleaseProvideTickerException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingBoy.fetch(null);
    }

    //Given manger,parking boy, parking lot with no position managed by parkingBoy
    //When manger tells parking boy to fetch car
    //Then manager output error msg "The parking lot is full."
    @Test
    public void should_manager_get_message_if_there_is_not_enough_position_when_specify_parkingboy_to_park_car() throws NotEnoughPositionException {
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