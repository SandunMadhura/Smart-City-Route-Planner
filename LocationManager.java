package main.java;

import java.util.*;

public class LocationManager {
    private AVLTree<Location> locationTree = new AVLTree<>();
    private Graph graph = new Graph();
    private Map<String, Integer> nameToId = new HashMap<>();
    private Map<Integer, String> idToName = new HashMap<>();
    private int idCounter = 1;

    public void addLocation(String name) {
        if (nameToId.containsKey(name.toLowerCase())) {
            System.out.println("Location already exists!");
            return;
        }

        // add new location
        
        int id = idCounter++;
        Location loc = new Location(id, name);
        locationTree.insert(loc);
        graph.addNode(id);
        nameToId.put(name.toLowerCase(), id);
        idToName.put(id, name);
        System.out.println("Added: " + name);
    }

    public void removeLocation(String name) {
        Integer id = nameToId.get(name.toLowerCase());
        if (id == null) {
            System.out.println("Location not found.");
            return;
        }
        Location loc = new Location(id, name);
        locationTree.delete(loc);
        graph.removeNode(id);
        nameToId.remove(name.toLowerCase());
        idToName.remove(id);
        System.out.println("Removed: " + name);
    }

    public void addRoad(String from, String to) {
        Integer id1 = nameToId.get(from.toLowerCase());
        Integer id2 = nameToId.get(to.toLowerCase());
        if (id1 == null || id2 == null) {
            System.out.println("One or both locations not found.");
            return;
        }
        graph.addEdge(id1, id2);
        System.out.println("Road added between " + from + " and " + to);
    }

    public void removeRoad(String from, String to) {
        Integer id1 = nameToId.get(from.toLowerCase());
        Integer id2 = nameToId.get(to.toLowerCase());
        if (id1 == null || id2 == null) {
            System.out.println("One or both locations not found.");
            return;
        }
        graph.removeEdge(id1, id2);
        System.out.println("Road removed between " + from + " and " + to);
    }

    public void displayAllConnections() {
        graph.displayConnections(idToName);
    }

    public void displayAllLocations() {
        System.out.println("\n--- All Locations (AVL Tree Inorder) ---");
        for (Location loc : locationTree.inorderTraversal()) {
            System.out.println(loc);
        }
    }

    public void bfsTraversal(String startName) {
        Integer id = nameToId.get(startName.toLowerCase());
        if (id == null) {
            System.out.println("Location not found.");
            return;
        }
        List<Integer> visited = graph.bfs(id);
        System.out.print("BFS: ");
        for (int v : visited) System.out.print(idToName.get(v) + " ");
        System.out.println();
    }

    public void dfsTraversal(String startName) {
        Integer id = nameToId.get(startName.toLowerCase());
        if (id == null) {
            System.out.println("Location not found.");
            return;
        }
        List<Integer> visited = graph.dfs(id);
        System.out.print("DFS: ");
        for (int v : visited) System.out.print(idToName.get(v) + " ");
        System.out.println();
    }
}
