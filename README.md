- Bonus Task — Dijkstra's Algorithm

This is an extension of the existing graph project. The bonus adds weighted edge support and Dijkstra's shortest path algorithm on top of the already implemented BFS and DFS traversals
* Changes made to existing files

* Edge.java
Added a weight field to the existing Edge class:
- New constructor: Edge(Vertex source, Vertex destination, int weight)
- New getter: getWeight()
- Updated toString() to include the weight

*Graph.java
The adjacency list type changed from `Map<Integer, List<Integer>>` to `Map<Integer, List<Edge>>` to store weighted edges
- addEdge(int from, int to) — kept for backward compatibility, uses weight = 1
- `addEdge(int from, int to, int weight)` — new overload with weight
- `bfs()` and `dfs()` — updated to iterate over `Edge` objects instead of plain integers
- printGraph() — now shows weights in output
- dijkstra(int start) — new method (see below)

* `Main.java`
Added a weighted graph demo and a `dijkstra(0)` call at the bottom. All existing code is unchanged.
*How Dijkstra works (step by step)

1. Set distance to the start vertex = `0`, all others = `∞`
2. Pick the unvisited vertex with the smallest known distance
3. For each neighbor: if `dist[current] + edgeWeight < dist[neighbor]`, update it
4. Mark current as visited, repeat until all reachable vertices are done

*New methods in `Graph.java`

| Method | Description |
|--------|-------------|
| `addEdge(from, to, weight)` | Adds a weighted undirected edge |
| `dijkstra(start)` | Runs Dijkstra from start, prints shortest distances |
| `pickNearest(dist, visited, size)` | Finds the closest unvisited vertex (linear scan) |
| `printDistances(start, dist, size)` | Prints the result |

*Example output

```
=== Dijkstra's Shortest Path ===
Shortest paths from vertex 0:
  -> vertex 0 : 0
  -> vertex 1 : 2
  -> vertex 2 : 5
  -> vertex 3 : 6
  -> vertex 4 : 10
  -> vertex 5 : 12
```

Graph used:
```
      2       3
  0 ----- 1 ----- 2
  |       |       |
6 |     8 |       | 7
  |       |       |
  3 ----- 4 ----- 5
      9       4
```

*Implementation notes

- No priority queue used — pickNearest does a simple O(V) linear scan as allowed by the task
- addEdge(from, to) without weight still works — existing `Experiment` and `Main` code required no changes
- Unreachable vertices print `unreachable` instead of a raw number