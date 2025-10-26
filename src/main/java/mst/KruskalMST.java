package mst;

import java.util.*;

public class KruskalMST {

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
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);

        UnionFind uf = new UnionFind(graph.getVertices());
        List<Edge> mst = new ArrayList<>();
        double totalCost = 0.0;
        long ops = 0;

        for (Edge e : sortedEdges) {
            ops++;
            if (uf.union(e.getFrom(), e.getTo())) {
                mst.add(e);
                totalCost += e.getWeight();
            }
            if (mst.size() == graph.vertexCount() - 1) break;
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        ops += uf.findOps + uf.unionOps;

        return new Result(mst, totalCost, ops, timeMs);
    }
}