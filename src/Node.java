import java.util.ArrayList;

/**
 * Class holding information about graph vertex
 *
 * @author Łukasz Mielczarek
 * @version 04.11.2016
 */
public class Node {
    private String id;
    private double latitude;
    private double longitude;
    private ArrayList<Node> edges;
    private ArrayList<Double> distances;
    private double distance = Double.MAX_VALUE;
    private Node previousNode = null;

    public Node(String id){
        this.id = id;
        this.edges = new ArrayList<>();
        this.distances = new ArrayList<>();
    }

    public Node(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = null;
        this.edges = null;
        this.distances = null;
    }

    public void setCoordinates(double latitude, double longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId(){
        return id;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void addEdge(Node edge, double distance){
        this.edges.add(edge);
        this.distances.add(distance);
    }

    public ArrayList<Node> getEdges(){
        return edges;
    }

    public ArrayList<Double> getDistances(){
        return distances;
    }

    public void setDistance(Double distance){
        this.distance = distance;
    }

    public void setPreviousNode(Node previousNode){
        this.previousNode = previousNode;
    }

    public double getDistance(){
        return distance;
    }

    public Node getPreviousNode(){
        return previousNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.getLatitude(), latitude) != 0) return false;
        return Double.compare(node.getLongitude(), longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return this.id;
    }

}
