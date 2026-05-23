public class Main {
    public static void main(String[] args) {
        System.out.println("=== Graph Structure (Small Graph) ===");
        Graph small = new Graph();
        for (int i = 0; i < 10; i++) small.addVertex(new Vertex(i));
        for (int i = 0; i < 10; i++) {
            small.addEdge(i, (i + 1) % 10);
            small.addEdge(i, (i + 2) % 10);
        }
        small.printGraph();
        System.out.println();
        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
        System.out.println("=== Dijkstra's Shortest Path ===");
        Graph weighted = new Graph();
        for (int i = 0; i < 6; i++) weighted.addVertex(new Vertex(i));
        weighted.addEdge(0, 1, 2);
        weighted.addEdge(0, 3, 6);
        weighted.addEdge(1, 2, 3);
        weighted.addEdge(1, 4, 8);
        weighted.addEdge(2, 5, 7);
        weighted.addEdge(3, 4, 9);
        weighted.addEdge(4, 5, 4);
        weighted.dijkstra(0);
    }
}