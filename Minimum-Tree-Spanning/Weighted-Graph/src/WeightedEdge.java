public class WeightedEdge implements Comparable<WeightedEdge>{
    private int V;
    private int W;
    private int Weight;

    public WeightedEdge(int v, int w, int weight) {
        V = v;
        W = w;
        Weight = weight;
    }

    public int getV() {
        return V;
    }

    public int getW() {
        return W;
    }

    public int getWeight() {
        return Weight;
    }

    @Override
    public String toString() {
        return String.format("%d-%d:%d", V, W, Weight);
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return Weight - o.Weight;
    }
}
