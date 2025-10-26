package mst;

import java.util.*;

public class PrimMST {

    public static class Result {
        public final List<Edge> mstEdges;
        public final double totalCost;
        public final long operations;
        public final double timeMs;

        public Result(List<Edge> mstEdges, double totalCost, long operations, double timeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.timeMs = timeMs;
        }
    }

    public static Result compute(Graph graph) {
        long start = System.nanoTime();
        if (graph.vertexCount() == 0) {
            return new Result(new ArrayList<>(), 0, 0, 0);
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();
        double totalCost = 0;
        long ops = 0;

        String startVertex = graph.getVertices().iterator().next();
        visited.add(startVertex);
        pq.addAll(graph.getAdjacentEdges(startVertex));
        ops += pq.size();

        while (!pq.isEmpty() && mst.size() < graph.vertexCount() - 1) {
            Edge edge = pq.poll();
            ops++;
            String u = edge.getFrom();
            String v = edge.getTo();
            String next = visited.contains(u) ? v : u;

            if (visited.contains(next)) {
                ops++;
                continue;
            }

            visited.add(next);
            mst.add(edge);
            totalCost += edge.getWeight();

            for (Edge e : graph.getAdjacentEdges(next)) {
                String neighbor = e.getFrom().equals(next) ? e.getTo() : e.getFrom();
                if (!visited.contains(neighbor)) {
                    pq.add(e);
                    ops++;
                }
            }
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        return new Result(mst, totalCost, ops, timeMs);
    }
}