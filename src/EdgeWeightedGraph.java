import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private List<DirectedEdge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		if (V < 0) throw new IllegalArgumentException();
		this.V = V;
		this.E = 0;
		adj = (ArrayList<DirectedEdge>[]) new ArrayList[V + 1];
		for (int v = 0; v <= V; v++) {
			adj[v] = new ArrayList<DirectedEdge>();
		}
	}
	
	public EdgeWeightedGraph(String filename) {
		this.V = V(filename);
		this.E = E(filename);
		adj = (List<DirectedEdge>[]) new List[getV() + 1];
		for(int v=0; v<=getV(); v++) adj[v] = new ArrayList<DirectedEdge>();
		
		int v;
		int w = 0;
		double weight = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = reader.readLine();
			while(line != null) {
				try (Scanner scanner = new Scanner(line)) {
					String[] edgeList = line.split("\t",-1);
					Scanner s = new Scanner(edgeList[0]);
					v = s.nextInt();
					s.close();
					for(int e = 1; e < edgeList.length; e++) {
						String[] toVertex = edgeList[e].split(",",0);
						if(toVertex.length >= 2) {
							Scanner T = new Scanner(toVertex[0]);
							if(T.hasNextInt()) w = T.nextInt();
							T.close();
							
							Scanner W = new Scanner(toVertex[1]);
							if(W.hasNextDouble()) weight = W.nextDouble();
							W.close();
						}
						DirectedEdge DE = new DirectedEdge(v, w, weight);
						addEdge(DE);
					}
				}
				line = reader.readLine();
			}
		} catch(FileNotFoundException e) {
			e.getMessage();
		} catch(IOException e) {
			e.getMessage();
		}
	}
	
	public int V(String filename) {
		int v = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = reader.readLine();
			while(line != null) {
				v++;
				line = reader.readLine();
			}
		} catch(FileNotFoundException e) {
			e.getMessage();
		} catch(IOException e) {
			e.getMessage();
		}
		return v;
	}
	
	public int E(String filename) {
		int E = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while((line = reader.readLine()) != null) {
				try (Scanner scanner = new Scanner(line)) {
					String[] edgeArray = line.split("\t" , 0);
					E += (edgeArray.length - 1);	
				}
			}
		} catch(FileNotFoundException e) {
			e.getMessage();
		} catch(IOException e) {
			e.getMessage();
		}
		return E;
	}
	
	private void validateVertex(int v) {
		if (v < 0 || v > this.V)
			throw new IndexOutOfBoundsException();
	}
	
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
	}

	public Iterable<DirectedEdge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public int outdegree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	public Iterable<DirectedEdge> edges() {
		List<DirectedEdge> list = new ArrayList<DirectedEdge>();
		for (int v = 1; v<=getV(); v++) {
			for (DirectedEdge e : adj(v)) {
				list.add(e);
			}
		}
		return list;
	}

	public int getV() {
		return V;
	}
}
