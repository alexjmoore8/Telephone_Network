import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Telephone_Network_Main {
	public static void main(String[] args) {
		// Create a new graph
		Graph graph = new Graph();
		// Variables
		String a = "";
		String b = "";
		
		
		//Read the input from a file
		try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))){
			// Read in the first line to add vertices and get number of switching stations
			// Each Vertex will be be passed in as a string with each vertex being separated by a ";"
			String line = reader.readLine();
			String[] substrings = line.split(";");
			int totalStations = substrings.length;
			System.out.println("Total switching centers: " + totalStations + "\n");
			for (int i = 0; i < totalStations; i++) {
			    String newVertex = substrings[i];
			    graph.addVertex(newVertex);
			}

			// Read in second & third lines as the start and end stations to pass into the findMaximumBandwidthPath() function
			a = reader.readLine();
			b = reader.readLine();
			System.out.println("\nCalculating the maximum bandwidth between the switching centers.\n"
					+ "Starting center: " + a + "\n"
					+ "Ending center: " + b +"\n");
			// For remaining line, split the line into the names of the two vertices (strings)
			// and the bandwidth (int) each separated by a ";"
			while ((line = reader.readLine()) != null) {
				
				String[] parts = line.split(";");
				String v1Name = parts[0];
				String v2Name = parts[1];
				int bandwidth = 0;
				try {
					bandwidth = Integer.parseInt(parts[2]);
				} catch (NumberFormatException e) {
					System.out.println("Error: Invalid value for bandwidth. Expected an integer.\n"
							+ "Will attempt to calculcate bandwidth with the valid information provided.\n");
				}
				
				
				//Add the edge to the graph
				graph.addEdge(v1Name, v2Name, bandwidth);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		  // Find the maximum bandwidth path between switching centers a and b
		  graph.findMaximumBandwidthPath(a, b);
		
	}	
}
