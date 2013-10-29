//Anthony Mace  CSC205AB
//This program ...

import java.util.*;

public class program5 {

    public static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        int railCond = 0;
        int choice = 0;
        char quit = '0';
        Container container = null;
        Contents contents = null;
        FreightCar car = null;
        Train train = null;
        Contents[] contentsArray = new Contents[5];
        contentsArray[0] = new Contents("Oil", 55, 7.85);
        contentsArray[1] = new Contents("Coal", 69, 50);
        contentsArray[2] = new Contents("Soybeans", 47, 2.72);
        contentsArray[3] = new Contents("Lineseed, meal", 32, 0.07);
        contentsArray[4] = new Contents("Oats", 27, 1.30);;
        printIntro();
        System.out.println();
        railCond = getRailConditions();
        System.out.println();
        train = constructTrain();
            do {
                System.out.println();
                choice = trainBuildingMenu();
                if (choice == 1) {
                    System.out.println();
                    container = constructContainer();
                    System.out.println(container.computeExteriorVolume());
                    System.out.println(container.computeInteriorVolume());
                    System.out.println(container);
                    System.out.println();
                    contents = constructContents(contentsArray);
                    System.out.println(contents);
                    System.out.println();
                    car = constructFreightCar(contents, container);
                    System.out.println(car);
                    train.addFreightCar(car);
                } else if (choice == 2) {
                    System.out.println();
                    System.out.println(train);
                } else if (choice == 3) {
                    System.out.println();
                    train.displayBriefSummary(railCond);
                } else if (choice == 4) {
                    System.out.println();
                    train.trainWeightAndValue();
                } else if (choice == 5) {
                    System.out.println();
                    train = constructTrain();
                }
            } while(choice >= 1 && choice <= 5);
        System.out.println();
        System.out.println("You have quit the program.");
    }

    public static void printIntro() {
        System.out.println("This program is designed to help you build a train!");
        System.out.println("The program will help you decide which cars to include \n" +
                           "in each trip as well as the details of the train such as \n" +
                           "the engine and aspects of each car.");
    }

    public static int getRailConditions() {
        int maxLoad = 0;
        System.out.println("Please enter the maximum acceptable load of a single car");
        System.out.println("that may pass over the Conemaugh River bridge safely!");
        System.out.print("Maximum acceptable load for a car (lbs.): ");
        maxLoad = console.nextInt();
        System.out.println();
        System.out.println("Let's build a train!");
        return maxLoad;
    }

    public static Engine constructAnEngine() {
        Engine newEngine = null;
        String ownerName = "";
        int idNum = 0;
        double baseFrameWeight = 0.0;
        double pullingCapacity = 0.0;
        System.out.print("Please enter the name of the owner of the Engine: ");
        ownerName = console.next();
        System.out.println();
        System.out.println("Please enter the ID number of the Engine.");
        System.out.print("ID #: ");
        idNum = console.nextInt();
        System.out.println();
        System.out.print("Please enter the weight of the base frame (lbs.): ");
        baseFrameWeight = console.nextDouble();
        System.out.println();
        System.out.println("Please enter the pulling capacity of the engine.");
        System.out.print("Pulling Capacity (lbs.): ");
        pullingCapacity = console.nextDouble();
        System.out.println();
        newEngine = new Engine(ownerName, idNum, baseFrameWeight, pullingCapacity);
        return newEngine;
    }

    public static Train constructTrain(){
        Engine engine = constructAnEngine();
        String engineerName = "";
        Train train = null;
        System.out.println("Enter the engineer name's");
        System.out.print("Engineer name: ");
        engineerName = console.next();
        System.out.println();
        train = new Train(engineerName, engine);
        return train;
    }

    public static Container constructContainer() {
        Container container = null;
        double wallThickness = 0.0;
        double wallDensity = 0.0;
        double height = 0.0;
        double width = 0.0;
        double upperLength = 0.0;
        double lowerLength = 0.0;
        int carType = 0;
        System.out.println("Which type of car would you like to add?");
        System.out.println("Choose a car type:");
        System.out.println("\t1 - Tank car");
        System.out.println("\t2 - Box Car");
        System.out.println("\t3 - Hopper car");
        System.out.print("Car choice: ");
        carType = console.nextInt();
        while (carType < 1 || carType > 3) {
            System.out.println("You didn't choose a valid car type!");
            System.out.print("Car choice: ");
            carType = console.nextInt();
        }
        System.out.println();
        System.out.print("Enter the thickness of the walls: ");
        wallThickness = console.nextDouble();
        System.out.println();
        System.out.print("Enter the wall density: ");
        wallDensity = console.nextDouble();
        System.out.println();
        if (carType == 1) {
            container = constructCylinder(wallThickness, wallDensity);
        } else if (carType == 2) {
            container = constructRectangle(wallThickness, wallDensity);
        } else if (carType == 3) {
            container = constructTrap(wallThickness, wallDensity);
        }
        return container;
    }

    public static Cylinder constructCylinder(double wallThickness, double wallDensity) {
        double radius = 0.0;
        double length = 0.0;
        Cylinder cylinder = null;
        System.out.println("You chose to build a tank car.");
        System.out.print("Enter the radius of the tank car: ");
        radius = console.nextDouble();
        System.out.println();
        System.out.print("Enter the length of the tank car: ");
        length = console.nextDouble();
        System.out.println();
        cylinder = new Cylinder(wallThickness, wallDensity, radius, length);
        return cylinder;
    }

    public static RectangularBox constructRectangle(double wallThickness,
                                                    double wallDensity) {
        double height = 0.0;
        double width = 0.0;
        double length = 0.0;
        RectangularBox rect = null;
        System.out.println("You chose to build a box car.");
        System.out.print("Enter the height of the box car: ");
        height = console.nextDouble();
        System.out.println();
        System.out.print("Enter the width of the box car: ");
        width = console.nextDouble();
        System.out.println();
        System.out.print("Enter the length of the box car: ");
        length = console.nextDouble();
        System.out.println();
        rect = new RectangularBox(wallThickness, wallDensity, height, width, length);
        return rect;
    }

    public static TrapezoidalBox constructTrap(double wallThickness,
                                               double wallDensity) {
        double height = 0.0;
        double width = 0.0;
        double upperLength = 0.0;
        double lowerLength = 0.0;
        TrapezoidalBox trap = null;
        System.out.println("You chose to build a hopper car.");
        System.out.print("Enter the height of the hopper car: ");
        height = console.nextDouble();
        System.out.println();
        System.out.print("Enter the width of the hopper car: ");
        width = console.nextDouble();
        System.out.println();
        System.out.print("Enter the upper length of the hopper car: ");
        upperLength = console.nextDouble();
        System.out.println();
        System.out.print("Enter the lower length of the hopper car: ");
        lowerLength = console.nextDouble();
        System.out.println();
        trap = new TrapezoidalBox(wallThickness, wallDensity, height,
                                  width, upperLength, lowerLength);
        return trap;
    }

    public static Contents constructContents(Contents[] contentsArray) {
        Contents[] contArray = new Contents[5];
        Contents contents = null;
        int contentsChoice = 0;
        System.out.println("What would you like to load the car up with?");
        System.out.println("Contents \t\t\t Density (pounds per cubic foot) \t Value " +
                            " (dollars per pound)");
        System.out.println("1. Oil \t\t\t\t 55 \t\t\t\t\t\t\t\t 7.85");
        System.out.println("2. Coal \t\t\t 69 \t\t\t\t\t\t\t\t 50");
        System.out.println("3. Soybeans \t\t 47 \t\t\t\t\t\t\t\t 2.72");
        System.out.println("4. Lineseed, meal \t 32 \t\t\t\t\t\t\t\t 0.07");
        System.out.println("5. Oats \t\t\t 27 \t\t\t\t\t\t\t\t 1.30");
        System.out.print("Contents choice: ");
        contentsChoice = console.nextInt();
        while (contentsChoice < 1 || contentsChoice > 5) {
            System.out.println("You did not pick a valid contents choice!");
            System.out.print("Contents choice: ");
            contentsChoice = console.nextInt();
        }
        System.out.println();
        if (contentsChoice == 1) {
            contents = contentsArray[0];
        } else if (contentsChoice == 2) {
            contents = contentsArray[1];
        } else if (contentsChoice == 3) {
            contents = contentsArray[2];
        } else if (contentsChoice == 4) {
            contents = contentsArray[3];
        } else if (contentsChoice == 5) {
            contents = contentsArray[4];
        }
        return contents;
    }

    public static FreightCar constructFreightCar(Contents contents, Container container) {
        FreightCar car = null;
        String ownerName = "";
        int idNum = 0;
        double baseFrameWeight = 0.0;
        double loadFactor = 0.0;
        System.out.print("Enter the name of the owner of the car: ");
        ownerName = console.next();
        System.out.println();
        System.out.print("Enter the ID #: ");
        idNum = console.nextInt();
        System.out.println();
        System.out.print("Enter the base frame weight: ");
        baseFrameWeight = console.nextDouble();
        System.out.println();
        System.out.print("Enter the load factor (%): ");
        loadFactor = console.nextDouble() / 100.0;
        car = new FreightCar(ownerName, idNum, baseFrameWeight, contents,
                             container, loadFactor);
        return car;
    }

    public static int trainBuildingMenu() {
        int choice = 0;
        System.out.println("Train Building Menu");
        System.out.println("1. Add a car");
        System.out.println("2. Display complete description of Train");
        System.out.println("3. Display brief summary of each car");
        System.out.println("4. Display total weight and value of Train");
        System.out.println("5. Start a new Train");
        System.out.println("6. Quit program");
        System.out.print("Menu choice: ");
        choice = console.nextInt();
        while (choice < 1 || choice > 6){
            System.out.println("You did not chose a valid option!");
            System.out.print("Menu choice: ");
            choice = console.nextInt();
        }
        return choice;
    }
}

