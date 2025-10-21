package main.java;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocationManager manager = new LocationManager();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations");
            System.out.println("7. BFS traversal");
            System.out.println("8. DFS traversal");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    manager.addLocation(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter location name to remove: ");
                    manager.removeLocation(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter first location: ");
                    String from = sc.nextLine();
                    System.out.print("Enter second location: ");
                    String to = sc.nextLine();
                    manager.addRoad(from, to);
                    break;
                case 4:
                    System.out.print("Enter first location: ");
                    from = sc.nextLine();
                    System.out.print("Enter second location: ");
                    to = sc.nextLine();
                    manager.removeRoad(from, to);
                    break;
                case 5:
                    manager.displayAllConnections();
                    break;
                case 6:
                    manager.displayAllLocations();
                    break;
                case 7:
                    System.out.print("Enter start location for BFS: ");
                    manager.bfsTraversal(sc.nextLine());
                    break;
                case 8:
                    System.out.print("Enter start location for DFS: ");
                    manager.dfsTraversal(sc.nextLine());
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
