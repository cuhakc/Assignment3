package mst;

import org.json.JSONObject;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputPath = "data/input.json";
        String outputPath = "results/output.json";

        List<JSONObject> results = new ArrayList<>();
        List<String[]> csvRows = new ArrayList<>();

        List<MSTUtils.GraphData> graphs = MSTUtils.readGraphsFull(inputPath);

        for (MSTUtils.GraphData gd : graphs) {
            Graph graph = gd.graph;
            int id = gd.id;

            PrimMST.Result prim = PrimMST.compute(graph);
            KruskalMST.Result kruskal = KruskalMST.compute(graph);

            JSONObject entry = new JSONObject();
            entry.put("graph_id", id);

            JSONObject stats = new JSONObject();
            stats.put("vertices", graph.vertexCount());
            stats.put("edges", graph.edgeCount());
            entry.put("input_stats", stats);

            JSONObject primObj = new JSONObject();
            primObj.put("mst_edges", prim.mstEdges);
            primObj.put("total_cost", prim.totalCost);
            primObj.put("operations_count", prim.operations);
            primObj.put("execution_time_ms", prim.timeMs);
            entry.put("prim", primObj);

            JSONObject kruskalObj = new JSONObject();
            kruskalObj.put("mst_edges", kruskal.mstEdges);
            kruskalObj.put("total_cost", kruskal.totalCost);
            kruskalObj.put("operations_count", kruskal.operations);
            kruskalObj.put("execution_time_ms", kruskal.timeMs);
            entry.put("kruskal", kruskalObj);

            results.add(entry);

            csvRows.add(new String[]{
                    String.valueOf(id),
                    String.valueOf(graph.vertexCount()),
                    String.valueOf(graph.edgeCount()),
                    String.valueOf(prim.totalCost),
                    String.valueOf(prim.timeMs),
                    String.valueOf(prim.operations),
                    String.valueOf(kruskal.totalCost),
                    String.valueOf(kruskal.timeMs),
                    String.valueOf(kruskal.operations)
            });
        }

        MSTUtils.writeResultsToJSON(outputPath, results);
        MSTUtils.writeSummaryCSV("results/summary.csv", csvRows);

        System.out.println("✅ Results written to " + outputPath);
    }
}