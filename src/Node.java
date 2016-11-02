import java.util.ArrayList;

/**
 * Class holding information about graph vertex
 *
 * @author Łukasz Mielczarek
 * @version 01.11.2016
 */
public class Node {
    private String id;
    private double longitude;
    private double lattitude;
    private ArrayList<String> edges;
    private ArrayList<Double> distances;
    private double distance;
    //private String/Node previous;

    public Node(String id){
        this.id = id;
    }

    public void setCoordinates(double longitude, double lattitude){
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLattitude(){
        return lattitude;
    }

    public void addEdge(String edge, double distance){
        this.edges.add(edge);
        this.distances.add(distance);
    }
}
