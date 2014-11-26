
public class DirectedEdge {

	private final int u;
	private final int v;
	private final double weight;
	
	public DirectedEdge(int u, int v, double weight) {
		if (u < 1) throw new IndexOutOfBoundsException("Vertex name must be at least 1.");
		if (v < 1) throw new IndexOutOfBoundsException("Vertex name must be at least 1.");
		if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int from() {
		return u;
	}
	
	public int to() {
		return v;
	}
}