class Train {
    private String _engineerName;
    private Engine _engine;
    private ArrayList<FreightCar> _freightCars = new ArrayList<FreightCar>();

    public Train(String engineerName, Engine engine) {
        _engineerName = engineerName;
        _engine = engine;
    }

    public String getEngineerName() {
        return _engineerName;
    }

    public void addFreightCar(FreightCar freightCar) {
        _freightCars.add(freightCar);
    }

    public void deleteFreightCar(int freightCarID) {
        for (FreightCar car: _freightCars) {
            if (car.getIDNum() == freightCarID) {
                _freightCars.remove(car);
            }
        }
    }

    public void changeLoadFactor(int freightCarID, double newLoadFactor) {
        for (FreightCar car: _freightCars) {
            if (car.getIDNum() == freightCarID) {
                car.setLoadFactor(newLoadFactor);
            }
        }
    }

    public void displayBriefSummary(int maxWeight) {
        for (FreightCar car: _freightCars) {
            System.out.println("ID #: " + car.getIDNum());
            System.out.println("Total Weight: " + car.computeTotalWeight());
            System.out.println();
            if (car.computeTotalWeight() > maxWeight) {
                System.out.println("This car's weight is greater than the weight allowed" +
                                   " to go over the bridge!");
            }
            System.out.println("Total Value: " + car.computeTotalValue() + "\n");
        }
    }

