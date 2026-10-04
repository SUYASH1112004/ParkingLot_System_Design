/*

    ParkingLot Automation System

    step 1 : Create required enums
    step 2 : Vehicle heirarchy creation
    step 3 : Vehicle Factory creation (Factory Design Pattern)
    step 4 : ParkingSpot Heirarchy 
    step 5 : ParkingObserver class
    step 6 : ParkingFloor class
    step 7 : ParkingDisplayBoard (Observer Design Pattern)
    step 8 : ParkingStrategy class (Strategy Design Pattern)
    step 9 : PricingStrategy class (Strategy Design Pattern)
    step 10 : PaymentStrategy class 
    step 11 : ParkingTicket class
    step 12 : EntryGate class
    step 13 : ExitGate class
    step 14 : ParkingLot class (singleton Design Pattern)
    step 15 : Main class (controller class)

*/

import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;

/////////////////////////////////////////////////////////////////////////////
// step 1 : Create Enums
// It is used to create fixed constants which are required 
// throughout the project
/////////////////////////////////////////////////////////////////////////////

// Representing different types of vehicles supported by project
enum VehicleType 
{
    CAR, BIKE, TRUCK
}

// Representing different types of parking spots supported by project
enum SpotType
{
    CAR, BIKE, TRUCK
}

//Representing current state of parking ticket
enum TicketStatus
{
    ACTIVE, CLOSED
}

/////////////////////////////////////////////////////////////////////////////
// step 2 : Vehicle Heirarchy Creation
// It is used to create multiple types of classes 
// which represents types of vehicles supported by project
/////////////////////////////////////////////////////////////////////////////

// Class which represents a generic vehicle type
abstract class Vehicle
{
    private String VehicleNumber;
    private VehicleType vehicleType;

    public Vehicle(String VehicleNumber, VehicleType vehicleType)
    {
        this.VehicleNumber = VehicleNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType()
    {
        return this.vehicleType;
    }

    public String getVehicleNumber()
    {
        return this.VehicleNumber;
    }

    public abstract void display();

}

// class which represents vehicle type as bike
class Bike extends Vehicle
{
    public Bike(String VehicleNumber)
    {
        super(VehicleNumber,VehicleType.BIKE);
    }

    @Override 
    public void display()
    {
        System.out.println("Bike : "+ getVehicleNumber());
    }

}

// Class which represents vehicle type as Car
class Car extends Vehicle
{
    public Car(String VehicleNumber)
    {
        super(VehicleNumber,VehicleType.CAR);
    }

    @Override
    public void display()
    {
        System.out.println("Car : "+ getVehicleNumber());
    }
}

// Class which represents vehicle type as Truck
class Truck extends Vehicle
{
    public Truck(String VehicleNumber)
    {
        super(VehicleNumber,VehicleType.TRUCK);
    }

    @Override
    public void display()
    {
        System.out.println("Truck : "+ getVehicleNumber());
    }
}


