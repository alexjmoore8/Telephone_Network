import java.util.*;


class Graph {
    // HashMap adjacencyList is a mapping from a vertex to a list of its adjacent vertices
    private final HashMap<String, ArrayList<Edge>> adjacencyList;
    // HashpMap vertices is a mapping from a vertex to its corresponding Vertex object
    private final HashMap<String, Vertex> vertices;

    // Constructor for the Graph class.
    public Graph() {
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
    }

    // Adds an edge between the vertices 'from' and 'to' with the given bandwidth.
    // If either vertex does not exist in the graph, it is added.

    public void addEdge(String from, String to, int bandwidth) {
        // Get the list of edges for the 'from' vertex
        ArrayList<Edge> edges = adjacencyList.get(from);
        if (edges == null) {
            edges = new ArrayList<>();
            adjacencyList.put(from, edges);
        }
        edges.add(new Edge(to, bandwidth));

        // Add the 'to' vertex to the graph if it doesn't exist
        if (!vertices.containsKey(to)) {
            addVertex(to);
        }

        // Add an edge from 'to' to 'from'
        ArrayList<Edge> reverseEdges = adjacencyList.get(to);
        if (reverseEdges == null) {
            reverseEdges = new ArrayList<>();
            adjacencyList.put(to, reverseEdges);
        }
        reverseEdges.add(new Edge(from, bandwidth));

        // Add the 'from' vertex to the graph if it doesn't exist
        if (!vertices.containsKey(from)) {
            addVertex(from);
        }
        System.out.println("Communication line added: [" + from + ", " + to + ", " + bandwidth + "]");
    }

    // Adds a vertex with the given id to the graph.
    
    public void addVertex(String id) {
    	if (getVertex(id) == null ) {
    		vertices.put(id, new Vertex(id, Integer.MAX_VALUE));
    		System.out.println("Switching center added: " + id);
    	}
    	else {
    		System.out.println("Duplicate switching center NOT added: " + id);
    	}
    }

   // Returns the Vertex object corresponding to the given id.
    
    public Vertex getVertex(String id) {
        return vertices.get(id);
    }

	    // Finds the path with the maximum bandwidth between the start and end switching centers in the graph.
	    // If the start and end switching centers are the same, the information does not need to travel anywhere and
	    // a message is printed to the console.
    
        public void findMaximumBandwidthPath(String start, String end) {
            // Check if the start and end vertices exist in the graph
            Vertex startVertex = getVertex(start);
            if (startVertex == null) {
                System.out.println("\nError: There is no station " + start + " in your network."
                		+ "\nInformation cannot be transmitted.");
                return;
            }
            Vertex endVertex = getVertex(end);
            if (endVertex == null) {
                System.out.println("\nError: There is no station " + end + " in your network."
                		+ "\nInformation cannot be transmitted.");
                return;
            }
            
            if (start.equals(end)) {
                // Check to see if start and end are the same switching center
                System.out.println("\nError: The same switching center has been selected for both the starting and ending centers."
                		+ "\nInformation does not need to travel anywhere.");
            }
            else {
            	// List path stores the character values of the vertices 
            	// that make up the path between the start and end vertices in the graph.
	            List<String> path = new ArrayList<>();
	
	            // Use Dijkstra's algorithm to find the shortest path between the start and end vertices
	            HashMap<String, Vertex> vertices = new HashMap<>();
	            for (Map.Entry<String, Vertex> entry : this.vertices.entrySet()) {
	                Vertex v = entry.getValue();
	                v.bandwidth = (v.id.equals(start)) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
	                vertices.put(v.id, v);
	            }
	            PriorityQueue<Vertex> queue = new PriorityQueue<>(new Comparator<Vertex>() {
	                @Override
	                public int compare(Vertex v1, Vertex v2) {
	                    return Integer.compare(v2.bandwidth, v1.bandwidth);
	                }
	            });
	            queue.offer(vertices.get(start));
	            Vertex u = null;
	            Vertex v = null;
	            while (!queue.isEmpty()) {
	                u = queue.poll();
	
	                // Check if the current vertex is the end vertex
	                if (u.id.equals(end)) {
	                    break;
	                }
	
	                // Check if the adjacency list for the current vertex is null
	                if (adjacencyList.get(u.id) != null) {
	                    for (Edge e : adjacencyList.get(u.id)) {
	                        v = vertices.get(e.to);
	                        int bandwidth = Math.min(u.bandwidth, e.bandwidth);
	                        if (bandwidth > v.bandwidth) {
	                            v.bandwidth = bandwidth;
	                            v.previous = u;
	                            queue.offer(v);
	                        }
	                    }
	                }
	            }
	
	            
	            // Check if the end vertex has been visited
	            if (vertices.get(end).bandwidth == Integer.MIN_VALUE) {
	              // End vertex has not been visited, so there is no path between the start and end vertices
	              System.out.println("\nError: There is no path between " + start + " and " + end + " switching centers."
	              		+ "\nInformation cannot be transmitted.");
	            } 
	            else {
		            // Traverse the path from the start vertex to the end vertex and find the maximum bandwidth
		            for (Vertex vertex = vertices.get(end); vertex != null; vertex = vertex.previous) {
		                path.add(vertex.id);
		            }
		            // Output the maximum bandwidth path
		            Collections.reverse(path);
		            if (vertices.get(end).bandwidth <= 0) {
		            	System.out.println("\nError: Negative bandwith detected in communication line."
		            			+ "\nInformation cannot be transmitted.");
		            }
		            else {
			            System.out.println("\nCommincation line path taken is: " + path);
			
			            // Output the maximum bandwidth of the path
			            System.out.println("\nMaximum bandwidth of the path is: " + vertices.get(end).bandwidth + "\n");
		            }
	            }
            }
        }
}