public class Experiment {

    public void runTraversals(Graph g) {
        long start;
        long end;
        System.out.println("BFS Traversal:");
        start = System.nanoTime();
        g.bfs(0);
        end = System.nanoTime();
        System.out.println("BFS Time: " + (end - start) + " ns");
        System.out.println();

        System.out.println("DFS Traversal:");
        start = System.nanoTime();
        g.dfs(0);
        end = System.nanoTime();
        System.out.println("DFS Time: " + (end - start) + " ns");
    }
    public void runMultipleTests() {
        System.out.println("=== Small (10 vertices) ===");
        runTraversals(buildGraph(10));
        System.out.println();

        System.out.println("=== Medium (30 vertices) ===");
        runTraversals(buildGraph(30));
        System.out.println();

        System.out.println("=== Large (100 vertices) ===");
        runTraversals(buildGraph(100));
        System.out.println();
    }

    public void printResults() {
        System.out.println("All results have been printed above.");
    }

    private Graph buildGraph(int n) {
        Graph g = new Graph();
        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(i, (i + 1) % n);
            g.addEdge(i, (i + 2) % n);
        }
        return g;
    }
}