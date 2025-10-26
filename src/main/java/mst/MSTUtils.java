package mst;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MSTUtils {

    public static class GraphData {
        public int id;
        public Graph graph;
        public GraphData(int id, Graph g) {
            this.id = id;
            this.graph = g;
        }
    }

    public static List<GraphData> readGraphsFull(String path) {
        List<GraphData> graphs = new ArrayList<>();
        try {
            String content = Files.readString(Path.of(path));
            JSONObject root = new JSONObject(content);
            JSONArray arr = root.getJSONArray("graphs");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject g = arr.getJSONObject(i);
                int id = g.getInt("id");
                JSONArray nodes = g.getJSONArray("nodes");
                JSONArray edges = g.getJSONArray("edges");

                List<String> nodeList = new ArrayList<>();
                for (int n = 0; n < nodes.length(); n++) {
                    nodeList.add(nodes.getString(n));
                }

                Graph graph = new Graph();
                for (int e = 0; e < edges.length(); e++) {
                    JSONObject edge = edges.getJSONObject(e);
                    graph.addEdge(edge.getString("from"), edge.getString("to"), edge.getDouble("weight"));
                }
                graphs.add(new GraphData(id, graph));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graphs;
    }

    public static void writeResultsToJSON(String outputPath, List<JSONObject> results) {
        JSONObject out = new JSONObject();
        out.put("results", results);
        try (FileWriter fw = new FileWriter(outputPath)) {
            fw.write(out.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSummaryCSV(String path, List<String[]> rows) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("graph_id,vertices,edges,prim_cost,prim_time_ms,prim_ops,kruskal_cost,kruskal_time_ms,kruskal_ops");
            for (String[] row : rows) pw.println(String.join(",", row));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}