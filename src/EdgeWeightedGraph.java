import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private List<Edge>[] adj;
	
	public EdgeWeightedGraph(String filename) {
		this.V = V(filename);
		this.E = E(filename);
	}
	
	public int V(String filename) {
		int v = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while((line = reader.readLine()) != null) {
				v++;
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
	
	public static void main(String[] args) {
		EdgeWeightedGraph EWG = new EdgeWeightedGraph("dijkstraData.txt");
		System.out.println(EWG.E);
		System.out.println(EWG.V);
	}

}
