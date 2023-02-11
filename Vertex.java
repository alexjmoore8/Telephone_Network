class Vertex implements Comparable<Vertex> {
    // Declare the fields for the Vertex class

    public final String id;
    public int bandwidth;
    public Vertex previous;

    // Constructor for the Vertex class.
   
    public Vertex(String id, int bandwidth) {
        this.id = id;
        this.bandwidth = bandwidth;
    }

    // Compare this vertex to another vertex based on their bandwidth.
 
    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(bandwidth, other.bandwidth);
    }
}