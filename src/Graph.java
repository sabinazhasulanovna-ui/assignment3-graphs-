import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjList;
    public Graph() {
        adjList = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }
    public void addEdge(int from, int to, int weight) {
        Vertex vFrom = new Vertex(from);
        Vertex vTo   = new Vertex(to);
        adjList.get(from).add(new Edge(vFrom, vTo, weight));
        adjList.get(to).add(new Edge(vTo, vFrom, weight));
    }

    public void printGraph() {
        for (int v : adjList.keySet()) {
            System.out.print(v + " -> ");
            for (Edge e : adjList.get(v)) {
                System.out.print(e.getDestination().getId() + "(w=" + e.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (Edge edge : adjList.get(vertex)) {
                int neighbor = edge.getDestination().getId();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (Edge edge : adjList.get(vertex)) {
            int neighbor = edge.getDestination().getId();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void dijkstra(int start) {
        int size = adjList.size();
        int[] dist    = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int step = 0; step < size; step++) {
            int u = pickNearest(dist, visited, size);
            if (u == -1) break;

            visited[u] = true;

            for (Edge edge : adjList.get(u)) {
                int v      = edge.getDestination().getId();
                int newDist = dist[u] + edge.getWeight();
                if (!visited[v] && newDist < dist[v]) {
                    dist[v] = newDist;
                }
            }
        }

        printDistances(start, dist, size);
    }
    private int pickNearest(int[] dist, boolean[] visited, int size) {
        int nearest = -1;
        for (int v = 0; v < size; v++) {
            if (!visited[v] && dist[v] != Integer.MAX_VALUE) {
                if (nearest == -1 || dist[v] < dist[nearest]) {
                    nearest = v;
                }
            }
        }
        return nearest;
    }
    private void printDistances(int start, int[] dist, int size) {
        System.out.println("Shortest paths from vertex " + start + ":");
        for (int i = 0; i < size; i++) {
            String d = (dist[i] == Integer.MAX_VALUE) ? "unreachable" : String.valueOf(dist[i]);
            System.out.println("  -> vertex " + i + " : " + d);
        }
    }
}