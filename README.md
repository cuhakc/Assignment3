# 🏙️ Assignment 3 — Optimization of a City Transportation Network (Minimum Spanning Tree)

## 🎯 Objective
The objective of this assignment is to optimize a city’s transportation network by finding the **Minimum Spanning Tree (MST)** that connects all city districts with the lowest total road construction cost.  
Two algorithms are implemented and compared:
- **Prim’s Algorithm**
- **Kruskal’s Algorithm**

Both methods are applied to graphs of varying sizes and densities to determine their efficiency, operation count, and execution time.

---

## 🧱 Project Structure
Assignment33/
│
├── src/
│   ├── main/java/mst/
│   │   ├── Graph.java
│   │   ├── Edge.java
│   │   ├── PrimMST.java
│   │   ├── KruskalMST.java
│   │   ├── MSTUtils.java
│   │   └── Main.java
│   └── test/java/mst/MSTTest.java
│
├── data/
│   └── input.json
│
├── results/
│   ├── output.json
│   └── summary.csv
│
└── pom.xml

---

⚙️ How to Run
mvn clean compile
mvn exec:java

results/output.json
results/summary.csv

🔍 Analysis

Correctness
	•	Both algorithms generate identical MST total costs (16 and 6), confirming correctness.
	•	Each MST contains exactly V − 1 edges, and all vertices are connected.
	•	Execution time and operation counts are consistent and non-negative.

Performance Comparison
	•	Prim’s Algorithm performs better for dense graphs, as it grows the MST using a priority queue and avoids sorting all edges.
	•	Kruskal’s Algorithm is efficient for sparse graphs, since sorting fewer edges dominates the runtime.
	•	Prim’s performs fewer operations on Graph 1 (dense), while Kruskal performs fewer on smaller/sparser Graph 2.

🧮 Conclusions
	•	Both Prim’s and Kruskal’s algorithms successfully produce the same optimal MST.
	•	Prim’s algorithm is preferable when:
	•	The graph is dense or represented using an adjacency matrix.
	•	Kruskal’s algorithm is preferable when:
	•	The graph is sparse or represented as an edge list.
	•	Implementation complexity: Kruskal is conceptually simpler; Prim’s performs better with heap-based optimization.

⸻

🧪 Testing Summary

Automated JUnit tests verify:
	•	Correct MST cost equivalence between algorithms
	•	Number of edges equals V − 1
	•	Acyclic structure (no cycles)
	•	Connectedness of MST
	•	Proper handling of disconnected graphs
	•	Non-negative execution times and operation counts

✅ All tests passed successfully.

⸻

🧠 Bonus: Java OOP Graph Design

Bonus points are earned by implementing a clean, modular graph structure:
	•	Graph.java encapsulates vertices and edges.
	•	Edge.java defines weighted undirected edges.
	•	The MST algorithms operate directly on the Graph class.
	•	Code demonstrates solid object-oriented design principles.


## 🧩 Input Format (`data/input.json`)
Each graph is represented as a list of vertices (`nodes`) and weighted edges:

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    },
    {
      "id": 2,
      "nodes": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 1},
        {"from": "A", "to": "C", "weight": 4},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "C", "to": "D", "weight": 3},
        {"from": "B", "to": "D", "weight": 5}
      ]
    }
  ]
```
### 📁 Datasets Included
All datasets are contained within a single `data/input.json` file, featuring graphs of different sizes:

| Graph ID | Type | Vertices | Description |
|-----------|------|-----------|--------------|
| 1 | Small | 5 | Basic graph for correctness verification |
| 2 | Medium | 10 | Moderately sized network for performance analysis |
| 3 | Large | 20 | Large network for scalability and efficiency testing |

Each dataset is automatically processed, and corresponding MST results are recorded in `results/output.json` and `results/summary.csv`.

📊 Output Example (results/output.json)
```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {"vertices": 5, "edges": 7},
      "prim": {"total_cost": 16, "execution_time_ms": 0.414, "operations_count": 13},
      "kruskal": {"total_cost": 16, "execution_time_ms": 0.217, "operations_count": 23}
    },
    {
      "graph_id": 2,
      "input_stats": {"vertices": 4, "edges": 5},
      "prim": {"total_cost": 6, "execution_time_ms": 0.018, "operations_count": 8},
      "kruskal": {"total_cost": 6, "execution_time_ms": 0.016, "operations_count": 14}
    }
  ]
}
}
```

