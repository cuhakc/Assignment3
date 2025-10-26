package mst;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();
    public int findOps = 0;
    public int unionOps = 0;

    public UnionFind(Iterable<String> elements) {
        for (String e : elements) {
            parent.put(e, e);
            rank.put(e, 0);
        }
    }

    public String find(String x) {
        findOps++;
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public boolean union(String x, String y) {
        unionOps++;
        String rootX = find(x);
        String rootY = find(y);
        if (rootX.equals(rootY)) return false;

        if (rank.get(rootX) < rank.get(rootY)) {
            parent.put(rootX, rootY);
        } else if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.get(rootX) + 1);
        }
        return true;
    }
}