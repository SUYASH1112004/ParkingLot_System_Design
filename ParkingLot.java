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

/////////////////////////////////////////////////////////////////////////////
// step 3 : Create Vehicle Factory CLass
// It is used to centralize the creation of vehicle objects
// 
/////////////////////////////////////////////////////////////////////////////

class VehicleFactory
{

    // Creates and returns the desired class object

    public static Vehicle createVehicle(VehicleType type, String number)
    {
        switch(type)
        {
            case BIKE:
                return new Bike(number);
            
            case CAR:
                return new Car(number);

            case TRUCK:
                return new Truck(number);

            default:
                throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
}


/////////////////////////////////////////////////////////////////////////////
// step 4 : Create ParkingSpot Hierarchy
// It is usedto create heirarchy of parking spots
/////////////////////////////////////////////////////////////////////////////

abstract class ParkingSpot
{
    //unique number for parking spot (Primary Key)
    private int spotNumber;

    //Type of parking spot
    private SpotType spotType;

    //Indicates whether spot is currently occupid or not 
    private boolean occupied;

    //store information about the vehicle
    private Vehicle vehicle;

    //Parameterized constructor
    public ParkingSpot(int spotNumber, SpotType spotType)
    {
        this.spotNumber = spotNumber;
        this.spotType = spotType;

        //Initialize with default values
        this.occupied = false;
        this.vehicle = null;

    }

    public int getSpotNumber()
    {
        return this.spotNumber;
    }

    public SpotType getSpotType()
    {
        return this.spotType;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }

    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    //The method is used to park the vehicle in the parking spot
    public void parkVehicle(Vehicle vehicle)
    {
        if(this.occupied == true)
        {
            throw new RuntimeException("Parking Spot is already occupied");
        }
        else
        {
            this.vehicle = vehicle;
            this.occupied = true;
        }
    }

    public Vehicle removeVehicle()
    {
        if(this.occupied == true)
        {
            Vehicle temp = null;

            this.vehicle = null;
            this.occupied = false;

            return temp;
        }
        else
        {
            throw new RuntimeException("Parking Spot is already empty");
        }
    }

    //This method decides whether we can park the vehicle in this spot or not
    public abstract boolean canFitVehicle(Vehicle vehicle);

    public void display()
    {
        System.out.println(" Spot : "+ spotNumber + " [" + spotType + "]");

        if(this.occupied == true)
        {
            System.out.println("Occupied by : "+ vehicle.getVehicleNumber());
        }
        else
        {
            System.out.println("Spot is available");
        }

    }
} // End of ParkingSpot class

class BikeSpot extends ParkingSpot
{

    public BikeSpot(int spotNumber)
    {
        super(spotNumber, SpotType.BIKE);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.BIKE)
        {
            return true;
        }
        else
        {
            return false;   
        }
    }

}

class CarSpot extends ParkingSpot
{

    public CarSpot(int spotNumber)
    {
        super(spotNumber, SpotType.CAR);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.CAR)
        {
            return true;
        }
        else
        {
            return false;   
        }
    }

}

class TruckSpot extends ParkingSpot
{

    public TruckSpot(int spotNumber)
    {
        super(spotNumber, SpotType.TRUCK);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.TRUCK)
        {
            return true;
        }
        else
        {
            return false;   
        }
    }

}

/////////////////////////////////////////////////////////////////////////////
// step 5 : Create ParkingObserver class
// It is automatically update display board when
// the parking availability changes
/////////////////////////////////////////////////////////////////////////////

interface ParkingObserver
{
    void update();
}

/////////////////////////////////////////////////////////////////////////////
// step 6 : ParkingFloor Class
// It is used to manage parking floors
/////////////////////////////////////////////////////////////////////////////

class ParkingFloor
{
    // unique floor number
    private int floorNumber;

    // Collection of all parking spots
    private List<ParkingSpot> parkingSpots;

    // collection of observers registered for the floor
    private List<ParkingObserver> observers;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.observers = new ArrayList<>(); 
    }

    public int getFloorNumber()
    {
        return this.floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot)
    {
        this.parkingSpots.add(spot);
    }

    public void addObserver(ParkingObserver observer)
    {
        this.observers.add(observer);
    }

    private void notifyObservers()
    {
        for(ParkingObserver observer : observers)
        {
            observer.update();
        }
    }

    // Method is going to search parking spot for specific type of vehicles
    public ParkingSpot findAvailableSpot(Vehicle vehicle)
    {
        for(ParkingSpot spot : parkingSpots)
        {
            if(!spot.isOccupied()&& spot.canFitVehicle(vehicle))
            {
                return spot;
            }

        }
        return null;
    }

    //called when new vehicle gets parked
    public void occupySpot(ParkingSpot spot, Vehicle vehicle)
    {
        // allocate spot for vehicle
        spot.parkVehicle(vehicle);

        // notify all observers about the availability of spots
        notifyObservers();

    }

    public void releaseSpot(ParkingSpot spot)
    {
        //release the already allocated spot
        spot.removeVehicle();

        // notify all obserevers about the availability of spots
        notifyObservers();
    }

    public int getAvailableCount(SpotType type)
    {
        int count = 0;

        for(ParkingSpot spot : parkingSpots)
        {
           if(spot.getSpotType() == type && !spot.isOccupied())
           {
               count++;
           }
        }
        return count;
    }

    // Display all parking spots on specific floor
    public void displayFloor()
    {
        System.out.println();

        System.out.println("Floor : "+ floorNumber);

        for(ParkingSpot spot : parkingSpots)
        {
            spot.display();
        }
    }

}

