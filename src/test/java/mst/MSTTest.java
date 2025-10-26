package mst;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph();
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 7);
        graph.addEdge("C", "E", 8);
        graph.addEdge("D", "E", 6);
    }

    @Test
    void testMSTCostEquality() {
        PrimMST.Result prim = PrimMST.compute(graph);
        KruskalMST.Result kruskal = KruskalMST.compute(graph);
        assertEquals(prim.totalCost, kruskal.totalCost, 1e-6, "MST costs must match");
    }

    @Test
    void testEdgeCount() {
        PrimMST.Result prim = PrimMST.compute(graph);
        assertEquals(graph.vertexCount() - 1, prim.mstEdges.size());
    }

    @Test
    void testNoNegativeValues() {
        KruskalMST.Result kruskal = KruskalMST.compute(graph);
        assertTrue(kruskal.operations >= 0);
        assertTrue(kruskal.timeMs >= 0);
    }

    @Test
    void testDisconnectedGraph() {
        Graph g = new Graph();
        g.addEdge("X", "Y", 5);
        g.addEdge("A", "B", 2);
        PrimMST.Result prim = PrimMST.compute(g);
        assertTrue(prim.mstEdges.size() < g.vertexCount() - 1, "Disconnected graph shouldn't form full MST");
    }
}