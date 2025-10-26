package mst;

public class Edge implements Comparable<Edge> {
    private final String from;
    private final String to;
    private final double weight;

    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("(%s - %s : %.2f)", from, to, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) return false;
        Edge e = (Edge) obj;
        return from.equals(e.from) && to.equals(e.to) && weight == e.weight;
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode() + Double.hashCode(weight);
    }
}