    public void trainWeightAndValue() {
        double totalWeight = 0.0;
        double totalValue = 0.0;
        for (FreightCar car: _freightCars) {
            totalWeight += car.computeTotalWeight();
            totalValue += car.computeTotalValue();
        }
        totalWeight += _engine.getBaseFrameWeight();
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Total Value: " + totalValue);
        if (totalWeight > _engine.getPullingCapacity()) {
            System.out.println("The train's total weight is greater than " +
                    "the engine's pulling capacity!");
        }
    }

    public String toString() {
        String cars = "";
        for (FreightCar car: _freightCars) {
            cars += car + "\n";
        }
        return "Train: \n" +
               "Engineer name: " + _engineerName + "\n\n" +
               "Engine: \n" + _engine + "\n\n" +
               "Train cars: \n" + cars;
    }
}

abstract class RollingStock {
    private String _ownerName;
    private int _idNum;
    private double _baseFrameWeight;

    public RollingStock(String ownerName, int idNum, double baseFrameWeight) {
        _ownerName = ownerName;
        _idNum = idNum;
        _baseFrameWeight = baseFrameWeight;
    }

    public String getOwnerName() {
        return _ownerName;
    }

    public int getIDNum() {
        return _idNum;
    }

    public double getBaseFrameWeight() {
        return _baseFrameWeight;
    }

    public String toString() {
        return "Owner: " + _ownerName + "\n" +
               "ID: #" + _idNum + "\n" +
               "Base Frame Weight: " + _baseFrameWeight + "\n";
    }
}

class Engine extends RollingStock {
    private double _pullingCapacity;

    public Engine(String ownerName, int idNum, double baseFrameWeight,
                  double pullingCapacity) {
        super(ownerName, idNum, baseFrameWeight);
        _pullingCapacity = pullingCapacity;
    }

    public double getPullingCapacity() {
        return _pullingCapacity;
    }

    public String toString() {
        return super.toString() +
               "Pulling capacity: " + _pullingCapacity;
    }
}

class FreightCar extends RollingStock {
    private Contents _contents;
    private Container _container;
    private double _loadFactor;

    public FreightCar(String ownerName, int idNum, double baseFrameWeight,
                      Contents contents, Container container, double loadFactor) {
        super(ownerName, idNum, baseFrameWeight);
        _contents = contents;
        _container = container;
        _loadFactor = loadFactor;
    }

