
public class Dijkstra {
	private double[] A;
	private DirectedEdge[] edgeTo; //edgeTo[v] = last edge on shortest s->v path
	private MinPQ<Double> Q; // priority queue of Dijkstra scores of vertices (undiscovered)
	
	public Dijkstra(EdgeWeightedGraph G, int s) {
		if (s < 1 || s > G.getV())
			throw new IllegalArgumentException();
		for (DirectedEdge e : G.edges()) {
			if(e.weight() < 0) 
				throw new IllegalArgumentException();
		}
		
		A = new double[G.getV() + 1]; // stores shortest distances
		edgeTo = new DirectedEdge[G.getV() + 1];
		for (int v = 1; v <= G.getV(); v++) {
			A[v] = Double.POSITIVE_INFINITY;
		}
		A[s] = 0.0;
		Q = new MinPQ<Double>(G.getV());
		Q.insert(s, A[s]);
	}
	
	private void relax(DirectedEdge edge) {
		int v = edge.from(), w = edge.to();
		validateVertex(v);
		validateVertex(w);
		if(A[w] > A[v] + edge.weight()) { // i.e., b/c w hasn't been discovered A[w] would be positive infinity
			A[w] = A[v] + edge.weight();
			edgeTo[w] = edge;
			if (!Q.contains(w))
				Q.insert(w, A[w]);
		}
	}
	
	
	private void validateVertex(int v) {
		if (v < 0 || v > A.length)
			throw new IndexOutOfBoundsException();
	}
	
	private boolean check(EdgeWeightedGraph G, int s) {
		for (DirectedEdge e : G.edges()) {
			if (e.weight() < 0) return false;
		}
		
		if(A[s] != 0.0 || edgeTo[s] != null) {
			return false;
		}
		
		for(int v = 1; v <= G.getV(); v++) {
			if (v == s) continue;
			if (edgeTo[v] == null && A[v] != Double.POSITIVE_INFINITY) {
				return false;
			}
		}
		
		for(int v = 1; v <= G.getV(); v++) {
			for (DirectedEdge e : G.adj(v)) {
				int w = e.to();
				if (A[v] + e.weight() < A[w]) {
					System.out.println("edge" + e + " not relaxed");
					return false;
				}
			}
		}
		
		for (int w = 1; w <= G.getV(); w++) {
			if (edgeTo[w] == null) continue;
			DirectedEdge e = edgeTo[w];
			int v = e.from();
			if (w!=e.to()) return false;
			if (A[v] + e.weight() != A[w]) {
				System.err.println("edge " + e + "on shortest path not tight");
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		EdgeWeightedGraph G = new EdgeWeightedGraph("dijkstraData.txt");
		Dijkstra D = new Dijkstra(G, 1);
		while(!D.Q.isEmpty()) {
			int v = D.Q.delMin();
			for(DirectedEdge e : G.adj(v))
				D.relax(e);
		}
		assert D.check(G,1);
		System.out.println("__"); // debug breakpoint here to look at A 
	}
}

