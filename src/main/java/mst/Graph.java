package mst;

import java.util.*;

public class Graph {
    private final Set<String> vertices;
    private final List<Edge> edges;
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String from, String to, double weight) {
        Edge edge = new Edge(from, to, weight);
        edges.add(edge);
        vertices.add(from);
        vertices.add(to);
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(edge);
        adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(edge);
    }

    public Set<String> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Edge> getAdjacentEdges(String vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int edgeCount() {
        return edges.size();
    }

    public void printGraph() {
        System.out.println("Graph:");
        for (String v : adjacencyList.keySet()) {
            System.out.print(v + " -> ");
            for (Edge e : adjacencyList.get(v)) {
                String neighbor = e.getFrom().equals(v) ? e.getTo() : e.getFrom();
                System.out.print(neighbor + "(" + e.getWeight() + ") ");
            }
            System.out.println();
        }
    }
}