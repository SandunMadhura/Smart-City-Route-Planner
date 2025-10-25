//Suneth suranjan 22ug3-0633
    

package main.java;
import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addNode(int id) {
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    public void removeNode(int id) {
        adjList.remove(id);
        for (List<Integer> list : adjList.values())
            list.remove(Integer.valueOf(id));
    }

    public boolean addEdge(int from, int to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to))
            return false;
        if (!adjList.get(from).contains(to))
            adjList.get(from).add(to);
        if (!adjList.get(to).contains(from))
            adjList.get(to).add(from);
        return true;
    }

    public boolean removeEdge(int from, int to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to))
            return false;
        adjList.get(from).remove(Integer.valueOf(to));
        adjList.get(to).remove(Integer.valueOf(from));
        return true;
    }

    public void displayConnections(Map<Integer, String> idToName) {
        System.out.println("\n--- City Connections ---");
        for (var entry : adjList.entrySet()) {
            System.out.print(idToName.get(entry.getKey()) + " → ");
            List<Integer> neighbors = entry.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(idToName.get(neighbors.get(i)));
                if (i < neighbors.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    // BFS traversal using Queue
    public List<Integer> bfs(int startId) {
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.add(startId);
        seen.add(startId);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited.add(current);
            for (int neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!seen.contains(neighbor)) {
                    queue.add(neighbor);
                    seen.add(neighbor);
                }
            }
        }
        return visited;
    }

    // DFS traversal using Stack
    public List<Integer> dfs(int startId) {
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();

        stack.push(startId);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!seen.contains(current)) {
                visited.add(current);
                seen.add(current);
                for (int neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                    stack.push(neighbor);
                }
            }
        }
        return visited;
    }

    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }
}