    public double getLoadFactor() {
        return _loadFactor;
    }

    public void setLoadFactor(double newLoadFactor) {
        _loadFactor = newLoadFactor;
    }

    public double computeTotalWeight() {
        double totalWeight = 0.0;
        totalWeight = (_container.wallWeight() + super.getBaseFrameWeight()) +
                      (_contents.getDensity() *
                      (_container.computeInteriorVolume() * _loadFactor));
        return totalWeight;
    }

    public double computeTotalValue() {
        double totalValue = 0.0;
        totalValue = _contents.getValue() * ((_container.computeInteriorVolume() *
                                            _loadFactor) * _contents.getDensity());
        return totalValue;
    }

    public String toString() {
        return super.toString() +
               "Contents: " + _contents.getType() + "\n" +
               "Container: " + _container + "\n" +
               "Load factor: " + _loadFactor * 100.0 + "%";
    }
}

abstract class Container {
    private double _wallThickness;
    private double _wallDensity;

    public Container(double wallThickness, double wallDensity) {
        _wallThickness = wallThickness;
        _wallDensity = wallDensity;
    }

    public double getWallThickness() {
        return _wallThickness;
    }

    public double getWallDensity() {
        return _wallDensity;
    }

    abstract public double computeExteriorVolume();

    abstract public double computeInteriorVolume();

    public double wallWeight() {
        double wallVolume = 0.0;
        double wallWeight = 0.0;
        wallVolume = computeExteriorVolume() - computeInteriorVolume();
        wallWeight = _wallDensity * wallVolume;
        return wallWeight;
    }

    public String toString() {
        return "Wall thickness: " + _wallThickness + "\n" +
               "Wall density: " + _wallDensity + "\n";
    }
}

class Cylinder extends Container {
    private double _radius;
    private double _length;

    public Cylinder(double wallThickness, double wallDensity, double radius,
                    double length) {
        super(wallThickness, wallDensity);
        _radius = radius;
        _length = length;
    }

    public double getRadius() {
        return _radius;
    }

    public double getLength() {
        return _length;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = Math.PI * Math.pow(_radius, 2) * _length;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = Math.PI * Math.pow(_radius - getWallThickness(), 2) *
                (_length - (2 * getWallThickness()));
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Car Type: " + "Tank" + "\n" +
               "Radius: " + _radius + "\n" +
               "Length: " + _length;
    }
}

class RectangularBox extends Container {
    private double _height;
    private double _width;
    private double _length;

    public RectangularBox(double wallThickness, double wallDensity, double height,
                          double width, double length) {
        super(wallThickness, wallDensity);
        _height = height;
        _width = width;
        _length = length;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getLength() {
        return _length;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = _height * _width * _length;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = (_height * (2 * getWallThickness())) *
                 (_width * (2 * getWallThickness())) *
                 (_length * (2 * getWallThickness()));
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Car Type: " + "Box" + "\n" +
               "Height: " + _height + "\n" +
               "Width: " + _width + "\n" +
               "Length: " + _length;
    }
}

class TrapezoidalBox extends Container {
    private double _height;
    private double _width;
    private double _upperLength;
    private double _lowerLength;

    public TrapezoidalBox(double wallThickness, double wallDensity, double height,
                          double width, double upperLength, double lowerLength) {
        super(wallThickness, wallDensity);
        _height = height;
        _width = width;
        _upperLength = upperLength;
        _lowerLength = lowerLength;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getUpperLength() {
        return _upperLength;
    }

    public double getLowerLength() {
        return _lowerLength;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = ((1.0/2.0) * (_upperLength + _lowerLength)) * _width * _height;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = ((1.0/2.0) * (_upperLength - (2* getWallThickness()) + _lowerLength -
                            (2* getWallThickness()))) * (_width - (2 * getWallThickness())) *
                                                       (_height - getWallThickness());
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Car Type: " + "Hopper" + "\n" +
               "Height: " + _height + "\n" +
               "Width: " + _width + "\n" +
               "Upper Length: " + _upperLength + "\n" +
               "Lower Length: " + _lowerLength;
    }
}

class Contents {
    private String _type;
    private double _density;
    private double _value;

    public Contents(String type, double density, double value) {
        _type = type;
        _density = density;
        _value = value;
    }

    public String getType() {
        return _type;
    }

    public double getDensity() {
        return _density;
    }

    public double getValue() {
        return _value;
    }

    public String toString() {
        return "Type: " + _type + "\n" +
               "Density: " + _density + "\n" +
               "Value: " + _value + "\n";
    }
}
