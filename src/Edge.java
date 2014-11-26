
public class Edge implements Comparable<Edge>{

	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		if (v < 1) throw new IndexOutOfBoundsException("Vertex name must be at least 1.");
		if (w < 1) throw new IndexOutOfBoundsException("Vertex name must be at least 1.");
		if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int vertex) {
		if (vertex == v) return w;
		else if (vertex == w) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}
	
	public int compareTo(Edge that) {
		if (this.weight() < that.weight()) return -1;
		if (this.weight() > that.weight()) return +1;
		else return 0;
	}
}